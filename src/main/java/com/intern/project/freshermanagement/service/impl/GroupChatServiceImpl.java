package com.intern.project.freshermanagement.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.intern.project.freshermanagement.common.constants.GroupChatType;
import com.intern.project.freshermanagement.common.exception.BusinessException;
import com.intern.project.freshermanagement.common.exception.NetworkApiException;
import com.intern.project.freshermanagement.common.log.Logger;
import com.intern.project.freshermanagement.common.util.OkHttp;
import com.intern.project.freshermanagement.data.entity.GroupChat;
import com.intern.project.freshermanagement.repository.GroupChatRepository;
import com.intern.project.freshermanagement.service.GroupChatService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
@RequiredArgsConstructor
public class GroupChatServiceImpl implements GroupChatService {
    @Value("${telegram.bot.api}")
    private String endpoints;

    @Value("${telegram.bot.token}")
    private String token;

    private final OkHttp okHttp;
    private final ObjectMapper mapper;
    private final GroupChatRepository groupChatRepository;
    public static String previousChatId = "";
    @Override
    public GroupChat createGroup(String username, String group, GroupChatType type) {
        username = username.replace("@", "");
        Optional<GroupChat> existedGroup = groupChatRepository.findByName(group);
        if (existedGroup.isPresent()) {
            throw BusinessException.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("Group is existed")
                    .build();
        }
        String url = "https://api.telegram.org/bot" + token + "/getUpdates";
        String res = okHttp.get(url, null, null);
        String chatId = "";
        try {
            ObjectNode  response = mapper.readValue(res, ObjectNode.class);
            List<LinkedHashMap<String, Object>> updateEvents = mapper.convertValue(response.get("result"), List.class);
            if (updateEvents == null) {
                updateEvents = new ArrayList<>();
            }
            updateEvents.sort((o1, o2) -> {
                Integer updateId1 = (Integer) o1.get("update_id");
                Integer updateId2 = (Integer) o2.get("update_id");
                return updateId2 - updateId1;
            });
            System.out.println(updateEvents);
            for (LinkedHashMap<String, Object> event : updateEvents) {
                chatId = getChatIdFromAddBotToGroupEvent(username, group, event);
                if (chatId.isEmpty()) {
                    chatId = getChatIdFromCreatedGroupEvent(username, group, event);
                }
                if (!chatId.isEmpty()) {
                    break;
                }
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if (chatId.isEmpty()) {
            throw BusinessException.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("Please add bot VMO Holdings Fresher Supporter to the group before")
                    .build();
        }
        GroupChat telegramGroup = new GroupChat();
        telegramGroup.setName(group);
        telegramGroup.setChatId(chatId);
        telegramGroup.setTelegramGroupType(type);
        telegramGroup = groupChatRepository.save(telegramGroup);
        notify("Hi, I'm VMO Holdings Fresher Supporter", telegramGroup.getChatId());
        return telegramGroup;
    }

    @Override
    public List<GroupChat> getAll() {
        return groupChatRepository.findAll();
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void notify(String message, String chatId) {
        String url = endpoints + token + "/sendMessage";
        Map<String, String> params = new HashMap<>() {{
            put("chat_id", chatId);
            put("text", message);
            put("parse_mode", "html");
        }};
        try {
            if (chatId.equals(previousChatId)) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            okHttp.postWithJsonBody(url, params, null, null);
            previousChatId = chatId;
        } catch (NetworkApiException e) {
            Logger.error("Send telegram message error: {}", e);
        }
    }
    private String getChatIdFromCreatedGroupEvent(String username, String group, LinkedHashMap<String, Object> event) {
        LinkedHashMap<String, Object> message = (LinkedHashMap) event.get("message");
        if (message == null) {
            return "";
        }
        Boolean groupChatCreated = (Boolean) message.get("group_chat_created");
        if (groupChatCreated == null || !groupChatCreated) {
            return "";
        }
        String fromUsername = (String) ((LinkedHashMap) message.get("from")).get("username");
        System.out.println(fromUsername+" "+1);
        if (StringUtils.isBlank(fromUsername) || !fromUsername.equals(username)) {
            return "";
        }
        LinkedHashMap<String, Object> chat = (LinkedHashMap) message.get("chat");
        String title = (String) chat.get("title");
        if (title == null || !title.equals(group)) {
            return "";
        }
        return String.valueOf(chat.get("id"));
    }

    private String getChatIdFromAddBotToGroupEvent(String username, String group, LinkedHashMap<String, Object> event) {
        LinkedHashMap<String, Object> chatMember = (LinkedHashMap) event.get("my_chat_member");
        System.out.println(chatMember);
        if (chatMember == null) {
            return "";
        }
        String fromUsername = (String) ((LinkedHashMap) chatMember.get("from")).get("username");
        if (StringUtils.isBlank(fromUsername) || !fromUsername.equals(username)) {
            return "";
        }
        LinkedHashMap<String, Object> chat = (LinkedHashMap) chatMember.get("chat");
        String title = (String) chat.get("title");
        if (!title.equals(group)) {
            return "";
        }
        LinkedHashMap<String, Object> newChatMember = (LinkedHashMap) chatMember.get("new_chat_member");
        if (newChatMember == null) {
            return "";
        }
        return String.valueOf(chat.get("id"));
    }


}
