package com.fixed4fun.android.animalstation.utilities;

import com.fixed4fun.android.animalstation.R;
import com.fixed4fun.android.animalstation.objects.Languages;

import java.util.ArrayList;
import java.util.List;

public class LanguagesToUse {


    public static List<Languages> getLanguages() {
        ArrayList<Languages> languages = new ArrayList<>();
        languages.add(new Languages(R.drawable.pol, R.drawable.polakcep, "polski", 0, R.raw.polish_name));
        languages.add(new Languages(R.drawable.usa, R.drawable.usaakcep, "english", 1, R.raw.english_name));
        languages.add(new Languages(R.drawable.spanish, R.drawable.spanishakcep, "español", 2, R.raw.spanish_name));
        languages.add(new Languages(R.drawable.french, R.drawable.frenchakcep, "français", 3, R.raw.french_name));
        languages.add(new Languages(R.drawable.ital, R.drawable.italakcep, "italiano", 4, R.raw.italian_name));
        languages.add(new Languages(R.drawable.ukrainian, R.drawable.ukrainianakcep, "український", 5, R.raw.ukrainian_name));


        return languages;
    }


}
