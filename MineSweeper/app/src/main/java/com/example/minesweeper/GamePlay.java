package com.example.minesweeper;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.UiThread;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class GamePlay extends Activity {
    public TextView numberOfMinesView;
    public static int counter = 0;
    public static boolean flag = false;
    private Thread t;
    private static Handler handler = new Handler();
    private static Runnable runnable;
    public static int j = 0;
    public  int i = 0;
    public static Activity activity;
    private static GifImageView bomb;
    private static GifImageView winner;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        activity = this;
        Intent mIntent = getIntent();
        int width = mIntent.getIntExtra("width", 0);
        int height = mIntent.getIntExtra("height", 0);
        int numberOfMines = mIntent.getIntExtra("numberOfMines", 0);

        Logic.getInstance().CreateGrid(this, width, height, numberOfMines);
        setContentView(R.layout.activity_game_play);

        numberOfMinesView = (TextView) findViewById(R.id.NumOfFlagsText);
        numberOfMinesView.setText("" + numberOfMines);
        bomb = (GifImageView) findViewById(R.id.bombExploded);
        bomb.setVisibility(GifImageView.INVISIBLE);
        winner = (GifImageView) findViewById(R.id.winner);
        winner.setVisibility(GifImageView.INVISIBLE);
        tickOnMainThreadForever();




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

    }


    @Override
    protected void onPause() {
        super.onPause();
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



}



