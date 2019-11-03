package com.fixed4fun.android.animalstation.utilities;

/* list of animals should go:
dog
cat
cow
horse
pig
goat
fox
sheep
hen
owl
elephant
giraffe
Lion
tiger
rhinoceros
penguin
bear
panda
monkey
hippo
kangaroo
Dolphin
rabbit
koala
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fixed4fun.android.animalstation.R;
import com.fixed4fun.android.animalstation.activities.MainActivity;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Translations extends AppCompatActivity {
    static String englishLanguage;
    private static Translate translate;
    private static boolean connected;
    private ProgressBar progressBar;

    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String ENGLISH_KEY = "english_language";
    private static final String POLISH_KEY = "polish_language";
    private static final String SPANISH_KEY = "spanish_language";
    private static final String FRENCH_KEY = "french_language";
    private static final String ITALIAN_KEY = "italian_language";
    private static final String UKRAINIAN_LANGUAGE = "ukrainian_language";

    private static final String SHORT_EN = "en";
    private static final String SHORT_PL = "pl";
    private static final String SHORT_ES = "es";
    private static final String SHORT_FR = "fr";
    private static final String SHORT_IT = "it";
    private static final String SHORT_UA = "uk";

    static String TAG = "testingLanguage2";
    static ArrayList<String> translationsNew;

    public static ArrayList<String> getTranslationsNew(Context context) {

        ArrayList<String> engArray = new ArrayList<>();
        translationsNew = new ArrayList<>();

        if (MainActivity.languageNumber == 1) {
            if (context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE).getString(ENGLISH_KEY, englishLanguage) == null) {
                //ENGLISH LANGUAGE//
                engArray.add("dog");
                engArray.add("cat");
                engArray.add("cow");
                engArray.add("horse");
                engArray.add("pig");
                engArray.add("goat");
                engArray.add("fox");
                engArray.add("sheep");
                engArray.add("chicken");
                engArray.add("owl");
                engArray.add("elephant");
                engArray.add("giraffe");
                engArray.add("lion");
                engArray.add("tiger");
                engArray.add("rhinoceros");
                engArray.add("penguin");
                engArray.add("bear");
                engArray.add("panda");
                engArray.add("monkey");
                engArray.add("hippo");
                engArray.add("kangaroo");
                engArray.add("dolphin");
                engArray.add("rabbit");
                engArray.add("koala");
                engArray.add("SIGN IN");
                /*index 25*/
                engArray.add("sign out");
                engArray.add("local");
                engArray.add("global");
                engArray.add("username");
                engArray.add("password");
                /*index 30*/
                engArray.add("your score");
                engArray.add("anonymous");
                engArray.add("register");
                engArray.add("log in");
                engArray.add("Enter username!");
                /*index 35*/
                engArray.add("Username too long!");
                engArray.add("Enter password!");
                engArray.add("Enter longer password!");
                engArray.add("Spaces not allowed!");
                engArray.add("You can log in!");
                /*index 40*/
                engArray.add("Cannot log in");
                engArray.add("Logged in!");
                engArray.add("Signing out");

                translationsNew.addAll(engArray);
                String englishJSON = new Gson().toJson(engArray);
                saveData(context, ENGLISH_KEY, englishJSON);
            } else {
                String englishLanguageJson = loadData(context, ENGLISH_KEY);
                translationsNew = fromJson(englishLanguageJson, new TypeToken<ArrayList<String>>() {
                }.getType());

            }
            engArray.addAll(translationsNew);
            return engArray;

        } else if (MainActivity.languageNumber == 0) {
            loadOrDownloadLanguage(context, POLISH_KEY, SHORT_PL);
        } else if (MainActivity.languageNumber == 2) {
            loadOrDownloadLanguage(context, SPANISH_KEY, SHORT_ES);
        } else if (MainActivity.languageNumber == 3) {
            loadOrDownloadLanguage(context, FRENCH_KEY, SHORT_FR);
        } else if (MainActivity.languageNumber == 4) {
            loadOrDownloadLanguage(context, ITALIAN_KEY, SHORT_IT);
        } else if (MainActivity.languageNumber == 5) {
            loadOrDownloadLanguage(context, UKRAINIAN_LANGUAGE, SHORT_UA);
        }
        return translationsNew;

    }

    public static void toastOnNoInternet() {
        Toast.makeText(AnimalStation.getContext(), "No Internet", Toast.LENGTH_LONG).show();

    }


    public static ArrayList<String> loadOrDownloadLanguage(Context context, String COUNTRY_KEY, String SHORT_LANG_CODE) {
        if (loadData(context, COUNTRY_KEY).length() != 0) {
            return returnIfInSharedPrefs(context, COUNTRY_KEY);
        } else {
            if (checkInternetConnection()) {
                downloadNewLanguage(context, COUNTRY_KEY, SHORT_LANG_CODE);
            } else {
                toastOnNoInternet();
            }
        }
        return translationsNew;
    }

    public static ArrayList<String> returnIfInSharedPrefs(Context context, String KEY) {
        String jsonToLoad = loadData(context, KEY);
        translationsNew = fromJson(jsonToLoad, new TypeToken<ArrayList<String>>() {
        }.getType());
        return translationsNew;
    }

    public static void downloadNewLanguage(Context context, String KEY, String languageShort) {
        String englishLanguageJson = loadData(context, ENGLISH_KEY);
        translationsNew = fromJson(englishLanguageJson, new TypeToken<ArrayList<String>>() {
        }.getType());
        ArrayList<String> tempArray = new ArrayList<>();
        for (int i = 0; i < translationsNew.size(); i++) {
            tempArray.add(translate(translationsNew.get(i), languageShort));
        }

        String tempJson = new Gson().toJson(tempArray);
        saveData(context, KEY, tempJson);
        translationsNew.addAll(tempArray);
    }


    public static void saveData(Context context, String KEYwhichLanguage, String languageToStore) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEYwhichLanguage, languageToStore);
        editor.apply();
    }

    public static String loadData(Context context, String whichLanguageKEY) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String text = sharedPreferences.getString(whichLanguageKEY, "");
        return text;
    }

    public static ArrayList<String> fromJson(String jsonString, Type type) {
        return new Gson().fromJson(jsonString, type);
    }

    public static boolean checkInternetConnection() {

        //Check internet connection:
        ConnectivityManager connectivityManager = (ConnectivityManager) AnimalStation.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        //Means that we are connected to a network (mobile or wi-fi)
        connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;

        return connected;
    }

    public static void getTranslateService() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try (InputStream is = AnimalStation.getContext().getResources().openRawResource(R.raw.credentials)) {

            //Get credentials:
            final GoogleCredentials myCredentials = GoogleCredentials.fromStream(is);

            //Set credentials and get translate service:
            TranslateOptions translateOptions = TranslateOptions.newBuilder().setCredentials(myCredentials).build();
            translate = translateOptions.getService();

        } catch (IOException ioe) {
            ioe.printStackTrace();

        }
    }

    public static String translate(String textToTranslate, String targerLanguage) {
        String translatedText = "";
        getTranslateService();
        Translation translation = translate.translate(
                textToTranslate,
                Translate.TranslateOption.sourceLanguage(SHORT_EN),
                Translate.TranslateOption.targetLanguage(targerLanguage), Translate.TranslateOption.model("base"));
        translatedText = translation.getTranslatedText();
        if (translatedText.contains("&#39;")) {
            translatedText = translatedText.replaceAll("&#39;", "\'");
        }
        return translatedText;
    }
}