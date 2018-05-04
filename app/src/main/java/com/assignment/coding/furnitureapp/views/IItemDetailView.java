package com.assignment.coding.furnitureapp.views;

import android.content.Context;

import com.assignment.coding.furnitureapp.models.Items;

/**
 * Created by victo on 04/05/2018.
 */

public interface IItemDetailView {
    public void initializeElements();

    public void displayItem();

    public void setItem(Items items);

    public Items getItem();

    public Context getAppContext();

    public int getMId();

    public String getNameEdtTxt();

    public String getDescriptionEdtTxt();

    public String getLocationEdtTxt();

    public Double getCostEdtTxt();

    public void redirectToItemListPage();

}
