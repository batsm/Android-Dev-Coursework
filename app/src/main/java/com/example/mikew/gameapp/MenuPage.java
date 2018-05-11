package com.example.mikew.gameapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MenuPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);
    }

    public void buttonOnClick(View v)
    {
        //On clicked for next page button
        openActivity();
    }

    public void helpButtonOnClick(View v)
    {
        //Creates new intent and starts the activity
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }

    public void openActivity()
    {
        //Creates new intent and starts the activity
        Intent intent = new Intent(this, difficultySelect.class);
        startActivity(intent);
    }
}
