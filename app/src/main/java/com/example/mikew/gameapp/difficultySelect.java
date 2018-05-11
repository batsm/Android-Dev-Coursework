package com.example.mikew.gameapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class difficultySelect extends AppCompatActivity {

    int difficulty = 650;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_select);
    }

    public void onClickEasyButton(View v)
    {
        difficulty = 650;
        Intent intent = new Intent(this, GameTest.class);
        intent.putExtra("DIFFICULTY", difficulty);
        startActivity(intent);
    }

    public void onClickMediumButton(View v)
    {
        difficulty = 600;
        Intent intent = new Intent(this, GameTest.class);
        intent.putExtra("DIFFICULTY", difficulty);
        startActivity(intent);
    }
    public void onClickHardButton(View v)
    {
        difficulty = 550;
        Intent intent = new Intent(this, GameTest.class);
        intent.putExtra("DIFFICULTY", difficulty);
        startActivity(intent);
    }
}
