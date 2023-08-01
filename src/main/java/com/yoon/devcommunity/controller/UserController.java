package com.yoon.devcommunity.controller;

import com.yoon.devcommunity.exception.CustomException;
import com.yoon.devcommunity.exception.ErrorCode;
import com.yoon.devcommunity.form.LoginForm;
import com.yoon.devcommunity.form.SignUpForm;
import com.yoon.devcommunity.form.UserUpdateForm;
import com.yoon.devcommunity.model.LoginToken;
import com.yoon.devcommunity.model.TokenInfo;
import com.yoon.devcommunity.service.UserService;
import com.yoon.devcommunity.util.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginForm loginForm, Errors errors) {
        if (errors.hasErrors()) throw new CustomException(ErrorCode.INVALID_DATA_INPUT);

        String token = userService.login(loginForm);

        return ResponseEntity.ok(new LoginToken(token));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestHeader("JWT-TOKEN") String token,
                                    @Valid @RequestBody UserUpdateForm updateForm, Errors errors) {
        if (errors.hasErrors()) throw new CustomException(ErrorCode.INVALID_DATA_INPUT);

        TokenInfo tokenInfo = JWTUtils.getTokenInfo(token);
        userService.update(tokenInfo, updateForm);

        return ResponseEntity.ok().build();
    }
}
