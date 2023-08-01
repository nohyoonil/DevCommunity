package com.yoon.devcommunity.controller;

import com.yoon.devcommunity.exception.CustomException;
import com.yoon.devcommunity.exception.ErrorCode;
import com.yoon.devcommunity.form.SignUpForm;
import com.yoon.devcommunity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {

    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpForm signUpForm, Errors errors) {

        if (errors.hasErrors()) throw new CustomException(ErrorCode.INVALID_DATA_INPUT);

        userService.signUp(signUpForm);

        return ResponseEntity.ok().build();
    }
}
