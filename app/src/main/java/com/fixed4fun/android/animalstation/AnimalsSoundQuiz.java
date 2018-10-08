package com.fixed4fun.android.animalstation;

/*
    when adding animals CHANGE  background.setOnTouchListener on ChosenAnimal "onSwipeLeft"!!!
 */

import java.util.ArrayList;
import java.util.List;

/*
    list of animals should go:
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
public class AnimalsSoundQuiz {
    public static int whichPictureToUse;

    public static List<Animal> getAnimals() {
        ArrayList<Animal> animals = new ArrayList<>();


        Translations.getTranslations();

        /*dog*/
        animals.add(new Animal(R.drawable.dog_icon, R.drawable.dog1, R.drawable.dog2, R.drawable.dog3,
                R.drawable.dog4, R.drawable.dog5, R.drawable.dog6, R.drawable.dog7, R.drawable.dog8,
                R.drawable.dog9, R.drawable.dog10, R.raw.dog1, R.raw.dog2, R.raw.dog3, Translations.getTranslations().get(0).toUpperCase(),
                Voices.getVoices().get(0), R.drawable.dog_black, R.drawable.meadow2, R.drawable.dog_big));

        /*cat*/
        animals.add(new Animal(R.drawable.cat_icon, R.drawable.cat1, R.drawable.cat2, R.drawable.cat3,
                R.drawable.cat4, R.drawable.cat5, R.drawable.cat6, R.drawable.cat7, R.drawable.cat8,
                R.drawable.cat9, R.drawable.cat10, R.raw.cat1, R.raw.cat2, R.raw.cat3, Translations.getTranslations().get(1).toUpperCase(),
                Voices.getVoices().get(1), R.drawable.cat_black, R.drawable.island, R.drawable.cat_big));

        /*   cow */
        animals.add(new Animal(R.drawable.cow_icon, R.drawable.krowa1, R.drawable.krowa2, R.drawable.krowa3,
                R.drawable.krowa4, R.drawable.krowa5, R.drawable.krowa6, R.drawable.krowa7, R.drawable.krowa8,
                R.drawable.krowa9, R.drawable.krowa10, R.raw.cow1, R.raw.cow2, R.raw.cow3, Translations.getTranslations().get(2).toUpperCase(),
                Voices.getVoices().get(2), R.drawable.cow_black, R.drawable.meadow, R.drawable.cow_big));

        /* horse  */
        animals.add(new Animal(R.drawable.horse_icon, R.drawable.kon1, R.drawable.kon2, R.drawable.kon3,
                R.drawable.kon4, R.drawable.kon5, R.drawable.kon6, R.drawable.kon7, R.drawable.kon8,
                R.drawable.kon9, R.drawable.kon10, R.raw.horse1, R.raw.horse2, R.raw.horse3, Translations.getTranslations().get(3).toUpperCase(),
                Voices.getVoices().get(3), R.drawable.horse_black, R.drawable.liscie, R.drawable.horse_big));

        /*  pig */
        animals.add(new Animal(R.drawable.pig_icon, R.drawable.swinka1, R.drawable.swinka2, R.drawable.swinka3,
                R.drawable.swinka4, R.drawable.swinka5, R.drawable.swinka6, R.drawable.swinka7, R.drawable.swinka8,
                R.drawable.swinka9, R.drawable.swinka10, R.raw.pig1, R.raw.pig2, R.raw.pig3, Translations.getTranslations().get(4).toUpperCase(),
                Voices.getVoices().get(4), R.drawable.pig_black, R.drawable.meadow, R.drawable.pig_big));

        /*  goat */
        animals.add(new Animal(R.drawable.goat_icon, R.drawable.koza1, R.drawable.koza2, R.drawable.koza3,
                R.drawable.koza4, R.drawable.koza5, R.drawable.koza6, R.drawable.koza7, R.drawable.koza8,
                R.drawable.koza9, R.drawable.koza10, R.raw.goat1, R.raw.goat2, R.raw.goat3, Translations.getTranslations().get(5).toUpperCase(),
                Voices.getVoices().get(5), R.drawable.goat_black, R.drawable.liscie, R.drawable.goat_big));

        /*  sheep */
        animals.add(new Animal(R.drawable.sheep_icon, R.drawable.owca1, R.drawable.owca2, R.drawable.owca3,
                R.drawable.owca4, R.drawable.owca5, R.drawable.owca6, R.drawable.owca7, R.drawable.owca8,
                R.drawable.owca9, R.drawable.owca10, R.raw.owca1, R.raw.owca2, R.raw.owca3, Translations.getTranslations().get(7).toUpperCase(),
                Voices.getVoices().get(7), R.drawable.sheep_black, R.drawable.meadow2, R.drawable.sheep_big));

        /*  chicken */
        animals.add(new Animal(R.drawable.chicken_icon, R.drawable.kura1, R.drawable.kura2, R.drawable.kura3,
                R.drawable.kura4, R.drawable.kura5, R.drawable.kura6, R.drawable.kura7, R.drawable.kura8,
                R.drawable.kura9, R.drawable.kura10, R.raw.chicken1, R.raw.chicken2, R.raw.chicken3, Translations.getTranslations().get(8).toUpperCase(),
                Voices.getVoices().get(8), R.drawable.chicken_black, R.drawable.meadow, R.drawable.chicken_big));

        /*  owl */
        animals.add(new Animal(R.drawable.owl_icon, R.drawable.owl1, R.drawable.owl2, R.drawable.owl3,
                R.drawable.owl4, R.drawable.owl5, R.drawable.owl6, R.drawable.owl7, R.drawable.owl8,
                R.drawable.owl9, R.drawable.owl10, R.raw.owl1, R.raw.owl2, R.raw.owl3, Translations.getTranslations().get(9).toUpperCase(),
                Voices.getVoices().get(9), R.drawable.owl_black, R.drawable.jungle, R.drawable.owl_big));

        /*  elephant */
        animals.add(new Animal(R.drawable.elephant_icon, R.drawable.slon1, R.drawable.slon2, R.drawable.slon3,
                R.drawable.slon4, R.drawable.slon5, R.drawable.slon6, R.drawable.slon7, R.drawable.slon8,
                R.drawable.slon9, R.drawable.slon10, R.raw.elephant1, R.raw.elephant2, R.raw.elephant3, Translations.getTranslations().get(10).toUpperCase(),
                Voices.getVoices().get(10), R.drawable.elephant_black, R.drawable.savanna, R.drawable.elephatn_big));

        /*  lion */
        animals.add(new Animal(R.drawable.lion_icon, R.drawable.lew1, R.drawable.lew2, R.drawable.lew3,
                R.drawable.lew4, R.drawable.lew5, R.drawable.lew6, R.drawable.lew7, R.drawable.lew8,
                R.drawable.lew9, R.drawable.lew10, R.raw.lion1, R.raw.lion2, R.raw.lion3, Translations.getTranslations().get(12).toUpperCase(),
                Voices.getVoices().get(12), R.drawable.lino_black, R.drawable.savanna, R.drawable.lion_big));

        /*  tiger */
        animals.add(new Animal(R.drawable.tiger_icon, R.drawable.tygrys1, R.drawable.tygrys2, R.drawable.tygrys3,
                R.drawable.tygrys4, R.drawable.tygrys5, R.drawable.tygrys6, R.drawable.tygrys7, R.drawable.tygrys8,
                R.drawable.tygrys9, R.drawable.tygrys10, R.raw.tiger1, R.raw.tiger2, R.raw.tiger3, Translations.getTranslations().get(13).toUpperCase(),
                Voices.getVoices().get(13), R.drawable.tiger_black, R.drawable.jungle, R.drawable.tiger_big));

        /*  rhino */
        animals.add(new Animal(R.drawable.rhino_icon, R.drawable.noso1, R.drawable.noso2, R.drawable.noso3,
                R.drawable.noso4, R.drawable.noso5, R.drawable.noso6, R.drawable.noso7, R.drawable.noso8,
                R.drawable.noso9, R.drawable.noso10, R.raw.rhino1, R.raw.rhino2, R.raw.rhino3, Translations.getTranslations().get(14).toUpperCase(),
                Voices.getVoices().get(14), R.drawable.rhino_black, R.drawable.savanna, R.drawable.rhino_big));

        /*  penguin */
        animals.add(new Animal(R.drawable.penguin_icon, R.drawable.ping1, R.drawable.ping2, R.drawable.ping3,
                R.drawable.ping4, R.drawable.ping5, R.drawable.ping6, R.drawable.ping7, R.drawable.ping8,
                R.drawable.ping9, R.drawable.ping10, R.raw.penguin, R.raw.penguin2, R.raw.penguin23, Translations.getTranslations().get(15).toUpperCase(),
                Voices.getVoices().get(15), R.drawable.penguin_black, R.drawable.ice, R.drawable.penguin_big));

        /*  bear */
        animals.add(new Animal(R.drawable.bear_icon, R.drawable.mis1, R.drawable.mis2, R.drawable.mis3,
                R.drawable.mis4, R.drawable.mis5, R.drawable.mis6, R.drawable.mis7, R.drawable.mis8,
                R.drawable.mis9, R.drawable.mis10, R.raw.bear1, R.raw.bear2, R.raw.bear3, Translations.getTranslations().get(16).toUpperCase(),
                Voices.getVoices().get(16), R.drawable.bear_black, R.drawable.forrest2, R.drawable.penguin_big));

        /*  panda */
        animals.add(new Animal(R.drawable.panda_icon, R.drawable.panda1, R.drawable.panda2, R.drawable.panda3,
                R.drawable.panda4, R.drawable.panda5, R.drawable.panda6, R.drawable.panda7, R.drawable.panda8,
                R.drawable.panda9, R.drawable.panda10, R.raw.panda1, R.raw.panda2, R.raw.panda3, Translations.getTranslations().get(17).toUpperCase(),
                Voices.getVoices().get(17), R.drawable.panda_black, R.drawable.forrest3, R.drawable.panda_big));

        /*  monkey */
        animals.add(new Animal(R.drawable.monkey_icon, R.drawable.malpa1, R.drawable.malpa2, R.drawable.malpa3,
                R.drawable.malpa4, R.drawable.malpa5, R.drawable.malpa6, R.drawable.malpa7, R.drawable.malpa8,
                R.drawable.malpa9, R.drawable.malpa10, R.raw.monkey3, R.raw.monkey1, R.raw.monkey2, Translations.getTranslations().get(18).toUpperCase(),
                Voices.getVoices().get(18), R.drawable.monkey_black, R.drawable.jungle, R.drawable.monkey_big));

        /*  hippo */
        animals.add(new Animal(R.drawable.hippo_icon, R.drawable.hippo1, R.drawable.hippo2, R.drawable.hippo3,
                R.drawable.hippo4, R.drawable.hippo5, R.drawable.hippo6, R.drawable.hippo7, R.drawable.hippo8,
                R.drawable.hippo9, R.drawable.hippo10, R.raw.hippo1, R.raw.hippo2, R.raw.hippo3, Translations.getTranslations().get(19).toUpperCase(),
                Voices.getVoices().get(19), R.drawable.hippo_black, R.drawable.forrest2, R.drawable.hippo_big));

        /*  dolphin */
        animals.add(new Animal(R.drawable.dolphin_icon, R.drawable.dolphin1, R.drawable.dolphin2, R.drawable.dolphin3,
                R.drawable.dolphin4, R.drawable.dolphin5, R.drawable.dolphin6, R.drawable.dolphin7, R.drawable.dolphin8,
                R.drawable.dolphin9, R.drawable.dolphin10, R.raw.dolphin1, R.raw.dolphin2, R.raw.dolphin3, Translations.getTranslations().get(21).toUpperCase(),
                Voices.getVoices().get(21), R.drawable.dolphin_black, R.drawable.ocean, R.drawable.dolphin_big));

        /*  koala */
        animals.add(new Animal(R.drawable.koala_icon, R.drawable.koala1, R.drawable.koala2, R.drawable.koala3,
                R.drawable.koala4, R.drawable.koala5, R.drawable.koala6, R.drawable.koala7, R.drawable.koala8,
                R.drawable.koala9, R.drawable.koala10, R.raw.koala1, R.raw.koala2, R.raw.koala3, Translations.getTranslations().get(23).toUpperCase(),
                Voices.getVoices().get(23), R.drawable.koala_black, R.drawable.jungle, R.drawable.koala_big));


        return animals;
    }

    public static int whichpicture(ArrayList<Animal> zwierzaki, int i) {
        int imagesToUse = 0;

        if (whichPictureToUse == 0) {
            imagesToUse = zwierzaki.get(i).getmAnimalPicture();
        } else if (whichPictureToUse == 1) {
            imagesToUse = zwierzaki.get(i).getmAnimalPicture2();
        } else if (whichPictureToUse == 2) {
            imagesToUse = zwierzaki.get(i).getmAnimalPicture3();
        } else if (whichPictureToUse == 3) {
            imagesToUse = zwierzaki.get(i).getmAnimalPicture4();
        } else if (whichPictureToUse == 4) {
            imagesToUse = zwierzaki.get(i).getmAnimalPicture5();
        } else if (whichPictureToUse == 5) {
            imagesToUse = zwierzaki.get(i).getmAnimalPicture6();
        } else if (whichPictureToUse == 6) {
            imagesToUse = zwierzaki.get(i).getmAnimalPicture7();
        } else if (whichPictureToUse == 7) {
            imagesToUse = zwierzaki.get(i).getmAnimalPicture8();
        } else if (whichPictureToUse == 8) {
            imagesToUse = zwierzaki.get(i).getmAnimalPicture9();
        } else if (whichPictureToUse == 9) {
            imagesToUse = zwierzaki.get(i).getmAnimalPicture10();
        } else if (whichPictureToUse == 10) {
            imagesToUse = zwierzaki.get(i).getmAnimalPicture();
        }
        return imagesToUse;
    }

}
