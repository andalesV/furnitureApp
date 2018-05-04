package com.assignment.coding.furnitureapp.item.detail;

import com.assignment.coding.furnitureapp.views.IItemDetailView;

/**
 * Created by victo on 04/05/2018.
 */

public class ItemDetailPresenter {
    private IItemDetailView mIITemDetailView;

    public ItemDetailPresenter(IItemDetailView iItemDetailView){
        mIITemDetailView = iItemDetailView;
    }

    public void initializeFragment(){
        mIITemDetailView.initializeFragment();
    }
}
