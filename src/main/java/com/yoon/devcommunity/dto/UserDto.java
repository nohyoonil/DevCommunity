package com.yoon.devcommunity.dto;

import com.yoon.devcommunity.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserDto {

    private long userId;

    private String email;
    private String nickname;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static UserDto of(User user) {

        return UserDto.builder().userId(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .createdDate(user.getCreatedDate())
                .modifiedDate(user.getModifiedDate())
                .build();
    }
}
