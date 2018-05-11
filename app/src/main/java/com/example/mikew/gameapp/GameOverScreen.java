package com.example.mikew.gameapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameOverScreen extends AppCompatActivity {

    int Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_screen);
        Score = getIntent().getIntExtra("SCORE", 0);
        TextView scoreText = findViewById(R.id.ScoreText);
        scoreText.setText("Score: " + String.valueOf(Score));
    }

    public void onClickedPlayAgain (View v)
    {
        Intent playAgainIntent = new Intent(this, GameTest.class);
        startActivity(playAgainIntent);
    }

    public void onClickedMainMenu (View v)
    {
        Intent mainPageIntent = new Intent(this, MenuPage.class);
        startActivity(mainPageIntent);
    }
}
