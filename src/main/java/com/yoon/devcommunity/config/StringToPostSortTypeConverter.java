package com.yoon.devcommunity.config;

import com.yoon.devcommunity.exception.CustomException;
import com.yoon.devcommunity.exception.ErrorCode;
import com.yoon.devcommunity.model.PostSortType;
import org.springframework.core.convert.converter.Converter;

//post controller post 목록 조회 기능에서 String으로 들어오는 param을 enum으로 바꿔주기 위한 converter 구현
public class StringToPostSortTypeConverter implements Converter<String, PostSortType> {
    @Override
    public PostSortType convert(String source) {

        source = source.toUpperCase();
        for (PostSortType postSortType : PostSortType.values()) {
            if (postSortType.name().equals(source)) return postSortType;
        }

        throw new CustomException(ErrorCode.INVALID_DATA_INPUT);
    }
}
