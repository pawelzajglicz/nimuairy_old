package com.nimuairy.serviceclients;

import com.nimuairy.conversation.ConversationParticipant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@FeignClient(name = "NIM-AUTH")
public interface UsersServiceClient {

	@GetMapping("/users/{userIds}")
	Set<ConversationParticipant> getUsersByIds(@PathVariable Set<Long> userIds);
}
