package com.nimuairy.conversation;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@AllArgsConstructor
@Builder
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "conversations", schema = "nim_chat")
public class Conversation implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "createdAt")
	private Instant createdAt;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "conversation_id")
	private Set<ConversationUsers> participantsByIds;

}