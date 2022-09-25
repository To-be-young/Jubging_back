package com.example.jubging.Repository;

import com.example.jubging.Model.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommunityPostingRepository extends JpaRepository<CommunityPost, Long> {
    Optional<CommunityPost> findByPostId(Long Id);

    List<CommunityPost> findByUserId(Long userId);
}
