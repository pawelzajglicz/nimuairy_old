package com.nimuairy.models;

import com.nimuairy.auth.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="conversations")
public class Conversation implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_conversations",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "conversation_id"))
	Set<User> participants;

	@Column(name = "createdAt")
	private Instant createdAt;

}