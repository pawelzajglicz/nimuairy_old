package com.nimuairy;

import com.nimuairy.models.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {

	Page<Message> findByConversationIdOrderByOrderNumberDesc(Long conversationId, Pageable pageable);
	List<Message> findByConversationIdAndOrderNumberGreaterThanEqualAndOrderNumberLessThanEqualOrderByOrderNumber(Long conversationId, Long from, Long to);
}
