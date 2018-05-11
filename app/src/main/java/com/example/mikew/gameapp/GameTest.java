package com.example.mikew.gameapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;
import java.util.Vector;

public class GameTest extends AppCompatActivity {

    //player variables
    int moveTick = 5;
    int playerMoveSpeed = -20;
    int playerTopPos;
    boolean boostPlayer = true;
    int difficulty = 650;
    int gameScore = 0;
    boolean playerIsOnFloor;

    //variables for pillars
    Vector spawned_pillars = new Vector();
    Random r = new Random();
    int previousPillarPos;

    //other variables
    int canvasWidth;
    int canvasHeight;
    boolean pointsAvailable;
    boolean isPaused = false;

    public class pillar
    {
        public ImageView pillar_;
        pillar(float x, float y)
        {
            final ConstraintLayout mainLayout = findViewById(R.id.background);
            pillar_ = new ImageView(getApplicationContext());
            pillar_.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.barrier_tall_pink));
            mainLayout.addView(pillar_);
            pillar_.setX(x);
            pillar_.setY(y);
            spawned_pillars.add(this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_test);
        // Game Size
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        canvasWidth = displayMetrics.widthPixels;
        canvasHeight = displayMetrics.heightPixels;

        difficulty = getIntent().getIntExtra("DIFFICULTY", 650);

        for(int i = 0; i < 2; i++)
        {
            pillar PillarBottom = new pillar((canvasWidth + (i * canvasWidth)), r.nextInt(canvasHeight - 800) + difficulty);
            PillarBottom.pillar_.setMaxHeight((canvasHeight - 400));
        }

        final ImageView player = findViewById(R.id.imageView);

        final Handler move_object_handler = new Handler();
        move_object_handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (!isPaused) {
                        playerTopPos = player.getTop() - playerMoveSpeed;
                        player.setTop(playerTopPos);

                        if (playerMoveSpeed < -20)
                        {
                            boostPlayer = false;
                        }

                        if (playerMoveSpeed > -20)
                        {
                            playerMoveSpeed = playerMoveSpeed - 1;
                        }

                    if((player.getTop()) > canvasHeight)
                    {
                        isPaused = true;
                        Intent gameOverIntent = new Intent(getApplicationContext(), GameOverScreen.class);
                        gameOverIntent.putExtra("SCORE", gameScore);
                        startActivity(gameOverIntent);
                    }

                    if(player.getTop() <= -128)
                    {
                        isPaused = true;
                        Intent gameOverIntent = new Intent(getApplicationContext(), GameOverScreen.class);
                        gameOverIntent.putExtra("SCORE", gameScore);
                        startActivity(gameOverIntent);
                    }
                    move_object_handler.postDelayed(this, moveTick);
                }
            }
        }, moveTick);

        final Handler move_pillar = new Handler();
        move_pillar.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!isPaused)
                {
                    for (int i = 0; i < spawned_pillars.size(); i++) {
                        pillar movePillar = (pillar) spawned_pillars.get(i);

                        if (movePillar.pillar_.getX() < (movePillar.pillar_.getWidth() * -1)) {
                            movePillar.pillar_.setX((canvasWidth * 2));
                            previousPillarPos = r.nextInt(canvasHeight - 800) + difficulty;
                            movePillar.pillar_.setY(previousPillarPos);
                        }

                        if (movePillar.pillar_.getX() >= player.getX() && movePillar.pillar_.getX() <= (player.getX() + player.getWidth()))
                        {
                            pointsAvailable = true;
                            if ((player.getY() + player.getWidth()) >= movePillar.pillar_.getY())
                            {
                                isPaused = true;
                                Intent gameOverIntent = new Intent(getApplicationContext(), GameOverScreen.class);
                                gameOverIntent.putExtra("SCORE", gameScore);
                                startActivity(gameOverIntent);
                            }
                        }
                        else if (pointsAvailable && movePillar.pillar_.getX() <= (player.getX() + player.getWidth()))
                        {
                            pointsAvailable = false;
                            gameScore++;

                            //final TextView score = findViewById(R.id.score);

//                            runOnUiThread(new Runnable() {
//                            public void run() {
//                                score.setText(String.valueOf(gameScore));
//                            }
//                            });
                            Toast toast = Toast.makeText(getApplicationContext(), String.valueOf(gameScore), Toast.LENGTH_LONG);
                            toast.show();

                            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.game_sound_effect_1);
                            mp.start();
                        }

                        movePillar.pillar_.setX(movePillar.pillar_.getX() - 5);
                    }
                }
                move_pillar.postDelayed(this, moveTick);
            }
        }, moveTick);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int event_action_type = event.getAction();

        if(event_action_type == MotionEvent.ACTION_DOWN)
        {
            boostPlayer = !boostPlayer;
            playerIsOnFloor = false;
            playerMoveSpeed = 20;
        }
        return true;
    }
}
