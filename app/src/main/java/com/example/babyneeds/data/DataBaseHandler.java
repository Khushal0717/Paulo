package com.example.babyneeds.data;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;

import androidx.annotation.Nullable;


import com.example.babyneeds.model.Item;
import com.example.babyneeds.util.Constants;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {
    private final Context context;

    public DataBaseHandler(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_BABY_TABLE = "CREATE TABLE " + Constants.DATABASE_TABLE + "("
                + Constants.KEY_ID + " INTEGER PRIMARY KEY,"
                + Constants.KEY_ITEM_NAME +" TEXT,"
                + Constants.KEY_QUANTITY + " INTEGER,"
                + Constants.KEY_COLOR + " TEXT,"
                + Constants.KEY_SIZE + " INTEGER);";
//                + Constants.KEY_DATE_NAME + " LONG);";

        db.execSQL(CREATE_BABY_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.DATABASE_TABLE);

        onCreate(sqLiteDatabase);
    }

    //Add Item
    public void AddItem(Item item){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_ITEM_NAME,item.getItemName());
        values.put(Constants.KEY_QUANTITY,item.getItemQty());
        values.put(Constants.KEY_COLOR,item.getItemColor());
        values.put(Constants.KEY_SIZE,item.getItemSize());
 //       values.put(Constants.KEY_DATE_NAME,java.lang.System.currentTimeMillis());//gives the time

        db.insert(Constants.DATABASE_TABLE,null, values);

        Log.d("DBHandler", "AddItem: " + "item added");

    }

    public Item getItem(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Constants.DATABASE_TABLE,
                new String[]{Constants.KEY_ID,
                        Constants.KEY_ITEM_NAME,
                        Constants.KEY_QUANTITY,
                        Constants.KEY_COLOR,
                        Constants.KEY_SIZE,
                      /*  Constants.KEY_DATE_NAME*/},
                Constants.KEY_ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        Item item = new Item();

        if(cursor.moveToFirst()){
            item.setId(Integer.parseInt(cursor.getString(0)));
            item.setItemName(cursor.getString(1));
            item.setItemQty(Integer.parseInt(cursor.getString(2)));
            item.setItemColor(cursor.getString(3));
            item.setItemSize(Integer.parseInt(cursor.getString(4)));

//            DateFormat dateFormat = DateFormat.getDateInstance();
  //          String formattedDate = dateFormat.format(new Date(cursor.getColumnIndex(Constants.KEY_DATE_NAME)));

    //        item.setDataItemAdded(formattedDate);

        }
        return item;
    }

    public List<Item> getAllItem(){

        SQLiteDatabase db = this.getReadableDatabase();
        List<Item> allItemList = new ArrayList<>();
        Cursor cursor = db.query(Constants.DATABASE_TABLE,
                new String[]{Constants.KEY_ID,
                        Constants.KEY_ITEM_NAME,
                        Constants.KEY_QUANTITY,
                        Constants.KEY_COLOR,
                        Constants.KEY_SIZE},
                      /*  Constants.KEY_DATE_NAME*/
                null,null,null,null,Constants.KEY_DATE_NAME + "DESC" );

        if(cursor.moveToFirst()){
            do {
                Item item = new Item();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setItemName(cursor.getString(1));
                item.setItemQty(Integer.parseInt(cursor.getString(2)));
                item.setItemColor(cursor.getString(3));
                item.setItemSize(Integer.parseInt(cursor.getString(4)));

        //        DateFormat dateFormat = DateFormat.getDateInstance();
          //      String formattedDate = dateFormat.format(new Date(cursor.getColumnIndex(Constants.KEY_DATE_NAME)));

            //      item.setDataItemAdded(formattedDate);

                allItemList.add(item);
            }while(cursor.moveToNext());
        }

        return allItemList;
    }

    public int updateItem(Item item){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_ITEM_NAME,item.getItemName());
        values.put(Constants.KEY_QUANTITY,item.getItemQty());
        values.put(Constants.KEY_COLOR,item.getItemColor());
        values.put(Constants.KEY_SIZE,item.getItemSize());

        return db.update(Constants.DATABASE_TABLE,values,Constants.KEY_ID + "=?",new String[item.getId()]);

    }

    public void deleteItem(Item item){

        SQLiteDatabase db = getReadableDatabase();

        db.delete(Constants.DATABASE_TABLE,Constants.KEY_ID + "=?", new String[item.getId()]);
        db.close();
    }

    public int getItemsCount(){

        SQLiteDatabase db = getReadableDatabase();

        String countQuery = "SELECT * FROM " + Constants.DATABASE_TABLE;

        Cursor cursor = db.rawQuery(countQuery,null);

        return cursor.getCount();
    }
}
