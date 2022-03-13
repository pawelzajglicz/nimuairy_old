package com.nimuairy.socialnetwork;

import com.nimuairy.socialnetwork.dto.ContactsInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class ContactsServiceImpl implements ContactsService {

	private final ContactsRepository contactsRepository;
	private final UsersServiceClient usersServiceClient;

	@Override
	public List<ContactsInfo> getUserContacts(Long userId) {

		log.info("Getting contacts for user with id {} from repository", userId);
		List<Long> connectedUsersIds = contactsRepository.findByFirstUserIdOrSecondUserId(userId ,userId)
				.stream()
				.collect(ArrayList::new,
						(set, contact) -> {
							set.add(contact.getFirstUserId());
							set.add(contact.getSecondUserId());
						},
						(set1, set2) -> set1.addAll(set2));

		connectedUsersIds.removeIf(id -> id.equals(userId));
		log.info("Requesting users info for user ids: {}", connectedUsersIds);
		List<ContactsInfo> contactsInfo = usersServiceClient.getUsersByIds(connectedUsersIds);

		return contactsInfo;
	}
}
