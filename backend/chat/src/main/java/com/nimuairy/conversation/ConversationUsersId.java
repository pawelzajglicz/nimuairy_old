package com.nimuairy.conversation;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@NoArgsConstructor
@Setter
public class ConversationUsersId implements Serializable {

	private Long conversationId;
	private Long userId;
}
