package com.intern.project.freshermanagement.service;

import com.intern.project.freshermanagement.common.constants.GroupChatType;
import com.intern.project.freshermanagement.data.entity.GroupChat;

import java.util.List;

public interface GroupChatService {

    GroupChat createGroup(String username, String group, GroupChatType type);

    List<GroupChat> getAll();

    void delete(String id);

    void notify(String message, String chatId);
}
