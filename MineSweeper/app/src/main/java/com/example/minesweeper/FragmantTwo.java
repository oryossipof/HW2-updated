package com.example.minesweeper;


import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmantTwo extends Fragment implements OnMapReadyCallback{
    GoogleMap mGoogeleMap;
    MapView mMapView;
    View mView;




    public FragmantTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragmant_two, container, false);
        return mView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = (MapView) mView.findViewById(R.id.map);
        if (mMapView != null)
        {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {
       MapsInitializer.initialize(getContext());
        mGoogeleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        Cursor res = MineSweeperMainActivity.myDb.getAllData();
        if (res.getCount() == 0) // no data for us
        {
            // show message
        
           /* CameraPosition HighScorePostion = CameraPosition.builder().target(new LatLng(32.089442, 34.8190357)).zoom(16).bearing(0).tilt(45).build();
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(HighScorePostion));*/
        }

        else
        {
            int counter = 0;

            while(res.moveToNext())
            {
                String name = res.getString(0);
                double longitude = Double.parseDouble( res.getString(1));
                double latitude = Double.parseDouble( res.getString(2));
                int time = res.getInt(3);


              // googleMap.addMarker(new MarkerOptions().position(new LatLng(32.089442, 34.8190357)).title("eli kelev").snippet("eli kelev2"));
                googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title(name).snippet("Time: " + time + " Seconds"));

                if (counter == 0)
                {
                    CameraPosition HighScorePostion = CameraPosition.builder().target(new LatLng(latitude, longitude)).zoom(16).bearing(0).tilt(45).build();
                    googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(HighScorePostion));
                }
                counter++;
            }


            //show all data

        }





    }
}



