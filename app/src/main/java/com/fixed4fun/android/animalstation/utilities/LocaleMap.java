package com.fixed4fun.android.animalstation.utilities;

import com.fixed4fun.android.animalstation.activities.MainActivity;

import java.util.Locale;

public class LocaleMap {


    public static Locale getLocales() {
        if (MainActivity.languageNumber == 0) {
            //POLISH LANGUAGE//
            return new Locale("pl_PL");
        } else if (MainActivity.languageNumber == 1) {
            //ENGLISH LANGUAGE//
            return Locale.ENGLISH;
        } else if (MainActivity.languageNumber == 2) {
            //SPANISH LANGUAGE//
            return new Locale("es_ES");
        } else if (MainActivity.languageNumber == 3) {
            //FRENCH LANGUAGE//
            return Locale.FRENCH;
        } else if (MainActivity.languageNumber == 4) {
            //ITALIAN LANGUAGE//
            return Locale.ITALIAN;
        } else if (MainActivity.languageNumber == 5) {
            //UKRAINIAN LANGUAGE//
            return new Locale("uk_UA");
        } else {
            return Locale.ENGLISH;
        }
    }


}
