package com.owlet.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IncludeDeleted {
    // هر متدی این انوتیشن را داشته باشد، فیلترِ حذف روی آن اعمال نمی‌شود
}