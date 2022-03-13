package com.nimuairy.socialnetwork.contact;

import java.util.List;

public interface ContactsService {
	List<ContactsInfo> getUserContacts(Long userId);
}
