package com.assignment.coding.furnitureapp.views;

import com.assignment.coding.furnitureapp.models.Items;

/**
 * Created by victo on 03/05/2018.
 */

public interface IProfileView {
    public void initializeElements();

    public void display();

    public void create();

    public void edit();

    public String getNameEdtTxt();

    public String getDescriptionEdtTxt();

    public String getLocationEdtTxt();

    public Double getCostEdtTxt();

    public void redirectToItemListPage();
}
