package com.yoon.devcommunity.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
@NoArgsConstructor
public class CreatePostForm {

    @NotBlank
    @Size(min = 6)
    private String title;

    @NotBlank
    @Size(min = 20)
    private String content;
}
