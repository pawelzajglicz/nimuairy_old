package com.nimuairy.conversation;

import com.nimuairy.message.Message;

public interface ConversationService {
	LoadConversationDto loadConversationWithUser(Long interlocutorId);

	Conversation getConversationById(Long conversationId);

	void handleNewMessage(Message message);

	void sendMessageToConversationParticipants(Message message);
}
