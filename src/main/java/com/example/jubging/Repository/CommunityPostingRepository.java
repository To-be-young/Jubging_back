package com.example.jubging.Repository;

import com.example.jubging.Model.CommunityPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommunityPostingRepository extends JpaRepository<CommunityPost, Long> {
    Optional<CommunityPost> findByPostId(Long Id);

    Page<CommunityPost> findByUserId(Long userId, Pageable pageable);

    Page<CommunityPost> findAll(Pageable pageable);
}