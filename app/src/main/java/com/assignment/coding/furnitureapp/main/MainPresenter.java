package com.assignment.coding.furnitureapp.main;

import com.assignment.coding.furnitureapp.views.IMainView;

/**
 * Created by victor on 01/05/2018.
 */

public class MainPresenter {

    private IMainView iMainView;

    public MainPresenter(IMainView iMainView){
        this.iMainView = iMainView;
    }

    public void initializeElements(){
        iMainView.initializeElements();
    }

    public void initializeMainFragment() { iMainView.initializeMainFragment();}
}
