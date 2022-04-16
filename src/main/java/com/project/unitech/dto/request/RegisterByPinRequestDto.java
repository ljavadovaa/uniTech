package com.project.unitech.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterByPinRequestDto {

    @Size(min = 7, max = 7)
    @NotBlank(message = "Pin must be provided.")
    private String pin;

    @NonNull
    private String name;

    @NonNull
    private String surname;

    @NonNull
    private String passcode;

    public String getPin() {
        return pin.toUpperCase();
    }

}
