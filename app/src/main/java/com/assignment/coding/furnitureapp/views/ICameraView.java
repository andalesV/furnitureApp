package com.assignment.coding.furnitureapp.views;

/**
 * Created by victo on 03/05/2018.
 */

public interface ICameraView {
    public void initializeElements();
    public void createCameraPreview();
    public void startBackgroundThread();
    public void stopBackgroundThread();
    public void captureImage();
    public void openCamera();
    public void updatePreview();
    public void closeCamera();
}
