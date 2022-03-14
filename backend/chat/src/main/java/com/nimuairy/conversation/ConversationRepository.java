package com.nimuairy.conversation;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ConversationRepository extends CrudRepository<Conversation, Long> {

	@Query(value = "select conversation from Conversation conversation left join conversation.participantsByIds p where p.userId in :participantsIds group by conversation.id having count(*) > 1")
	Conversation findConversationIdByInterlocutors(Collection<Long> participantsIds);
}
