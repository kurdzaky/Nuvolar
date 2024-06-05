package com.nuvolar;

public @interface Scenario {

    String description() default "";

    String[] steps() default {};
}