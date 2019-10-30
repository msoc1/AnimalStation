package com.fixed4fun.android.animalstation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fixed4fun.android.animalstation.R;
import com.fixed4fun.android.animalstation.objects.Score;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class YourScoreActivity extends AppCompatActivity {

    ImageView rankingFromYourScore;
    TextView yourPoints;
    TextView yourTime;
    private String KEY_POINTS = "points_from_main";
    private String KEY_NUMBEROFQUESTION = "number_of_question";
    private int points;
    ImageView backtoMain;
    private FirebaseDatabase mFirebaseDatabaseInstance;
    private DatabaseReference mFirebaseDatabase;
    TextView anonimTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.your_score_layout);

        rankingFromYourScore = findViewById(R.id.ranking_from_yourscore);
        rankingFromYourScore.setOnClickListener(this::gotoRanking);

        yourPoints = findViewById(R.id.your_points_textview);
        yourTime = findViewById(R.id.your_time_textview);
        anonimTextView = findViewById(R.id.anonim);

        backtoMain = findViewById(R.id.back_to_main);
        backtoMain.setOnClickListener(view -> finish());

        mFirebaseDatabaseInstance = FirebaseDatabase.getInstance();


        if (getIntent().getParcelableExtra("PLAYER_SCORE") != null) {
            Intent intent = getIntent();
            Score score = intent.getParcelableExtra("PLAYER_SCORE");
            yourPoints.setText(String.valueOf(score.getScore()));

            if (score.getName().equals("Anonymous")) {
                anonimTextView.setVisibility(View.VISIBLE);
            }

            String toSet;
            long time = score.getTime();
            if (time / 1000 < 60) {
                toSet = time / 1000 + ":" + time % 100 + "s";
            } else {
                toSet = time / 60000 + ":" + (time % 60000) / 1000 + ":" + time % 100 + "s";
            }

            yourTime.setText(toSet);
            MainActivity.listOfScores.add(score);

            mFirebaseDatabase = mFirebaseDatabaseInstance.getReference("scores");
            mFirebaseDatabase.push().setValue(score);
        } else {
            Toast.makeText(getApplicationContext(), "empty", Toast.LENGTH_LONG).show();

        }


    }

    public void gotoRanking(View view) {
        startActivity(new Intent(YourScoreActivity.this, RankingActivity.class));
    }

}
