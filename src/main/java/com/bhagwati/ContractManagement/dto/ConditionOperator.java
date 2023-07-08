package com.bhagwati.ContractManagement.dto;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public enum ConditionOperator {
    EQ(ConditionEvaluator.getEqualsEvaluator()),
    GT(ConditionEvaluator.getGreaterThanEvaluator()),
    GE(ConditionEvaluator.getGreaterThanOrEqualsEvaluator()),
    IN(ConditionEvaluator.getInEvaluator()),
    NI(ConditionEvaluator.getNotInEvaluator()),
    NE(ConditionEvaluator.getNotEqualsEvaluator()),
    LT(ConditionEvaluator.getLessThanEvaluator()),
    LE(ConditionEvaluator.getLessThanOrEqualsEvaluator()),
    LK(ConditionEvaluator.getLikeEvaluator()),
    BT(ConditionEvaluator.getBetweenEvaluator());

    private final ConditionOperatorPredicate evaluator;

    ConditionOperator(ConditionOperatorPredicate evaluator) {
        this.evaluator = evaluator;
    }

    Predicate operate(Root<?> root, CriteriaBuilder criteriaBuilder, Filter filter, ClassAndFieldValue classAndFieldValue) {
        return evaluator.operate(root, criteriaBuilder, filter, classAndFieldValue.getFieldClass(), classAndFieldValue.getFieldValue(), classAndFieldValue.getSecondLevelClass() ,classAndFieldValue.getSecondLevelValue());
    }

}
