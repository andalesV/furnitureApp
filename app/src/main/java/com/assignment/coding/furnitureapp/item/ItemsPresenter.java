package com.assignment.coding.furnitureapp.item;

import android.content.Context;
import android.util.Log;

import com.assignment.coding.furnitureapp.models.Items;
import com.assignment.coding.furnitureapp.sqlite.DbAdapter;
import com.assignment.coding.furnitureapp.views.IItemsViews;

import java.util.List;

/**
 * Created by victo on 04/05/2018.
 */

public class ItemsPresenter {

    private IItemsViews mIItemsViews;
    private Context mContext;
    private DbAdapter mDbAdapter;

    public ItemsPresenter(IItemsViews iItemsViews, Context context) {
        mIItemsViews = iItemsViews;
        mContext = context;
        mDbAdapter = new DbAdapter(mContext);
    }

    public void initializeElements() {
        mIItemsViews.initializeElements();
    }

    public void displayItemList() {
        mIItemsViews.displayItemList();
    }

    public void query() {
        List<Items> itemsList = mDbAdapter.getItemList();
        mIItemsViews.setItemList(itemsList);
    }

    public void openDbConnection() {
        mDbAdapter.open();
    }

    public void closeDbConnection() {
        mDbAdapter.close();
    }
}
