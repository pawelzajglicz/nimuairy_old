package com.nimuairy.message;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

	private final EntityManager entityManager;
	private final MessageRepository messageRepository;

	@Override
	public Page<Message> getLastMessages(Long conversationId, int lastMessagesNumber) {
		return messageRepository.findByConversationIdOrderByOrderNumberDesc(conversationId, PageRequest.of(0, lastMessagesNumber));
	}

	@Override
	public List<Message> getMessages(Long conversationId, Long from, Long to) {
		return messageRepository
				.findByConversationIdAndOrderNumberGreaterThanEqualAndOrderNumberLessThanEqualOrderByOrderNumber(conversationId, from, to);
	}

	@Override
	@Transactional
	public void save(Message message) {
		Message result = messageRepository.save(message);
		entityManager.refresh(result);
	}
}
