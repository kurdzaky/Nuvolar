package com.nuvolar.tests;

public @interface Scenario {

    String description() default "";

    String[] steps() default {};
}