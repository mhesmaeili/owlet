package com.owlet.api.security.permission.annotation;

import com.owlet.api.security.permission.enums.Module;

import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CrudPermission {

    Module value();

}
