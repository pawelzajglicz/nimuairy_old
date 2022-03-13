package com.nimuairy.socialnetwork;

import com.nimuairy.socialnetwork.dto.ContactsInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "NIM-AUTH")
public interface UsersServiceClient {

	@GetMapping("/users/{userIds}")
	List<ContactsInfo> getUsersByIds(@PathVariable List<Long> userIds);
}
