package com.example.minesweeper;

import android.content.Context;
import android.view.View;

/**
 * Created by אלי on 18/08/2017.
 */

public abstract  class BaseCell extends View {


    private  int value;
    private  boolean isBoom;
    private  boolean isRevealed;
    private  boolean isClicked;
    private  boolean isFlagClick;

    private int x;
    private int y;
    private  int pos;


    public BaseCell(Context context) {
        super(context);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        isBoom = false;
        isFlagClick =false;
        isRevealed =false;
        isClicked =false;

        if(value == -1)
        {

            isBoom =true;
        }
        this.value = value;
    }

    public boolean isBoom() {
        return isBoom;
    }

    public void setBoom(boolean boom) {
        isBoom = boom;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed() {
        isRevealed = true;
        invalidate();
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked() {
        isClicked = true;
        isRevealed = true;

        invalidate();
    }

    public boolean isFlagClick() {
        return isFlagClick;
    }

    public void setFlagClick(boolean flagClick) {
        isFlagClick = flagClick;
    }

    public int getXPos() {
        return x;
    }



    public int getYPos() {
        return y;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.pos = y * Logic.width + x;
        invalidate();
    }

}
