package com.intern.project.freshermanagement.repository;

import com.intern.project.freshermanagement.data.entity.GroupChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupChatRepository extends JpaRepository<GroupChat,Long> {
    Optional<GroupChat> findByName(String name);
}
