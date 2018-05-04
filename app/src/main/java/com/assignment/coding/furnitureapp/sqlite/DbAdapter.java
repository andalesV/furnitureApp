package com.assignment.coding.furnitureapp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.assignment.coding.furnitureapp.models.Items;

import java.util.ArrayList;
import java.util.List;

import static com.assignment.coding.furnitureapp.Utils.Utils.LOG_TAG;

/**
 * Created by victo on 03/05/2018.
 */

public class DbAdapter {
    private Context mContext;
    private DbHelper mDbHelper;
    private SQLiteDatabase sql;

    public DbAdapter(Context context) {
        mContext = context;
        mDbHelper = new DbHelper(context);
    }

    public DbAdapter open() {
        sql = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public long saveItem(Items items) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(mDbHelper.mNAME, items.getName());
        contentValues.put(mDbHelper.mDESCRIPTION, items.getDescription());
        contentValues.put(mDbHelper.mLOCATION, items.getLocation());
        contentValues.put(mDbHelper.mCOST, items.getCost());

        return sql.insert(mDbHelper.mTABLE_NAME, null, contentValues);
    }

    public List<Items> getItemList() {
        Cursor cursor = null;
        String[] columns = new String[]{mDbHelper.mID, mDbHelper.mNAME, mDbHelper.mDESCRIPTION, mDbHelper.mLOCATION, mDbHelper.mCOST};
        try {
            cursor = sql.query(mDbHelper.mTABLE_NAME, columns, null, null, null, null, null);
            List<Items> itemsList = new ArrayList<>();
            while (cursor.moveToNext()) {
                Items items = new Items();

                int index1 = cursor.getColumnIndex(mDbHelper.mID);
                int index2 = cursor.getColumnIndex(mDbHelper.mNAME);
                int index3 = cursor.getColumnIndex(mDbHelper.mDESCRIPTION);
                int index4 = cursor.getColumnIndex(mDbHelper.mLOCATION);
                int index5 = cursor.getColumnIndex(mDbHelper.mCOST);

                items.setId(cursor.getInt(index1));
                items.setName(cursor.getString(index2));
                items.setDescription(cursor.getString(index3));
                items.setLocation(cursor.getString(index4));
                items.setCost(cursor.getDouble(index5));
                itemsList.add(items);
            }

            return itemsList;
        } catch (NullPointerException e) {
            Log.e(LOG_TAG, e.getMessage());
        }
        return null;
    }

    public int delete(int id) {
        int count = sql.delete(mDbHelper.mTABLE_NAME, mDbHelper.mID + " = " + id, null);
        return count;
    }


    public int update(Items items) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(mDbHelper.mNAME, items.getName());
        contentValues.put(mDbHelper.mDESCRIPTION, items.getDescription());
        contentValues.put(mDbHelper.mLOCATION, items.getLocation());
        contentValues.put(mDbHelper.mCOST, items.getCost());

        return sql.update(mDbHelper.mTABLE_NAME, contentValues, mDbHelper.mID + " = " + items.getId(), null);
    }
}