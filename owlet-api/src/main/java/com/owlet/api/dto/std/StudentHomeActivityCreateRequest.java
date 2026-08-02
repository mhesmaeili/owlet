package com.owlet.api.dto.std;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.service.base.helper.EntityIdDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentHomeActivityCreateRequest {

    @NotNull
    private EntityIdDto sessionStudent;
    private Map<String, Object> parentResponse;
    private String description;

    public void addParentResponse(String key, Object value) {
        if (parentResponse == null) {
            parentResponse = new HashMap<>();
        }
        parentResponse.put(key, value);
    }

}
