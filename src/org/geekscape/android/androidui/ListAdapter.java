package org.geekscape.android.androidui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
      viewHolder.textView2 = (TextView) convertView.findViewById(R.id.textView2);

      convertView.setTag(viewHolder);
    } 
    else {
      viewHolder = (ViewHolder) convertView.getTag();
    }       

    viewHolder.textView1.setText(TransducerList.names[index]);
    viewHolder.textView2.setText(TransducerList.values[index]  ?  "true"  :  "false");

    return(convertView);
  }       

  static class ViewHolder {
    TextView textView1;
    TextView textView2;
  }       
}