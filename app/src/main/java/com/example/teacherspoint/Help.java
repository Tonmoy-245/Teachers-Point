package com.example.teacherspoint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Help extends AppCompatActivity {

    private ImageButton call1, mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.help);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        call1 = findViewById(R.id.call1);
        mail = findViewById(R.id.mail);


        call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = "+880 1845756081";
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + number));
                startActivity(callIntent);
            }
        });


        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = "tonmoybanik916@gmail.com";
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                emailIntent.setType("message/rfc822");
                startActivity(Intent.createChooser(emailIntent, "Send email using:"));
            }
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.help) {
                return true;
            } else if (itemId == R.id.person) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (itemId == R.id.policy) {
                startActivity(new Intent(getApplicationContext(), Privacy.class));
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