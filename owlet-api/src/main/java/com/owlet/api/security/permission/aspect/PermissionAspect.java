package com.owlet.api.security.permission.aspect;


import com.owlet.api.security.permission.annotation.HasPermission;
import com.owlet.api.security.permission.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Aspect
@Component
@RequiredArgsConstructor
public class PermissionAspect {


    private final PermissionService permissionService;


    @Around("@annotation(hasPermission) || @within(hasPermission)")
    public Object checkPermission(
            ProceedingJoinPoint joinPoint,
            HasPermission hasPermission
    ) throws Throwable {


        boolean allowed =
                permissionService.hasPermission(
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication(),

                        hasPermission.module()
                                .value(),

                        hasPermission.action()
                );


        if (!allowed) {
            throw new AccessDeniedException(
                    "Permission denied"
            );
        }


        return joinPoint.proceed();
    }

}
