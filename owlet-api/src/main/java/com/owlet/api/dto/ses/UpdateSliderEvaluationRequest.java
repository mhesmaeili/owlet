package com.owlet.api.dto.ses;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
public class UpdateSliderEvaluationRequest {
    @NotNull(message = "نمرات ارزیابی نمی‌تواند خالی باشد")
    private Map<String, Integer> scores;
}
