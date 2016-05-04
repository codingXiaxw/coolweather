package com.coolweather.app.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.coolweather.app.db.CoolWeatherOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by codingBoy on 16/5/2.
 */
public class CoolWeatherDB
{
    public static final String DB_NAME="cool_weather";//数据库名
    public static final int VERSION=1;//数据库版本
    private static CoolWeatherDB coolWeatherDB;
    private SQLiteDatabase db;
    //将构造方法进行私有化
    private CoolWeatherDB(Context context)
    {
        CoolWeatherOpenHelper dbHelper=new CoolWeatherOpenHelper(context,DB_NAME,null,VERSION);
        db=dbHelper.getWritableDatabase();
    }
    //获取CoolWeatherDB的实例
    public synchronized static CoolWeatherDB getInstance(Context context)
    {
        if(coolWeatherDB==null)
        {
            coolWeatherDB=new CoolWeatherDB(context);
        }
        return coolWeatherDB;
    }

    //将Province实例存储到数据库
    public void saveProvince(Province province)
    {
        if(province!=null)
        {
            ContentValues value=new ContentValues();
            value.put("province_name",province.getProvinceName());
            value.put("province_code",province.getProvinceCode());
            db.insert("Province",null,value);
        }
    }
    //从数据库中读取全国所有的省份信息
    public List<Province> loadProvinces()
    {
        List<Province> list=new ArrayList<Province>();
        Cursor cursor=db.query("Province",null,null,null,null,null,null);
        if(cursor.moveToFirst())
        {
            do{
                Province province=new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            }while (cursor.moveToNext());
        }
        if(cursor!=null)
        {
            cursor.close();
        }
        return list;
    }
    //将City实例存储到数据库
    public void saveCity(City city)
    {
        if(city!=null)
        {
            ContentValues value=new ContentValues();
            value.put("city_name",city.getCityName());
            value.put("city_code", city.getCityCode());
            value.put("province_id",city.getProvinceId());
            db.insert("City", null, value);
        }
    }
    //从数据库读取某省下所有的城市信息
    public List<City> loadCities(int provinceId)
    {
        List<City> list=new ArrayList<City>();
        Cursor cursor=db.query("City",null,"province_id=?",new String[]{String.valueOf(provinceId)},null,null,null);
        if(cursor.moveToFirst())
        {
            do{
                City city=new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                list.add(city);
            }while (cursor.moveToNext());
        }
        if(cursor!=null)
        {
            cursor.close();
        }
        return list;
    }
    //将county实例存储到数据库
    public void saveCounty(County county)
    {
        if (county!=null)
        {
            ContentValues values=new ContentValues();
            values.put("county_name",county.getCountyName());
            values.put("county_code",county.getCountyCode());
            values.put("city_id",county.getCityId());
            db.insert("County", null, values);
        }
    }
    //从数据库读取某城市下所有的显信息
    public List<County> loadCounties(int cityId)
    {
        List<County> list=new ArrayList<County>();
        Cursor cursor=db.query("County",null,"city_id=?",new String[]{String.valueOf(cityId)},null,null,null);
        if(cursor.moveToFirst())
        {
            do{
                County county=new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCityId(cityId);
                list.add(county);
            }while (cursor.moveToNext());
        }
        if(cursor!=null)
        {
            cursor.close();
        }
        return list;
    }


}














