package com.example.minesweeper;

import android.util.Log;

/**
 * Created by אלי on 18/08/2017.
 */

public class Print {


    public static  void printM (final int [][] grid , final int width,final int height)
    {
        String printedText;
        for(int x = 0 ; x < width ; x++) {
            printedText = "| ";
            for (int y = 0; y < height; y++){
                printedText += String.valueOf(grid[x][y]).replace("-1","b")+" | ";
            }
            Log.e("",printedText);
        }




    }
}
