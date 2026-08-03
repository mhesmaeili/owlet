package com.owlet.api.security.permission.enums;

public enum Module {

    STUDENT("student"),
    PARENT("parent"),
    TEACHER("teacher"),
    ACCOUNT("account"),
    SCHOOL("school"),
    PRODUCT("product"),
    SESSION("session"),
    ASSESSMENT("assessment"),
    BASE("base"),
    AIREPORT("aireport");

    private final String value;

    Module(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}