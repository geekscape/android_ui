/**
 * Please do not remove the following notices.
 * Copyright (c) 2011 by Geekscape Pty. Ltd.
 * License: AGPLv3 http://geekscape.org/static/aiko_license.html
 */

package org.geekscape.android.androidui;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.geekscape.android.androidservice.*;

import org.geekscape.android.androidui.R;

public class AndroidUIActivity extends Activity {

  public static String LOG_TAG = AndroidUIActivity.class.getSimpleName();

  private static String SERVICE_INTENT = "org.geekscape.android.androidservice.AndroidService";

  private Handler handler;

  private MessageApi messageApi;

  private TextView statusView;

  private ListView checkBoxListView;

  private ListView sliderListView;

  public void sendMessage(
    Message message)
    throws  RemoteException {

    if (messageApi != null) messageApi.sendMessage(message);
  }

  private MessageListener.Stub messageListener = new MessageListener.Stub() {
    public void handleMessage()
      throws RemoteException {

//  Log.d(LOG_TAG, "handleMessage()");

      handler.post(new Runnable() {
        public void run() {
          try {
            Message message = messageApi.getMessage();
            String output = message.getTopic() + ", " + message.getPayload();
            Log.d(LOG_TAG, "Message: " + output);
            statusView.setText(output);
          }
          catch (Throwable throwable) {
            Log.e(LOG_TAG, "Error while handling message", throwable);
          }
        }
      });
    }
  };

  private ServiceConnection serviceConnection = new ServiceConnection() {
    public void onServiceConnected(
      ComponentName componentName,
      IBinder       service) {

      Log.d(LOG_TAG, "Service connection established");

      messageApi = MessageApi.Stub.asInterface(service);

      try {
        messageApi.addListener(messageListener);
      }
      catch (RemoteException remoteException) {
        Log.e(LOG_TAG, "Failed to add listener", remoteException);
      }

// ToDo: Update Message view
    }

    public void onServiceDisconnected(
      ComponentName componentName) {

      Log.d(LOG_TAG, "Service connection closed");
    }
  };

  @Override
  public void onCreate(
    Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    Log.d(LOG_TAG, "onCreate()");
    setContentView(R.layout.main);
    handler = new Handler(); // bound to the current UI thread
    statusView = (TextView) findViewById(R.id.statusView);

    checkBoxListView = (ListView) findViewById(R.id.checkBoxListView);
    checkBoxListView.setAdapter(new CheckBoxListAdapter(this));

    sliderListView = (ListView) findViewById(R.id.sliderListView);
    sliderListView.setAdapter(new SliderListAdapter(this));

    Intent intent = new Intent(SERVICE_INTENT);
    startService(intent);
    bindService(intent, serviceConnection, 0);

    Log.d(LOG_TAG, "Activity created and Service started");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d(LOG_TAG, "onDestroy()");

    try {
      messageApi.removeListener(messageListener);
      unbindService(serviceConnection);
    }
    catch (Throwable throwable) {
      Log.e(LOG_TAG, "Failed to unbind from the service", throwable);
    }

    Log.d(LOG_TAG, "Activity destroyed");
  }
}
