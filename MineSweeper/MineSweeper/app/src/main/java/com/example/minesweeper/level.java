package com.example.minesweeper;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class level extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        Intent intent = getIntent();



        final Button play =(Button) findViewById(R.id.easy);
        play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent intent = new Intent(level.this,GamePlay.class);
                intent.putExtra("width", 10);
                intent.putExtra("height", 10);
                intent.putExtra("numberOfMines", 5);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });


        final Button play2 =(Button) findViewById(R.id.mid);
        play2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                Intent intent = new Intent(level.this,GamePlay.class);
                intent.putExtra("width", 10);
                intent.putExtra("height", 10);
                intent.putExtra("numberOfMines", 10);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });


        final Button play3 =(Button) findViewById(R.id.Hard);
        play3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent intent = new Intent(level.this,GamePlay.class);
                intent.putExtra("width", 5);
                intent.putExtra("height", 5);
                intent.putExtra("numberOfMines", 10);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });


    }



}
