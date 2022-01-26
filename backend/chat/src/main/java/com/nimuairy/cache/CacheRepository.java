package com.nimuairy.cache;

public interface CacheRepository {

	void putTicket(String token, Long userId);

	Long getUserIdByAccessToken(String token);

}
