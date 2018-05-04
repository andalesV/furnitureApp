package com.assignment.coding.furnitureapp.item;

import android.content.Context;

import com.assignment.coding.furnitureapp.models.Items;
import com.assignment.coding.furnitureapp.sqlite.DbAdapter;
import com.assignment.coding.furnitureapp.views.IItemsFragmentViews;

import java.util.List;

/**
 * Created by victo on 04/05/2018.
 */

public class ItemsFragmentPresenter {

    private IItemsFragmentViews mIItemsFragmentViews;
    private Context mContext;
    private DbAdapter mDbAdapter;

    public ItemsFragmentPresenter(IItemsFragmentViews iItemsFragmentViews, Context context) {
        mIItemsFragmentViews = iItemsFragmentViews;
        mContext = context;
        mDbAdapter = new DbAdapter(mContext);
    }

    public void displayItemList() {
        mIItemsFragmentViews.displayItemList();
    }

    public void query() {
        List<Items> itemsList = mDbAdapter.getItemList();
        mIItemsFragmentViews.setItemList(itemsList);
    }

    public void openDbConnection() {
        mDbAdapter.open();
    }

    public void closeDbConnection() {
        mDbAdapter.close();
    }
}
