package com.yoon.devcommunity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class TokenInfo {
    private Long id;
    private String email;
    private String nickname;
}
