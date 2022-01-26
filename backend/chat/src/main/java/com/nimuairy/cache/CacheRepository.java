package com.nimuairy.cache;

public interface CacheRepository {

	void putAccessToken(String token, String userId);

	String getUserIdByAccessToken(String token);

}
