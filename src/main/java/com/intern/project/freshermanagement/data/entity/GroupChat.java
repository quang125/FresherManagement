package com.intern.project.freshermanagement.data.entity;

import com.intern.project.freshermanagement.common.constants.GroupChatType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
public class GroupChat extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String chatId;

    private String url;
    private GroupChatType telegramGroupType;

}
