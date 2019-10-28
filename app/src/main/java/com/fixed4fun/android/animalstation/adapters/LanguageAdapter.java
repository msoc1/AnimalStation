package com.fixed4fun.android.animalstation.adapters;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.fixed4fun.android.animalstation.objects.Languages;
import com.fixed4fun.android.animalstation.R;

import java.util.List;

public class LanguageAdapter extends ArrayAdapter<Languages> {
    public LanguageAdapter(Activity context, List<Languages> languages) {
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
