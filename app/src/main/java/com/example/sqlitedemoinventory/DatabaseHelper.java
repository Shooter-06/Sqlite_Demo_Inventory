package com.example.sqlitedemoinventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Inventory.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "product";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "BRAND";
    public static final String COL_4 = "COST";
    public static final String COL_5 = "QTY";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table product (id integer primary key autoincrement, name varchar, brand varchar, cost number, qty number)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists product");
        onCreate(db);

    }

    public boolean addItems (String name, String brand, String cost, String qty) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("brand", brand);
        contentValues.put("cost", cost);
        contentValues.put("qty", qty);
        db.insert("product", null, contentValues);
        db.close();
        return true;

    }

    public boolean updateItems (String id,String name, String brand, String cost, String qty ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("brand", brand);
        contentValues.put("cost", cost);
        contentValues.put("qty", qty);
        db.update(TABLE_NAME, contentValues, " ID = ? ", new String[] {id});
        return true;

    }
    //it returns the rows to be deleted which is an integer value
    public Integer deleteproduct (String id)  {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, " ID = ? ", new String[] {id});
    }

    public Cursor viewallProduct() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(" select * from " + TABLE_NAME, null);
        return  res;
    }
}
