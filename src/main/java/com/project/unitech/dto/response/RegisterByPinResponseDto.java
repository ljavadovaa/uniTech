package com.project.unitech.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterByPinResponseDto {

    @JsonProperty("user_id")
    private String userId;

}
