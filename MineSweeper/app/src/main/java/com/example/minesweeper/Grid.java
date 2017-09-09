package com.example.minesweeper;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

/**
 * Created by אלי on 18/08/2017.
 */

public class Grid extends GridView {


    public Grid(Context context , AttributeSet atttrs)
    {
        super(context,atttrs);
        setNumColumns(Logic.width);
        setAdapter(new GridAdapter());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public class GridAdapter extends BaseAdapter{




        @Override
        public int getCount() {
            return Logic.getInstance().width * Logic.getInstance().height;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int pos, View view, ViewGroup viewGroup) {
            return Logic.getInstance().getCellPos(pos);
        }
    }
}
