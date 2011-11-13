package org.geekscape.android.androidui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

  private LayoutInflater layoutInflater;

  public ListAdapter(
    Context context) {

    layoutInflater = LayoutInflater.from(context);
  }   

  public int getCount() {
    return(TransducerList.names.length);
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
      convertView = layoutInflater.inflate(R.layout.list, null);

      viewHolder = new ViewHolder();
      viewHolder.textView1 = (TextView) convertView.findViewById(R.id.textView1);
      viewHolder.checkBox1 = (CheckBox) convertView.findViewById(R.id.checkBox1);

      viewHolder.checkBox1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
          CheckBox checkBox = (CheckBox) compoundButton;
          TransducerList.values[(Integer) checkBox.getTag()] = isChecked;
        }
      });

      convertView.setTag(viewHolder);
    } 
    else {
      viewHolder = (ViewHolder) convertView.getTag();
    }       

    viewHolder.textView1.setText(TransducerList.names[index]);
    viewHolder.checkBox1.setTag(index);  // Must be before setChecked()
    viewHolder.checkBox1.setChecked(TransducerList.values[index]);

    return(convertView);
  }       

  static class ViewHolder {
    TextView textView1;
    CheckBox checkBox1;
  }       
}