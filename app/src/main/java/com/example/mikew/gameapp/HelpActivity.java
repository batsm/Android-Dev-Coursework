package com.example.mikew.gameapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }

    public void mainMenuButtonOnClick(View v)
    {
        Intent menuPageIntent = new Intent(this, MenuPage.class);
        startActivity(menuPageIntent);
    }
}
