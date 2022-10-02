package com.example.jubging.Repository;

import com.example.jubging.Model.JoinMember;
import com.example.jubging.Model.JoinMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoinMemberRepository extends JpaRepository<JoinMember, JoinMemberId> {
}
