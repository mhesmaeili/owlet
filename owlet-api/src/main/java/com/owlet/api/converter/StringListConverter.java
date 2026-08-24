package com.owlet.api.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

    private static final String SEPARATOR = ",";

    // هنگام ذخیره در دیتابیس اجرا می‌شود: تبدیل List به String
    @Override
    public String convertToDatabaseColumn(List<String> list) {
        if (list == null || list.isEmpty()) {
            return null; // یا می‌توانید یک رشته خالی "" برگردانید
        }
        return String.join(SEPARATOR, list);
    }

    // هنگام خواندن از دیتابیس اجرا می‌شود: تبدیل String به List
    @Override
    public List<String> convertToEntityAttribute(String joinedString) {
        if (joinedString == null || joinedString.trim().isEmpty()) {
            return Collections.emptyList();
        }
        // تبدیل مجدد رشته با جداکننده به لیست
        return Arrays.asList(joinedString.split(SEPARATOR));
    }
}
