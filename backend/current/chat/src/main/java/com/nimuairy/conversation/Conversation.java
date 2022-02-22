package com.nimuairy.conversation;

import com.nimuairy.auth.models.User;
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
@Table(name = "conversations", schema = "nimuairy")
public class Conversation implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "createdAt")
	private Instant createdAt;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_conversations",
			   schema = "nimuairy",
			   joinColumns = @JoinColumn(name = "conversation_id"),
			   inverseJoinColumns = @JoinColumn(name = "user_id"))
	Set<User> participants;

}