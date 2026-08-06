package com.owlet.api.repository.specification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FilterNode {

    public enum LogicalOperator { AND, OR }

    // فیلدهای مربوط به حالت "گروه (Node)"
    private LogicalOperator logicalOperator;
    private List<FilterNode> children;

    // فیلدهای مربوط به حالت "شرط ساده (Leaf)"
    private String key;
    private SearchOperation operation;
    private Object value;

    // کانستراکتور برای شرط ساده
    public FilterNode(String key, SearchOperation operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    // کانستراکتور برای گروه
    public FilterNode(LogicalOperator logicalOperator, List<FilterNode> children) {
        this.logicalOperator = logicalOperator;
        this.children = children;
    }

    // ---- متدهای کمکی (Factory Methods) برای ساخت فوق‌العاده تمیز ----

    public static FilterNode condition(String key, SearchOperation operation, Object value) {
        return new FilterNode(key, operation, value);
    }

    public static FilterNode and(FilterNode... nodes) {
        return new FilterNode(LogicalOperator.AND, Arrays.asList(nodes));
    }

    public static FilterNode or(FilterNode... nodes) {
        return new FilterNode(LogicalOperator.OR, Arrays.asList(nodes));
    }
}