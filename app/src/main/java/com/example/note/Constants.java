package com.example.note;

import java.io.Serializable;
import java.util.List;

public class Constants implements Serializable {
    public static final String DATABASE_NAME = "note.db";
    public static final int VERSION_CODE = 1;
    public static final String TABLE_NAME = "Text_Content";

    private int mid;
    private String mcontent;
    private String mIsimportant;


    public Constants(){

    }

    public Constants(int id, String content, String Isimportant){
        mid = id;
        mcontent = content;
        mIsimportant = Isimportant;
    }

    public int getId(){
        return mid;
    }

    public String getContent(){
        return mcontent;
    }

    public String getIsimportant(){
        return mIsimportant;
    }

    public void setId(int id){
        this.mid = id;
    }

    public void setContent(String content){
        this.mcontent = content;
    }

    public void setIsimportant(String Isimportant){
        this.mIsimportant = Isimportant;
    }

}
