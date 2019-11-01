package com.fixed4fun.android.animalstation.activities;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fixed4fun.android.animalstation.R;
import com.fixed4fun.android.animalstation.adapters.LanguageAdapter;
import com.fixed4fun.android.animalstation.utilities.AnimalsToUse;
import com.fixed4fun.android.animalstation.utilities.LanguagesToUse;

public class LanguagesView extends AppCompatActivity {

    private Toast toast;
    private Handler h;
    private SoundPool soundPool;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.languages_layout);
        toast = new Toast(getApplicationContext());
        h = new Handler();

        final ImageView flag = findViewById(R.id.falg2);
        flag.setImageResource(LanguagesToUse.getLanguages().get(MainActivity.languageNumber).getmLanguageChosen());


        ImageView backToMain = findViewById(R.id.back_to_main);
        backToMain.setOnClickListener(v -> {
            if (ChosenAnimal.wasOnAnimal) {
                Intent intent = new Intent(LanguagesView.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            } else {
                Intent intent = getIntent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
            finish();
        });

        LanguageAdapter adapter = new LanguageAdapter(this, LanguagesToUse.getLanguages());
        GridView gridView = findViewById(R.id.gridViewLanguages);
        gridView.setAdapter(adapter);

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);


        gridView.setOnItemClickListener((parent, view, position, id) -> {

            MainActivity.languageNumber = LanguagesToUse.getLanguages().get(position).getmCodeOfCountry();


            final Intent chosenLanguage;
            if (ChosenAnimalsView.wasOnList) {
                chosenLanguage = new Intent(LanguagesView.this, ChosenAnimalsView.class);
            } else if (ChosenAnimal.wasOnAnimal) {
                chosenLanguage = new Intent(LanguagesView.this, ChosenAnimal.class);
            } else {
                chosenLanguage = new Intent(LanguagesView.this, MainActivity.class);
            }


           // chosenLanguage.putExtra("My Animal", AnimalsToUse.getAnimals().get(ChosenAnimalsView.pos));
            chosenLanguage.putExtra("My Language", LanguagesToUse.getLanguages().get(position).getmCodeOfCountry());
            final int sound1 = soundPool.load(getApplicationContext(), LanguagesToUse.getLanguages().get(position).getmNameOfLanguage(), 1);
            h.postDelayed(() -> {
                ImageView view2 = new ImageView(getApplicationContext());
                view2.setImageResource(LanguagesToUse.getLanguages().get(position).getmLanguageChosen());
                toast.setView(view2);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
                chosenLanguage.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                soundPool.play(sound1, 1, 1, 0, 0, 1);
                final ImageView falg1 = findViewById(R.id.falg2);
                falg1.setImageResource(LanguagesToUse.getLanguages().get(MainActivity.languageNumber).getmLanguage());
                h.postDelayed(() -> {
                    toast.cancel();
                    startActivity(chosenLanguage);
                    finish();
                }, 1000);

            }, 100);

            ChosenAnimalsView.wasOnList = false;
            ChosenAnimal.wasOnAnimal = false;
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        h.postDelayed(() -> {
            if (soundPool != null) {
                soundPool.release();
                soundPool = null;
            }

        }, 300);


    }
}
