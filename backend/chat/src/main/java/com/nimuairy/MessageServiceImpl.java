package com.nimuairy;

import com.nimuairy.models.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class MessageServiceImpl implements MessageService {


	MessageRepository messageRepository;
/*
	@Autowired
	CacheRepository cacheRepository;

	@Autowired
	UserRepository userRepository;*/

	MessageHandler messageHandler;

	@Override
	public void sendMessage(String accessToken, Long sendTo, String msg) {
		System.out.println("sendMessage");
		/*Long senderUserId = 0L;
		String senderId = cacheRepository.getUserIdByAccessToken(accessToken);

		if (senderId == null) {
			User sender = userRepository.findByToken(accessToken);
			if (sender != null) {
				senderUserId = sender.getUserId();
			}
		} else {
			senderUserId = Long.valueOf(senderId);
		}
		if (senderUserId == 0L) {
			return;
		}

		try {
			// enrich message with senderId
			JSONObject msgJson = new JSONObject();
			msgJson.put("msg", msg);
			msgJson.put("senderId", senderUserId);
			messageHandler.sendMessageToUser(sendTo, msgJson.toString());
		} catch (IOException e) {
			return;
		}*/
	}

	@Override
	public Page<Message> getLastMessages(Long conversationId, int lastMessagesNumber) {
		return messageRepository.findByConversationIdOrderByOrderNumberDesc(conversationId, PageRequest.of(0, lastMessagesNumber));
	}

	@Override
	public List<Message> getMessages(Long conversationId, Long from, Long to) {
		return messageRepository.findByConversationIdAndOrderNumberGreaterThanEqualAndOrderNumberLessThanEqualOrderByOrderNumber(conversationId, from, to);
	}

}
