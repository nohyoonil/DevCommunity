package com.yoon.devcommunity.controller;

import com.yoon.devcommunity.exception.CustomException;
import com.yoon.devcommunity.exception.ErrorCode;
import com.yoon.devcommunity.form.CreatePostForm;
import com.yoon.devcommunity.model.TokenInfo;
import com.yoon.devcommunity.service.PostService;
import com.yoon.devcommunity.util.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/post")
@Controller
public class PostController {

    private final PostService postService;

    //게시글 작성
    @PostMapping
    public ResponseEntity<?> createPost(@RequestHeader("JWT-TOKEN") String token,
                                        @Valid @RequestBody CreatePostForm postForm, Errors errors) {
        if (errors.hasErrors()) throw new CustomException(ErrorCode.INVALID_DATA_INPUT);

        TokenInfo tokenInfo = JWTUtils.getTokenInfo(token);
        postService.createPost(tokenInfo.getId(), postForm);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
