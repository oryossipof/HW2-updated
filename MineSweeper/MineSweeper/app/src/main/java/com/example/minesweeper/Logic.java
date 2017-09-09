package com.example.minesweeper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.id.message;

/**
 * Created by אלי on 18/08/2017.
 */

public class Logic {
    private static Logic instance;
    private int numberOfFlags =5;
    public static int width = 10;
    public static int height = 10;
    public static int numberOfMins = 5;

    private Cell[][] mineSweep = new Cell[width][height];


    private Context context;


    public static Logic getInstance() {


        if (instance == null) {

            instance = new Logic();

        }

        return instance;
    }

    private Logic() {

    }


    public void CreateGrid(Context context, int width, int height, int mines) {
        this.context = context;
        this.width = width;
        this.height = height;
        this.numberOfMins = mines;
        this.numberOfFlags = mines;


        int[][] generatorGrid = GridGenerator.generate(numberOfMins, width, height);
        Print.printM(generatorGrid, width, height);
        setGrid(context, generatorGrid);

    }

    private void setGrid(Context context, final int[][] grid) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                if (mineSweep[x][y] == null) {
                    mineSweep[x][y] = new Cell(context, x,y);
                }
                mineSweep[x][y].setValue(grid[x][y]);
                mineSweep[x][y].invalidate();

            }
        }
    }

    public View getCellPos(int pos) {

        int x = pos % width;
        int y = pos / width;
        return mineSweep[x][y];
    }


    public Cell getCellPos(int x, int y) {
        return mineSweep[x][y];
    }


    public void flag(int xPos, int yPos) {
        Log.e("Flagged", "Now");
       if( getCellPos(xPos, yPos).isRevealed()) return;
        boolean isFlagged = getCellPos(xPos, yPos).isFlagClick();


        getCellPos(xPos, yPos).setFlagClick(!isFlagged);
        getCellPos(xPos, yPos).invalidate();

    }


    public void click(int x, int y) {

        if (x >= 0 && y >= 0 && x < width && y < height && !getCellPos(x, y).isClicked()) {
            getCellPos(x, y).setClicked();

            //check the neighbores

            if (getCellPos(x, y).getValue() == 0) {
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {

                        if (i != j) {
                            click(x + i, y + j);
                        }
                    }
                }


            }

            if (getCellPos(x, y).isBoom()) {
                endGame();
            }

        }

        checkEnd();
    }

    private boolean checkEnd() {
        int bombNotFound = numberOfMins;
        int notReaveled = (width * height);
        for (int i = 0; i < width; i++) {


            for (int j = 0; j < height; j++) {
                if (getCellPos(i, j).isRevealed() || getCellPos(i, j).isFlagClick())

                {
                    notReaveled--;
                }

                if (getCellPos(i, j).isFlagClick() && getCellPos(i, j).isBoom()) {
                    bombNotFound--;
                }

            }
        }
        if (bombNotFound == 0 && notReaveled == 0) {

            Toast.makeText(context, "Game Won", Toast.LENGTH_SHORT).show();


            Intent intent = new Intent(context,EndGame.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.putExtra("result", "won");
            intent.putExtra("timer", GamePlay.j);


            context.startActivity(intent);

        }
        return false;
    }

    private void endGame() {
        //handle lost game
        Toast.makeText(context, "Game Lost", Toast.LENGTH_SHORT).show();




        Intent intent = new Intent(context,EndGame.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.putExtra("result", "Lost");

        context.startActivity(intent);

        for (int i = 0; i < width; i++) {


            for (int j = 0; j < height; j++) {

                getCellPos(i,j).setRevealed();

            }
        }

    }
}



