package com.example.jubging.Repository;

import com.example.jubging.Model.JoinMember;
import com.example.jubging.Model.JoinMemberId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JoinMemberRepository extends JpaRepository<JoinMember, JoinMemberId> {
    Page<JoinMember> findJoinMembersByUserId(Long userId, Pageable pageable);
    List<JoinMember> findJoinMembersByPostId_PostId(Long postId);
}
