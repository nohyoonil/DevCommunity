package com.yoon.devcommunity.controller;

import com.yoon.devcommunity.model.TokenInfo;
import com.yoon.devcommunity.service.HeartService;
import com.yoon.devcommunity.util.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/heart")
@Controller
public class HeartController {

    private final HeartService heartService;

    //게시글 좋아요
    @PostMapping("/{postId}")
    public ResponseEntity<?> likePost(@RequestHeader("JWT-TOKEN") String token, @PathVariable long postId) {

        TokenInfo tokenInfo = JWTUtils.getTokenInfo(token);
        heartService.likePost(tokenInfo.getId(), postId);

        return ResponseEntity.ok().build();
    }

    //게시글 좋아요 취소
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> unlikePost(@RequestHeader("JWT-TOKEN") String token, @PathVariable long postId) {

        TokenInfo tokenInfo = JWTUtils.getTokenInfo(token);
        heartService.unlikePost(tokenInfo.getId(), postId);

        return ResponseEntity.ok().build();
    }
}
