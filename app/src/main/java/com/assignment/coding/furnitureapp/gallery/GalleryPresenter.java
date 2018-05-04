package com.assignment.coding.furnitureapp.gallery;

import com.assignment.coding.furnitureapp.views.IGalleryView;

/**
 * Created by victo on 03/05/2018.
 */

public class GalleryPresenter {

    private IGalleryView iGalleryView;

    public GalleryPresenter(IGalleryView iGalleryView) {
        this.iGalleryView = iGalleryView;
    }

    public void initializeProfileFragment() {
        iGalleryView.initializeProfileFragment();
    }
}