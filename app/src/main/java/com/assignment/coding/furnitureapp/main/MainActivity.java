package com.assignment.coding.furnitureapp.main;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.assignment.coding.furnitureapp.R;
import com.assignment.coding.furnitureapp.camera.CameraActivity;
import com.assignment.coding.furnitureapp.views.IMainView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static com.assignment.coding.furnitureapp.Utils.Utils.LOG_TAG;
import static com.assignment.coding.furnitureapp.Utils.Utils.getOutputMediaFile;

public class MainActivity extends AppCompatActivity implements IMainView {

    @BindView(R.id.toolbar)
    public Toolbar mToolBar;
    private MainPresenter mainPresenter;

    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenter(this);
        mainPresenter.initializeElements();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.initializeMainFragment();
    }


    @Override
    public void initializeElements() {
        ButterKnife.bind(this);
        setSupportActionBar(mToolBar);
    }

    @Override
    public void initializeMainFragment() {
        if (flag) {
            Intent intent = getIntent();
            String location = intent.getExtras().getString("path");
            Log.i(LOG_TAG, location);

        } else {
            MainFragment mainFragment = new MainFragment();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, mainFragment)
                    .commit();
        }

    }


}
