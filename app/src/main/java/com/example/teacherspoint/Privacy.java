package com.example.teacherspoint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.io.InputStream;
import java.util.Scanner;
import com.example.teacherspoint.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Privacy extends AppCompatActivity {

    private TextView privacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.policy);

        ActionBar actionBar = getSupportActionBar(); //actionbar = toolbar
        if (actionBar != null) {
            actionBar.hide();
        }

        privacy = findViewById(R.id.privacy);
        InputStream inputStream = getResources().openRawResource(R.raw.privacy);

        try {
            Scanner scanner = new Scanner(inputStream);
            StringBuilder text = new StringBuilder();
            while (scanner.hasNextLine()) {
                text.append(scanner.nextLine());
            }
            privacy.setText(text.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.policy) {
                return true;
            } else if (itemId == R.id.person) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.help) {
                startActivity(new Intent(getApplicationContext(), Help.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.home) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.about) {
                startActivity(new Intent(getApplicationContext(), AboutUs.class));
                overridePendingTransition(0, 0);
                return true;
            }
            return false;
        });
    }

    public void onBackPressed() {
        super.onBackPressed();
    }
}