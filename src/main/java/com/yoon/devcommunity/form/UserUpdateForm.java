package com.yoon.devcommunity.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
@NoArgsConstructor
public class UserUpdateForm {

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;
    private String updatePassword;
    private String updateNickname;
}
