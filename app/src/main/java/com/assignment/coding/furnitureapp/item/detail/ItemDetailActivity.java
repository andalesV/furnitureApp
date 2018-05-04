package com.assignment.coding.furnitureapp.item.detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.assignment.coding.furnitureapp.R;
import com.assignment.coding.furnitureapp.item.ItemsActivity;
import com.assignment.coding.furnitureapp.models.Items;
import com.assignment.coding.furnitureapp.views.IItemDetailView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemDetailActivity extends AppCompatActivity implements IItemDetailView{

    @BindView(R.id.toolbar)
    public Toolbar mToolbar;

    private ItemDetailPresenter mItemDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        ButterKnife.bind(this);
        mItemDetailPresenter = new ItemDetailPresenter(this);
        setSupportActionBar(mToolbar);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mItemDetailPresenter.initializeFragment();
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(this, ItemsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        return true;
    }

    @Override
    public void initializeFragment() {
        Intent intent = getIntent();
        Items items = (Items) intent.getExtras().get("item");

        Bundle bundle = new Bundle();

        bundle.putSerializable("item", items);


        ItemDetailFragmentFragment imItemDetailFragment = new ItemDetailFragmentFragment();
        imItemDetailFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.item_detail_container, imItemDetailFragment)
                .commit();
    }
}