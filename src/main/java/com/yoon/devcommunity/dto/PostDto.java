package com.yoon.devcommunity.dto;

import com.yoon.devcommunity.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostDto {

    private long postId;

    private UserDto user;

    private String title;
    private String content;

    private long heart;
    private long view;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static PostDto of(Post post) {

        return PostDto.builder().postId(post.getId())
                .user(UserDto.of(post.getUser()))
                .title(post.getTitle())
                .content(post.getContent())
                .heart(post.getHeart())
                .view(post.getView())
                .createdDate(post.getCreatedDate())
                .modifiedDate(post.getModifiedDate())
                .build();
    }
}
