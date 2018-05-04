package com.assignment.coding.furnitureapp.profile;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.assignment.coding.furnitureapp.models.Items;
import com.assignment.coding.furnitureapp.sqlite.DbAdapter;
import com.assignment.coding.furnitureapp.views.IProfileView;

import static com.assignment.coding.furnitureapp.Utils.Utils.LOG_TAG;

/**
 * Created by victo on 03/05/2018.
 */

public class ProfilePresenter {

    private IProfileView iProfileView;
    private Context mContext;
    private DbAdapter mDbAdapter;


    public ProfilePresenter(IProfileView profileView, Context context) {
        iProfileView = profileView;
        mContext = context;
        mDbAdapter = new DbAdapter(mContext);
    }

    public void initializeElements() {
        iProfileView.initializeElements();
    }

    public void display() {
        iProfileView.display();
    }

    public void create() {
        iProfileView.create();

        Items items = new Items();
        items.setName(iProfileView.getNameEdtTxt());
        items.setDescription(iProfileView.getDescriptionEdtTxt());
        items.setLocation(iProfileView.getLocationEdtTxt());
        items.setCost(iProfileView.getCostEdtTxt());

        long result = mDbAdapter.saveItem(items);
    }

    public void edit() {
        iProfileView.edit();
    }

    public void redirectToItemListPage() {
        iProfileView.redirectToItemListPage();
    }

    public void openDbConnection() {
        mDbAdapter.open();
    }

    public void closeDbConnection() {
        mDbAdapter.close();
    }

}
