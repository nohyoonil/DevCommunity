package com.yoon.devcommunity.repository;

import com.yoon.devcommunity.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
