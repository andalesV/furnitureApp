package com.assignment.coding.furnitureapp.item;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.assignment.coding.furnitureapp.R;
import com.assignment.coding.furnitureapp.gallery.GalleryAdapter;
import com.assignment.coding.furnitureapp.item.detail.ItemDetailActivity;
import com.assignment.coding.furnitureapp.models.Items;
import com.bumptech.glide.Glide;

import java.util.List;

import static com.assignment.coding.furnitureapp.Utils.Utils.LOG_TAG;

/**
 * Created by victo on 03/05/2018.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private List<Items> mItemList;
    private Context mContext;

    public ItemsAdapter(List<Items> itemList, Activity activity) {
        mItemList = itemList;
        mContext = activity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView itemsImgView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemsImgView = (ImageView) itemView.findViewById(R.id.itemsImgView);

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, ItemDetailActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("item", mItemList.get(getPosition()));
            mContext.startActivity(intent);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutAdapter = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_items, null);
        layoutAdapter.setFocusable(true);
        return new ViewHolder(layoutAdapter);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(mContext).load(mItemList.get(position).getLocation())
                .placeholder(R.mipmap.ic_blur_on).centerCrop()
                .into(holder.itemsImgView);

        Log.i(LOG_TAG, mItemList.get(position).getName());
        Log.i(LOG_TAG, mItemList.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

}
