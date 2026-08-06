package com.owlet.api.dto.ses;

import com.owlet.api.dto.base.BaseFilter;
import com.owlet.api.repository.specification.SearchFilter;
import com.owlet.api.repository.specification.SearchOperation;
import lombok.Data;
import java.util.UUID;
import java.util.List;

@Data
public class SessionStudentFilterDto implements BaseFilter {

    // این فیلد Nested است، پس مسیر (path) را صریح مشخص می‌کنیم
    @SearchFilter(path = "session.id", operation = SearchOperation.EQUAL)
    private UUID sessionId;

    // چون مسیر نوشته نشده، از نام متغیر (present) استفاده می‌شود
    @SearchFilter(operation = SearchOperation.EQUAL)
    private Boolean present;

    // پیدا کردن نمره‌های بزرگ‌تر از مقدار داده شده
    @SearchFilter(path = "point", operation = SearchOperation.GREATER_THAN)
    private Integer minPoint;

    // چک کردن لیستی از وضعیت‌ها (مثلا IN)
    @SearchFilter(path = "stateEvaluation.id", operation = SearchOperation.IN)
    private List<UUID> evaluationIds;
}