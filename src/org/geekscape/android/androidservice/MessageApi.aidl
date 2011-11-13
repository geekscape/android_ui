package org.geekscape.android.androidservice;

import org.geekscape.android.androidservice.Message;
import org.geekscape.android.androidservice.MessageListener;

interface MessageApi {
	Message getMessage();

  void sendMessage(in Message message);

	void addListener(MessageListener messageListener);

	void removeListener(MessageListener messageListener);
}