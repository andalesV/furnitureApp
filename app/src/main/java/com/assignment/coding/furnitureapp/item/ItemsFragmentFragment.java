package com.assignment.coding.furnitureapp.item;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.coding.furnitureapp.R;
import com.assignment.coding.furnitureapp.models.Items;
import com.assignment.coding.furnitureapp.views.IItemsFragmentViews;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by victo on 03/05/2018.
 */

public class ItemsFragmentFragment extends Fragment implements IItemsFragmentViews {

    private Context context;
    private View view;
    private ItemsFragmentPresenter itemsPresenter;
    private List<Items> mItemsList;
    private StaggeredGridLayoutManager sglm;

    @BindView(R.id.mRecyclerView)
    public RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_items, container, false);
        ButterKnife.bind(this, view);
        context = getActivity();
        itemsPresenter = new ItemsFragmentPresenter(this, context);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        itemsPresenter.openDbConnection();
        itemsPresenter.query();
        itemsPresenter.displayItemList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        itemsPresenter.closeDbConnection();
    }

    @Override
    public void setItemList(List<Items> itemsList) {
        mItemsList = itemsList;
    }

    @Override
    public void displayItemList() {
        ItemsAdapter adapter = new ItemsAdapter(mItemsList, getActivity());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setHasFixedSize(false);

        int columnCount = getResources().getInteger(R.integer.list_column_count);
        sglm = new StaggeredGridLayoutManager(columnCount, LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(sglm);
    }
}