package com.yoon.devcommunity.service;

import com.yoon.devcommunity.dto.PostDto;
import com.yoon.devcommunity.entity.Post;
import com.yoon.devcommunity.entity.User;
import com.yoon.devcommunity.exception.CustomException;
import com.yoon.devcommunity.exception.ErrorCode;
import com.yoon.devcommunity.form.PostCreateForm;
import com.yoon.devcommunity.form.PostUpdateForm;
import com.yoon.devcommunity.model.PostSortType;
import com.yoon.devcommunity.repository.PostRepository;
import com.yoon.devcommunity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final int SIZE = 10;

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

    //게시글 단건 조회
    public PostDto getPost(long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_EXISTS));

        return PostDto.of(post);
    }

    //게시글들 조회 - page 기준 10건씩 조회 (최신순, 좋아요 수, 조회수)
    public Page<PostDto> getPostList(int page, PostSortType sortType) {
        PageRequest pageRequest = PageRequest.of(page, SIZE, Sort.by(sortType.getValue()).descending());

        return postRepository.findAll(pageRequest).map(PostDto::of);
    }
}
