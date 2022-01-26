package com.nimuairy;

import com.nimuairy.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

/*
	@Autowired
	MessageRepository messageRepository;

	@Autowired
	CacheRepository cacheRepository;

	@Autowired
	UserRepository userRepository;*/

	@Autowired
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


	private void storeMessageToUser(Message message) {
	//	messageRepository.save(message);
	}
}
