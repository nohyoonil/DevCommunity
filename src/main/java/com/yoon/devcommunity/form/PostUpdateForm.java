package com.yoon.devcommunity.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
@NoArgsConstructor
public class PostUpdateForm {

    @NotBlank
    @Size(min = 20)
    private String content;
}
