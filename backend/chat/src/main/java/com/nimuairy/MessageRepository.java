package com.nimuairy;

import com.nimuairy.models.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {

	Page<Message> findByConversationIdOrderBySentAt(Long conversationId, Pageable pageable);
	Page<Message> findByConversationIdOrderBySentAtDesc(Long conversationId, Pageable pageable);
}
