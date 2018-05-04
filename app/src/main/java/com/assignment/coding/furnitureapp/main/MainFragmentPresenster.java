package com.assignment.coding.furnitureapp.main;

import com.assignment.coding.furnitureapp.views.IMainFragmentView;

/**
 * Created by victo on 04/05/2018.
 */

public class MainFragmentPresenster {

    private IMainFragmentView mIMainFragmentView;

    public MainFragmentPresenster(IMainFragmentView iMainFragmentView){
        mIMainFragmentView = iMainFragmentView;
    }

    public void capture(){
        mIMainFragmentView.capture();
    }

    public void openGallery(){
        mIMainFragmentView.openGallery();
    }

    public void redirectToItemListPage(){
        mIMainFragmentView.redirectToItemListPage();
    }
}
