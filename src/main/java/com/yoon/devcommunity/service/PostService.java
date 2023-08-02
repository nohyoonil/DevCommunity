package com.yoon.devcommunity.service;

import com.yoon.devcommunity.entity.Post;
import com.yoon.devcommunity.entity.User;
import com.yoon.devcommunity.exception.CustomException;
import com.yoon.devcommunity.exception.ErrorCode;
import com.yoon.devcommunity.form.PostCreateForm;
import com.yoon.devcommunity.form.PostUpdateForm;
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
    public void createPost(long userId, PostCreateForm postForm) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_EXISTS));

        Post post = Post.builder().title(postForm.getTitle())
                .user(user)
                .content(postForm.getContent())
                .createdDate(LocalDateTime.now())
                .build();

        postRepository.save(post);
    }

    //게시글 수정 - content 수정 가능
    public void updatePost(long userId, long postId, PostUpdateForm updateForm) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_EXISTS));

        if (post.getUser().getId() != userId) throw new CustomException(ErrorCode.HAS_NO_AUTHORIZATION);
        if (post.getContent().equals(updateForm.getContent())) throw new CustomException(ErrorCode.INVALID_DATA_INPUT);

        post.setContent(updateForm.getContent());
        post.setModifiedDate(LocalDateTime.now());

        postRepository.save(post);
    }

    //게시글 삭제
    public void deletePost(long userId, long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_EXISTS));

        if (post.getUser().getId() != userId) throw new CustomException(ErrorCode.HAS_NO_AUTHORIZATION);

        postRepository.delete(post);
    }
}
