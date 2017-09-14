package com.example.minesweeper;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.nfc.Tag;
import android.opengl.Visibility;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EndGame extends AppCompatActivity {
private String result;
    private int timer;
    GPSTracker gps;
    Context mcontext;
   private  double latitude;
    private double longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent mIntent = getIntent();
         result = mIntent.getStringExtra("result");
        timer = mIntent.getIntExtra("timer", 0);

        setContentView(R.layout.activity_end_game);

mcontext= this;
        TextView resulttext = (TextView)findViewById(R.id.Youwon_lose_Label);
        resulttext.setText(resulttext.getText() + "" + result);

        TextView timeText = (TextView) findViewById(R.id.timeLabel);
        if (result.equals("won")) {

            timeText.setText(timeText.getText() + "" + timer);

            /*insert your name and take your location*/


        }
        else {
            timeText.setText("");
            TextView enterNameLabel = (TextView)findViewById(R.id.enterName);
            enterNameLabel.setVisibility(TextView.INVISIBLE);
            EditText nameText = (EditText)findViewById(R.id.nameText);
            nameText.setVisibility(EditText.INVISIBLE);
        }




        final Button okbt  =(Button) findViewById(R.id.okBT);
        okbt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (result.equals("won"))
                {


                    if (ContextCompat.checkSelfPermission(mcontext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mcontext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(EndGame.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                    }
                    else{
                       // Toast.makeText(mcontext,"You need have granted permission",Toast.LENGTH_SHORT).show();
                            gps = new GPSTracker(mcontext, EndGame.this);
                            if (gps.canGetLocation()) {
                                 latitude = gps.getLatitude();
                                 longitude = gps.getLongitude();


                                Toast.makeText(getApplicationContext(), "your location is\n Lat: " + latitude + "\nLong : " + longitude, Toast.LENGTH_SHORT).show();
                                EditText nameText = (EditText)findViewById(R.id.nameText);
                               MineSweeperMainActivity.myDb.insertData(nameText.getText().toString(), timer, latitude+"",longitude+"");

                                Intent intent = new Intent(EndGame.this,High_score.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            } else {
                                gps.showSettingsAlert();
                                latitude = gps.getLatitude();
                                longitude = gps.getLongitude();
                            }



                            //go to result screen
                        }

                    //insert to db




                }
                else
                {
                    Intent intent = new Intent(EndGame.this,MineSweeperMainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);  //to home screen
                }


            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the

                    // contacts-related task you need to do.

                    gps = new GPSTracker(mcontext, EndGame.this);

                    // Check if GPS enabled
                    if (gps.canGetLocation()) {

                        latitude = gps.getLatitude();
                         longitude = gps.getLongitude();

                        // \n is for new line
                        Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                    } else {
                        // Can't get location.
                        // GPS or network is not enabled.
                        // Ask user to enable GPS/network in settings.
                        gps.showSettingsAlert();

                        latitude = gps.getLatitude();
                        longitude = gps.getLongitude();
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                    Toast.makeText(mcontext, "You need to grant permission", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
       Log.e("ONPAUSE_ENDGAME", "PAUSE THE ACTIVITY");

        return;
    }
}
