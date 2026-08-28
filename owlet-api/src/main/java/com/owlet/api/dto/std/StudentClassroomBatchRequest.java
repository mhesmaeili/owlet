package com.owlet.api.dto.std;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class StudentClassroomBatchRequest {
    @NotEmpty(message = "لیست شناسه‌ها نمی‌تواند خالی باشد")
    private List<UUID> ids;

    @NotNull(message = "وضعیت باید مشخص شود")
    private Boolean active;
}
