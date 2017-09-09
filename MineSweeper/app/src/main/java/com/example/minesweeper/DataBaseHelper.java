package com.example.minesweeper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Time;

/**
 * Created by or yossipof on 05/09/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper{
public static final String DB_NAME = "mineSweeper.db";
    public  static final String TABLE_NAME = "mineSweeprTable";
    public  static final String USERS_COL = "user_name";
    public  static final String LNG_COL = "lng";
    public  static final String LAT_COL = "lat";
    public  static final String TIME_COL  = "time";


    public DataBaseHelper(Context context) {
        super(context, DB_NAME , null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (user_name varchar(20), lng TEXT , lat TEXT, time int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,int time, String latitue, String longitude)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERS_COL,name);
        contentValues.put(TIME_COL,time);
        contentValues.put(LNG_COL, longitude);
        contentValues.put(LAT_COL, latitue);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1)
            return false;
        return true;

    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " ORDER BY time LIMIT 10", null);
        return res;
    }

}
