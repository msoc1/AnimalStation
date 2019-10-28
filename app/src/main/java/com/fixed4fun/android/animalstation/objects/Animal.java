package com.fixed4fun.android.animalstation.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class Animal implements Parcelable {
    public static final Creator<Animal> CREATOR = new Creator<Animal>() {
        @Override
        public Animal createFromParcel(Parcel in) {
            return new Animal(in);
        }

        @Override
        public Animal[] newArray(int size) {
            return new Animal[size];
        }
    };
    private int mAnimalIcon;
    private int mAnimalPicture;
    private int mAnimalPicture2;
    private int mAnimalPicture3;
    private int mAnimalPicture4;
    private int mAnimalPicture5;
    private int mAnimalPicture6;
    private int mAnimalPicture7;
    private int mAnimalPicture8;
    private int mAnimalPicture9;
    private int mAnimalPicture10;
    private int mAnimalSound;
    private int mAnimalSound2;
    private int mAnimalSound3;
    private String mAnimalName;
    private int mAnimalVoice;
    private int mAnimalBlack;
    private int mBackground;
    private int iconsBig;

    protected Animal(Parcel in) {
        mAnimalIcon = in.readInt();
        mAnimalPicture = in.readInt();
        mAnimalPicture2 = in.readInt();
        mAnimalPicture3 = in.readInt();
        mAnimalPicture4 = in.readInt();
        mAnimalPicture5 = in.readInt();
        mAnimalPicture6 = in.readInt();
        mAnimalPicture7 = in.readInt();
        mAnimalPicture8 = in.readInt();
        mAnimalPicture9 = in.readInt();
        mAnimalPicture10 = in.readInt();
        mAnimalSound = in.readInt();
        mAnimalSound2 = in.readInt();
        mAnimalSound3 = in.readInt();
        mAnimalName = in.readString();
        mAnimalVoice = in.readInt();
        mAnimalBlack = in.readInt();
        mBackground = in.readInt();
        iconsBig = in.readInt();
    }

    public Animal(int mAnimalIcon, int mAnimalPicture, int mAnimalPicture2, int mAnimalPicture3, int mAnimalPicture4, int mAnimalPicture5, int mAnimalPicture6, int mAnimalPicture7, int mAnimalPicture8, int mAnimalPicture9, int mAnimalPicture10, int mAnimalSound, int mAnimalSound2, int mAnimalSound3, String mAnimalName, int mAnimalVoice, int mAnimalBlack, int mBackground, int iconsBig) {
        this.mAnimalIcon = mAnimalIcon;
        this.mAnimalPicture = mAnimalPicture;
        this.mAnimalPicture2 = mAnimalPicture2;
        this.mAnimalPicture3 = mAnimalPicture3;
        this.mAnimalPicture4 = mAnimalPicture4;
        this.mAnimalPicture5 = mAnimalPicture5;
        this.mAnimalPicture6 = mAnimalPicture6;
        this.mAnimalPicture7 = mAnimalPicture7;
        this.mAnimalPicture8 = mAnimalPicture8;
        this.mAnimalPicture9 = mAnimalPicture9;
        this.mAnimalPicture10 = mAnimalPicture10;
        this.mAnimalSound = mAnimalSound;
        this.mAnimalSound2 = mAnimalSound2;
        this.mAnimalSound3 = mAnimalSound3;
        this.mAnimalName = mAnimalName;
        this.mAnimalVoice = mAnimalVoice;
        this.mAnimalBlack = mAnimalBlack;
        this.mBackground = mBackground;
        this.iconsBig = iconsBig;
    }

    public int getmAnimalIcon() {
        return mAnimalIcon;
    }

    public int getmAnimalPicture() {
        return mAnimalPicture;
    }

    public int getmAnimalPicture2() {
        return mAnimalPicture2;
    }

    public int getmAnimalPicture3() {
        return mAnimalPicture3;
    }

    public int getmAnimalPicture4() {
        return mAnimalPicture4;
    }

    public int getmAnimalPicture5() {
        return mAnimalPicture5;
    }

    public int getmAnimalPicture6() {
        return mAnimalPicture6;
    }

    public int getmAnimalPicture7() {
        return mAnimalPicture7;
    }

    public int getmAnimalPicture8() {
        return mAnimalPicture8;
    }

    public int getmAnimalPicture9() {
        return mAnimalPicture9;
    }

    public int getmAnimalPicture10() {
        return mAnimalPicture10;
    }

    public int getmAnimalSound() {
        return mAnimalSound;
    }

    public int getmAnimalSound2() {
        return mAnimalSound2;
    }

    public int getmAnimalSound3() {
        return mAnimalSound3;
    }

    public String getmAnimalName() {
        return mAnimalName;
    }

    public int getmAnimalVoice() {
        return mAnimalVoice;
    }

    public int getmAnimalBlack() {
        return mAnimalBlack;
    }

    public int getmBackground() {
        return mBackground;
    }

    public int getIconsBig() {
        return iconsBig;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mAnimalIcon);
        dest.writeInt(mAnimalPicture);
        dest.writeInt(mAnimalPicture2);
        dest.writeInt(mAnimalPicture3);
        dest.writeInt(mAnimalPicture4);
        dest.writeInt(mAnimalPicture5);
        dest.writeInt(mAnimalPicture6);
        dest.writeInt(mAnimalPicture7);
        dest.writeInt(mAnimalPicture8);
        dest.writeInt(mAnimalPicture9);
        dest.writeInt(mAnimalPicture10);
        dest.writeInt(mAnimalSound);
        dest.writeInt(mAnimalSound2);
        dest.writeInt(mAnimalSound3);
        dest.writeString(mAnimalName);
        dest.writeInt(mAnimalVoice);
        dest.writeInt(mAnimalBlack);
        dest.writeInt(mBackground);
        dest.writeInt(iconsBig);
    }
}
