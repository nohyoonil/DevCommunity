package com.yoon.devcommunity.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_DATA_INPUT(HttpStatus.BAD_REQUEST, "올바른 정보를 입력해주세요."),
    EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "사용중인 이메일입니다."),
    NICKNAME_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "사용중인 닉네임입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "잠시 후 다시 이용해주세요.");

    private final HttpStatus status;
    private final String message;
}
