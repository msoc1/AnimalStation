package com.fixed4fun.android.animalstation;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

public class LanguageAdapter extends ArrayAdapter<Languages> {
    LanguageAdapter(Activity context, List<Languages> languages) {
        super(context, 0, languages);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View languageView = convertView;

        if (languageView == null) {
            languageView = LayoutInflater.from(getContext()).inflate(R.layout.language, parent, false);
        }


        Languages currentLanguage = getItem(position);

        ImageView languageImageView = languageView.findViewById(R.id.falg);
        assert currentLanguage != null;
        languageImageView.setImageResource(currentLanguage.getmLanguage());


        return languageView;


    }
}
