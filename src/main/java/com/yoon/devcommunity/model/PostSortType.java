package com.yoon.devcommunity.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostSortType {
    LATEST("createdDate"), HEART("heart"), VIEW("view");

    private final String value;
}
