package com.nimuairy;

import com.nimuairy.models.Message;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MessageService {
	public void sendMessage(String accessToken, Long sendTo, String msg);
	Page<Message> getLastMessages(Long conversationId, int lastMessagesNumber);
	List<Message> getMessages(Long conversationId, Long from, Long to);
}
