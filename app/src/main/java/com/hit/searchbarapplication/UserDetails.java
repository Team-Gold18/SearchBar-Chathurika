package com.hit.searchbarapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class UserDetails extends AppCompatActivity {

    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        userName = findViewById(R.id.user_name);

        String s = getIntent().getStringExtra("username");
        String desc = getIntent().getStringExtra("userDesc");

        userName.setText(s + "   -   " + desc);
    }
}