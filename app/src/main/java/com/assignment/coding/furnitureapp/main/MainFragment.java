package com.assignment.coding.furnitureapp.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.assignment.coding.furnitureapp.R;
import com.assignment.coding.furnitureapp.camera.CameraActivity;
import com.assignment.coding.furnitureapp.gallery.GalleryActivity;
import com.assignment.coding.furnitureapp.item.ItemsActivity;
import com.assignment.coding.furnitureapp.views.IMainFragmentView;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static com.assignment.coding.furnitureapp.Utils.Utils.LOG_TAG;
import static com.assignment.coding.furnitureapp.Utils.Utils.getOutputMediaFile;

/**
 * Created by victo on 03/05/2018.
 */

public class MainFragment extends Fragment implements IMainFragmentView, View.OnClickListener {

    private View view;

    @BindView(R.id.captureImgView)
    public ImageView captureImgView;

    @BindView(R.id.galleryImgView)
    public ImageView galleryImgView;

    @BindView(R.id.listItemImgView)
    public ImageView listItemImgView;

    @BindView(R.id.settingsImgView)
    public ImageView settingsImgView;

    public MainFragmentPresenster mMainFragmentPresenster;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        captureImgView.setOnClickListener(this);
        galleryImgView.setOnClickListener(this);
        listItemImgView.setOnClickListener(this);
        settingsImgView.setOnClickListener(this);

        mMainFragmentPresenster = new MainFragmentPresenster(this);

        return view;
    }

    @Override
    public void capture() {
        Intent intent = new Intent(getActivity(), CameraActivity.class);
        startActivity(intent);
    }

    @Override
    public void openGallery() {
        Intent intent = new Intent(getActivity(), GalleryActivity.class);
        startActivity(intent);
    }

    @Override
    public void redirectToItemListPage() {
        Intent intent = new Intent(getActivity(), ItemsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.captureImgView:
                mMainFragmentPresenster.capture();
                break;
            case R.id.galleryImgView:
                mMainFragmentPresenster.openGallery();
                break;
            case R.id.listItemImgView:
                mMainFragmentPresenster.redirectToItemListPage();
                break;
            case R.id.settingsImgView:
                break;
        }
    }
}
