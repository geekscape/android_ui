/**
 * Please do not remove the following notices.
 * Copyright (c) 2011 by Geekscape Pty. Ltd.
 * License: AGPLv3 http://geekscape.org/static/aiko_license.html
 */

package org.geekscape.android.androidservice;

import org.geekscape.android.androidservice.Message;
import org.geekscape.android.androidservice.MessageListener;

interface MessageApi {
	Message getMessage();

  void sendMessage(in Message message);

	void addListener(MessageListener messageListener);

	void removeListener(MessageListener messageListener);
}
