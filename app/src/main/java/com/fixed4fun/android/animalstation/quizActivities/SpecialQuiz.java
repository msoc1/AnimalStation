package com.fixed4fun.android.animalstation.quizActivities;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fixed4fun.android.animalstation.R;
import com.fixed4fun.android.animalstation.activities.MainActivity;
import com.fixed4fun.android.animalstation.activities.YourScoreActivity;
import com.fixed4fun.android.animalstation.objects.Animal;
import com.fixed4fun.android.animalstation.objects.Score;
import com.fixed4fun.android.animalstation.utilities.AnimalsToUse;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Collections;

public class SpecialQuiz extends AppCompatActivity {
    private Handler h = new Handler();
    private Toast toast;
    private SoundPool soundPool;
    private int sound1;
    private int sound2;
    ImageView viewCorrect;
    ImageView viewinCorrect;
    Chronometer chronometer;
    TextView questionNumberTV;
    TextView howManyQuestionsTV;
    private boolean chronoRunning;
    private FirebaseAuth firebaseAuth;


    private int points;
    private String KEY_POINTS = "points_from_main";
    private int numberOfQuestion;
    private String KEY_NUMBEROFQUESTION = "number_of_question";
    private int QUESTION_LIMIT = 10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.special_quiz_layout);
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        sound1 = soundPool.load(this, R.raw.correct_sound, 0);
        sound2 = soundPool.load(this, R.raw.wrong_sound, 0);
        viewCorrect = new ImageView(getApplicationContext());
        viewinCorrect = new ImageView(getApplicationContext());
        viewCorrect.setImageResource(R.drawable.dobrze);
        viewinCorrect.setImageResource(R.drawable.zle);

        firebaseAuth = FirebaseAuth.getInstance();
        points = getIntent().getExtras().getInt(KEY_POINTS);
        numberOfQuestion = getIntent().getExtras().getInt(KEY_NUMBEROFQUESTION);

        final ArrayList<Animal> zwierzaki = (ArrayList<Animal>) AnimalsToUse.getAnimals();
        Collections.shuffle(zwierzaki);

        questionNumberTV = findViewById(R.id.question_number);
        howManyQuestionsTV = findViewById(R.id.how_many_questions);

        questionNumberTV.setText(String.valueOf(numberOfQuestion));
        howManyQuestionsTV.setText("/ " + String.valueOf(QUESTION_LIMIT));

        chronometer = findViewById(R.id.chronometer);
        //chronometer.setFormat("MM:SS");
        startChr(chronometer);


        int randomAnimal = (int) (Math.random() * 4);

        final ImageView animalImage = findViewById(R.id.iconQuizOne);
        animalImage.setImageResource(AnimalsToUse.whichpicture(zwierzaki, 0));
        final String firstAnimalName = zwierzaki.get(0).getmAnimalName();
        AnimalsToUse.whichPictureToUse = (int) (Math.random() * 10);

        final ImageView animalImage2 = findViewById(R.id.iconQuizTwo);
        animalImage2.setImageResource(AnimalsToUse.whichpicture(zwierzaki, 1));
        final String firstAnimalName2 = zwierzaki.get(1).getmAnimalName();
        AnimalsToUse.whichPictureToUse = (int) (Math.random() * 10);

        final ImageView animalImage3 = findViewById(R.id.iconQuizThree);
        animalImage3.setImageResource(AnimalsToUse.whichpicture(zwierzaki, 2));
        final String firstAnimalName3 = zwierzaki.get(2).getmAnimalName();
        AnimalsToUse.whichPictureToUse = (int) (Math.random() * 10);

        final ImageView animalImage4 = findViewById(R.id.iconQuizFour);
        animalImage4.setImageResource(AnimalsToUse.whichpicture(zwierzaki, 3));
        final String firstAnimalName4 = zwierzaki.get(3).getmAnimalName();


        final ImageView correctAnimalName = findViewById(R.id.icon_quiz);

        int correctAnimal = MainActivity.checkForPrevious(randomAnimal, zwierzaki);
        if (numberOfQuestion % 2 == 0) {
            correctAnimalName.setImageResource(zwierzaki.get(correctAnimal).getIconsBig());
        } else {
            correctAnimalName.setImageResource(zwierzaki.get(correctAnimal).getmAnimalBlack());

        }
        final String correctAnimalText = zwierzaki.get(correctAnimal).getmAnimalName();


        final ImageView backToMain = findViewById(R.id.back_to_main);
        backToMain.setOnClickListener(v -> {
            chronometer.stop();
            MainActivity.pauseChrono = 0;
            resetChr(chronometer);
            finish();
        });


        animalImage.setOnClickListener(v -> {
            if (firstAnimalName.equals(correctAnimalText)) {
                checkAnswer(animalImage, animalImage2, animalImage3, animalImage4, backToMain, viewCorrect);
            } else
                checkAnswer(animalImage, animalImage2, animalImage3, animalImage4, backToMain, viewinCorrect);
        });

        animalImage2.setOnClickListener(v -> {
            if (firstAnimalName2.equals(correctAnimalText)) {
                checkAnswer(animalImage, animalImage2, animalImage3, animalImage4, backToMain, viewCorrect);
            } else
                checkAnswer(animalImage, animalImage2, animalImage3, animalImage4, backToMain, viewinCorrect);
        });

        animalImage3.setOnClickListener(v -> {
            if (firstAnimalName3.equals(correctAnimalText)) {
                checkAnswer(animalImage, animalImage2, animalImage3, animalImage4, backToMain, viewCorrect);
            } else
                checkAnswer(animalImage, animalImage2, animalImage3, animalImage4, backToMain, viewinCorrect);
        });

        animalImage4.setOnClickListener(v -> {
            if (firstAnimalName4.equals(correctAnimalText)) {
                checkAnswer(animalImage, animalImage2, animalImage3, animalImage4, backToMain, viewCorrect);
            } else
                checkAnswer(animalImage, animalImage2, animalImage3, animalImage4, backToMain, viewinCorrect);
        });


    }

    public void checkAnswer(ImageView diableAnimal, ImageView diableAnimal2, ImageView diableAnimal3, ImageView diableAnimal4, ImageView backToMain, ImageView correctToast) {
        diableAnimal.setClickable(false);
        diableAnimal2.setClickable(false);
        diableAnimal3.setClickable(false);
        diableAnimal4.setClickable(false);
        backToMain.setClickable(false);
        numberOfQuestion++;
        if (correctToast == viewCorrect) {
            points++;
            soundPool.play(sound1, 1, 1, 0, 0, 1);
        } else {
            soundPool.play(sound2, 1, 1, 0, 0, 1);
        }


        toast = new Toast(getApplicationContext());
        toast.setView(correctToast);
        toast.show();
        h.postDelayed(() -> {
            Intent intent;

            if (numberOfQuestion > QUESTION_LIMIT) {
                String username;
                if (firebaseAuth.getCurrentUser() == null) {
                    username = "Anonymous";
                } else {
                    String fullName = firebaseAuth.getCurrentUser().getEmail();
                    username = fullName.split("@")[0];
                }

                chronometer.stop();
                Score currentScore = new Score(username, MainActivity.pauseChrono, points);
                intent = new Intent(SpecialQuiz.this, YourScoreActivity.class);
                intent.putExtra("PLAYER_SCORE", currentScore);
                MainActivity.pauseChrono = 0;//SystemClock.elapsedRealtime();
            } else {
                MainActivity.pauseChrono = SystemClock.elapsedRealtime() - chronometer.getBase();
                intent = new Intent(SpecialQuiz.this, SpecialQuiz.class);
            }
            intent.putExtra(KEY_POINTS, points);
            intent.putExtra(KEY_NUMBEROFQUESTION, numberOfQuestion);
            finish();
            toast.cancel();
            startActivityForResult(intent, 0);
            if (soundPool != null) {
                soundPool.release();
                soundPool = null;
            }
        }, 500);
    }


    public void startChr(View v) {
        if (!chronoRunning) {
            chronometer.setBase(SystemClock.elapsedRealtime() - MainActivity.pauseChrono);
            chronometer.start();
            chronoRunning = true;
        }
    }

    public void stopChr(View chr) {
        if (chronoRunning) {
            chronometer.stop();
            MainActivity.pauseChrono = SystemClock.elapsedRealtime() - chronometer.getBase();
            chronoRunning = false;
        }
    }

    public void resetChr(View chr) {
        chronometer.setBase(SystemClock.elapsedRealtime());
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        points = 0;
        numberOfQuestion = 0;
        chronometer.stop();
        resetChr(chronometer);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        points = 0;
        numberOfQuestion = 0;
        chronometer.stop();
        resetChr(chronometer);
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
        finish();
    }
}
