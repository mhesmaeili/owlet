package com.owlet.api.security.permission.aspect;

import com.owlet.api.security.permission.annotation.HasPermission;
import com.owlet.api.security.permission.service.PermissionService;
import com.owlet.common.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class PermissionAspect {

    private final PermissionService permissionService;

    // رهگیری تمام متدهای کنترلر برای حل مشکل ارث‌بری
    // اگر از این انوتیشن در سرویس‌ها هم استفاده می‌کنید، مسیر را به com.owlet.api..*.*(..) تغییر دهید
    @Around("execution(* com.owlet.api.controller..*.*(..))")
    public Object checkPermission(ProceedingJoinPoint joinPoint) throws Throwable {

        Class<?> targetClass = joinPoint.getTarget().getClass();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        // ۱. اولویت اول: بررسی انوتیشن روی خود متد (چه در کلاس پدر، چه فرزند)
        HasPermission hasPermission = AnnotationUtils.findAnnotation(signature.getMethod(), HasPermission.class);

        // ۲. اولویت دوم: اگر روی متد نبود، بررسی کل کلاس (Target Class)
        if (hasPermission == null) {
            hasPermission = AnnotationUtils.findAnnotation(targetClass, HasPermission.class);
        }

        // ۳. اگر انوتیشن پیدا شد، دسترسی را چک می‌کنیم
        if (hasPermission != null) {
            boolean allowed = permissionService.hasPermission(
                    SecurityContextHolder.getContext().getAuthentication(),
                    hasPermission.module().value(),
                    hasPermission.action()
            );

            if (!allowed) {
                throw new UnauthorizedException("Permission denied");
            }
        }

        // اگر انوتیشن وجود نداشت یا دسترسی مجاز بود، ریکوست ادامه پیدا می‌کند
        return joinPoint.proceed();
    }
}