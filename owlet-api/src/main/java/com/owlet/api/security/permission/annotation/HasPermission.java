package com.owlet.api.security.permission.annotation;

import com.owlet.api.security.permission.enums.Module;

import java.lang.annotation.*;

@Target({
        ElementType.METHOD,
        ElementType.TYPE
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HasPermission {

    Module module();

    String action();
}