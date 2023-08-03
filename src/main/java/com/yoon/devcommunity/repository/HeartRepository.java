package com.yoon.devcommunity.repository;

import com.yoon.devcommunity.entity.Heart;
import com.yoon.devcommunity.entity.Post;
import com.yoon.devcommunity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    boolean existsByUserAndPost(User user, Post post);

    Optional<Heart> findByUserAndPost(User user, Post post);
}
