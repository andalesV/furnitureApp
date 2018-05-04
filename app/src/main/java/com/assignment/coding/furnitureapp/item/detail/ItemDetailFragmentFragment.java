package com.assignment.coding.furnitureapp.item.detail;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import com.assignment.coding.furnitureapp.views.IItemDetailFragmentView;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by victo on 04/05/2018.
 */

public class ItemDetailFragmentFragment extends Fragment implements IItemDetailFragmentView, View.OnClickListener {

    private View view;

    @BindView(R.id.imageView)
    public ImageView mImageView;

    @BindView(R.id.nameEdt)
    public EditText mNameEdt;

    @BindView(R.id.descriptionEdt)
    public EditText mDescriptionEdt;

    @BindView(R.id.locationEdt)
    public EditText mLocationEdt;

    @BindView(R.id.costEdt)
    public EditText mCostEdt;

    private Items mItems;

    @BindView(R.id.updateBtn)
    public Button mUpdateBtn;

    @BindView(R.id.deleteBtn)
    public Button mDeleteBtn;

    private ItemDetailFragmentPresenter mItemDetailFragmentPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail_item, container, false);
        mItemDetailFragmentPresenter = new ItemDetailFragmentPresenter(this);
        ButterKnife.bind(this, view);

        mUpdateBtn.setOnClickListener(this);
        mDeleteBtn.setOnClickListener(this);

        mItemDetailFragmentPresenter.getItem();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mItemDetailFragmentPresenter.displayItem();
    }

    @Override
    public void onResume() {
        super.onResume();
        mItemDetailFragmentPresenter.openDbConnection();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mItemDetailFragmentPresenter.closeDbConnection();
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
    public String getCostEdtTxt() {
        return mCostEdt.getText().toString();
    }

    @Override
    public void redirectToItemListPage() {
        Intent intent = new Intent(getActivity(), ItemsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void update() {
        int result = 0;
        result = mItemDetailFragmentPresenter.update();
        if (result > 0)
            Toast.makeText(getActivity(), "Successful Update Transaction!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showAlertDialog() {
        DialogFragment dialogFragment = new com.assignment.coding.furnitureapp.dialog.AlertDialog();
        dialogFragment.show(getActivity().getSupportFragmentManager(), "");
    }

    @Override
    public void showDeleteAlertDialog() {
        AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
        builder.setMessage("Do you want to delete this item?")
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mItemDetailFragmentPresenter.execute();
                    }
                }).setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
        });
        builder.create().show();
    }

    @Override
    public void delete() {
        int result = 0;
        result = mItemDetailFragmentPresenter.delete();
        if (result > 0)
            mItemDetailFragmentPresenter.redirectToItemListPage();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.updateBtn:
                mItemDetailFragmentPresenter.isEmpty();
                break;
            case R.id.deleteBtn:
                mItemDetailFragmentPresenter.showDeleteAlertDialog();
                break;
        }
    }
}