package com.example.teacherspoint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ProfileActivity extends AppCompatActivity {


    Button sign_out;
    TextView profile_name, profile_email;

    //firebase components
    FirebaseAuth auth;
    FirebaseUser user;
    String uid;
    FirebaseFirestore db;
    String userEmail, userName, userPassword, userAddress;
    DocumentReference docRef;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.person);
        ActionBar actionBar = getSupportActionBar();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        db = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();


        if(user == null){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            profile_email = findViewById(R.id.profile_email);
            profile_name = findViewById(R.id.profile_name);


            uid = user.getUid();
            docRef = db.collection("User").document(uid);

            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            // Data for the user exists, you can access it here
                            userName = document.getString("name");
                            userEmail = document.getString("email");
                            userPassword = document.getString("password");
                            userAddress = document.getString("address");

                            // Update UI with the retrieved data
                            profile_email.setText(userEmail);
                            profile_name.setText(userName); // Assuming "username" is a field in the Firestore document
                        } else {
                            // Document does not exist
                            Toast.makeText(ProfileActivity.this, "Profile Activity User Do Not Exists", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // An error occurred while fetching the data
                        Toast.makeText(ProfileActivity.this, "ERROR WHILE FRETCHING DATA", Toast.LENGTH_SHORT).show();
                    }

                }
            });



            sign_out = findViewById(R.id.sign_out);
            sign_out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    //finish();
                }
            });
        }

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.person) {
                return true;
            } else if (itemId == R.id.policy) {
                startActivity(new Intent(getApplicationContext(), Privacy.class));
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