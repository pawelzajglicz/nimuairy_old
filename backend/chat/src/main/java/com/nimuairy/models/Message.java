package com.nimuairy.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "messages", schema = "nimuairy")
public class Message implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name="conversation_id")
	private Long conversationId;

	@Column(name="sender_id")
	private Long senderId;

	@Column(name="content")
	private String content;

	@Column(name="sent_at")
	private Instant sentAt;

}