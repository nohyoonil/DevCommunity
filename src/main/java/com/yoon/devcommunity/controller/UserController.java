package com.yoon.devcommunity.controller;

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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {

    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpForm signUpForm) {

        userService.signUp(signUpForm);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginForm loginForm) {

        return ResponseEntity.ok(new LoginToken(userService.login(loginForm)));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestHeader("JWT-TOKEN") String token,
                                    @Valid @RequestBody UserUpdateForm updateForm) {

        TokenInfo tokenInfo = JWTUtils.getTokenInfo(token);
        userService.update(tokenInfo, updateForm);

        return ResponseEntity.ok().build();
    }
}
