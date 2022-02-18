package com.nimuairy.brokers;

import com.nimuairy.message.Message;

public interface MessageSender {
	void send(Message message);
}
