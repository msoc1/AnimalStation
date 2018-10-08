package com.fixed4fun.android.animalstation;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class NameQuiz extends AppCompatActivity {
    private Handler h;
    private Toast toast;
    private SoundPool soundPool2;
    private int currentSound2;
    private int soundCorrect;
    private int soundWrong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.name_quiz);
        toast = new Toast(getApplicationContext());
        h = new Handler();
        int randomAnimal = (int) (Math.random() * 4);
        AnimalsToUse.whichPictureToUse = (int) (Math.random() * 10);


        final ArrayList<Animal> zwierzaki = (ArrayList<Animal>) AnimalsToUse.getAnimals();
        Collections.shuffle(zwierzaki);


        soundPool2 = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundCorrect = soundPool2.load(this, R.raw.correct_sound, 0);
        soundWrong = soundPool2.load(this, R.raw.wrong_sound, 0);


        final ImageView animalImage = findViewById(R.id.pierwszaIkona);
        animalImage.setImageResource(AnimalsToUse.whichpicture(zwierzaki, 0));
        final String firstAnimalName = zwierzaki.get(0).getmAnimalName();
        AnimalsToUse.whichPictureToUse = (int) (Math.random() * 10);


        final ImageView animalImage2 = findViewById(R.id.drugaIkona);
        animalImage2.setImageResource(AnimalsToUse.whichpicture(zwierzaki, 1));
        final String firstAnimalName2 = zwierzaki.get(1).getmAnimalName();
        AnimalsToUse.whichPictureToUse = (int) (Math.random() * 10);


        final ImageView animalImage3 = findViewById(R.id.trzeciaIkona);
        animalImage3.setImageResource(AnimalsToUse.whichpicture(zwierzaki, 2));
        final String firstAnimalName3 = zwierzaki.get(2).getmAnimalName();
        AnimalsToUse.whichPictureToUse = (int) (Math.random() * 10);


        final ImageView animalImage4 = findViewById(R.id.czwartaIkona);
        animalImage4.setImageResource(AnimalsToUse.whichpicture(zwierzaki, 3));
        final String firstAnimalName4 = zwierzaki.get(3).getmAnimalName();


        final TextView correctAnimalName = findViewById(R.id.name_of_anmial);
        int correctAnimal = MainActivity.checkForPrevious(randomAnimal, zwierzaki);
        currentSound2 = soundPool2.load(getApplicationContext(), zwierzaki.get(correctAnimal).getmAnimalVoice(), 1);
        correctAnimalName.setText(zwierzaki.get(correctAnimal).getmAnimalName());


        ConstraintLayout soundForNames = findViewById(R.id.names);
        soundForNames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soundPool2 != null) {
                    soundPool2.play(currentSound2, 1, 1, 0, 0, 1);
                }
            }
        });


        final ImageView backToMain = findViewById(R.id.back_to_main);
        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMain = new Intent(NameQuiz.this, MainActivity.class);
                goToMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(goToMain);
                finish();
            }
        });


        animalImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstAnimalName == correctAnimalName.getText()) {
                    animalImage.setClickable(false);
                    isOk(animalImage, animalImage2, animalImage3, animalImage4, backToMain);
                } else {
                    isNotOk();
                }
            }
        });

        animalImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstAnimalName2 == correctAnimalName.getText()) {
                    animalImage2.setClickable(false);
                    isOk(animalImage, animalImage2, animalImage3, animalImage4, backToMain);
                } else isNotOk();
            }
        });

        animalImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstAnimalName3 == correctAnimalName.getText()) {
                    animalImage3.setClickable(false);
                    isOk(animalImage, animalImage2, animalImage3, animalImage4, backToMain);
                } else isNotOk();
            }
        });

        animalImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstAnimalName4 == correctAnimalName.getText()) {
                    animalImage4.setClickable(false);
                    isOk(animalImage, animalImage2, animalImage3, animalImage4, backToMain);
                } else isNotOk();
            }
        });


    }


    private void isOk(ImageView diableAnimal, ImageView diableAnimal2, ImageView diableAnimal3, ImageView diableAnimal4, ImageView backToMain) {
        diableAnimal.setClickable(false);
        diableAnimal2.setClickable(false);
        diableAnimal3.setClickable(false);
        diableAnimal4.setClickable(false);
        backToMain.setClickable(false);
        soundPool2.play(soundCorrect, 1, 1, 0, 0, 1);

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
                if (soundPool2 != null) {
                    soundPool2.release();
                    soundPool2 = null;
                }
            }
        }, 1700);


    }

    private void isNotOk() {
        ImageView view = new ImageView(getApplicationContext());
        view.setImageResource(R.drawable.zle);
        toast.setView(view);
        toast.show();
        soundPool2.play(soundWrong, 1, 1, 0, 0, 1);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (soundPool2 != null) {
            soundPool2.release();
            soundPool2 = null;
        }
    }


}
