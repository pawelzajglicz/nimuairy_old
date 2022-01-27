package com.nimuairy.dtos;

import com.nimuairy.models.Message;

import java.util.List;

public record LoadConversationDto(Long conversationId, List<Message> lastMessages, Long totalMessages) {

}
