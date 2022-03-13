package com.nimuairy.socialnetwork;

import com.nimuairy.socialnetwork.dto.ContactsInfo;

import java.util.List;

public interface ContactsService {
	List<ContactsInfo> getUserContacts(Long userId);
}
