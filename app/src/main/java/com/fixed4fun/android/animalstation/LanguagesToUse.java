package com.fixed4fun.android.animalstation;

import java.util.ArrayList;
import java.util.List;

public class LanguagesToUse {


    public static List<Languages> getLanguages() {
        ArrayList<Languages> languages = new ArrayList<>();
        languages.add(new Languages(R.drawable.pol, R.drawable.polakcep, "POLSKI", 0, R.raw.polish_name));
        languages.add(new Languages(R.drawable.usa, R.drawable.usaakcep, "ANGIELSKI", 1, R.raw.english_name));
        languages.add(new Languages(R.drawable.spanish, R.drawable.spanishakcep, "HISZPAŃSKI", 2, R.raw.spanish_name));
        languages.add(new Languages(R.drawable.french, R.drawable.frenchakcep, "FRANCUSKI", 3, R.raw.french_name));
        languages.add(new Languages(R.drawable.ital, R.drawable.italakcep, "WŁOSKI", 4, R.raw.italian_name));
        languages.add(new Languages(R.drawable.ukrainian, R.drawable.ukrainianakcep, "UKRAIŃSKI", 5, R.raw.ukrainian_name));


        return languages;
    }


}
