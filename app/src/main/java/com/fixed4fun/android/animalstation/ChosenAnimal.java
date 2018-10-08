package com.fixed4fun.android.animalstation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class ChosenAnimal extends AppCompatActivity {


    static boolean wasOnAnimal;
    ImageView animalImage;
    ArrayList<Integer> imageToUse;
    private int currentImage = (int) (Math.random() * 10);
    private int currentSound = (int) (Math.random() * 3);
    private int currentSound2 = (int) (Math.random() * 3);
    private SoundPool soundPool;
    private SoundPool soundPool2;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.animal_detail_layout);

        final ImageView falg = findViewById(R.id.falg2);
        falg.setImageResource(LanguagesToUse.getLanguages().get(MainActivity.languageNumber).getmLanguage());
        falg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wasOnAnimal = true;
                Intent gotoLanguage = new Intent(ChosenAnimal.this, LanguagesView.class);
                gotoLanguage.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(gotoLanguage);
                if (soundPool != null) {
                    soundPool.release();
                    soundPool = null;
                }
                if (soundPool2 != null) {
                    soundPool2.release();
                    soundPool2 = null;
                }
            }
        });


        RelativeLayout background = findViewById(R.id.background_for_animal);


        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundPool2 = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        final Intent chosenAnimal = getIntent();

        final Animal animal = chosenAnimal.getParcelableExtra("My Animal");


        imageToUse = new ArrayList<>();
        imageToUse.add(animal.getmAnimalPicture());
        imageToUse.add(animal.getmAnimalPicture2());
        imageToUse.add(animal.getmAnimalPicture3());
        imageToUse.add(animal.getmAnimalPicture4());
        imageToUse.add(animal.getmAnimalPicture5());
        imageToUse.add(animal.getmAnimalPicture6());
        imageToUse.add(animal.getmAnimalPicture7());
        imageToUse.add(animal.getmAnimalPicture8());
        imageToUse.add(animal.getmAnimalPicture9());
        imageToUse.add(animal.getmAnimalPicture10());
        Collections.shuffle(imageToUse);


        animalImage = findViewById(R.id.chosen_animal_image);
        animalImage.setImageResource(imageToUse.get(currentImage));


        background.setBackgroundResource(animal.getmBackground());
        background.setOnTouchListener(new OnSwipeTouchListener(ChosenAnimal.this) {

            public void onSwipeRight() {

                if (ChosenAnimalsView.pos == 0) {
                    chosenAnimal.putExtra("My Animal", AnimalsToUse.getAnimals().get(23));
                    ChosenAnimalsView.pos = AnimalsToUse.getAnimals().size();
                } else {
                    chosenAnimal.putExtra("My Animal", AnimalsToUse.getAnimals().get(ChosenAnimalsView.pos - 1));
                }
                ChosenAnimalsView.pos--;
                finish();
                startActivity(chosenAnimal);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

            }


            public void onSwipeLeft() {
                if (ChosenAnimalsView.pos + 1 == AnimalsToUse.getAnimals().size()) {
                    chosenAnimal.putExtra("My Animal", AnimalsToUse.getAnimals().get(0));
                    ChosenAnimalsView.pos = -1;
                } else if (ChosenAnimalsView.pos + 1 < AnimalsToUse.getAnimals().size()) {
                    chosenAnimal.putExtra("My Animal", AnimalsToUse.getAnimals().get(ChosenAnimalsView.pos + 1));
                }
                ChosenAnimalsView.pos++;
                finish();
                startActivity(chosenAnimal);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);


            }
        });

        ImageView backToMain = findViewById(R.id.back_to_main);
        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChosenAnimal.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                if (soundPool != null) {
                    soundPool.release();
                    soundPool = null;
                }
                if (soundPool2 != null) {
                    soundPool2.release();
                    soundPool2 = null;
                }

            }
        });
        final ArrayList<Integer> soundsToUse2 = new ArrayList<>();

        soundsToUse2.add(soundPool2.load(getApplicationContext(), animal.getmAnimalVoice(), 1));
        soundsToUse2.add(soundPool2.load(getApplicationContext(), animal.getmAnimalVoice(), 1));
        soundsToUse2.add(soundPool2.load(getApplicationContext(), animal.getmAnimalVoice(), 1));


        TextView animalName = findViewById(R.id.name_of_anmial);
        animalName.setText(animal.getmAnimalName());


        ConstraintLayout animalsNames = findViewById(R.id.names);


        animalsNames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSound2++;
                currentSound2 = currentSound2 % soundsToUse2.size();
                soundPool2.play(soundsToUse2.get(currentSound2), 1, 1, 0, 0, 1);

            }
        });


        ImageView backToList = findViewById(R.id.back_to_list);
        backToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent chosenLanguage = new Intent(ChosenAnimal.this, ChosenAnimalsView.class);
                chosenLanguage.putExtra("My Language", LanguagesToUse.getLanguages().get(MainActivity.languageNumber).getmCodeOfCountry());
                chosenLanguage.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(chosenLanguage);

                finish();
                if (soundPool != null) {
                    soundPool.release();
                    soundPool = null;
                }
                if (soundPool2 != null) {
                    soundPool2.release();
                    soundPool2 = null;
                }
            }
        });


        final ArrayList<Integer> soundsToUse = new ArrayList<>();
        soundsToUse.add(soundPool.load(getApplicationContext(), animal.getmAnimalSound(), 1));
        soundsToUse.add(soundPool.load(getApplicationContext(), animal.getmAnimalSound2(), 1));
        soundsToUse.add(soundPool.load(getApplicationContext(), animal.getmAnimalSound3(), 1));

        animalImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImage++;
                currentImage = currentImage % imageToUse.size();
                animalImage.setImageResource(imageToUse.get(currentImage));

                currentSound++;
                currentSound = currentSound % soundsToUse.size();
                soundPool.play(soundsToUse.get(currentSound), 1, 1, 0, 0, 1);
            }

        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
        if (soundPool2 != null) {
            soundPool2.release();
            soundPool2 = null;
        }
        finish();


    }

}