package com.codekiller.bankingsystem.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDataBase extends SQLiteOpenHelper {
    public static final String TAG = "DATABASE";

    public static final String DBNAME = "BANKING";
    public static final String TABLE_NAME = "usertable";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String PHNO = "ph_no";
    public static final String INITAMT = "initial_amount";
    public static final String MAILID = "mail_id";
    public static final String ACC_NO = "account_number";

    public MyDataBase(Context context){
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table = "CREATE TABLE " + TABLE_NAME +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME + " TEXT," +
                    AGE + " TEXT," +
                    PHNO + " TEXT," +
                    INITAMT + " INTEGER," +
                    MAILID + " TEXT," +
                    ACC_NO + " TEXT UNIQUE" +
                ")";
        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean add(String name, String age, String phno, String initamt, String mailid, String acc_no){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(AGE, age);
        values.put(PHNO, phno);
        values.put(INITAMT, Integer.parseInt(initamt));
        values.put(MAILID, mailid);
        values.put(ACC_NO, acc_no);
        try {
            db.insert(TABLE_NAME, null, values);
        } catch (Exception e) {
            Log.e(TAG, "Error while adding record", e);
            return false;
        }
        return true;
    }

    public boolean updateAcc(String name, String age, String phno, String initamt, String mailid, String acc_no){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " +
                       NAME + "=?, " +
                       AGE + "=?, " +
                       PHNO + "=?, " +
                       INITAMT + "=?, " +
                       MAILID + "=? " +
                       "WHERE " + ACC_NO + "=?";
        try {
            db.execSQL(query, new String[]{name, age, phno, initamt, mailid, acc_no});
        } catch (Exception e) {
            Log.e(TAG, "Error while updating record", e);
            return false;
        }
        return true;
    }

    public Cursor getDetails(String accno){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {NAME, AGE, PHNO, INITAMT, MAILID, ACC_NO};
        String selection = ACC_NO + "=?";
        String[] selectionArgs = {accno};
        return db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
    }

    public String getAmount(String accno){
        SQLiteDatabase db = this.getReadableDatabase();
        String amount = null;
        Cursor c = null;
        try {
            c = db.rawQuery("SELECT " + INITAMT + " FROM " + TABLE_NAME + " WHERE " + ACC_NO + "=?", new String[]{accno});
            if (c.moveToFirst()) {
                amount = c.getString(c.getColumnIndex(INITAMT));
            }
        } catch (Exception e) {
            Log.e(TAG, "Error while fetching amount", e);
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return amount;
    }

    public boolean putAmount(String accno, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + INITAMT + "=? WHERE " + ACC_NO + "=?";
        try {
            db.execSQL(query, new String[]{amount, accno});
        } catch (Exception e) {
            Log.e(TAG, "Error while updating amount", e);
            return false;
        }
        return true;
    }
}
