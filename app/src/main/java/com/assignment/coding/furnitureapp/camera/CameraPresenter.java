package com.assignment.coding.furnitureapp.camera;

import com.assignment.coding.furnitureapp.views.ICameraView;

/**
 * Created by victor.t.andales.iii on 5/2/2018.
 */

public class CameraPresenter {

    private ICameraView iCameraView;

    public CameraPresenter(ICameraView iCameraView){
        this.iCameraView = iCameraView;
    }

    public void initializeElements(){
        iCameraView.initializeElements();
    }

    public void createCameraPreview() { iCameraView.createCameraPreview();}

    public void startBackgroundThread(){ iCameraView.startBackgroundThread(); }

    public void stopBackgroundThread(){ iCameraView.stopBackgroundThread(); }

    public void captureImage() { iCameraView.captureImage();}

    public void openCamera() { iCameraView.openCamera();}

    public void updatePreview() { iCameraView.updatePreview();}

    public void closeCamera() { iCameraView.closeCamera();}
}