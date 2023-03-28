package com.mrthinkj.kythucac.modelDTO.user;

import com.mrthinkj.kythucac.annotation.PasswordMatches;
import com.mrthinkj.kythucac.annotation.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@PasswordMatches
public class AccountRegisterDTO {
    @NotNull(message = "Không được để trống thông tin")
    @NotEmpty(message = "Không được để trống thông tin")
    private String username;

    @NotNull(message = "Không được để trống thông tin")
    @NotEmpty(message = "Không được để trống thông tin")
    private String password;
    private String matchingPassword;

    @NotNull(message = "Không được để trống thông tin")
    @NotEmpty(message = "Không được để trống thông tin")
    @ValidEmail
    private String email;
}
