package com.coolweather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by codingBoy on 16/5/2.
 */
public class CoolWeatherOpenHelper extends SQLiteOpenHelper
{
    public static final String CREATE_PROVINCE="create table if not exists Province ("
            +"_id integer primary key autoincrement, "
            +"province_name text, "
            +"province_code text) ";
    public static final String CREATE_CITY="create table if not exists City ("
            +"_id integer primary key autoincrement, "
            +"city_name text, "
            +"city_code text, "
            +"province_id integer) ";
    public static final String CREATE_COUNTY="create table if not exists County ("
            +"_id integer primary key autoincrement, "
            +"county_name text, "
            +"county_code text, "
            +"city_id integer)";

    public CoolWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROVINCE);
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_COUNTY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
