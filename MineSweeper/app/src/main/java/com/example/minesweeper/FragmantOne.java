package com.example.minesweeper;


import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmantOne extends Fragment {
    private ArrayList<User> users = new ArrayList<>();


    public FragmantOne() {

        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmant_one, container, false);
        ListView mlistView = (ListView)view.findViewById(R.id.listView);
        Cursor res = MineSweeperMainActivity.myDb.getAllData();
        if (res.getCount() == 0) // no data for us
        {
            // show message

        }

        else
        {
            int counter = 1;

            while(res.moveToNext())
            {
                String name = res.getString(0);
                int time = res.getInt(3);
                users.add(new User(name,time,counter++));

            }


            //show all data

        }

        UserListAdapter adapter = new UserListAdapter(getActivity(),R.layout.adapter_view_layout,users);
        mlistView.setAdapter(adapter);


        // Inflate the layout for this fragment
        return view;
    }

}


