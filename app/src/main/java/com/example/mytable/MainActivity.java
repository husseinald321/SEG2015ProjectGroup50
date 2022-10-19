package com.example.mytable;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void registerClient(View v) {
        Intent i = new Intent(this, ClientRegisterScreen.class);

        startActivity(i);
    }

    public void registerCook(View v) {
        Intent i = new Intent(this, CookRegisterScreen.class);

        startActivity(i);
    }

    public void login(View v) {
        Intent i = new Intent(this, LoginScreen.class);

        startActivity(i);
    }

}