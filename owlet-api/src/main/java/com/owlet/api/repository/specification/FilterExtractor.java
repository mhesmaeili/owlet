package com.owlet.api.repository.specification;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class FilterExtractor {

    private FilterExtractor() {}

    public static FilterNode extract(Object filterDto) {
        if (filterDto == null) {
            return null;
        }

        List<FilterNode> nodes = new ArrayList<>();
        Class<?> clazz = filterDto.getClass();

        // حلقه برای اسکن کلاس جاری و کلاس‌های پدر (اگر DTO ارث‌بری دارد)
        while (clazz != null && clazz != Object.class) {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true); // اجازه خواندن فیلد پرایوت
                try {
                    Object value = field.get(filterDto);

                    // نادیده گرفتن مقادیر null یا String های خالی
                    if (value == null || (value instanceof String && ((String) value).trim().isEmpty())) {
                        continue;
                    }

                    String path = field.getName();
                    SearchOperation operation = SearchOperation.EQUAL;

                    // بررسی وجود انوتیشن و استخراج تنظیمات آن
                    SearchFilter annotation = field.getAnnotation(SearchFilter.class);
                    if (annotation != null) {
                        path = annotation.path().isEmpty() ? field.getName() : annotation.path();
                        operation = annotation.operation();
                    }

                    // ساخت نود شرطی و افزودن به لیست
                    nodes.add(FilterNode.condition(path, operation, value));

                } catch (IllegalAccessException e) {
                    // لاگ خطا در صورت بروز مشکل رفلکشن
                    e.printStackTrace();
                }
            }
            clazz = clazz.getSuperclass();
        }

        if (nodes.isEmpty()) {
            return null;
        }

        // تمام فیلترهای استخراج شده را با AND ترکیب می‌کنیم
        return FilterNode.and(nodes.toArray(new FilterNode[0]));
    }
}