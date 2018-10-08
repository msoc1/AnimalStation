package com.fixed4fun.android.animalstation;

import android.os.Parcel;
import android.os.Parcelable;

public class Languages implements Parcelable {

    public static final Creator<Languages> CREATOR = new Creator<Languages>() {
        @Override
        public Languages createFromParcel(Parcel in) {
            return new Languages(in);
        }

        @Override
        public Languages[] newArray(int size) {
            return new Languages[size];
        }
    };
    private int mLanguage;
    private int mLanguageChosen;
    private String nameOfCountry;
    private int mCodeOfCountry;
    private int mNameOfLanguage;

    public Languages(int mLanguage, int mLanguageChosen, String nameOfCountry, int mCodeOfCountry, int mNameOfLanguage) {
        this.mLanguage = mLanguage;
        this.mLanguageChosen = mLanguageChosen;
        this.nameOfCountry = nameOfCountry;
        this.mCodeOfCountry = mCodeOfCountry;
        this.mNameOfLanguage = mNameOfLanguage;
    }

    protected Languages(Parcel in) {
        mLanguage = in.readInt();
        mLanguageChosen = in.readInt();
        nameOfCountry = in.readString();
        mCodeOfCountry = in.readInt();
        mNameOfLanguage = in.readInt();
    }

    public int getmLanguage() {
        return mLanguage;
    }

    public int getmLanguageChosen() {
        return mLanguageChosen;
    }

    public String getNameOfCountry() {
        return nameOfCountry;
    }

    public int getmCodeOfCountry() {
        return mCodeOfCountry;
    }

    public int getmNameOfLanguage() {
        return mNameOfLanguage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mLanguage);
        dest.writeInt(mLanguageChosen);
        dest.writeString(nameOfCountry);
        dest.writeInt(mCodeOfCountry);
        dest.writeInt(mNameOfLanguage);
    }
}

