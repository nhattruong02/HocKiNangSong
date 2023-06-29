package com.example.app_hoc_ki_nang_song.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.app_hoc_ki_nang_song.DTO.Baihoc;
import com.example.app_hoc_ki_nang_song.DTO.Cauhoi;
import com.example.app_hoc_ki_nang_song.DTO.Chude;
import com.example.app_hoc_ki_nang_song.DTO.Taikhoan;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "hockynangsong.db";
    private static final String TABLE_NAME = "Taikhoan";
    private static final String TABLE_NAME_1 = "Chude";
    private static final String TABLE_NAME_2 = "Baihoc";
    private static final String TABLE_NAME_3 ="Cauhoi";
    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +TABLE_NAME+ "(" +
                "mataikhoan INTEGER PRIMARY KEY AUTOINCREMENT," +
                "taikhoan TEXT, " +
                "matkhau TEXT," +
                "hoten TEXT," +
                "gioitinh TEXT,"+
                "namsinh DATE," +
                "sdt TEXT," +
                "loaitaikhoan TEXT)");
        db.execSQL("CREATE TABLE " +TABLE_NAME_1+ "(" +
                "machude INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenchude TEXT, " +
                "hinhchude BLOB)");
        db.execSQL("CREATE TABLE " + TABLE_NAME_2 + "(" +
                "mabaihoc INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenbaihoc TEXT, " +
                "mota TEXT ," +
                "noidung TEXT ," +
                "hinh BLOB ," +
                "video TEXT," +
                "machude INTEGER, FOREIGN KEY(machude) REFERENCES "+TABLE_NAME_1+" (machude) ON DELETE CASCADE )");
        db.execSQL("CREATE TABLE " + TABLE_NAME_3+ "(" +
                "macauhoi INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cauhoi TEXT, " +
                "dapanA TEXT," +
                "dapanB TEXT," +
                "dapanC TEXT," +
                "dapanD TEXT," +
                "dapandung TEXT)");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('1','Admin','1','Mai Nhật Trường','Nam','1','0865990502','Admin');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_2);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME_3);
    }
    public boolean ktDangky(String taikhoan){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " +TABLE_NAME+ " WHERE taikhoan='"+taikhoan+"'",null);
        if(cursor.getCount()!=0) {
            return true;
        }
        else{
            return false;
        }
    }
    public boolean Dangky(String taikhoan, String matkhau, String hoten, String gioitinh, String namsinh,String sdt,String loaitaikhoan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("taikhoan", taikhoan);
        contentValues.put("matkhau", matkhau);
        contentValues.put("hoten", hoten);
        contentValues.put("gioitinh", gioitinh);
        contentValues.put("namsinh", namsinh);
        contentValues.put("sdt", sdt);
        contentValues.put("loaitaikhoan", loaitaikhoan);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1){
            return true;
        }else{
            return false;
        }
    }
    public boolean doimatkhau(String taikhoan,String matkhaumoi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("matkhau",matkhaumoi);
        db.update(TABLE_NAME,contentValues,"taikhoan = '"+taikhoan+"'",null);
        return true;
    }
    public boolean ktdangnhap(String tk,String mk){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM "  +TABLE_NAME + " WHERE taikhoan='"+tk+"' AND matkhau='"+mk+"'",null);
        if(cursor.getCount() != 0) {
            return true;
        }
        else{
            return false;
        }
    }
    public boolean themchude(String tenchude,byte[] img){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenchude",tenchude);
        contentValues.put("hinhchude",img);
        long result = db.insert(TABLE_NAME_1,null,contentValues);
        if(result == -1){
            return true;
        }else{
            return false;
        }
    }
    public boolean suachude(int machude,String tenchude,byte[] hinhchude){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("machude",machude);
        contentValues.put("tenchude",tenchude);
        contentValues.put("hinhchude",hinhchude);
        db.update(TABLE_NAME_1,contentValues,"machude = '"+machude+"'",null);
        return true;
    }
    public Integer xoachude(int machude){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_1,"machude='"+machude+"'",null);
    }
    public List<Chude> getListChude(){
        List<Chude> chudeList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " +TABLE_NAME_1,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Chude chude = new Chude(cursor.getInt(0),cursor.getString(1),cursor.getBlob(2));
            chudeList.add(chude);
            cursor.moveToNext();
        }
        db.close();
        return chudeList;
    }
    public boolean themcauhoi(String cauhoi,String dapanA,String dapanB,String dapanC,String dapanD,String dapandung){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("cauhoi",cauhoi);
        contentValues.put("dapanA",dapanA);
        contentValues.put("dapanB",dapanB);
        contentValues.put("dapanC",dapanC);
        contentValues.put("dapanD",dapanD);
        contentValues.put("dapandung",dapandung);
        long result = db.insert(TABLE_NAME_3,null,contentValues);
        if(result == -1){
            return true;
        }else{
            return false;
        }
    }
    public boolean suacauhoi(int macauhoi,String cauhoi,String dapanA,String dapanB,String dapanC,String dapanD,String dapandung){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("macauhoi",macauhoi);
        contentValues.put("cauhoi",cauhoi);
        contentValues.put("dapanA",dapanA);
        contentValues.put("dapanB",dapanB);
        contentValues.put("dapanC",dapanC);
        contentValues.put("dapanD",dapanD);
        contentValues.put("dapandung",dapandung);
        db.update(TABLE_NAME_3,contentValues,"macauhoi = '"+macauhoi+"'",null);
        return true;
    }
    public Integer xoacauhoi(int macauhoi){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_3,"macauhoi='"+macauhoi+"'",null);
    }
    public List<Cauhoi> getListCauhoi(){
        List<Cauhoi> cauhoiList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " +TABLE_NAME_3,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Cauhoi cauhoi = new Cauhoi(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                    cursor.getString(3),cursor.getString(4),
                    cursor.getString(5),cursor.getString(6));
            cauhoiList.add(cauhoi);
            cursor.moveToNext();
        }
        db.close();
        return cauhoiList;
    }
    public boolean thembaihoc(String tenbaihoc,String mota,String noidung,byte[] hinh,String video,int machude){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenbaihoc",tenbaihoc);
        contentValues.put("mota",mota);
        contentValues.put("noidung",noidung);
        contentValues.put("hinh",hinh);
        contentValues.put("video",video);
        contentValues.put("machude",machude);
        long result = db.insert(TABLE_NAME_2,null,contentValues);
        if(result == -1){
            return true;
        }else{
            return false;
        }
    }
    public boolean suabaihoc(int mabaihoc,String tenbaihoc,String mota,String noidung,byte[] hinh,String video){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mabaihoc",mabaihoc);
        contentValues.put("tenbaihoc",tenbaihoc);
        contentValues.put("mota",mota);
        contentValues.put("noidung",noidung);
        contentValues.put("hinh",hinh);
        contentValues.put("video",video);
        db.update(TABLE_NAME_2,contentValues,"mabaihoc = '"+mabaihoc+"'",null);
        return true;
    }
    public Integer xoabaihoc(int mabaihoc){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_2,"mabaihoc='"+mabaihoc+"'",null);
    }
    public List<Baihoc> getListBaihoc(int machude){
        List<Baihoc> baihocList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " +TABLE_NAME_2 + " WHERE machude= '"+machude+"'",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Baihoc baihoc = new Baihoc(cursor.getInt(0),cursor.getString(1),cursor.getString(2)
                    ,cursor.getString(3),cursor.getBlob(4),cursor.getString(5),cursor.getInt(6));
            baihocList.add(baihoc);
            cursor.moveToNext();
        }
        db.close();
        return baihocList;
    }
}
