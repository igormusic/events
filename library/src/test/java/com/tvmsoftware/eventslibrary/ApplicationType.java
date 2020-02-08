package com.tvmsoftware.eventslibrary;

import org.springframework.context.ApplicationContext;

public enum ApplicationType {
    SMALL_BUSINESS("small-business"),
    COMMERCIAL("commercial");

    private String name;

    ApplicationType(String name){
        this.name = name;
    }
}
