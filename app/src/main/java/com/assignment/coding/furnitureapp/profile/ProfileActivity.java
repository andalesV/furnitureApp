package com.assignment.coding.furnitureapp.profile;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.assignment.coding.furnitureapp.R;
import com.assignment.coding.furnitureapp.gallery.GalleryFragment;
import com.assignment.coding.furnitureapp.main.MainActivity;
import com.assignment.coding.furnitureapp.models.Data;
import com.assignment.coding.furnitureapp.views.IProfileView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.assignment.coding.furnitureapp.R.string.location;
import static com.assignment.coding.furnitureapp.Utils.Utils.LOG_TAG;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    public Toolbar mToolbar;

    private Data data;

    public ProfileActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeProfileFragment();
    }

    private void initializeProfileFragment() {
        Intent intent = getIntent();
        try {
            data = (Data) intent.getExtras().get("data");
        } catch (NullPointerException e) {
            Log.e(LOG_TAG, e.getMessage());
        }


        Bundle bundle = new Bundle();

        bundle.putSerializable("data", data);


        ProfileFragment profileFragment = new ProfileFragment();
        profileFragment.setArguments(bundle);


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.profile_container, profileFragment)
                .commit();
        Toast.makeText(getApplicationContext(), location, Toast.LENGTH_LONG).show();

    }

}