package com.assignment.coding.furnitureapp.item.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.assignment.coding.furnitureapp.R;
import com.assignment.coding.furnitureapp.item.ItemsActivity;
import com.assignment.coding.furnitureapp.models.Items;
import com.assignment.coding.furnitureapp.views.IItemDetailView;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by victo on 04/05/2018.
 */

public class ItemDetailFragment extends Fragment implements IItemDetailView, View.OnClickListener {

    private View view;

    public ImageView mImageView;

    public EditText mNameEdt;

    public EditText mDescriptionEdt;

    public EditText mLocationEdt;

    public EditText mCostEdt;

    private Items mItems;

    private Button mUpdateBtn;

    private Button mDeleteBtn;

    private ItemDetailPresenter mItemDetailPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail_item, container, false);
        mItemDetailPresenter = new ItemDetailPresenter(this);
        mItemDetailPresenter.initializeElements();

        mItemDetailPresenter.getItem();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mItemDetailPresenter.displayItem();
    }

    @Override
    public void onResume() {
        super.onResume();
        mItemDetailPresenter.openDbConnection();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mItemDetailPresenter.closeDbConnection();
    }

    @Override
    public void initializeElements() {
        mImageView = (ImageView) view.findViewById(R.id.imageView);
        mNameEdt = (EditText) view.findViewById(R.id.nameEdt);
        mDescriptionEdt = (EditText) view.findViewById(R.id.descriptionEdt);
        mLocationEdt = (EditText) view.findViewById(R.id.locationEdt);
        mCostEdt = (EditText) view.findViewById(R.id.costEdt);

        mUpdateBtn = (Button) view.findViewById(R.id.updateBtn);
        mDeleteBtn = (Button) view.findViewById(R.id.deleteBtn);

        mUpdateBtn.setOnClickListener(this);
        mDeleteBtn.setOnClickListener(this);
    }

    @Override
    public void displayItem() {
        Glide.with(getActivity()).load(mItems.getLocation())
                .placeholder(R.mipmap.ic_blur_on).centerCrop()
                .into(mImageView);

        mNameEdt.setText(mItems.getName());
        mDescriptionEdt.setText(mItems.getDescription());
        mLocationEdt.setText(mItems.getLocation());
        mCostEdt.setText(String.valueOf(mItems.getCost()));
    }

    @Override
    public void setItem(Items items) {
        mItems = items;
    }

    @Override
    public Items getItem() {
        return (Items) getArguments().get("item");
    }

    @Override
    public Context getAppContext() {
        return getActivity();
    }

    @Override
    public int getMId() {
        return mItems.getId();
    }

    @Override
    public String getNameEdtTxt() {
        return mNameEdt.getText().toString();
    }

    @Override
    public String getDescriptionEdtTxt() {
        return mDescriptionEdt.getText().toString();
    }

    @Override
    public String getLocationEdtTxt() {
        return mLocationEdt.getText().toString();
    }

    @Override
    public Double getCostEdtTxt() {
        return Double.parseDouble(mCostEdt.getText().toString());
    }

    @Override
    public void redirectToItemListPage() {
        Intent intent = new Intent(getActivity(), ItemsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        int result = 0;

        switch (v.getId()) {
            case R.id.updateBtn:
                result = mItemDetailPresenter.update();
                if (result > 0)
                    Toast.makeText(getActivity(), "Successful Update Transaction!", Toast.LENGTH_LONG).show();
                break;
            case R.id.deleteBtn:
                result = mItemDetailPresenter.delete();
                if (result > 0)
                    mItemDetailPresenter.redirectToItemListPage();
                break;
        }
    }
}