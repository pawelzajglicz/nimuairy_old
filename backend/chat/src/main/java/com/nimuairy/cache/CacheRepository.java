package com.nimuairy.cache;

import java.util.Optional;

public interface CacheRepository {

	void putTicket(String token, Long userId);

	Optional<Long> getUserIdByAccessToken(String token);

}
