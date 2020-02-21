package com.example.note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Dao {

    private final DatabaseHelper mHelper;

    public Dao(Context context){

        mHelper = new DatabaseHelper(context);
        //创建数据库
    }

    public void insert(Constants constants){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        String sql = "insert into " + Constants.TABLE_NAME + " (_id,_content,_isimportant) values(?,?,?)";
        Log.d("删除操作", "删除成功");
        db.execSQL(sql, new Object[]{constants.getId(), constants.getContent(),constants.getIsimportant()});
        db.close();
    }

    //提供插入id、内容、重要标签的接口
    public void insert(int id, String content, String isimportant){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        String sql = "insert into " + Constants.TABLE_NAME + " (_id,_content,_isimportant) values(?,?,?)";
        Log.d("删除操作", "删除成功");
        db.execSQL(sql, new Object[]{id, content,isimportant});
        db.close();
    }

    public void delete(int id){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        String sql = "delete from " + Constants.TABLE_NAME + " where _id = " + id;
        db.execSQL(sql);
        db.close();
    }

    //提供更新内容的接口
    public void updatecontent(Constants constants){
        SQLiteDatabase db = mHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("_content", constants.getContent());
        db.update(Constants.TABLE_NAME, values, null, null);
        db.close();

    }

    public List<Constants> querycontent(){
        SQLiteDatabase db = mHelper.getWritableDatabase();

        List<Constants> list = new ArrayList<Constants>();
        Cursor cursor = db.query(Constants.TABLE_NAME, null, null, null, null, null,"_id" + " desc");

        if(cursor != null){
            while (cursor.moveToNext()){
                Constants constants = new Constants();
                constants.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                constants.setContent(cursor.getString(cursor.getColumnIndex("_content")));
                constants.setIsimportant(cursor.getString(cursor.getColumnIndex("_isimportant")));
                list.add(constants);
            }
        }

        assert cursor != null;
        cursor.close();
        db.close();
        return list;
    }

}
