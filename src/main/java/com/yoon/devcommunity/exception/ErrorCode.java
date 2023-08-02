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
    USER_NOT_EXISTS(HttpStatus.BAD_REQUEST, "유저가 존재하지 않습니다."),
    POST_NOT_EXISTS(HttpStatus.BAD_REQUEST, "게시글이 존재하지 않습니다."),
    REQUIRED_LOGIN_AGAIN(HttpStatus.BAD_REQUEST, "재 로그인 후 이용해주세요."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "잠시 후 다시 이용해주세요."),
    UNABLE_TO_UPDATE(HttpStatus.BAD_REQUEST, "수정 가능한 정보를 입력해주세요."),
    HAS_NO_AUTHORIZATION(HttpStatus.UNAUTHORIZED, "권한이 없습니다"),
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "옳바른 비밀번호를 입력해주세요.");

    private final HttpStatus status;
    private final String message;
}
