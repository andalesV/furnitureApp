package com.assignment.coding.furnitureapp.item;

import com.assignment.coding.furnitureapp.views.IItemsView;

/**
 * Created by victo on 04/05/2018.
 */

public class ItemsPresenter {
    private IItemsView mIItemsView;

    public ItemsPresenter(IItemsView iItemsView){
        mIItemsView = iItemsView;
    }

    public void initializeFragment(){
        mIItemsView.initializeFragment();
    }
}
