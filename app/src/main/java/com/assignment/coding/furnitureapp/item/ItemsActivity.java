package com.assignment.coding.furnitureapp.item;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.assignment.coding.furnitureapp.R;
import com.assignment.coding.furnitureapp.main.MainActivity;
import com.assignment.coding.furnitureapp.main.MainFragment;
import com.assignment.coding.furnitureapp.views.IItemsViews;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    public Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        initializeFragment();
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        return true;
    }

    private void initializeFragment() {
        ItemsFragment itemsFragment = new ItemsFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.item_container, itemsFragment)
                .commit();
    }


}