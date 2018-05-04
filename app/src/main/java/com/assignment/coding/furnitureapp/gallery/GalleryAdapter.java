package com.assignment.coding.furnitureapp.gallery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.coding.furnitureapp.R;
import com.assignment.coding.furnitureapp.main.MainActivity;
import com.assignment.coding.furnitureapp.models.Data;
import com.assignment.coding.furnitureapp.profile.ProfileActivity;
import com.assignment.coding.furnitureapp.profile.ProfileFragment;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static com.assignment.coding.furnitureapp.R.id.itemImgView;
import static com.assignment.coding.furnitureapp.Utils.Utils.LOG_TAG;
import static com.assignment.coding.furnitureapp.Utils.Utils.getOutputMediaFile;
import static com.assignment.coding.furnitureapp.Utils.Utils.saveFromGallery;

/**
 * Created by victo on 03/05/2018.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private List<String> mImagePaths = new ArrayList<>();
    private Activity mActivity;
    private Data mData;

    public GalleryAdapter(ArrayList<String> imagePaths, Activity activity) {
        mImagePaths = imagePaths;
        mActivity = activity;
        mData = new Data();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView itemImgView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            itemImgView = (ImageView) itemView.findViewById(R.id.itemImgView);
        }

        @Override
        public void onClick(View v) {

            mData.setLocation(mImagePaths.get(getPosition()));
            mData.setSource("gallery");
            Log.i(LOG_TAG, mImagePaths.get(getPosition()));
            Intent intent = new Intent(mActivity, ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("data", mData);
            mActivity.startActivity(intent);

        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutAdapter = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_gallery, null);
        layoutAdapter.setFocusable(true);
        return new ViewHolder(layoutAdapter);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(mActivity).load(mImagePaths.get(position))
                .placeholder(R.mipmap.ic_blur_on).centerCrop()
                .into(holder.itemImgView);
    }

    @Override
    public int getItemCount() {
        return mImagePaths.size();
    }
}