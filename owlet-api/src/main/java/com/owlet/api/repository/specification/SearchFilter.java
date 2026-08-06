package com.owlet.api.repository.specification;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SearchFilter {
    // مسیر فیلد در دیتابیس (اگر خالی باشد، از نام متغیر استفاده می‌کند)
    String path() default "";

    // نوع عملیات (پیش‌فرض EQUAL است)
    SearchOperation operation() default SearchOperation.EQUAL;
}
