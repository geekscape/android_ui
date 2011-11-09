package org.geekscape.android.androidservice;

import android.os.Parcel;
import android.os.Parcelable;

public final class Message implements Parcelable {

  public static final Creator<Message> CREATOR = new Creator<Message>() {
    public Message[] newArray(
      int size) {

      return(new Message[size]);
    }

    public Message createFromParcel(
      Parcel source) {

      return(new Message(source));
    }
  };

  private String topic;

  private String payload;

  public Message(
    String topic,
    String payload) {

    this.topic = topic;
    this.payload = payload;
  }

  private Message(
    Parcel source) {

    topic   = source.readString();
    payload = source.readString();
  }

  public int describeContents() {
    return(0);
  }

  public String getTopic() {
    return(topic);
  }

  public String getPayload() {
    return(payload);
  }

  public void writeToParcel(
    Parcel parcel,
    int    flags) {

    parcel.writeString(topic);
    parcel.writeString(payload);
  }
}