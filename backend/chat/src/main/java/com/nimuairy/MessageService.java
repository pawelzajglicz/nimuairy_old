package com.nimuairy;

import com.nimuairy.models.Message;
import org.springframework.data.domain.Page;

public interface MessageService {
	public void sendMessage(String accessToken, Long sendTo, String msg);

	Page<Message> getLastMessages(Long id, int lastMessagesNumber);
}
