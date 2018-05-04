package com.assignment.coding.furnitureapp.item.detail;

import android.util.Log;

import com.assignment.coding.furnitureapp.models.Items;
import com.assignment.coding.furnitureapp.sqlite.DbAdapter;
import com.assignment.coding.furnitureapp.views.IItemDetailFragmentView;

/**
 * Created by victo on 04/05/2018.
 */

public class ItemDetailFragmentPresenter {
    private IItemDetailFragmentView mIItemDetailFragmentView;

    private DbAdapter mDbAdapter;

    public ItemDetailFragmentPresenter(IItemDetailFragmentView iItemDetailFragmentView) {
        mIItemDetailFragmentView = iItemDetailFragmentView;
        mDbAdapter = new DbAdapter(iItemDetailFragmentView.getAppContext());
    }

    public void getItem() {
        Items items = mIItemDetailFragmentView.getItem();
        mIItemDetailFragmentView.setItem(items);
    }

    public void displayItem() {
        mIItemDetailFragmentView.displayItem();
    }


    public int update() {
        Items items = new Items();
        items.setId(mIItemDetailFragmentView.getMId());
        items.setName(mIItemDetailFragmentView.getNameEdtTxt());
        items.setDescription(mIItemDetailFragmentView.getDescriptionEdtTxt());
        items.setLocation(mIItemDetailFragmentView.getLocationEdtTxt());
        items.setCost(Double.parseDouble(mIItemDetailFragmentView.getCostEdtTxt()));

        return mDbAdapter.update(items);
    }

    public int delete() {
        return mDbAdapter.delete(mIItemDetailFragmentView.getMId());
    }

    public void redirectToItemListPage() {
        mIItemDetailFragmentView.redirectToItemListPage();
    }

    public void openDbConnection() {
        mDbAdapter.open();
    }

    public void closeDbConnection() {
        mDbAdapter.close();
    }

    public void isEmpty() {
        if (mIItemDetailFragmentView.getNameEdtTxt().isEmpty() || mIItemDetailFragmentView.getDescriptionEdtTxt().isEmpty() ||
                mIItemDetailFragmentView.getLocationEdtTxt().isEmpty() || mIItemDetailFragmentView.getCostEdtTxt().toString().isEmpty())
            mIItemDetailFragmentView.showAlertDialog();
        else
            mIItemDetailFragmentView.update();
    }

    public void showDeleteAlertDialog(){
        mIItemDetailFragmentView.showDeleteAlertDialog();
    }

    public void execute(){
        mIItemDetailFragmentView.delete();
    }
}