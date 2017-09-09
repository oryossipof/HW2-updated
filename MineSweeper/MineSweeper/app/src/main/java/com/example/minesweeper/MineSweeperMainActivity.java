package com.example.minesweeper;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MineSweeperMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_sweeper_main);


        final Button play =(Button) findViewById(R.id.play_button);
        play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(MineSweeperMainActivity.this,level.class);
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
