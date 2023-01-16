package com.koke.koke_backend.user.dto;

import jakarta.validation.constraints.NotBlank;
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
    @Size(max = 30, message = "사용자 ID는 30자 이하로 입력해주세요.")
    private String userId;

    @NotBlank(message = "패스워드를 입력하지 않으셨습니다.")
    @Size(min = 10, max = 30, message = "패스워드는 30자 이하로 입력해주세요.")
    private String password;

}
