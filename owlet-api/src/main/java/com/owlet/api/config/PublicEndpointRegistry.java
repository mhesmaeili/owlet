package com.owlet.api.config;

import com.owlet.api.annotation.PublicEndpoint;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashSet;
import java.util.Set;

@Component
public class PublicEndpointRegistry {

    private final ApplicationContext applicationContext;

    // استفاده از Lazy برای جلوگیری از ارور Circular Dependency هنگام استارت پروژه
    public PublicEndpointRegistry(@Lazy ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public String[] getPublicEndpoints() {
        Set<String> publicUrls = new HashSet<>();
        try {
            RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);

            mapping.getHandlerMethods().forEach((info, method) -> {
                if (method.hasMethodAnnotation(PublicEndpoint.class) ||
                        method.getBeanType().isAnnotationPresent(PublicEndpoint.class)) {

                    // بررسی حالت استاندارد Spring 6+ (PathPatternsCondition)
                    if (info.getPathPatternsCondition() != null) {
                        info.getPathPatternsCondition().getPatterns()
                                .forEach(pattern -> publicUrls.add(pattern.getPatternString()));
                    }
                    // بررسی حالت قدیمی یا fallback
                    else if (info.getPatternsCondition() != null) {
                        publicUrls.addAll(info.getPatternsCondition().getPatterns());
                    }
                }
            });
        } catch (Exception e) {
            // لاگ خطا در صورت بروز مشکل
            System.err.println("خطا در لود مسیرهای عمومی: " + e.getMessage());
        }

        return publicUrls.toArray(new String[0]);
    }
}