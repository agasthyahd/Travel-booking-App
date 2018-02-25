package com.example.agasthya.travelbooking;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<Messages> {
    public MessageAdapter(Context context, int resource, List<Messages> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.list_item, parent, false);
        }


        TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        TextView authorTextView = (TextView) convertView.findViewById(R.id.nameTextView);
        TextView authorTextView2 = (TextView) convertView.findViewById(R.id.nameTextView2);


        Messages message = getItem(position);

            messageTextView.setText("From :"+message.getFrom()+"\n");

        authorTextView.setText("To :"+message.getTo()+"\n");
        authorTextView2.setText("Name :"+message.getName()+"\n");

        return convertView;
    }
}
