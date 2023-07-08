package com.bhagwati.ContractManagement.dto;

import com.bhagwati.ContractManagement.annotation.LookupConstraint;
import com.bhagwati.ContractManagement.exception.CustomExceptions;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.http.HttpStatus;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.bhagwati.ContractManagement.dto.ConditionOperator.*;


@Slf4j
public class SearchFilter {

    private static final String REGEX_TO_FIND_WHITESPACE_AND_OPEN_CLOSE_SQUARE_BRACKETS = "\\[|\\]|\\s";
    private static final String BLANK = "";
    private static final Map<Class<?>, List<ConditionOperator>> validValues;
    private static final Integer DEFAULT_PAGE_SIZE = 8;
    private static final String LOOKUP = "LOOKUP";
    private static final String DATE = "DATE";
    private static final String STR = "STR";
    private static final String DTE = "DTE";
    private static final String NBR = "NBR";

    static {
        Map<Class<?>, List<ConditionOperator>> values = new HashMap<>();
        values.put(String.class, Arrays.asList(EQ, IN, LK, NI, NE));
        values.put(Temporal.class, Arrays.asList(EQ, GT, LT, BT, GE, LE, NE));
        values.put(Number.class, Arrays.asList(EQ, LT, GT, LE, GE, NE));
        values.put(Boolean.class, Arrays.asList(EQ));
        values.put(Character.class, Arrays.asList(EQ, IN, LK, NI, NE));
        values.put(Byte.class, Arrays.asList(EQ, IN, LK, NI, NE));
        values.put(Short.class, Arrays.asList(EQ, LT, GT, LE, GE, NE));
        values.put(Integer.class, Arrays.asList(EQ, LT, GT, LE, GE, NE));
        values.put(Long.class, Arrays.asList(EQ, LT, GT, LE, GE, NE));
        values.put(Float.class, Arrays.asList(EQ, LT, GT, LE, GE, NE));
        values.put(Double.class, Arrays.asList(EQ, LT, GT, LE, GE, NE));
        values.put(Void.class, Arrays.asList(EQ));
        values.put(LocalDateTime.class, Arrays.asList(EQ, GT, LT, BT, GE, LE, NE));
        values.put(JsonNode.class, Arrays.asList(EQ)); // FIXME: handling jsonNode operations
        validValues = Collections.unmodifiableMap(values);
    }

    public static <T, S> PageableResponse<S> getFilteredResults(SearchFilterPageRequest filters, Class<T> entity, JpaSpecificationExecutor<T> repo, Function<List<T>, List<S>> entityMapper) {
        Page<T> result = getFilteredPage(filters, entity, repo);
        return getPageableResponse(entityMapper, result);
    }

    public static <T> Page<T> getFilteredPage(SearchFilterPageRequest filters, Class<T> entity, JpaSpecificationExecutor<T> repo) {
        validateFilters(filters.getFilters(), entity);
        return getResult(filters, entity, repo);
    }

    public static <T, S> ArrayList<FilterData> getFilterData(Class<T> clazz, Class<S> classDto) {
        Field[] declaredFields = clazz.getDeclaredFields();
        ArrayList<FilterData> filterData = new ArrayList<>();
        for (Field field : declaredFields) {
            FilterData filter = FilterData.builder()
                    .field(field.getName())
                    .fieldType(getFieldType(field, classDto))
                    .dataType(getDataType(field.getType()))
                    .fieldDesc("")
                    .operators(getOperators(field))
                    .build();
            populateLookup(classDto, field, filter);
            filterData.add(filter);
        }
        return filterData;
    }

    public static <T, Q> Page<T> getFilteredViewPage(SearchFilterPageRequest filters,
                                                     EntityManager entityManager, Class<T> entity,
                                                     Class<Q> view, Function<Root<Q>, List<Selection<?>>> selections,
                                                     Function<Root<Q>, Expression<?>> idSelection) {
        PageRequest pageRequest = preparePageableRequest(filters);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(entity);
        Root<Q> viewRoot = query.from(view);
        List<Predicate> predicates = SearchFilter.processFilters(filters, viewRoot, criteriaBuilder, view, entity);
        query.multiselect(selections.apply(viewRoot)).where(predicates.toArray(new Predicate[0])).distinct(true).
                orderBy(getOrders(filters, criteriaBuilder, viewRoot));
        TypedQuery<T> filterQuery = entityManager.createQuery(query);
        filterQuery.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
        filterQuery.setMaxResults(pageRequest.getPageSize());
        List<T> resultList = filterQuery.getResultList();
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        countQuery.from(view);
        countQuery.select(criteriaBuilder.countDistinct(idSelection.apply(viewRoot))).where(predicates.toArray(new Predicate[0]));
        Long count = entityManager.createQuery(countQuery).getSingleResult();
        if (!resultList.isEmpty()) {
            return new PageImpl<T>(resultList, PageRequest.of(pageRequest.getPageNumber(), resultList.size(), pageRequest.getSort()), count);
        }
        return Page.empty();
    }

    private static <X> List<Order> getOrders(PageableRequestDto pageableRequest, CriteriaBuilder criteriaBuilder, Root<X> root) {
        return null == pageableRequest.getSortBy() ? defaultSortBy(criteriaBuilder, root)
                : prepareOrders(pageableRequest.getSortBy(), criteriaBuilder, root);
    }

    private static <X> List<Order> defaultSortBy(CriteriaBuilder criteriaBuilder, Root<X> root) {
        List<Order> orders = new ArrayList<>();
        orders.add(criteriaBuilder.desc(root.get("modifiedDate")));
        orders.add(criteriaBuilder.desc(root.get("version")));
        return orders;
    }

    private static <X> List<Order> prepareOrders(String sortby, CriteriaBuilder criteriaBuilder, Root<X> root) {
        List<Order> orders = new ArrayList<>();
        String[] sortList = sortby.split(",");
        for (String sort : sortList) {
            Order order;
            if ("desc".equalsIgnoreCase(sort.split(":")[0])) {
                if (sort.contains(".")) {
                    String outerObject = sort.split("\\.")[0].split(":")[1];
                    String innerField = sort.split("\\.")[1];
                    order = criteriaBuilder.desc(root.get(outerObject).get(innerField));
                } else {
                    order = criteriaBuilder.desc(root.get(sort.split(":")[1]));
                }
            } else {
                if (sort.contains(".")) {
                    String outerObject = sort.split("\\.")[0].split(":")[1];
                    String innerField = sort.split("\\.")[1];
                    order = criteriaBuilder.asc(root.get(outerObject).get(innerField));
                } else {
                    order = criteriaBuilder.asc(root.get(sort.split(":")[1]));
                }
            }
            orders.add(order);
        }
        return orders;
    }

    private static <T, S> PageableResponse<S> getPageableResponse(Function<List<T>, List<S>> entityMapper, Page<T> result) {
        PageableResponse<S> response = new PageableResponse<>();
        response.setCurrentPage(result.getNumber());
        response.setTotalRecords(result.getTotalElements());
        response.setTotalPages(result.getTotalPages());
        response.setData(entityMapper.apply(result.getContent()));
        return response;
    }

    private static <T> Page<T> getResult(SearchFilterPageRequest filters, Class<T> entity, JpaSpecificationExecutor<T> repo) {
        return repo.findAll(getEntitySpecification(filters, entity), preparePageableRequest(filters));
    }

    private static <T> Specification<T> getEntitySpecification(SearchFilterPageRequest filters, Class<T> entity) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = processFilters(filters, root, criteriaBuilder, entity, null);
            if (!predicates.isEmpty()) {
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
            return null;
        };
    }

    private static <S> void populateLookup(Class<S> classDto, Field f, FilterData filter) {
        if (LOOKUP.equals(filter.getFieldType())) {
            try {
                Field declaredField = classDto.getDeclaredField(f.getName());
                filter.setLookupCode(declaredField.getAnnotation(LookupConstraint.class).lookupConstant());
            } catch (NoSuchFieldException e) {
                // do nothing
            }
        }
    }

    private static String getOperators(Field f) {
        Optional<Class<?>> first = validValues.keySet().stream().filter(key -> key.isAssignableFrom(f.getType())).findFirst();
        if (first.isPresent()) {
            List<ConditionOperator> conditionOperators = validValues.get(first.get());
            return conditionOperators.toString().replaceAll(REGEX_TO_FIND_WHITESPACE_AND_OPEN_CLOSE_SQUARE_BRACKETS, BLANK);
        }
        return "";
    }

    private static <S> String getFieldType(Field f, Class<S> classDto) {
        try {
            Field declaredField = classDto.getDeclaredField(f.getName());
            if (declaredField.isAnnotationPresent(LookupConstraint.class)) {
                return LOOKUP;
            }
            if (Temporal.class.isAssignableFrom(f.getType())) {
                return DATE;
            }
        } catch (NoSuchFieldException e) {
            // do nothing
        }
        return "";
    }

    private static String getDataType(Class<?> f) {
        if (String.class.isAssignableFrom(f)) {
            return STR;
        } else if (Temporal.class.isAssignableFrom(f)) {
            return DTE;
        } else if (Number.class.isAssignableFrom(f)) {
            return NBR;
        }
        return "";
    }

    private static <T> List<Predicate> processFilters(SearchFilterPageRequest filters, Root<T> root, CriteriaBuilder criteriaBuilder, Class<T> entity, Class<?> parent) {
        return filters.getFilters().
                stream().
                map(filter -> getPredicate(root, criteriaBuilder, filter, entity, parent)).
                filter(Objects::nonNull).
                collect(Collectors.toList());
    }

    private static <T> Predicate getPredicate(Root<T> root, CriteriaBuilder criteriaBuilder, Filter filter, Class<T> entity, Class<?> parent) {
        ClassAndFieldValue classAndFieldValue = extractFieldType(entity, parent, filter.getField());
        return filter.getOperator().operate(root, criteriaBuilder, filter, classAndFieldValue);
    }

    private static <T> void validateFilters(List<Filter> filters, Class<T> entity) {
        List<String> validationMessages = new ArrayList<>();
        for (Filter filter : filters) {
            String field = filter.getField();
            ClassAndFieldValue classAndFieldValue = extractFieldType(entity, null, filter.getField());
            validate(field, classAndFieldValue.getFieldClass(), filter.getOperator(), validationMessages);
        }
        if (!validationMessages.isEmpty()) {
            throw new CustomExceptions(HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value(), validationMessages.toString());
        }
    }

    private static void validate(String field, Class<?> fieldType, ConditionOperator operator, List<String> validationMessages) {
        Optional<Class<?>> fieldClass = validValues.keySet().
                stream().
                filter(clazz -> clazz.isAssignableFrom(fieldType)).
                findFirst();
        fieldClass.ifPresent(cl -> {
            if (!validValues.get(cl).contains(operator)) {
                validationMessages.add(field + " cannot be compared with " + operator);
            }
        });
    }

    private static PageRequest preparePageableRequest(PageableRequestDto pageableRequest) {
        int page;
        int pageSize = 0 == pageableRequest.getPageSize() ? DEFAULT_PAGE_SIZE : pageableRequest.getPageSize();
        page = pageableRequest.getPageNo();
        Sort sort = null == pageableRequest.getSortBy() ? Sort.by(defaultSortBy())
                : Sort.by(prepareOrders(pageableRequest.getSortBy()));
        return PageRequest.of(page, pageSize, sort);
    }

    private static List<Sort.Order> prepareOrders(String sortby) {
        List<Sort.Order> orders = new ArrayList<>();
        String[] sortList = sortby.split(",");
        for (String sort : sortList) {
            Sort.Order order = new Sort.Order("asc".equalsIgnoreCase(sort.split(":")[0]) ? Sort.Direction.ASC
                    : Sort.Direction.DESC, sort.split(":")[1]);
            orders.add(order);
        }
        return orders;
    }

    private static List<Sort.Order> defaultSortBy() {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "modifiedDate"));
        orders.add(new Sort.Order(Sort.Direction.DESC, "version"));
        return orders;
    }


    public static <T> ClassAndFieldValue extractFieldType(Class<T> entity, Class<?> parent, String field) {
        ClassAndFieldValue classAndFieldValue = new ClassAndFieldValue();
        try {
            if (parent != null) {
                List<Field> parentField = Arrays.stream(entity.getDeclaredFields()).filter(f -> f.getType() == parent).collect(Collectors.toList());
                if (!parentField.isEmpty() && parentField.get(0) != null) {
                    parent.getDeclaredField(field);
                    classAndFieldValue.setFieldClass(parentField.get(0).getType());
                    classAndFieldValue.setFieldValue(parentField.get(0).getName());
                    return classAndFieldValue;
                }
            }
            Class<?> fieldType = entity.getDeclaredField(field).getType();
            classAndFieldValue.setFieldClass(fieldType);
            return classAndFieldValue;
        } catch (NoSuchFieldException e) {
            return extractClassAndField(entity, field, classAndFieldValue);
        }
    }

    private static <T> ClassAndFieldValue extractClassAndField(Class<T> entity, String field, ClassAndFieldValue classAndFieldValue) {
        String fieldForInnerObjectName = null;
        String secondLevelObjectName = null;
        Class<?> fieldType = null;
        Class<?> secondLevelFieldType = null;
        Field[] declaredFields = entity.getDeclaredFields();
        List<Field> fields = Arrays.stream(declaredFields).filter(f -> !validValues.containsKey(f.getType())).collect(Collectors.toList());
        for (Field currentField : fields) {
            try {
                currentField.getType().getDeclaredField(field);
                fieldType = currentField.getType();
                fieldForInnerObjectName = currentField.getName();
                break;
            } catch (NoSuchFieldException exp) {
                continue;
            }
        }
        if (fieldType == null) {
            for (Field thisField : fields) {
                Field[] innerTotalFields = thisField.getType().getDeclaredFields();
                List<Field> innerFields = Arrays.stream(innerTotalFields).filter(f -> !validValues.containsKey(f.getType())).collect(Collectors.toList());
                for (Field innerField : innerFields) {
                    try {
                        innerField.getType().getDeclaredField(field);
                        fieldType = thisField.getType();
                        fieldForInnerObjectName = thisField.getName();
                        secondLevelFieldType = innerField.getType();
                        secondLevelObjectName = innerField.getName();
                        break;
                    } catch (NoSuchFieldException exp) {
                        continue;
                    }
                }
            }
            if (secondLevelFieldType == null) {
                log.error("Filter was attempted to applied of non-existent field with name: " + field);
                throw new CustomExceptions("Unable to filter for field." + field);
            }
        }
        classAndFieldValue.setFieldClass(fieldType);
        classAndFieldValue.setFieldValue(fieldForInnerObjectName);
        classAndFieldValue.setSecondLevelValue(secondLevelObjectName);
        classAndFieldValue.setSecondLevelClass(secondLevelFieldType);
        return classAndFieldValue;
    }

}
