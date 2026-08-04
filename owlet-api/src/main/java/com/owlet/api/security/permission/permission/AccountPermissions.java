package com.owlet.api.security.permission.permission;

public final class AccountPermissions {

    private AccountPermissions() {
    }

    public static final String CREATE = "create";
    public static final String READ = "read";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";
    public static final String LIST = "list";
    public static final String SEARCH = "search";

    public static final String LOCK = "lock";
    public static final String UNLOCK = "unlock";
    public static final String RESET_PASSWORD = "reset-password";
    public static final String CHANGE_PASSWORD = "change-password";
    public static final String ASSIGN_ROLE = "assign-role";
}