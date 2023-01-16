package com.koke.koke_backend.user.dto;

import jakarta.validation.constraints.Email;
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
public class SignUpRequestDto {

    @NotBlank(message = "사용자 ID를 입력하지 않으셨습니다.")
    @Size(max = 30, message = "사용자 ID는 30자 이하로 입력해주세요.")
    private String userId;

    @NotBlank(message = "패스워드를 입력하지 않으셨습니다.")
    @Size(min = 10, max = 30, message = "패스워드는 30자 이하로 입력해주세요.")
    private String password;

    @NotBlank(message = "사용자명을 입력하지 않으셨습니다.")
    @Size(max = 30, message = "사용자명은 30자 이하로 입력해주세요.")
    private String username;

    @NotBlank(message = "이메일을 입력하지 않으셨습니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @Size(max = 40, message = "이메일은 40자 이하로 입력해주세요.")
    private String email;

    @NotBlank(message = "전화번호를 입력하지 않으셨습니다.")
    @Size(max = 20, message = "전화번호는 20자 이하로 입력해주세요.")
    private String phoneNo;

}
