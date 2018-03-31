package com.rsc.annaparrish.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ToDoListDB.db";
    public static final String TABLE_ITEM = "ITEM";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TODO = "ToDo";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int
            version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    //changed here a little, I like to use the clause "if not exists" as a fail-safe
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_ITEM + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TODO + " TEXT " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
        onCreate(db);

    }

    public void addItem(Item item) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TODO, item.getToDo());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_ITEM, null, values);
        db.close();

    }

    public int deleteItem(String Item) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = COLUMN_TODO + " = \"" + Item + "\"";
        int deleteResult;
        try {
            deleteResult = db.delete(TABLE_ITEM, whereClause, null);
        } catch (Exception e) {
            Log.e("TODOLIST", "delete problem");
            deleteResult = 0;
        }
        return deleteResult;
    }


    //delete table
   /* public int deleteAll(String tableItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("ToDoListDB.db", null, 1);
        String selectQuery = "DROP TABLE IF EXISTS " + TABLE_ITEM + " ";
    }*/





    public String databaseToString(){

        StringBuilder stringBuilder = new StringBuilder();

        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();


        String query = "SELECT * FROM " + TABLE_ITEM;


        Cursor recordSet = db.rawQuery(query, null);


        if (recordSet != null && recordSet.moveToFirst()){
            do{

                stringBuilder.append(recordSet.getString(recordSet.getColumnIndex("ToDo")));
                stringBuilder.append("\n");

            }while(recordSet.moveToNext());
        }

        dbString = stringBuilder.toString();

/*
        while (!recordSet.isAfterLast()){
            if (recordSet.getString(recordSet.getColumnIndex("ToDO")) !=null);{
                dbString += recordSet.getString(recordSet.getColumnIndex("ToDo"));
                dbString += "\n";
            }
            recordSet.moveToNext();
        }

        */

        db.close();
        return  dbString;
    }
}