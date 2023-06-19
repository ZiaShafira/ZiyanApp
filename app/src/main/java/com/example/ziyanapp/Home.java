package com.example.ziyanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    public void create(View view)
    {
            Intent intent = new Intent(Home.this, Create.class);
            startActivity(intent);

    }
    public void logout(View view)
    {
        Intent intent = new Intent(Home.this, MainActivity.class);
        startActivity(intent);

    }
    public void inventory(View view)
    {
        Intent intent = new Intent(Home.this, Inventory.class);
        startActivity(intent);

    }
}