package com.yoon.devcommunity.service;

import com.yoon.devcommunity.entity.Heart;
import com.yoon.devcommunity.entity.Post;
import com.yoon.devcommunity.entity.User;
import com.yoon.devcommunity.exception.CustomException;
import com.yoon.devcommunity.exception.ErrorCode;
import com.yoon.devcommunity.repository.HeartRepository;
import com.yoon.devcommunity.repository.PostRepository;
import com.yoon.devcommunity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class HeartService {

    private final HeartRepository heartRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    //게시글 좋아요 기능
    public void likePost(long userId, long postId) {
        if (!userRepository.existsById(userId)) throw new CustomException(ErrorCode.USER_NOT_EXISTS);
        if (!postRepository.existsById(postId)) throw new CustomException(ErrorCode.POST_NOT_EXISTS);

        User user = userRepository.getReferenceById(userId);
        Post post = postRepository.getReferenceById(postId);

        if (heartRepository.existsByUserAndPost(user, post))
            throw new CustomException(ErrorCode.INVALID_DATA_INPUT);

        Heart heart = Heart.builder().user(user).post(post).build();

        heartRepository.save(heart);
    }

    //게시글 좋아요 취소 기능
    public void unlikePost(long userId, long postId) {
        if (!userRepository.existsById(userId)) throw new CustomException(ErrorCode.USER_NOT_EXISTS);
        if (!postRepository.existsById(postId)) throw new CustomException(ErrorCode.POST_NOT_EXISTS);

        User user = userRepository.getReferenceById(userId);
        Post post = postRepository.getReferenceById(postId);

        Heart heart = heartRepository.findByUserAndPost(user, post)
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_DATA_INPUT));

        heartRepository.delete(heart);
    }
}
