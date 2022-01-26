package com.nimuairy.cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;
import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
@RedisHash("Ticket")
public class Ticket implements Serializable {

	private String id;
	private Long userId;
	//private String ticket;
}
