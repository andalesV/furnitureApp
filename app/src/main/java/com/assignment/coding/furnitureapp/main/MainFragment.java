package com.assignment.coding.furnitureapp.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.assignment.coding.furnitureapp.R;
import com.assignment.coding.furnitureapp.camera.CameraActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static com.assignment.coding.furnitureapp.Utils.Utils.getOutputMediaFile;

/**
 * Created by victo on 03/05/2018.
 */

public class MainFragment extends Fragment implements View.OnClickListener{

    private View view;

    private Context context;

    private int PICK_IMAGE_REQUEST = 1;

    @BindView(R.id.captureImgView)
    public ImageView captureImgView;

    @BindView(R.id.galleryImgView)
    public ImageView galleryImgView;

    @BindView(R.id.listItemImgView)
    public ImageView listItemImgView;

    @BindView(R.id.settingsImgView)
    public ImageView settingsImgView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this,view);
        captureImgView.setOnClickListener(this);
        galleryImgView.setOnClickListener(this);
        listItemImgView.setOnClickListener(this);
        settingsImgView.setOnClickListener(this);

        context = getActivity();

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);

                final File file = getOutputMediaFile(MEDIA_TYPE_IMAGE, context);

//                ImageView imageView = (ImageView) findViewById(R.id.imageView);
//                imageView.setImageBitmap(bitmap);

                save(bitmap,file);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void capture(){
        Intent intent = new Intent(context, CameraActivity.class);
        startActivity(intent);
        Toast.makeText(context,"test",Toast.LENGTH_LONG).show();
    }

    public void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.selectPic)), PICK_IMAGE_REQUEST);
    }


    private void save(Bitmap finalBitmap, File file) throws IOException {
        OutputStream output = null;
        try {
            output = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, output);

        } finally {
            if (null != output) {
                output.flush();
                output.close();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.captureImgView:
                capture();
                break;
            case R.id.galleryImgView:
                openGallery();
                break;
            case R.id.listItemImgView:
                break;
            case R.id.settingsImgView:
                break;
        }
    }
}
