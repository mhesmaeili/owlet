package com.owlet.api.dto.org;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.dto.BaseDto;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AcademicYearCreateRequest {
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
}
