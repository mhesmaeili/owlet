package com.owlet.api.repository.specification;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class FilterExtractor {

    private FilterExtractor() {}

    @SuppressWarnings("unchecked")
    public static FilterNode extract(Object filterDto) {
        if (filterDto == null) {
            return null;
        }

        // حالت اول: اگر ورودی Map باشد (ارسال شده از طریق CrudController)
        if (filterDto instanceof Map<?, ?> map) {
            return extractFromMap((Map<String, Object>) map);
        }

        // حالت دوم: اگر ورودی یک DTO عادی باشد (استفاده از Reflection)
        return extractFromDto(filterDto);
    }

    private static FilterNode extractFromMap(Map<String, Object> filterMap) {
        if (filterMap == null || filterMap.isEmpty()) {
            return null;
        }

        List<FilterNode> nodes = new ArrayList<>();

        for (Map.Entry<String, Object> entry : filterMap.entrySet()) {
            String path = entry.getKey();
            Object value = entry.getValue();

            // رد کردن مقادیر null یا رشته‌های خالی
            if (value == null || (value instanceof String str && str.trim().isEmpty())) {
                continue;
            }

            // تبدیل مقادیر متنی بولی به Boolean واقعی در صورت امکان
            if (value instanceof String str) {
                if ("true".equalsIgnoreCase(str.trim())) {
                    value = Boolean.TRUE;
                } else if ("false".equalsIgnoreCase(str.trim())) {
                    value = Boolean.FALSE;
                }
            }

            // ایجاد شرط EQUAL به ازای هر پارامتر مپ (پشتیبانی از فیلدهای تودرتو مثل school.id)
            nodes.add(FilterNode.condition(path, SearchOperation.EQUAL, value));
        }

        if (nodes.isEmpty()) {
            return null;
        }

        return FilterNode.and(nodes.toArray(new FilterNode[0]));
    }

    private static FilterNode extractFromDto(Object filterDto) {
        List<FilterNode> nodes = new ArrayList<>();
        Class<?> clazz = filterDto.getClass();

        while (clazz != null && clazz != Object.class) {
            for (Field field : clazz.getDeclaredFields()) {
                // نادیده گرفتن فیلدهای استاتیک و نهایی
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }

                field.setAccessible(true);
                try {
                    Object value = field.get(filterDto);

                    if (value == null || (value instanceof String str && str.trim().isEmpty())) {
                        continue;
                    }

                    String path = field.getName();
                    SearchOperation operation = SearchOperation.EQUAL;

                    SearchFilter annotation = field.getAnnotation(SearchFilter.class);
                    if (annotation != null) {
                        path = annotation.path().isEmpty() ? field.getName() : annotation.path();
                        operation = annotation.operation();
                    }

                    nodes.add(FilterNode.condition(path, operation, value));

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            clazz = clazz.getSuperclass();
        }

        if (nodes.isEmpty()) {
            return null;
        }

        return FilterNode.and(nodes.toArray(new FilterNode[0]));
    }
}