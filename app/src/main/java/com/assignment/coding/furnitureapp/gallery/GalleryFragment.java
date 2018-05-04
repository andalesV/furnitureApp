package com.assignment.coding.furnitureapp.gallery;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.coding.furnitureapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by victo on 03/05/2018.
 */

public class GalleryFragment extends Fragment {

    private View view;

    @BindView(R.id.mRecyclerView)
    public RecyclerView mRecyclerView;

    private StaggeredGridLayoutManager sglm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gallery, container, false);
        ButterKnife.bind(this, view);

        setRecyclerViewLayoutManager();
        return view;
    }


    private void setRecyclerViewLayoutManager() {

        GalleryAdapter adapter = new GalleryAdapter(getAllShownImagesPath(getActivity()), getActivity());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setHasFixedSize(false);

        int columnCount = getResources().getInteger(R.integer.list_column_count);
        sglm = new StaggeredGridLayoutManager(columnCount, LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(sglm);
    }


    private ArrayList<String> getAllShownImagesPath(Activity activity) {
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;
        ArrayList<String> listOfAllImages = new ArrayList<String>();
        String absolutePathOfImage = null;
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        cursor = activity.getContentResolver().query(uri, projection, null,
                null, null);

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);

            listOfAllImages.add(absolutePathOfImage);
        }
        return listOfAllImages;
    }
}