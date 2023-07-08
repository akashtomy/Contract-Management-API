package com.bhagwati.ContractManagement.dto;

import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

/**
 * The type Condition evaluator.
 *
 * @author Athrav Jaiswal
 * @author Dipak Desai
 */
public class ConditionEvaluator {

    /**
     * Gets equals evaluator.
     *
     * @return the equals evaluator
     */
    public static ConditionOperatorPredicate getEqualsEvaluator() {
        return (root, criteriaBuilder, filter, fieldClass, innerObjectName, secondLevelClass ,secondLevelObjectName) -> {
            Predicate predicate;
            String value = filter.getValue();
            if (Temporal.class.isAssignableFrom(fieldClass)) {
                predicate = getPredicate(criteriaBuilder, filter, LocalDateTime.parse(value), (f, v) -> criteriaBuilder.equal(root.get(f), v));
            } else  if(secondLevelClass!=null &&
                    innerObjectName!=null && secondLevelObjectName!=null &&
                    !ClassUtils.isPrimitiveOrWrapper(fieldClass) && String.class!=fieldClass &&
                    !ClassUtils.isPrimitiveOrWrapper(secondLevelClass) && String.class!=secondLevelClass
                    ) {
                return getPredicate(criteriaBuilder, filter, filter.getValue(),(f, v) -> criteriaBuilder.equal(criteriaBuilder.upper(root.get(innerObjectName).get(secondLevelObjectName).get(filter.getField())), v.toUpperCase()));
            } else if(!ClassUtils.isPrimitiveOrWrapper(fieldClass) && String.class!=fieldClass){
                return getPredicate(criteriaBuilder, filter, filter.getValue(),(f, v) -> criteriaBuilder.equal(criteriaBuilder.upper(root.get(innerObjectName).get(filter.getField())), v.toUpperCase()));
            } else if(ClassUtils.isPrimitiveOrWrapper(fieldClass)){
                return getPredicate(criteriaBuilder, filter, filter.getValue(),(f, v) -> criteriaBuilder.equal(root.get(f),v));
            } else {
                predicate = getPredicate(criteriaBuilder, filter, value, (f, v) -> criteriaBuilder.equal(criteriaBuilder.upper(root.get(f)), v.toUpperCase()));
            }
            return predicate;
        };
    }

    /**
     * Gets less than evaluator.
     *
     * @return the less than evaluator
     */
    public static ConditionOperatorPredicate getLessThanEvaluator() {
        return (root, criteriaBuilder, filter, fieldClass, innerObjectName, secondLevelClass, secondLevelObjectName) -> {
            Predicate predicate;
            String value = filter.getValue();
            if (Temporal.class.isAssignableFrom(fieldClass)) {
                predicate = getPredicate(criteriaBuilder, filter, LocalDateTime.parse(value), (f, v) -> criteriaBuilder.lessThan(root.get(f), v));
            }
            else {
                predicate = getPredicate(criteriaBuilder, filter, value, (f, v) -> criteriaBuilder.lessThan(root.get(f), v));
            }
            return predicate;
        };
    }

    /**
     * Gets greater than evaluator.
     *
     * @return the greater than evaluator
     */
    public static ConditionOperatorPredicate getGreaterThanEvaluator() {
        return (root, criteriaBuilder, filter, fieldClass, innerObjectName, secondLevelClass, secondLevelObjectName) -> {
            Predicate predicate;
            String value = filter.getValue();
            if (Temporal.class.isAssignableFrom(fieldClass)) {
                predicate = getPredicate(criteriaBuilder, filter, LocalDateTime.parse(value), (f, v) -> criteriaBuilder.greaterThan(root.get(f), v));
            }
            else {
                predicate = getPredicate(criteriaBuilder, filter, value, (f, v) -> criteriaBuilder.greaterThan(root.get(f), v));
            }
            return predicate;
        };
    }

    /**
     * Gets greater than or equals evaluator.
     *
     * @return the greater than or equals evaluator
     */
    public static ConditionOperatorPredicate getGreaterThanOrEqualsEvaluator() {
        return (root, criteriaBuilder, filter, fieldClass, innerObjectName, secondLevelClass, secondLevelObjectName) -> {
            Predicate predicate;
            String value = filter.getValue();
            if (Temporal.class.isAssignableFrom(fieldClass)) {
                predicate = getPredicate(criteriaBuilder, filter, LocalDateTime.parse(value), (f, v) -> criteriaBuilder.greaterThanOrEqualTo(root.get(f), v));
            }
            else {
                predicate = getPredicate(criteriaBuilder, filter, value, (f, v) -> criteriaBuilder.greaterThanOrEqualTo(root.get(f), v));
            }
            return predicate;
        };
    }

    /**
     * Gets less than or equals evaluator.
     *
     * @return the less than or equals evaluator
     */
    public static ConditionOperatorPredicate getLessThanOrEqualsEvaluator() {
        return (root, criteriaBuilder, filter, fieldClass, innerObjectName, secondLevelClass, secondLevelObjectName) -> {
            Predicate predicate;
            String value = filter.getValue();
            if (Temporal.class.isAssignableFrom(fieldClass)) {
                predicate = getPredicate(criteriaBuilder, filter, LocalDateTime.parse(value), (f, v) -> criteriaBuilder.lessThanOrEqualTo(root.get(f), v));
            }
            else {
                predicate = getPredicate(criteriaBuilder, filter, value, (f, v) -> criteriaBuilder.lessThanOrEqualTo(root.get(f), v));
            }
            return predicate;
        };
    }

    /**
     * Gets like evaluator.
     *
     * @return the like evaluator
     */
    public static ConditionOperatorPredicate getLikeEvaluator() {
        return (root, criteriaBuilder, filter, fieldClass, innerObjectName, secondLevelClass, secondLevelObjectName) -> {
            if(secondLevelClass!=null &&
                    innerObjectName!=null && secondLevelObjectName!=null &&
                    !ClassUtils.isPrimitiveOrWrapper(fieldClass) && String.class!=fieldClass &&
                    !ClassUtils.isPrimitiveOrWrapper(secondLevelClass) && String.class!=secondLevelClass
            ) {
                return getPredicate(criteriaBuilder, filter, filter.getValue(),(f, v) -> criteriaBuilder.like(criteriaBuilder.upper(root.get(innerObjectName).get(secondLevelObjectName).get(filter.getField())), "%" + v.toUpperCase() + "%"));
            } else
            if(!ClassUtils.isPrimitiveOrWrapper(fieldClass) && String.class!=fieldClass){
                return getPredicate(criteriaBuilder, filter, filter.getValue(),(f, v) -> criteriaBuilder.like(criteriaBuilder.upper(root.get(innerObjectName).get(filter.getField())), "%" + v.toUpperCase() + "%"));
            }
             return getPredicate(criteriaBuilder, filter, filter.getValue(),(f, v) -> criteriaBuilder.like(criteriaBuilder.upper(root.get(f)), "%" + v.toUpperCase() + "%"));
        };
    }

    /**
     * Gets between evaluator.
     *
     * @return the between evaluator
     */
    public static ConditionOperatorPredicate getBetweenEvaluator() {
        return (root, criteriaBuilder, filter, fieldClass, innerObjectName, secondLevelClass, secondLevelObjectName) -> {
            String[] dates = filter.getValue().split(",");
            return criteriaBuilder.between(root.get(filter.getField()), LocalDateTime.parse(dates[0]), LocalDateTime.parse(dates[1]));
        };
    }

    /**
     * Gets in evaluator.
     *
     * @return the in evaluator
     */
    public static ConditionOperatorPredicate getInEvaluator() {
        return (root, criteriaBuilder, filter, fieldClass, innerObjectName, secondLevelClass, secondLevelObjectName) -> {
            String value = filter.getValue();
            String[] values = value.split(",");
            String[] strings = Arrays.stream(values).map(String::toUpperCase).toArray(String[]::new);
            if(secondLevelClass!=null &&
                    innerObjectName!=null && secondLevelObjectName!=null &&
                    !ClassUtils.isPrimitiveOrWrapper(fieldClass) && String.class!=fieldClass &&
                    !ClassUtils.isPrimitiveOrWrapper(secondLevelClass) && String.class!=secondLevelClass
            ) {
                return getPredicate(criteriaBuilder, filter, Arrays.asList(strings),(f, v) -> criteriaBuilder.upper(root.get(innerObjectName).get(secondLevelObjectName).get(filter.getField())).in(v));
            } else
            if(!ClassUtils.isPrimitiveOrWrapper(fieldClass) && String.class!=fieldClass && innerObjectName!=null){
                return getPredicate(criteriaBuilder, filter, Arrays.asList(strings),(f, v) -> criteriaBuilder.upper(root.get(innerObjectName).get(filter.getField())).in(v));
            }
            return getPredicate(criteriaBuilder, filter, Arrays.asList(strings), (f, v) -> criteriaBuilder.upper(root.get(f)).in(v));
        };
    }

    /**
     * Gets not in evaluator.
     *
     * @return the not in evaluator
     */
    public static ConditionOperatorPredicate getNotInEvaluator() {
        return (root, criteriaBuilder, filter, fieldClass, innerObjectName, secondLevelClass, secondLevelObjectName) -> {
            String value = filter.getValue();
            String[] values = value.split(",");
            String[] strings = Arrays.stream(values).map(String::toUpperCase).toArray(String[]::new);
            if(secondLevelClass!=null &&
                    innerObjectName!=null && secondLevelObjectName!=null &&
                    !ClassUtils.isPrimitiveOrWrapper(fieldClass) && String.class!=fieldClass &&
                    !ClassUtils.isPrimitiveOrWrapper(secondLevelClass) && String.class!=secondLevelClass
            ) {
                return getPredicate(criteriaBuilder, filter, Arrays.asList(strings),(f, v) -> criteriaBuilder.upper(root.get(innerObjectName).get(secondLevelObjectName).get(filter.getField())).in(v).not());
            } else
            if(!ClassUtils.isPrimitiveOrWrapper(fieldClass) && String.class!=fieldClass){
                return getPredicate(criteriaBuilder, filter, Arrays.asList(strings),(f, v) -> criteriaBuilder.upper(root.get(innerObjectName).get(filter.getField())).in(v).not());
            }
            return getPredicate(criteriaBuilder, filter, Arrays.asList(strings), (f, v) -> criteriaBuilder.upper(root.get(f)).in(v).not());
        };
    }

    /**
     * Gets not equals evaluator.
     *
     * @return the not equals evaluator
     */
    public static ConditionOperatorPredicate getNotEqualsEvaluator() {
        return (root, criteriaBuilder, filter, fieldClass, innerObjectName, secondLevelClass, secondLevelObjectName) -> {
            Predicate predicate;
            String value = filter.getValue();
            if (Temporal.class.isAssignableFrom(fieldClass)) {
                predicate = getPredicate(criteriaBuilder, filter, LocalDateTime.parse(value), (f, v) -> criteriaBuilder.notEqual(root.get(f), v));
            } else if(secondLevelClass!=null &&
                    innerObjectName!=null && secondLevelObjectName!=null &&
                    !ClassUtils.isPrimitiveOrWrapper(fieldClass) && String.class!=fieldClass &&
                    !ClassUtils.isPrimitiveOrWrapper(secondLevelClass) && String.class!=secondLevelClass
            ) {
                return getPredicate(criteriaBuilder, filter, filter.getValue(),(f, v) -> criteriaBuilder.notEqual(criteriaBuilder.upper(root.get(innerObjectName).get(secondLevelObjectName).get(filter.getField())), v.toUpperCase()));
            } else if(!ClassUtils.isPrimitiveOrWrapper(fieldClass) && String.class!=fieldClass){
                return getPredicate(criteriaBuilder, filter, filter.getValue(),(f, v) -> criteriaBuilder.notEqual(criteriaBuilder.upper(root.get(innerObjectName).get(filter.getField())), v.toUpperCase() ));
            } else if(ClassUtils.isPrimitiveOrWrapper(fieldClass)){
                return getPredicate(criteriaBuilder, filter, filter.getValue(),(f, v) -> criteriaBuilder.notEqual(root.get(f),v));
            } else {
                predicate = getPredicate(criteriaBuilder, filter, value, (f, v) -> criteriaBuilder.notEqual(criteriaBuilder.upper(root.get(f)), v.toUpperCase()));
            }
            return predicate;
        };
    }

    private static <T> Predicate getPredicate(CriteriaBuilder criteriaBuilder, Filter filter, T value, BiFunction<String, T, Predicate> function) {
        Predicate predicate;
        if (CollectionUtils.isEmpty(filter.getEntityFieldValues())) {
            predicate = function.apply(filter.getField(), value);
        }
        else {
            List<Predicate> predicates = new ArrayList<>();
            for (String entityFieldValue : filter.getEntityFieldValues()) {
                predicates.add(function.apply(entityFieldValue, value));
            }
            predicate = criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        }
        return predicate;
    }
}
