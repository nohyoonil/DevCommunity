package com.yoon.devcommunity.service;

import com.yoon.devcommunity.entity.Post;
import com.yoon.devcommunity.entity.User;
import com.yoon.devcommunity.exception.CustomException;
import com.yoon.devcommunity.exception.ErrorCode;
import com.yoon.devcommunity.form.CreatePostForm;
import com.yoon.devcommunity.repository.PostRepository;
import com.yoon.devcommunity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    //게시글 작성
    public void createPost(long userId, CreatePostForm postForm) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_EXISTS));

        Post post = Post.builder().title(postForm.getTitle())
                .user(user)
                .content(postForm.getContent())
                .createdDate(LocalDateTime.now())
                .build();

        postRepository.save(post);
    }
}
