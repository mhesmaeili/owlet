package com.owlet.api.repository.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class SpecificationBuilder {

    private SpecificationBuilder() {}

    // متد قبلی (برای جستجوی سراسری keyword)
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

    // متد جدید برای خواندن درخت فیلترها
    public static <T> Specification<T> byNode(FilterNode rootNode) {
        return (root, query, cb) -> buildPredicate(rootNode, root, cb);
    }

    // متد بازگشتی (Recursive) برای پیمایش درخت
    private static <T> Predicate buildPredicate(FilterNode node, Root<T> root, CriteriaBuilder cb) {
        if (node == null) return null;

        // اگر نود جاری یک "گروه" (دارای فرزند) باشد
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

        // اگر نود جاری یک "شرط ساده" (Leaf) باشد
        else if (node.getKey() != null && node.getOperation() != null) {
            Path<?> path = getPath(root, node.getKey());
            Object value = node.getValue();

            return switch (node.getOperation()) {
                case EQUAL -> cb.equal(path, value);
                case NOT_EQUAL -> cb.notEqual(path, value);
                case GREATER_THAN -> cb.greaterThan(path.as(Comparable.class), (Comparable) value);
                case LESS_THAN -> cb.lessThan(path.as(Comparable.class), (Comparable) value);
                case LIKE -> cb.like(cb.lower(path.as(String.class)), "%" + value.toString().toLowerCase() + "%");
                case IN -> path.in((Collection<?>) value);
                case NOT_IN -> cb.not(path.in((Collection<?>) value));
            };
        }

        return null;
    }

    // متد جدید (برای فیلترهای پویا و پیشرفته)
    public static <T> Specification<T> byCriteria(List<SearchCriteria> criteriaList) {
        return (root, query, cb) -> {
            if (criteriaList == null || criteriaList.isEmpty()) {
                return cb.conjunction();
            }

            List<Predicate> predicates = new ArrayList<>();

            for (SearchCriteria criteria : criteriaList) {
                Path<?> path = getPath(root, criteria.getKey());
                Object value = criteria.getValue();

                switch (criteria.getOperation()) {
                    case EQUAL -> predicates.add(cb.equal(path, value));
                    case NOT_EQUAL -> predicates.add(cb.notEqual(path, value));
                    case GREATER_THAN -> predicates.add(cb.greaterThan(path.as(Comparable.class), (Comparable) value));
                    case LESS_THAN -> predicates.add(cb.lessThan(path.as(Comparable.class), (Comparable) value));
                    case LIKE -> predicates.add(cb.like(cb.lower(path.as(String.class)), "%" + value.toString().toLowerCase() + "%"));
                    case IN -> predicates.add(path.in((Collection<?>) value));
                    case NOT_IN -> predicates.add(cb.not(path.in((Collection<?>) value)));
                }
            }

            // تمام فیلترهای این لیست با AND با هم ترکیب می‌شوند
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
}