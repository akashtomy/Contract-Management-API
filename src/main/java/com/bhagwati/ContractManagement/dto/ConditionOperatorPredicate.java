package com.bhagwati.ContractManagement.dto;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@FunctionalInterface
interface ConditionOperatorPredicate {
    Predicate operate(Root<?> root, CriteriaBuilder criteriaBuilder, Filter filter, Class<?> fieldClass, String fieldValue, Class<?> secondLevelClass, String secondLevelObjectName);
}
