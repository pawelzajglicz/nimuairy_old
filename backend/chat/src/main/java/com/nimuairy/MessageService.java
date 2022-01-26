package com.nimuairy;

import com.nimuairy.model.Message;

import java.util.List;

public interface MessageService {
	public void sendMessage(String accessToken, Long sendTo, String msg);
}
