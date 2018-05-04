package com.assignment.coding.furnitureapp.gallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.assignment.coding.furnitureapp.R;
import com.assignment.coding.furnitureapp.views.IGalleryView;

public class GalleryActivity extends AppCompatActivity implements IGalleryView {

    private GalleryPresenter mGalleryPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        mGalleryPresenter = new GalleryPresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGalleryPresenter.initializeProfileFragment();
    }

    @Override
    public void initializeProfileFragment() {
        GalleryFragment galleryFragment = new GalleryFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.gallery_container, galleryFragment)
                .commit();
    }
}