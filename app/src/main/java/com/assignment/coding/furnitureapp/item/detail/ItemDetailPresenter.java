package com.assignment.coding.furnitureapp.item.detail;

import com.assignment.coding.furnitureapp.models.Items;
import com.assignment.coding.furnitureapp.sqlite.DbAdapter;
import com.assignment.coding.furnitureapp.views.IItemDetailView;

/**
 * Created by victo on 04/05/2018.
 */

public class ItemDetailPresenter {
    private IItemDetailView mIItemDetailView;

    private DbAdapter mDbAdapter;

    public ItemDetailPresenter(IItemDetailView iItemDetailView) {
        mIItemDetailView = iItemDetailView;
        mDbAdapter = new DbAdapter(iItemDetailView.getAppContext());
    }

    public void initializeElements() {
        mIItemDetailView.initializeElements();
    }

    public void getItem() {
        Items items = mIItemDetailView.getItem();
        mIItemDetailView.setItem(items);
    }

    public void displayItem() {
        mIItemDetailView.displayItem();
    }


    public int update() {
        Items items = new Items();
        items.setId(mIItemDetailView.getMId());
        items.setName(mIItemDetailView.getNameEdtTxt());
        items.setDescription(mIItemDetailView.getDescriptionEdtTxt());
        items.setLocation(mIItemDetailView.getLocationEdtTxt());
        items.setCost(mIItemDetailView.getCostEdtTxt());

        return mDbAdapter.update(items);
    }

    public int delete() {
        return mDbAdapter.delete(mIItemDetailView.getMId());
    }

    public void redirectToItemListPage() {
        mIItemDetailView.redirectToItemListPage();
    }

    public void openDbConnection() {
        mDbAdapter.open();
    }

    public void closeDbConnection() {
        mDbAdapter.close();
    }

}