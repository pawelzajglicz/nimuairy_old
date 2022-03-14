package com.nimuairy.message;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@Builder
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "messages", schema = "nim_chat")
public class Message implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="content")
	private String content;

	@Column(name="order_number")
	private Long orderNumber;

	@Column(name="saved_at")
	private Instant savedAt;

	@Column(name="conversation_id")
	private Long conversationId;

	@Column(name="sender_id")
	private Long senderId;

}