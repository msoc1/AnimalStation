package com.fixed4fun.android.animalstation;


import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class IconQuiz extends AppCompatActivity {
    private Handler h = new Handler();
    private Toast toast;
    private SoundPool soundPool;
    private int sound1;
    private int sound2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.icon_quiz);
        toast = new Toast(getApplicationContext());

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        sound1 = soundPool.load(this, R.raw.correct_sound, 0);
        sound2 = soundPool.load(this, R.raw.wrong_sound, 0);


        final ArrayList<Animal> zwierzaki = (ArrayList<Animal>) AnimalsToUse.getAnimals();
        Collections.shuffle(zwierzaki);


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


        correctAnimalName.setImageResource(zwierzaki.get(correctAnimal).getIconsBig());
        final String correctAnimalText = zwierzaki.get(correctAnimal).getmAnimalName();


        correctAnimalName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        final ImageView backToMain = findViewById(R.id.back_to_main);
        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        animalImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstAnimalName.equals(correctAnimalText)) {
                    isOk(animalImage, animalImage2, animalImage3, animalImage4, backToMain);
                } else isNotOk();
            }
        });

        animalImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstAnimalName2.equals(correctAnimalText)) {
                    isOk(animalImage, animalImage2, animalImage3, animalImage4, backToMain);
                } else isNotOk();
            }
        });

        animalImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstAnimalName3.equals(correctAnimalText)) {
                    isOk(animalImage, animalImage2, animalImage3, animalImage4, backToMain);
                } else isNotOk();
            }
        });

        animalImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstAnimalName4.equals(correctAnimalText)) {
                    isOk(animalImage, animalImage2, animalImage3, animalImage4, backToMain);
                } else isNotOk();
            }
        });


    }

    public void isOk(ImageView diableAnimal, ImageView diableAnimal2, ImageView diableAnimal3, ImageView diableAnimal4, ImageView backToMain) {
        diableAnimal.setClickable(false);
        diableAnimal2.setClickable(false);
        diableAnimal3.setClickable(false);
        diableAnimal4.setClickable(false);
        backToMain.setClickable(false);

        soundPool.play(sound1, 1, 1, 0, 0, 1);

        ImageView view = new ImageView(getApplicationContext());
        view.setImageResource(R.drawable.dobrze);
        toast.setView(view);
        toast.show();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                if (soundPool != null) {
                    soundPool.release();
                    soundPool = null;
                }

            }
        }, 1700);
    }

    public void isNotOk() {
        soundPool.play(sound2, 1, 1, 0, 0, 1);

        ImageView view = new ImageView(getApplicationContext());
        view.setImageResource(R.drawable.zle);
        toast.setView(view);
        toast.show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }

        finish();
    }
}