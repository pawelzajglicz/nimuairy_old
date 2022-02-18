package com.nimuairy.message;

import org.springframework.data.domain.Page;

import java.util.List;

public interface MessageService {
	Page<Message> getLastMessages(Long conversationId, int lastMessagesNumber);
	List<Message> getMessages(Long conversationId, Long from, Long to);

	void save(Message message);
}
