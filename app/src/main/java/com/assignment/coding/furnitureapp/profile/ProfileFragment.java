package com.assignment.coding.furnitureapp.profile;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.assignment.coding.furnitureapp.R;
import com.assignment.coding.furnitureapp.item.ItemsActivity;
import com.assignment.coding.furnitureapp.models.Data;
import com.assignment.coding.furnitureapp.models.Items;
import com.assignment.coding.furnitureapp.views.IProfileView;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static com.assignment.coding.furnitureapp.Utils.Utils.LOG_TAG;
import static com.assignment.coding.furnitureapp.Utils.Utils.getOutputMediaFile;
import static com.assignment.coding.furnitureapp.Utils.Utils.saveFromGallery;

/**
 * Created by victor.t.andales.iii on 5/2/2018.
 */

public class ProfileFragment extends Fragment implements IProfileView {

    private View view;
    private Data data;
    private Context context;

    private EditText mNameEdt;
    private EditText mDescriptionEdt;
    private EditText mLocationEdt;
    private EditText mCostEdt;
    private Button mSaveBtn;

    private Items mItems;
    private ImageView imageView2;

    private ProfilePresenter profilePresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        data = (Data) getArguments().get("data");
        context = getActivity();

        profilePresenter = new ProfilePresenter(this, context);

        profilePresenter.initializeElements();

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profilePresenter.create();
            }
        });

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        profilePresenter.display();
    }

    @Override
    public void onResume() {
        super.onResume();
        profilePresenter.openDbConnection();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        profilePresenter.closeDbConnection();
    }

    @Override
    public void initializeElements() {
        imageView2 = (ImageView) view.findViewById(R.id.imageView);
        mCostEdt = (EditText) view.findViewById(R.id.costEdt);
        mDescriptionEdt = (EditText) view.findViewById(R.id.descriptionEdt);
        mLocationEdt = (EditText) view.findViewById(R.id.locationEdt);
        mNameEdt = (EditText) view.findViewById(R.id.nameEdt);
        mSaveBtn = (Button) view.findViewById(R.id.saveBtn);
    }

    @Override
    public void display() {
        Glide.with(context).load(data.getLocation())
                .placeholder(R.mipmap.ic_blur_on).centerCrop()
                .into(imageView2);
        mLocationEdt.setText(data.getLocation());
    }

    @Override
    public void create() {
        new SaveToExternalFiles().execute();
    }

    @Override
    public void edit() {

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

    private class SaveToExternalFiles extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                if (data.getSource().equals("gallery")) {
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), Uri.parse("file://" + data.getLocation()));
                        final File file = getOutputMediaFile(MEDIA_TYPE_IMAGE, getActivity());
                        saveFromGallery(bitmap, file);
                    } catch (IOException e) {
                        Log.e(LOG_TAG, e.getMessage());
                    }
                }

            } catch (NullPointerException e) {
                Log.e(LOG_TAG, e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            profilePresenter.redirectToItemListPage();
        }
    }

}