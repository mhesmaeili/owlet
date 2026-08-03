package com.owlet.api.security.permission.aspect;


import com.owlet.api.security.permission.annotation.CrudPermission;
import com.owlet.api.security.permission.permission.CrudActions;
import com.owlet.api.security.permission.enums.Module;
import com.owlet.api.security.permission.service.PermissionService;

import jakarta.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.*;


@Aspect
@Component
@RequiredArgsConstructor
public class CrudPermissionAspect {


    private final PermissionService permissionService;



    @Around("@within(crudPermission)")
    public Object check(
            ProceedingJoinPoint joinPoint,
            CrudPermission crudPermission
    ) throws Throwable {


        Module module =
                crudPermission.value();


        String action =
                resolveAction();



        boolean allowed =
                permissionService.hasPermission(
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication(),

                        module.value(),

                        action
                );


        if (!allowed) {
            throw new AccessDeniedException(
                    "Permission denied"
            );
        }


        return joinPoint.proceed();
    }



    private String resolveAction() {


        ServletRequestAttributes attributes =
                (ServletRequestAttributes)
                        RequestContextHolder
                                .getRequestAttributes();



        if(attributes == null) {
            return CrudActions.READ;
        }



        HttpServletRequest request =
                attributes.getRequest();



        return switch (
                request.getMethod()
                ) {

            case "POST" ->
                    CrudActions.CREATE;


            case "PUT", "PATCH" ->
                    CrudActions.UPDATE;


            case "DELETE" ->
                    CrudActions.DELETE;


            default ->
                    CrudActions.READ;
        };

    }

}