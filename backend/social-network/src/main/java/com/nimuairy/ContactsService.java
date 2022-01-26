package com.nimuairy;

import com.nimuairy.auth.dto.SimpleUserInfo;

import java.util.Set;

public interface ContactsService {
	Set<SimpleUserInfo> getUserContacts(Long userId);
}
