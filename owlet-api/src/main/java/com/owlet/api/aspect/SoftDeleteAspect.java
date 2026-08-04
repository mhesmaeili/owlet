package com.owlet.api.aspect;

import com.owlet.api.annotation.IncludeDeleted;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

@Aspect
@Component
public class SoftDeleteAspect {

    @PersistenceContext
    private EntityManager entityManager;

    // مسیر پکیج repository های خود را در خط پایین جایگزین کنید
    @Around("execution(* com.owlet.api.repository..*(..))")
    public Object manageSoftDeleteFilter(ProceedingJoinPoint joinPoint) throws Throwable {

        // دریافت سشن جاری هایبرنیت
        Session session = entityManager.unwrap(Session.class);

        // بررسی اینکه آیا متد فراخوانی شده، انوتیشن @IncludeDeleted را دارد یا خیر
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        if (method.isAnnotationPresent(IncludeDeleted.class)) {
            // اگر انوتیشن را داشت، فیلتر را خاموش کن (همه رکوردها بیایند)
            session.disableFilter("deletedFilter");
        } else {
            // در غیر این صورت، فیلتر را روشن کن (فقط رکوردهای حذف نشده بیایند)
            session.enableFilter("deletedFilter");
        }

        // ادامه اجرای متد اصلی
        return joinPoint.proceed();
    }
}