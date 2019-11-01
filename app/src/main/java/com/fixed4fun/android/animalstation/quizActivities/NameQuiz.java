package com.fixed4fun.android.animalstation.quizActivities;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.fixed4fun.android.animalstation.R;
import com.fixed4fun.android.animalstation.activities.MainActivity;
import com.fixed4fun.android.animalstation.objects.Animal;
import com.fixed4fun.android.animalstation.utilities.AnimalsToUse;
import com.fixed4fun.android.animalstation.utilities.LocaleMap;

import java.util.ArrayList;
import java.util.Collections;

public class NameQuiz extends AppCompatActivity {
    private Handler h;
    private Toast toast;
    private SoundPool soundPool2;
    private int soundCorrect;
    private int soundWrong;
    ImageView viewCorrect;
    ImageView viewinCorrect;
    TextToSpeech textToSpeech;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.name_quiz);
        h = new Handler();
        int randomAnimal = (int) (Math.random() * 4);
        AnimalsToUse.whichPictureToUse = (int) (Math.random() * 10);
        viewCorrect = new ImageView(getApplicationContext());
        viewinCorrect = new ImageView(getApplicationContext());
        viewCorrect.setImageResource(R.drawable.dobrze);
        viewinCorrect.setImageResource(R.drawable.zle);

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
        String correctAnimalText = zwierzaki.get(correctAnimal).getmAnimalName();
        correctAnimalName.setText(correctAnimalText);

        textToSpeech = new TextToSpeech(getApplicationContext(), i -> textToSpeech.setLanguage(LocaleMap.getLocales()));


        ConstraintLayout soundForNames = findViewById(R.id.names);
        soundForNames.setOnClickListener(v -> {
            textToSpeech.speak(correctAnimalText, TextToSpeech.QUEUE_FLUSH, null);
        });


        final ImageView backToMain = findViewById(R.id.back_to_main);
        backToMain.setOnClickListener(v -> {
            Intent goToMain = new Intent(NameQuiz.this, MainActivity.class);
            goToMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(goToMain);
            finish();
        });


        animalImage.setOnClickListener(v -> {
            if (firstAnimalName.contentEquals(correctAnimalName.getText())) {
                animalImage.setClickable(false);
                isOk(animalImage, animalImage2, animalImage3, animalImage4, backToMain);
            } else {
                isNotOk();
            }
        });

        animalImage2.setOnClickListener(v -> {
            if (firstAnimalName2 == correctAnimalName.getText()) {
                animalImage2.setClickable(false);
                isOk(animalImage, animalImage2, animalImage3, animalImage4, backToMain);
            } else isNotOk();
        });

        animalImage3.setOnClickListener(v -> {
            if (firstAnimalName3 == correctAnimalName.getText()) {
                animalImage3.setClickable(false);
                isOk(animalImage, animalImage2, animalImage3, animalImage4, backToMain);
            } else isNotOk();
        });

        animalImage4.setOnClickListener(v -> {
            if (firstAnimalName4 == correctAnimalName.getText()) {
                animalImage4.setClickable(false);
                isOk(animalImage, animalImage2, animalImage3, animalImage4, backToMain);
            } else isNotOk();
        });


    }


    private void isOk(ImageView diableAnimal, ImageView diableAnimal2, ImageView diableAnimal3, ImageView diableAnimal4, ImageView backToMain) {
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

    private void isNotOk() {
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
