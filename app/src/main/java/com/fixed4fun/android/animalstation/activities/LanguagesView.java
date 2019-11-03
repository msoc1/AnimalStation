package com.fixed4fun.android.animalstation.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fixed4fun.android.animalstation.R;
import com.fixed4fun.android.animalstation.adapters.LanguageAdapter;
import com.fixed4fun.android.animalstation.utilities.AnimalsToUse;
import com.fixed4fun.android.animalstation.utilities.LanguagesToUse;
import com.fixed4fun.android.animalstation.utilities.LocaleMap;

import static com.fixed4fun.android.animalstation.utilities.AnimalStation.getContext;

public class LanguagesView extends AppCompatActivity {

    private Toast toast;
    private Handler h;
    private ProgressBar progressBar;
    private TextView loadingTextView;
    TextToSpeech textToSpeech;
    static String SHARED_PREFS = "sharedPrefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.languages_layout);
        toast = new Toast(getApplicationContext());
        h = new Handler();

        progressBar = findViewById(R.id.languageProgressBar);
        progressBar.setVisibility(View.INVISIBLE);
        loadingTextView = findViewById(R.id.load_text_view);
        loadingTextView.setVisibility(View.INVISIBLE);

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


        gridView.setOnItemClickListener((parent, view, position, id) -> {
            MainActivity.languageNumber = LanguagesToUse.getLanguages().get(position).getmCodeOfCountry();
            if (checkInternetConnection() || loadData(languageCode(position)).length() >= 20) {
                loadLanguage(position);
            } else {
                Toast.makeText(getApplicationContext(), "No internet", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void loadLanguage(int position) {
        textToSpeech = new TextToSpeech(getApplicationContext(), i -> textToSpeech.setLanguage(LocaleMap.getLocales()));
        saveLanguageToPrefs(position);
        startAsync();
        h.postDelayed(() -> {
            textToSpeech.speak(LanguagesToUse.getLanguages().get(position).getNameOfCountry(), TextToSpeech.QUEUE_FLUSH, null);
            ImageView view2 = new ImageView(getApplicationContext());
            view2.setImageResource(LanguagesToUse.getLanguages().get(position).getmLanguageChosen());
            toast.setView(view2);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
            final ImageView falg1 = findViewById(R.id.falg2);
            falg1.setImageResource(LanguagesToUse.getLanguages().get(MainActivity.languageNumber).getmLanguage());
            h.postDelayed(() -> toast.cancel(), 800);

        }, 100);
    }


    public void startAsync() {
        TranslateAsyncTask task = new TranslateAsyncTask();
        task.execute();
    }


    private String languageCode(int number) {
        if (number == 0) {
            return "polish_language";
        } else if (number == 1) {
            return "english_language";
        } else if (number == 2) {
            return "spanish_language";
        } else if (number == 3) {
            return "french_language";
        } else if (number == 4) {
            return "italian_language";
        } else
            return "ukrainian_language";
    }

    public static String loadData(String whichLanguageKEY) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String text = sharedPreferences.getString(whichLanguageKEY, "");
        return text;
    }

    public static void saveLanguageToPrefs(int positionOfLanguage) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("language_key", positionOfLanguage);
        editor.apply();
    }


    public static boolean checkInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
    }

    private class TranslateAsyncTask extends AsyncTask<Void, Void, Intent> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            loadingTextView.setVisibility(View.VISIBLE);
        }

        @Override
        protected Intent doInBackground(Void... voids) {
            final Intent chosenLanguage;
            if (ChosenAnimalsView.wasOnList) {
                chosenLanguage = new Intent(LanguagesView.this, ChosenAnimalsView.class);
            } else if (ChosenAnimal.wasOnAnimal) {
                chosenLanguage = new Intent(LanguagesView.this, ChosenAnimal.class);
            } else {
                chosenLanguage = new Intent(LanguagesView.this, MainActivity.class);
            }
            chosenLanguage.putExtra("My Animal", AnimalsToUse.getAnimals().get(ChosenAnimalsView.pos));
            chosenLanguage.putExtra("My Language", LanguagesToUse.getLanguages().get(MainActivity.languageNumber).getmCodeOfCountry());
            return chosenLanguage;
        }


        @Override
        protected void onPostExecute(Intent intent) {
            super.onPostExecute(intent);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            ChosenAnimalsView.wasOnList = false;
            ChosenAnimal.wasOnAnimal = false;

        }
    }


}
