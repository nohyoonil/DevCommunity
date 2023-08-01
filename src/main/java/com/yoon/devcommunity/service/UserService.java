package com.yoon.devcommunity.service;

import com.yoon.devcommunity.entity.User;
import com.yoon.devcommunity.exception.CustomException;
import com.yoon.devcommunity.exception.ErrorCode;
import com.yoon.devcommunity.form.LoginForm;
import com.yoon.devcommunity.form.SignUpForm;
import com.yoon.devcommunity.repository.UserRepository;
import com.yoon.devcommunity.util.JWTUtils;
import com.yoon.devcommunity.util.PasswordUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void signUp(SignUpForm signUpForm) {
        if (userRepository.existsByEmail(signUpForm.getEmail()))
            throw new CustomException(ErrorCode.EMAIL_ALREADY_EXISTS);

        if (userRepository.existsByNickname(signUpForm.getNickname()))
            throw new CustomException(ErrorCode.NICKNAME_ALREADY_EXISTS);

        User user = User.builder().email(signUpForm.getEmail())
                .nickname(signUpForm.getNickname())
                .password(PasswordUtils.getEncodedPassword(signUpForm.getPassword()))
                .createdDate(LocalDateTime.now())
                .build();

        userRepository.save(user);
    }

    public String login(LoginForm loginForm) {
        User user = userRepository.findByEmail(loginForm.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_EXISTS));

        if (!PasswordUtils.equalPassword(loginForm.getPassword(), user.getPassword()))
            throw new CustomException(ErrorCode.PASSWORD_MISMATCH);

        return JWTUtils.createToken(user.getId(), user.getEmail(), user.getNickname());
    }
}
