package com.att.attcare.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    MANAGER_READ("management:read"),
    MANAGER_UPDATE("management:update"),
    MANAGER_CREATE("management:create"),
    MANAGER_DELETE("management:delete"),
    DOCTOR_READ("doctor:read"),
    DOCTOR_UPDATE("doctor:update"),
    DOCTOR_DELETE("doctor:delete"),
    DOCTOR_CREATE("doctor:create"),
    RECEPTIONIST_READ("receptionist:read"),
    RECEPTIONIST_UPDATE("receptionist:update"),
    RECEPTIONIST_DELETE("receptionist:delete"),
    RECEPTIONIST_CREATE("receptionist:create"),

    ;

    @Getter
    private final String permission;
}
