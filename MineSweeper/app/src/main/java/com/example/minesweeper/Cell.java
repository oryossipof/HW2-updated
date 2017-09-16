package com.example.minesweeper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import java.io.InputStream;

import static android.content.ContentValues.TAG;

/**
 * Created by אלי on 18/08/2017.
 */

public class Cell extends  BaseCell implements View.OnClickListener , View.OnLongClickListener {
    private Movie movie;
    private InputStream input;



    @Override
    public boolean onLongClick(View view) {
        Logic.getInstance().flag(getXPos(),getYPos());

        return true;
    }



    @Override
    public void onClick(View view) {

        Logic.getInstance().click(getXPos(),getYPos());

    }


    public  Cell(Context context, int x , int y)
{
    super(context);
    setPos(x,y);
    setOnClickListener(this);
    setOnLongClickListener(this);
}

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        drawButton(canvas);

        if (isFlagClick())
        {
            drawFlag(canvas);
        }else if (isRevealed() && isBoom() && !isClicked())
        {
            drawNormalBomb(canvas);
        }
        else
        {
            if(isClicked())
            {
                if(getValue() == -1)
                {
                    drawExplodedBomb(canvas);

                }
                else
                {
                    putNumber(canvas);
                }
            }
            else
                drawButton(canvas);
        }
    }


    private void drawFlag( Canvas canvas)
    {

        Drawable draw = ContextCompat.getDrawable(getContext(),R.drawable.flag);
        draw.setBounds(0,0,getWidth(),getHeight());
        draw.draw(canvas);


    }

    private void drawExplodedBomb(Canvas canvas) {

        Drawable draw = ContextCompat.getDrawable(getContext(),R.drawable.boom);
        draw.setBounds(0,0,getWidth(),getHeight());
        draw.draw(canvas);

    }


    private void drawButton(Canvas canvas) {


        Drawable draw = ContextCompat.getDrawable(getContext(),R.drawable.gamebt);
                    draw.setBounds(0,0,getWidth(),getHeight());
        draw.draw(canvas);

    }

    private void drawNormalBomb(Canvas canvas) {

        Drawable draw = ContextCompat.getDrawable(getContext(),R.drawable.bomb);
        draw.setBounds(0,0,getWidth(),getHeight());
        draw.draw(canvas);

    }

    private void putNumber(Canvas canvas)
    {
        Drawable draw = null;

        switch(getValue())
        {
            case 0:
                 draw = ContextCompat.getDrawable(getContext(),R.drawable.number_0);

                break;

            case 1:
                draw = ContextCompat.getDrawable(getContext(),R.drawable.number_1);

                break;

            case 2:
                draw = ContextCompat.getDrawable(getContext(),R.drawable.number_2);

                break;

            case 3:
                draw = ContextCompat.getDrawable(getContext(),R.drawable.number_3);

                break;

            case 4:
                draw = ContextCompat.getDrawable(getContext(),R.drawable.number_4);

                break;

            case 5:
                draw = ContextCompat.getDrawable(getContext(),R.drawable.number_5);

                break;

            case 6:
                draw = ContextCompat.getDrawable(getContext(),R.drawable.number_6);

                break;

            case 7:
                draw = ContextCompat.getDrawable(getContext(),R.drawable.number_7);

                break;

            case 8:
                draw = ContextCompat.getDrawable(getContext(),R.drawable.number_8);
                break;


        }

        draw.setBounds(0,0,getWidth(),getHeight());
        draw.draw(canvas);

    }


}
