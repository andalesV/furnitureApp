package com.assignment.coding.furnitureapp.item.detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.assignment.coding.furnitureapp.R;
import com.assignment.coding.furnitureapp.item.ItemsActivity;
import com.assignment.coding.furnitureapp.item.ItemsFragment;
import com.assignment.coding.furnitureapp.main.MainActivity;
import com.assignment.coding.furnitureapp.models.Items;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    public Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        initializeFragment();
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(this, ItemsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        return true;
    }

    private void initializeFragment() {
        Intent intent = getIntent();
        Items items = (Items) intent.getExtras().get("item");

        Bundle bundle = new Bundle();

        bundle.putSerializable("item", items);


        ItemDetailFragment imItemDetailFragment = new ItemDetailFragment();
        imItemDetailFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.item_detail_container, imItemDetailFragment)
                .commit();
    }
}
