package com.yoon.devcommunity.repository;

import com.yoon.devcommunity.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
