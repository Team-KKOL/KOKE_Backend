package com.koke.koke_backend.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "사용자 ID를 입력하지 않으셨습니다.")
    @Size(min = 8, max = 30, message = "사용자 ID는 8자 이상 30자 이하로 입력해주세요.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)[a-z\\d]{8,20}$", message = "사용자 ID는 영소문자와 숫자만 입력해주세요.")
    private String userId;

    @NotBlank(message = "패스워드를 입력하지 않으셨습니다.")
    @Size(min = 8, max = 20, message = "패스워드는 8자 이상 20자 이하로 입력해주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()])[A-Za-z\\d!@#$%^&*()]{8,20}$",
            message = "패스워드는 영문자, 숫자, 특수문자를 포함해야 합니다.")
    private String password;

}
