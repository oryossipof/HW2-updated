package com.example.minesweeper;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by or yossipof on 05/09/2017.
 */

public class UserListAdapter extends ArrayAdapter<User> {
    private Context mContext;
    int mResource;
    public UserListAdapter(Context context, int resource, ArrayList<User> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getName();
        int time = getItem(position).getTime();
        int id = getItem(position).getID();
        User user = new User(name,time,id);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView number = (TextView)convertView.findViewById(R.id.textView1);
        TextView tvName = (TextView)convertView.findViewById(R.id.textView2);
        TextView tvTime = (TextView)convertView.findViewById(R.id.textView3);

        number.setText(id + "");
        tvName.setText(name);
        tvTime.setText(time+"");

        return convertView;


    }
}
