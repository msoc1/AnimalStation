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
import android.util.Log;

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
    static String polishLanguage;
    static String englishLanguage;
    static String spanishLanguage;
    static String frenchLanguage;
    static String italianLanguage;
    static String ukrainianLanguage;
    private static Translate translate;
    private static boolean connected;

    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String ENGLISH_KEY = "english_language";
    private static final String POLISH_KEY = "polish_language";
    static String TAG = "testing language transl";
    static ArrayList<String> translationsNew;

    public static ArrayList<String> getTranslationsNew(Context context) {
        ArrayList<String> engArray = new ArrayList<>();
        translationsNew = new ArrayList<>();
        if (MainActivity.languageNumber == 1) {
            Log.d(TAG, "getTranslationsNew: translationsNew: " + translationsNew.toString());
            Log.d(TAG, "getTranslationsNew: polish: not here" );
            Log.d(TAG, "getTranslationsNew: english: " + engArray.toString());

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
                /*index 25*/engArray.add("sign out");
                engArray.add("local");
                engArray.add("global");
                engArray.add("username");
                engArray.add("password");
                /*index 30*/engArray.add("your score");
                engArray.add("anonymous");
                engArray.add("register");
                engArray.add("log in");
                engArray.add("Enter username!");
                /*index 35*/ engArray.add("Username too long!");
                engArray.add("Enter password!");
                engArray.add("Enter longer password!");
                engArray.add("Spaces not allowed!");
                engArray.add("You can log in!");
                /*index 40*/engArray.add("Cannot log in:");
                engArray.add("Logged in!");
                engArray.add("Signing out");

                translationsNew.addAll(engArray);
                String englishJSON = new Gson().toJson(engArray);
                saveData(context, ENGLISH_KEY, englishJSON);
            } else {
                String englishLanguageJson = loadData(context, ENGLISH_KEY);
                translationsNew = (ArrayList<String>) fromJson(englishLanguageJson, new TypeToken<ArrayList<String>>() {
                }.getType());

            }
            engArray.addAll(translationsNew);
            Log.d(TAG, "getTranslationsNew: translationsNew: " + translationsNew.toString());
            Log.d(TAG, "getTranslationsNew: polish: not here" );
            Log.d(TAG, "getTranslationsNew: english: " + engArray.toString());
            Log.d(TAG, "getTranslationsNew: ending english");
            return engArray;

        } else if (MainActivity.languageNumber == 0) {
            Log.d(TAG, "getTranslationsNew: starting polish");
            Log.d(TAG, "getTranslationsNew: polish key: " + POLISH_KEY);
            Log.d(TAG, "getTranslationsNew: translationsNew: " + translationsNew.toString());
            Log.d(TAG, "getTranslationsNew: polish: not here" );
            Log.d(TAG, "getTranslationsNew: english: " + engArray.toString());
            Log.d(TAG, "getTranslationsNew: key: " + loadData(context, POLISH_KEY) );
            if (loadData(context, POLISH_KEY).length()!=0) {
                Log.d(TAG, "getTranslationsNew: there is key");
                //translationsNew.clear();
                String polishLanguageJson = loadData(context, POLISH_KEY);
                Log.d(TAG, "getTranslationsNew: polishlanguage json: " + polishLanguageJson.length());
                translationsNew = (ArrayList<String>) fromJson(polishLanguageJson, new TypeToken<ArrayList<String>>() {
                }.getType());
                Log.d(TAG, "getTranslationsNew: translationsNew: " + translationsNew.toString());
                Log.d(TAG, "getTranslationsNew: polish: not here" );
                Log.d(TAG, "getTranslationsNew: english: " + engArray.toString());
                return translationsNew;
            } else {
                Log.d(TAG, "getTranslationsNew: no prefs");
                if (checkInternetConnection()) {
                    Log.d(TAG, "getTranslationsNew: checking connection");
                    String englishLanguageJson = loadData(context, ENGLISH_KEY);
                    Log.d(TAG, "getTranslationsNew: englishJson" + englishLanguageJson);
                    translationsNew = (ArrayList<String>) fromJson(englishLanguageJson, new TypeToken<ArrayList<String>>() {
                    }.getType());
                    ArrayList<String> polishLanguageArray = new ArrayList<>();
                    for (int i = 0; i < translationsNew.size(); i++) {
                        polishLanguageArray.add(translate(translationsNew.get(i), "pl"));
                    }
                    Log.d(TAG, "getTranslationsNew: translationsNew: " + translationsNew.toString());
                    Log.d(TAG, "getTranslationsNew: polish: e" + polishLanguageArray.toString());
                    Log.d(TAG, "getTranslationsNew: english: " + engArray.toString());
                    String polishJSON = new Gson().toJson(polishLanguageArray);
                    saveData(context, POLISH_KEY, polishJSON);
                    translationsNew.addAll(polishLanguageArray);
                    Log.d(TAG, "getTranslationsNew: adding translations");
                    Log.d(TAG, "getTranslationsNew: translationsNew: " + translationsNew.toString());
                    Log.d(TAG, "getTranslationsNew: polish: e" + polishLanguageArray.toString());
                    Log.d(TAG, "getTranslationsNew: english: " + engArray.toString());
                }
            }
        }
        return translationsNew;

    }


    public static void saveData(Context context, String KEYwhichLanguage, String languageToStore) {
        Log.d(TAG, "getTranslationsNew: in save data");
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEYwhichLanguage, languageToStore);
        editor.apply();
    }

    public static String loadData(Context context, String whichLanguageKEY) {
        Log.d(TAG, "getTranslationsNew: in loadData");

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        Log.d(TAG, "getTranslationsNew: after getting sharedPrefs");

        String text = sharedPreferences.getString(whichLanguageKEY, "");
        Log.d(TAG, "getTranslationsNew: after getting text: " + text);

        return text;
    }

    public static Object fromJson(String jsonString, Type type) {
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
        Log.d(TAG, "getTranslationsNew: " + textToTranslate);
        String translatedText = "";
        getTranslateService();
        Translation translation = translate.translate(
                textToTranslate,
                Translate.TranslateOption.sourceLanguage("en"),
                Translate.TranslateOption.targetLanguage(targerLanguage), Translate.TranslateOption.model("base"));
        translatedText = translation.getTranslatedText();
        return translatedText;
    }
}


// else if (MainActivity.languageNumber == 2) {
//            //SPANISH LANGUAGE//
//        } else if (MainActivity.languageNumber == 3) {
//            //FRENCH LANGUAGE//
//        } else if (MainActivity.languageNumber == 4) {
//            //ITALIAN LANGUAGE//
//        } else if (MainActivity.languageNumber == 5) {
//            //UKRAINIAN LANGUAGE//



