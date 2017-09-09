package com.example.minesweeper;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.Button;

public class MineSweeperMainActivity extends Activity {
public static DataBaseHelper  myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_sweeper_main);

        myDb = new DataBaseHelper(this); //creating the db and the table
        
        final Button play =(Button) findViewById(R.id.play_button);
        play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(MineSweeperMainActivity.this,level.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        final Button records =(Button) findViewById(R.id.records_button);
        records.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(MineSweeperMainActivity.this,High_score.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });



        final Button exit =(Button) findViewById(R.id.exit_bt);
        exit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            //System.exit(0);

                finish();
                moveTaskToBack(true);
            }
        });

    }




}
