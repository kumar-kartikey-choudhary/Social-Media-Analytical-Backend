package com.socialanalyzer.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginRequest {

    @NotNull
    private String username;


    @NotNull
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\\\S+$).{8,20}$",
            message =" Must contain - at least one digit " +
                    "at least one lowercase" +
                    "at least one uppercase" +
                    "at least one special char" +
                    "minimum 8 characters")
    private String password;
}
