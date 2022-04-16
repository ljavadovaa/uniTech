package com.project.unitech.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;


@Data
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
public class LoginByPinPassRequestDto {

    @NotBlank(message = "Pin must be provided")
    @JsonProperty
    @ApiModelProperty(value = "Pin number of the user.", example = "123ABC2", required = true)
    private String pin;

    @NotBlank(message = "Password must be provided")
    @ApiModelProperty(value = "Password of the user.", required = true)
    private String passcode;
}
