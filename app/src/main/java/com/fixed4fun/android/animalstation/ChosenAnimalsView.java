package com.fixed4fun.android.animalstation;


import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

public class ChosenAnimalsView extends AppCompatActivity {
    static int pos;
    static boolean wasOnList;
    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.chosen_animal_view);
        AnimalsToUse.getAnimals();


        AnimalAdapter adapter = new AnimalAdapter(this, AnimalsToUse.getAnimals());
        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();

                Intent chosenAnimal = new Intent(ChosenAnimalsView.this, ChosenAnimal.class);
                chosenAnimal.putExtra("My Animal", AnimalsToUse.getAnimals().get(position));
                startActivity(chosenAnimal);
                pos = position;
            }
        });

        ImageView backToMain = findViewById(R.id.back_to_main);
        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                finish();
            }
        });

        final ImageView falg = findViewById(R.id.falg2);
        falg.setImageResource(LanguagesToUse.getLanguages().get(MainActivity.languageNumber).getmLanguage());

        falg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wasOnList = true;
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                Intent gotoLanguage = new Intent(ChosenAnimalsView.this, LanguagesView.class);
                gotoLanguage.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(gotoLanguage);
                finish();

            }
        });

    }
}