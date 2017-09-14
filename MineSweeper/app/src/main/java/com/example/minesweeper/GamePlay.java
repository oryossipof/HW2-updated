package com.example.minesweeper;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.UiThread;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.games.Game;

import org.w3c.dom.Text;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class GamePlay extends Activity implements SensorEventListener {
    public TextView numberOfMinesView;
    private boolean goIn = true;
    public static int counter = 0;
    public static boolean flag = false;
    private Thread t;

    private static Handler handler = new Handler();
    private static Runnable runnable;
    public static int j = 0;
    private Long timeNow = 0l;
    private int warningCounter = 0;
    private boolean isFirstTime = false;
    private float firstTime = 0;
    public  int i = 0;
    private static final double THRESHOLD = 3;
    public static Activity activity;
    private static GifImageView bomb;
    private static GifImageView winner;
    private static Sensor accelerometer;
    private static SensorManager sm;
    HandlerThread sensorThread;
    private Handler sensorHandler;
    private SensorEventListener sl;
    private static SensorEventListener mlistener;
    private int numberOfMines;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        activity = this;
        Intent mIntent = getIntent();
        int width = mIntent.getIntExtra("width", 0);
        int height = mIntent.getIntExtra("height", 0);
        numberOfMines = mIntent.getIntExtra("numberOfMines", 0);

        Logic.getInstance().CreateGrid(this, width, height, numberOfMines);
        setContentView(R.layout.activity_game_play);

        numberOfMinesView = (TextView) findViewById(R.id.NumOfFlagsText);
        numberOfMinesView.setText("" + numberOfMines);
        bomb = (GifImageView) findViewById(R.id.bombExploded);
        bomb.setVisibility(GifImageView.INVISIBLE);
        winner = (GifImageView) findViewById(R.id.winner);
        winner.setVisibility(GifImageView.INVISIBLE);
        tickOnMainThreadForever();


        final Button records =(Button) findViewById(R.id.gamePlayHighBT);
        records.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(GamePlay.this,High_score.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });



        ////////////////////////////////////////////////
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer= sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

      //  sm.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        sensorThread = new HandlerThread(GamePlay.class.getSimpleName());
        sensorThread.start();
        sensorHandler = new Handler(sensorThread.getLooper());

        sm.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL,sensorHandler);
        mlistener = this;
       ////////////////////////




    }

    private void tickOnMainThreadForever() {

        runnable = new Runnable() {
            @Override
            public void run() {
                tick();
                handler.postDelayed(this, 1000);
            }
        };

        runnable.run();


    }


    private void tick() {

        TextView Timetext = (TextView) findViewById(R.id.TimeText);
        Timetext.setText("" + i);
        i++;
        j = i;
    }



    @Override
    protected void onStop() {
        super.onStop();
        i = 0 ;
        handler.removeCallbacks(runnable);
        sm.unregisterListener(mlistener,accelerometer);
        //accelerometer = null;
        //sensorThread.quit();
        Log.e("ONsTOPP" ,"13213213");

    }

    @Override
    protected void onDestroy()
    {

        super.onDestroy();
        sm.unregisterListener(this,accelerometer);
        goIn =false;
        sensorHandler.removeCallbacks(sensorThread);
        sm.unregisterListener(mlistener,accelerometer);

        /*sm.unregisterListener(this);
        sm = null;
        sensorThread.interrupt();
        accelerometer = null;*/

        Log.e("OnDESTROY" ,"13213213");
    }




    @Override
    protected void onResume() {
        super.onResume();
        Log.e("OnRESUME" ,"13213213");
        return;

    }


    @Override
    protected void onPause() {

       // sm.unregisterListener((SensorEventListener) this);//

        super.onPause();
        sm.unregisterListener(mlistener,accelerometer);
       /* sm.unregisterListener(this);
        Log.e("OnPAUSE" ,"13213213");

        accelerometer = null;*/

        return;
    }






    public static void showAlert(Activity activity, String message) {

        TextView title = new TextView(activity);
        title.setText("Title");
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(20);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        // builder.setTitle("Title");
        builder.setCustomTitle(title);
        // builder.setIcon(R.drawable.alert_36);

        builder.setMessage(message);


        builder.setCancelable(false);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();

            }




        });



        AlertDialog alert = builder.create();
        alert.show();
    }


    public static void setBombVisible() {

        bomb.setVisibility(GifImageView.VISIBLE);
    }

    public static void setWinnerVisible()
    {

        winner.setVisibility(GifImageView.VISIBLE);
    }

    public static void stopTimer()
    {
        handler.removeCallbacks(runnable);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //  if (goIn)
        //  Toast.makeText(GamePlay.this, "x: " + sensorEvent.values[0] + " y: " + sensorEvent.values[1]+ " z: " + sensorEvent.values[2], Toast.LENGTH_SHORT).show();

        float value = sensorEvent.values[1];

        if (!isFirstTime) {
            firstTime = value;
            isFirstTime = true;
        }

        if (value > firstTime + THRESHOLD || value < firstTime - THRESHOLD) {
            if (System.currentTimeMillis() - timeNow > 2500) {
                timeNow = System.currentTimeMillis();
                warningCounter++;
                Log.v("device moving too much", "device moving too much");
                if (warningCounter == 1)
                    Toast.makeText(this, "Stop Moving Your Phone... ", Toast.LENGTH_SHORT).show();

                else if (warningCounter == 2)
                {
                    Toast.makeText(this, "Last Warning!\n Stop Moving Your Phone... ", Toast.LENGTH_SHORT).show();
                }

                if (warningCounter == 3) {
                    Toast.makeText(this, "I Warned You!... Another Bomb Added!", Toast.LENGTH_SHORT).show();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                                //stuff that updates ui
                            int[][] mat = GridGenerator.addBombAndGenerate(1, Logic.width, Logic.height);
                            Logic.setGrid2(GamePlay.this, mat);
                            setBombNum();
                            Logic.numberOfMins++;
                        }
                    });

                    warningCounter = 0;
                }
            }


        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    public void setBombNum()
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                numberOfMines++;
                numberOfMinesView = (TextView) findViewById(R.id.NumOfFlagsText);
                numberOfMinesView.setText("" + numberOfMines);
            }
        });
    }

    public static void stopSensor() {
        sm.unregisterListener(mlistener,accelerometer);
    }
}



