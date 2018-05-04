package com.assignment.coding.furnitureapp.views;

import com.assignment.coding.furnitureapp.models.Items;

import java.util.List;

/**
 * Created by victo on 04/05/2018.
 */

public interface IItemsViews {
    public void initializeElements();

    public void setItemList(List<Items> itemsList);

    public void displayItemList();
}
