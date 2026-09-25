package com.owlet.api.repository.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public final class SpecificationBuilder {

    private SpecificationBuilder() {}

    // متد جستجوی متنی سراسری
    public static <T> Specification<T> contains(String keyword, String... fields) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.isBlank() || fields == null || fields.length == 0) {
                return cb.conjunction();
            }

            List<Predicate> predicates = new ArrayList<>();
            String pattern = "%" + keyword.toLowerCase() + "%";

            for (String field : fields) {
                Path<?> path = getPath(root, field);
                predicates.add(cb.like(cb.lower(path.as(String.class)), pattern));
            }

            return cb.or(predicates.toArray(new Predicate[0]));
        };
    }

    // متد خواندن درخت فیلترها
    public static <T> Specification<T> byNode(FilterNode rootNode) {
        return (root, query, cb) -> buildPredicate(rootNode, root, cb);
    }

    // متد بازگشتی (Recursive) با تبدیل خودکار تایپ‌ها
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static <T> Predicate buildPredicate(FilterNode node, Root<T> root, CriteriaBuilder cb) {
        if (node == null) return null;

        // ۱. نود گروهی (دارای فرزند)
        if (node.getChildren() != null && !node.getChildren().isEmpty()) {
            List<Predicate> predicates = new ArrayList<>();
            for (FilterNode child : node.getChildren()) {
                Predicate p = buildPredicate(child, root, cb);
                if (p != null) predicates.add(p);
            }

            if (predicates.isEmpty()) return null;

            Predicate[] predArray = predicates.toArray(new Predicate[0]);
            return node.getLogicalOperator() == FilterNode.LogicalOperator.OR
                    ? cb.or(predArray)
                    : cb.and(predArray);
        }

        // ۲. شرط ساده (Leaf)
        else if (node.getKey() != null && node.getOperation() != null) {
            Path<?> path = getPath(root, node.getKey());
            Object rawValue = node.getValue();

            // تبدیل امن تایپ String ورودی به تایپ واقعی فیلد انتیتی (مثل UUID، Boolean، Long و ...)
            Object value = castToRequiredType(path.getJavaType(), rawValue);

            return switch (node.getOperation()) {
                case EQUAL -> cb.equal(path, value);
                case NOT_EQUAL -> cb.notEqual(path, value);
                case GREATER_THAN -> cb.greaterThan((Path<Comparable>) path, (Comparable) value);
                case LESS_THAN -> cb.lessThan((Path<Comparable>) path, (Comparable) value);
                case LIKE -> cb.like(cb.lower(path.as(String.class)), "%" + value.toString().toLowerCase() + "%");
                case IN -> path.in((Collection<?>) value);
                case NOT_IN -> cb.not(path.in((Collection<?>) value));
            };
        }

        return null;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> Specification<T> byCriteria(List<SearchCriteria> criteriaList) {
        return (root, query, cb) -> {
            if (criteriaList == null || criteriaList.isEmpty()) {
                return cb.conjunction();
            }

            List<Predicate> predicates = new ArrayList<>();

            for (SearchCriteria criteria : criteriaList) {
                Path<?> path = getPath(root, criteria.getKey());
                Object value = castToRequiredType(path.getJavaType(), criteria.getValue());

                switch (criteria.getOperation()) {
                    case EQUAL -> predicates.add(cb.equal(path, value));
                    case NOT_EQUAL -> predicates.add(cb.notEqual(path, value));
                    case GREATER_THAN -> predicates.add(cb.greaterThan((Path<Comparable>) path, (Comparable) value));
                    case LESS_THAN -> predicates.add(cb.lessThan((Path<Comparable>) path, (Comparable) value));
                    case LIKE -> predicates.add(cb.like(cb.lower(path.as(String.class)), "%" + value.toString().toLowerCase() + "%"));
                    case IN -> predicates.add(path.in((Collection<?>) value));
                    case NOT_IN -> predicates.add(cb.not(path.in((Collection<?>) value)));
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static <T> Path<?> getPath(Root<T> root, String attributePath) {
        String[] parts = attributePath.split("\\.");
        Path<?> path = root;
        for (String part : parts) {
            path = path.get(part);
        }
        return path;
    }

    // متد حل مشکل مغایرت تایپ‌ها (به‌ویژه String به UUID و Boolean و اعداد)
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static Object castToRequiredType(Class<?> targetType, Object value) {
        if (value == null) {
            return null;
        }

        // اگر از قبل هم‌تایپ باشند نیازی به تبدیل نیست
        if (targetType.isAssignableFrom(value.getClass())) {
            return value;
        }

        String strValue = value.toString().trim();

        // ۱. تبدیل به UUID (رفع خطای اصلی)
        if (UUID.class.equals(targetType)) {
            return UUID.fromString(strValue);
        }

        // ۲. تبدیل به Boolean
        if (Boolean.class.equals(targetType) || boolean.class.equals(targetType)) {
            return Boolean.valueOf(strValue);
        }

        // ۳. تبدیل به انواع عددی
        if (Long.class.equals(targetType) || long.class.equals(targetType)) {
            return Long.valueOf(strValue);
        }
        if (Integer.class.equals(targetType) || int.class.equals(targetType)) {
            return Integer.valueOf(strValue);
        }
        if (Double.class.equals(targetType) || double.class.equals(targetType)) {
            return Double.valueOf(strValue);
        }

        // ۴. تبدیل به Enum
        if (targetType.isEnum()) {
            return Enum.valueOf((Class<Enum>) targetType, strValue);
        }

        return value;
    }
}