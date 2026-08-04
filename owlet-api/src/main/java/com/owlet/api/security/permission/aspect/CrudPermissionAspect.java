package com.owlet.api.security.permission.aspect;

import com.owlet.api.security.permission.annotation.CrudPermission;
import com.owlet.api.security.permission.permission.CrudActions;
import com.owlet.api.security.permission.enums.Module;
import com.owlet.api.security.permission.service.PermissionService;

import com.owlet.common.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.*;

@Aspect
@Component
@RequiredArgsConstructor
public class CrudPermissionAspect {

    private final PermissionService permissionService;

    // تغییر حیاتی: به جای @within، تمام متدهای داخل پکیج controller (چه پدر، چه فرزند) را رهگیری می‌کنیم
    @Around("execution(* com.owlet.api.controller..*.*(..))")
    public Object check(ProceedingJoinPoint joinPoint) throws Throwable {

        // کلاس نهایی که در حال دریافت ریکوست است (مثلاً AccountController) را می‌گیریم
        Class<?> targetClass = joinPoint.getTarget().getClass();

        // انوتیشن را از روی کلاسِ نهایی جستجو می‌کنیم
        CrudPermission crudPermission = AnnotationUtils.findAnnotation(targetClass, CrudPermission.class);

        // اگر انوتیشن روی کلاس نبود، روی خود متدی که صدا زده شده می‌گردیم
        if (crudPermission == null) {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            crudPermission = AnnotationUtils.findAnnotation(signature.getMethod(), CrudPermission.class);
        }

        // فقط در صورتی که انوتیشن روی کلاس فرزند یا متد پیدا شد، احراز هویت را انجام می‌دهیم
        if (crudPermission != null) {
            Module module = crudPermission.value();
            String action = resolveAction();

            boolean allowed = permissionService.hasPermission(
                    SecurityContextHolder.getContext().getAuthentication(),
                    module.value(),
                    action
            );

            if (!allowed) {
                throw new UnauthorizedException("Permission denied");
            }
        }

        // اگر انوتیشن کلاً وجود نداشت (مثلاً یک کنترلر عمومی است) یا دسترسی مجاز بود، متد اجرا می‌شود
        return joinPoint.proceed();
    }

    private String resolveAction() {

        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes == null) {
            return CrudActions.READ;
        }

        HttpServletRequest request = attributes.getRequest();

        return switch (request.getMethod()) {
            case "POST" -> CrudActions.CREATE;
            case "PUT", "PATCH" -> CrudActions.UPDATE;
            case "DELETE" -> CrudActions.DELETE;
            default -> CrudActions.READ;
        };
    }
}