package com.example.moneyfrog.logindemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by moneyfrog on 23/6/16.
 */
public class Dbhelper extends SQLiteOpenHelper {

    private static final int DB_VERSION= 1;
    private static final String DB_NAME="data.db";
    private static final String TABLE_NAME="data";
    private static final String COLOUMN_ID="id";
    private static final String COLOUMN_NAME="name";
    private static final String COLOUMN_UNAME="uname";
    private static final String COLOUMN_PASS="pass";
    private static final String COLOUMN_ADD="address";
    private static final String COLOUMN_EMAIL="email";
    SQLiteDatabase sqLiteDatabase;
    String u ,p;

    private static final String CREATE_TABLE="create table data (id integer primary key not null," +
    "name text not null, uname text not null, pass text not null, address text not null, email text not null);";

    public Dbhelper(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);

    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE);
        this.sqLiteDatabase=sqLiteDatabase;
    }

    public void insertConnections(Connections c)
    {
        sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        String query ="select * from data";
        Cursor cursor= sqLiteDatabase.rawQuery(query,null);

        int count=cursor.getCount();
        contentValues.put(COLOUMN_ID,count);
        contentValues.put(COLOUMN_NAME,c.getName());
        contentValues.put(COLOUMN_EMAIL,c.getEmail());
        contentValues.put(COLOUMN_UNAME,c.getUname());
        contentValues.put(COLOUMN_PASS,c.getPass());
        contentValues.put(COLOUMN_ADD,c.getAdd());
        contentValues.put(COLOUMN_EMAIL,c.getEmail());
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();
        cursor.close();
    }

    public String searchPass(String uname)
    {
        sqLiteDatabase=this.getReadableDatabase();
        String query="select uname, pass from "+TABLE_NAME;
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
        p="not Found";
        if(cursor.moveToFirst()){

            do{
                u=cursor.getString(0);
                p=cursor.getString(1);
                if (u.equals(uname)){
                    p=cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return p;

    }








    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String query="DROP TABLE IF EXISTS"+TABLE_NAME;
        sqLiteDatabase.execSQL(query);
        this.onCreate(sqLiteDatabase);

    }
}
