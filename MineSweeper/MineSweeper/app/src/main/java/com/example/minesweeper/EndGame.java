package com.example.minesweeper;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent mIntent = getIntent();
        String result = mIntent.getStringExtra("result");
        int timer = mIntent.getIntExtra("timer", 0);

        setContentView(R.layout.activity_end_game);


        TextView resulttext = (TextView)findViewById(R.id.Youwon_lose_Label);
        resulttext.setText(resulttext.getText() + "" + result);

        TextView timeText = (TextView) findViewById(R.id.timeLabel);
        if (result.equals("won")) {

            timeText.setText(timeText.getText() + "" + timer);
        }
        else
            timeText.setText("");




        final Button okbt  =(Button) findViewById(R.id.okBT);
        okbt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(EndGame.this,MineSweeperMainActivity.class);
                startActivity(intent);

            }
        });

    }
}
