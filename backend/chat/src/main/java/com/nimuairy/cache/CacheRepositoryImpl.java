package com.nimuairy.cache;


import org.springframework.stereotype.Service;


import redis.clients.jedis.Jedis;

@Service
public class CacheRepositoryImpl implements CacheRepository {

	@Override
	public void putTicket(String token, Long userId) {

		try (Jedis jedis = JedisFactory.getConnection()) {

			jedis.set(token, userId.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Long getUserIdByAccessToken(String token) {

		try (Jedis jedis = JedisFactory.getConnection()) {

			return Long.parseLong(jedis.get(token));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}