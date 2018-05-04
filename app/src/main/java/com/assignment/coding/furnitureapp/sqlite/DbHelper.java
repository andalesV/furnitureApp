package com.assignment.coding.furnitureapp.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.assignment.coding.furnitureapp.Utils.Utils.LOG_TAG;

/**
 * Created by victo on 03/05/2018.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASENAME = "Todolist";
    public static final int DATABASE_VERSION = 1;
    public static final String mID = "_id";
    public static final String mNAME = "mName";
    public static final String mDESCRIPTION = "mDescription";
    public static final String mLOCATION = "mLocation";
    public static final String mCOST = "mCost";
    public static final String mTABLE_NAME = "forniture";

    private static final String CREATE_TABLE_FORNITURE_APP = "CREATE TABLE " + mTABLE_NAME + "(" + mID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + mNAME + " VARCHAR(100) NOT NULL, " + mDESCRIPTION +
            " VARCHAR(255) NOT NULL, " + mLOCATION + " VARCHAR(255) NOT NULL, " + mCOST + " DECIMAL(10,2) NOT NULL);";


    private static final String DROP_TABLE_FORNITURE_APP = "DROP TABLE IF EXITS " + mTABLE_NAME;

    public DbHelper(Context context) {
        super(context, DATABASENAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(LOG_TAG, "DATABSE OnCREATE");
        db.execSQL(CREATE_TABLE_FORNITURE_APP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_FORNITURE_APP);
        onCreate(db);
    }
}
