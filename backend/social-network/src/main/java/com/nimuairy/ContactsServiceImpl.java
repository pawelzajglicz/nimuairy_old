package com.nimuairy;

import com.nimuairy.auth.dto.SimpleUserInfo;
import com.nimuairy.auth.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ContactsServiceImpl implements ContactsService {

	ContactsRepository contactsRepository;

	@Override
	public Set<SimpleUserInfo> getUserContacts(Long userId) {

		Set<User> connectedUsers = contactsRepository.findByFirstUserIdOrSecondUserId(userId ,userId)
				.stream()
				.collect(HashSet::new,
						(set, contact) -> {
							set.add(contact.getFirstUser());
							set.add(contact.getSecondUser());
						},
						(set1, set2) -> set1.addAll(set2));

		connectedUsers.removeIf(user -> user.getId().equals(userId));

		return connectedUsers.stream().map(SimpleUserInfo::userToSimpleUserInfo).collect(Collectors.toSet());
	}
}
