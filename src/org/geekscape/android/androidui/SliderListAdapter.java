package org.geekscape.android.androidui;

import org.geekscape.android.androidservice.Message;

import android.content.Context;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SliderListAdapter extends BaseAdapter {

  private AndroidUIActivity androidUIActivity;

  private LayoutInflater layoutInflater;

  public SliderListAdapter(
    Context context) {

    androidUIActivity = (AndroidUIActivity) context;
    layoutInflater = LayoutInflater.from(context);
  }   

  public int getCount() {
    return(TransducerList.linearNames.length);
  }   

  public Object getItem(
    int index) {

    return(index);
  }   

  public long getItemId(
    int index) {

    return(index);
  }   

  public View getView(
    int       index,
    View      convertView,
    ViewGroup parent) {

    ViewHolder viewHolder;

    if (convertView == null) {
      convertView = layoutInflater.inflate(R.layout.slideritem, null);

      viewHolder = new ViewHolder();
      viewHolder.textView2 = (TextView) convertView.findViewById(R.id.textView2);
      viewHolder.seekBar2  = (SeekBar) convertView.findViewById(R.id.seekBar2);

      viewHolder.seekBar2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
        public void onStopTrackingTouch(SeekBar seekBar) {
          int progress = seekBar.getProgress();
          TransducerList.linearValues[(Integer) seekBar.getTag()] = progress;

          Message message = new Message("topic", "(pin a" + seekBar.getTag() + " " + progress + ")");
          try {
            androidUIActivity.sendMessage(message);
          }
          catch (RemoteException remoteException) {}
        }

        public void onStartTrackingTouch(SeekBar seekBar) {}

        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}
      });

      convertView.setTag(viewHolder);
    } 
    else {
      viewHolder = (ViewHolder) convertView.getTag();
    }       

    viewHolder.textView2.setText(TransducerList.linearNames[index]);
    viewHolder.seekBar2.setTag(index);  // Must be before setChecked()
    viewHolder.seekBar2.setProgress(TransducerList.linearValues[index]);

    return(convertView);
  }       

  static class ViewHolder {
    TextView textView2;
    SeekBar  seekBar2;
  }       
}