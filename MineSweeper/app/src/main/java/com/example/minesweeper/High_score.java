package com.example.minesweeper;

import android.app.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class High_score extends Activity {
    boolean status = false;
private ArrayList<User> users = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        FragmantOne f1 = new FragmantOne();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        FragmantOne f2 = new FragmantOne();
        fragmentTransaction.add(R.id.fragmantContainer,f2);
        fragmentTransaction.commit();

        Button byTableBT = (Button)findViewById(R.id.tableButton) ;
        Button byMapBT = (Button)findViewById(R.id.mapButton);

        byTableBT.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)  {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                if(!status)
                {

                    //clear all fragments before
                    getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.fragmantContainer)).commit();

                    FragmantOne f1 = new FragmantOne();
                    fragmentTransaction.add(R.id.fragmantContainer,f1);
                    fragmentTransaction.commit();

                }
            }
        });

        byMapBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                if(!status)
                {
                    //clear all fragments before
                    getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.fragmantContainer)).commit();

                    FragmantTwo f3 = new FragmantTwo();
                    fragmentTransaction.add(R.id.fragmantContainer,f3);
                    fragmentTransaction.commit();

                }

            }
        });


     //   ListView mlistView = (ListView) findViewById(R.id.listView);





    /*   Cursor res = MineSweeperMainActivity.myDb.getAllData();
        if (res.getCount() == 0) // no data for us
        {
            // show message
            return;
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

        }*/


       // UserListAdapter adapter = new UserListAdapter(this,R.layout.adapter_view_layout,users);
      //  mlistView.setAdapter(adapter);


    }



}
