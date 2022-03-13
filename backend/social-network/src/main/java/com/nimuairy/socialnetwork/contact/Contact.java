package com.nimuairy.socialnetwork.contact;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contacts", schema = "nimuairy")
public class Contact implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "first_user_id")
	private Long firstUserId;

	@Column(name = "second_user_id")
	private Long secondUserId;

	@Column(name = "connected_at")
	private Instant connectedAt;

}