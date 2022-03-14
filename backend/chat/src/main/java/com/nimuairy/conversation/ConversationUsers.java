package com.nimuairy.conversation;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Builder
@Entity
@Getter
@IdClass(ConversationUsersId.class)
@NoArgsConstructor
@Setter
@Table(name = "users_conversations", schema = "nimuairy")
public class ConversationUsers {

	@Id
	@Column(name = "conversation_id")
	private Long conversationId;

	@Id
	@Column(name = "user_id")
	private Long userId;
}
