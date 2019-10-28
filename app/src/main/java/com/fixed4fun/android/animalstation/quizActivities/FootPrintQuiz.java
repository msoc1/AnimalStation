package com.fixed4fun.android.animalstation.quizActivities;


import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.fixed4fun.android.animalstation.objects.Animal;
import com.fixed4fun.android.animalstation.utilities.AnimalsToUse;
import com.fixed4fun.android.animalstation.activities.MainActivity;
import com.fixed4fun.android.animalstation.R;

import java.util.ArrayList;
import java.util.Collections;

public class FootPrintQuiz extends AppCompatActivity {
    private Handler h = new Handler();
    private Toast toast;
    private SoundPool soundPool2;
    private int soundCorrect;
    private int soundWrong;
    ImageView viewCorrect;
    ImageView viewinCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.icon_quiz);

        viewCorrect = new ImageView(getApplicationContext());
        viewinCorrect = new ImageView(getApplicationContext());
        viewCorrect.setImageResource(R.drawable.dobrze);
        viewinCorrect.setImageResource(R.drawable.zle);
        soundPool2 = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundCorrect = soundPool2.load(this, R.raw.correct_sound, 0);
        soundWrong = soundPool2.load(this, R.raw.wrong_sound, 0);

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

        correctAnimalName.setImageResource(zwierzaki.get(correctAnimal).getmAnimalBlack());
        final String correctAnimalText = zwierzaki.get(correctAnimal).getmAnimalName();


        final ImageView backToMain = findViewById(R.id.back_to_main);
        backToMain.setOnClickListener(v -> finish());


        animalImage.setOnClickListener(v -> {
            if (firstAnimalName.equals(correctAnimalText)) {
                isOk(animalImage, animalImage2, animalImage3, animalImage4, backToMain);
            } else isNotOk();
        });

        animalImage2.setOnClickListener(v -> {
            if (firstAnimalName2.equals(correctAnimalText)) {
                isOk(animalImage, animalImage2, animalImage3, animalImage4, backToMain);
            } else isNotOk();
        });

        animalImage3.setOnClickListener(v -> {
            if (firstAnimalName3.equals(correctAnimalText)) {
                isOk(animalImage, animalImage2, animalImage3, animalImage4, backToMain);
            } else isNotOk();
        });

        animalImage4.setOnClickListener(v -> {
            if (firstAnimalName4.equals(correctAnimalText)) {
                isOk(animalImage, animalImage2, animalImage3, animalImage4, backToMain);
            } else isNotOk();
        });

        correctAnimalName.setOnClickListener(v -> {

        });

    }

    public void isOk(ImageView diableAnimal, ImageView diableAnimal2, ImageView diableAnimal3, ImageView diableAnimal4, ImageView backToMain) {
        diableAnimal.setClickable(false);
        diableAnimal2.setClickable(false);
        diableAnimal3.setClickable(false);
        diableAnimal4.setClickable(false);
        backToMain.setClickable(false);

        soundPool2.play(soundCorrect, 1, 1, 0, 0, 1);


        toast = new Toast(getApplicationContext());
        toast.setView(viewCorrect);
        toast.show();
        h.postDelayed(() -> {
            Intent intent = getIntent();
            finish();
            toast.cancel();
            startActivity(intent);
            if (soundPool2 != null) {
                soundPool2.release();
                soundPool2 = null;
            }
        }, 1700);

    }

    public void isNotOk() {
        toast = new Toast(getApplicationContext());
        toast.setView(viewinCorrect);
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
