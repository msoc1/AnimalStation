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

import com.fixed4fun.android.animalstation.R;
import com.fixed4fun.android.animalstation.activities.MainActivity;
import com.fixed4fun.android.animalstation.objects.Animal;
import com.fixed4fun.android.animalstation.utilities.AnimalsSoundQuiz;

import java.util.ArrayList;
import java.util.Collections;

public class SoundQuiz extends AppCompatActivity {

    private int currentSound = (int) (Math.random() * 3);
    private int soundCorrect;
    private int soundWrong;
    private SoundPool wrongAndCorrect;
    private SoundPool soundPool;
    private Handler h = new Handler();
    private Toast toast;
    private ImageView correctAnimalName;
    ImageView viewCorrect;
    ImageView viewinCorrect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.sound_quiz);
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        viewCorrect = new ImageView(getApplicationContext());
        viewCorrect.setImageResource(R.drawable.dobrze);
        viewinCorrect = new ImageView(getApplicationContext());
        viewinCorrect.setImageResource(R.drawable.zle);


        wrongAndCorrect = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundCorrect = wrongAndCorrect.load(this, R.raw.correct_sound, 0);
        soundWrong = wrongAndCorrect.load(this, R.raw.wrong_sound, 0);

        final ArrayList<Animal> zwierzaki = (ArrayList<Animal>) AnimalsSoundQuiz.getAnimals();
        Collections.shuffle(zwierzaki);


        int randomAnimal = (int) (Math.random() * 4);


        final ImageView animalImage = findViewById(R.id.pierwszaIkona);
        animalImage.setImageResource(AnimalsSoundQuiz.whichpicture(zwierzaki, 0));
        final String firstAnimalName = zwierzaki.get(0).getmAnimalName();
        AnimalsSoundQuiz.whichPictureToUse = (int) (Math.random() * 10);


        final ImageView animalImage2 = findViewById(R.id.drugaIkona);
        animalImage2.setImageResource(AnimalsSoundQuiz.whichpicture(zwierzaki, 1));
        final String firstAnimalName2 = zwierzaki.get(1).getmAnimalName();
        AnimalsSoundQuiz.whichPictureToUse = (int) (Math.random() * 10);


        final ImageView animalImage3 = findViewById(R.id.trzeciaIkona);
        animalImage3.setImageResource(AnimalsSoundQuiz.whichpicture(zwierzaki, 2));
        final String firstAnimalName3 = zwierzaki.get(2).getmAnimalName();
        AnimalsSoundQuiz.whichPictureToUse = (int) (Math.random() * 10);


        final ImageView animalImage4 = findViewById(R.id.czwartaIkona);
        animalImage4.setImageResource(AnimalsSoundQuiz.whichpicture(zwierzaki, 3));
        final String firstAnimalName4 = zwierzaki.get(3).getmAnimalName();


        correctAnimalName = findViewById(R.id.play_sound_for_quiz);


        int correctAnimal = MainActivity.checkForPrevious(randomAnimal, zwierzaki);

        final ArrayList<Integer> soundsToUse = new ArrayList<>();
        soundsToUse.add(soundPool.load(this, zwierzaki.get(correctAnimal).getmAnimalSound(), 1));
        soundsToUse.add(soundPool.load(this, zwierzaki.get(correctAnimal).getmAnimalSound2(), 1));
        soundsToUse.add(soundPool.load(this, zwierzaki.get(correctAnimal).getmAnimalSound3(), 1));


        correctAnimalName.setOnClickListener(v -> {
            currentSound++;
            currentSound = currentSound % soundsToUse.size();
            if (soundsToUse.get(currentSound) != null) {
                soundPool.play(soundsToUse.get(currentSound), 1, 1, 0, 0, 1);
            }
        });


        final String correctAnimalText = zwierzaki.get(correctAnimal).getmAnimalName();


        final ImageView backToMain = findViewById(R.id.back_to_main);
        backToMain.setOnClickListener(v -> finish());

        animalImage.setOnClickListener(v -> {
            if (firstAnimalName.equals(correctAnimalText)) {
                animalImage.setClickable(false);
                isOk(animalImage, animalImage2, animalImage3, animalImage4, backToMain);
            } else isNotOk();
        });

        animalImage2.setOnClickListener(v -> {
            if (firstAnimalName2.equals(correctAnimalText)) {
                animalImage2.setClickable(false);
                isOk(animalImage, animalImage2, animalImage3, animalImage4, backToMain);
            } else isNotOk();
        });

        animalImage3.setOnClickListener(v -> {
            if (firstAnimalName3.equals(correctAnimalText)) {
                animalImage3.setClickable(false);
                isOk(animalImage, animalImage2, animalImage3, animalImage4, backToMain);
            } else isNotOk();
        });

        animalImage4.setOnClickListener(v -> {
            if (firstAnimalName4.equals(correctAnimalText)) {
                animalImage4.setClickable(false);
                isOk(animalImage, animalImage2, animalImage3, animalImage4, backToMain);
            } else isNotOk();
        });


    }

    public void isOk(ImageView diableAnimal, ImageView diableAnimal2, ImageView diableAnimal3, ImageView diableAnimal4, ImageView backToMain) {
        diableAnimal.setClickable(false);
        diableAnimal2.setClickable(false);
        diableAnimal3.setClickable(false);
        diableAnimal4.setClickable(false);
        backToMain.setClickable(false);
        correctAnimalName.setClickable(false);
        wrongAndCorrect.play(soundCorrect, 1, 1, 0, 0, 1);

        toast = new Toast(getApplicationContext());
        toast.setView(viewCorrect);
        toast.show();
        h.postDelayed(() -> {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
            if (wrongAndCorrect != null) {
                wrongAndCorrect.release();
                wrongAndCorrect = null;
            }
            if (soundPool != null) {
                soundPool.release();
                soundPool = null;
            }
        }, 1700);
    }

    public void isNotOk() {
        toast = new Toast(getApplicationContext());
        toast.setView(viewinCorrect);
        toast.show();
        wrongAndCorrect.play(soundWrong, 1, 1, 0, 0, 1);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
        if (wrongAndCorrect != null) {
            wrongAndCorrect.release();
            wrongAndCorrect = null;
        }

        finish();
    }
}
