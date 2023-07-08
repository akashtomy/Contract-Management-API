package com.bhagwati.ContractManagement.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface Lookup constraint.
 *
 * @author Akash Thomas.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LookupConstraint {
    /**
     * Lookup constant string.
     *
     * @return the string
     */
    String lookupConstant() default "";

    /**
     * Check for blank boolean.
     *
     * @return the boolean
     */
    boolean checkForBlank() default false;

    /**
     * Dependent on property name string.
     *
     * @return the string
     */
    String dependentOnPropertyName() default "";

    /**
     * Dependent on property value string.
     *
     * @return the string
     */
    String dependentOnPropertyValue() default "";

    /**
     * Dependent on property equality boolean.
     *
     * @return the boolean
     */
    boolean dependentOnPropertyEquality() default true;
}
