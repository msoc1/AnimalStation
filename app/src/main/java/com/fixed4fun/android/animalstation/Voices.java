package com.fixed4fun.android.animalstation;


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


import java.util.ArrayList;
import java.util.List;

public class Voices {

    public static List<Integer> getVoices() {
        ArrayList<Integer> voices = new ArrayList<>();

        if (MainActivity.languageNumber == 0) {
            //POLISH LANGUAGE//

            voices.add(R.raw.dog_polish);
            voices.add(R.raw.cat_polish);
            voices.add(R.raw.cow_polish);
            voices.add(R.raw.horse_polish);
            voices.add(R.raw.pig_polish);
            voices.add(R.raw.koza_polish);
            voices.add(R.raw.fox_polish);
            voices.add(R.raw.sheep_polish);
            voices.add(R.raw.chicken_polish);
            voices.add(R.raw.owl_polish);
            voices.add(R.raw.elephant_polish);
            voices.add(R.raw.giraffe_polish);
            voices.add(R.raw.lion_polish);
            voices.add(R.raw.tiger_polish);
            voices.add(R.raw.rhino_polish);
            voices.add(R.raw.peanguin_polish);
            voices.add(R.raw.bear_polish);
            voices.add(R.raw.panda_polish);
            voices.add(R.raw.monkey_polish);
            voices.add(R.raw.hippo_polish);
            voices.add(R.raw.kangaroo_polish);
            voices.add(R.raw.dolphin_polish);
            voices.add(R.raw.rabbit_polish);
            voices.add(R.raw.koala_polish);
        } else if (MainActivity.languageNumber == 1) {
            //ENGLISH LANGUAGE//

            voices.add(R.raw.dog_english);
            voices.add(R.raw.cat_english);
            voices.add(R.raw.cow_english);
            voices.add(R.raw.horse_english);
            voices.add(R.raw.pig_english);
            voices.add(R.raw.goat_english);
            voices.add(R.raw.fox_english);
            voices.add(R.raw.sheep_english);
            voices.add(R.raw.chicken_english);
            voices.add(R.raw.owl_english);
            voices.add(R.raw.elephant_english);
            voices.add(R.raw.giraffe_english);
            voices.add(R.raw.lion_english);
            voices.add(R.raw.tiger_english);
            voices.add(R.raw.rhinoceros_english);
            voices.add(R.raw.penguin_english);
            voices.add(R.raw.bear_english);
            voices.add(R.raw.panda_english);
            voices.add(R.raw.monkey_english);
            voices.add(R.raw.hippo_english);
            voices.add(R.raw.kangaroo_english);
            voices.add(R.raw.dolphin_english);
            voices.add(R.raw.rabbit_english);
            voices.add(R.raw.koala_english);
        } else if (MainActivity.languageNumber == 2) {
            //SPANISH LANGUAGE//

            voices.add(R.raw.dog_spanish);
            voices.add(R.raw.cat_spanish);
            voices.add(R.raw.cow_spanish);
            voices.add(R.raw.horse_spanish);
            voices.add(R.raw.pig_spanish);
            voices.add(R.raw.goat_spanish);
            voices.add(R.raw.fox_spanish);
            voices.add(R.raw.sheep_spanish);
            voices.add(R.raw.chicken_spanish);
            voices.add(R.raw.owl_spanish);
            voices.add(R.raw.elephant_spanish);
            voices.add(R.raw.giraffe_spanish);
            voices.add(R.raw.lion_spanish);
            voices.add(R.raw.tiger_spanish);
            voices.add(R.raw.rhinoceros_spanish);
            voices.add(R.raw.penguin_spanish);
            voices.add(R.raw.bear_spanish);
            voices.add(R.raw.panda_spanish);
            voices.add(R.raw.monkey_spanish);
            voices.add(R.raw.hippo_spanish);
            voices.add(R.raw.kangaroo_spanish);
            voices.add(R.raw.dolphin_spanish);
            voices.add(R.raw.rabbit_spanish);
            voices.add(R.raw.koala_spanish);
        } else if (MainActivity.languageNumber == 3) {
            //FRENCH LANGUAGE//

            voices.add(R.raw.dog_french);
            voices.add(R.raw.cat_french);
            voices.add(R.raw.cow_french);
            voices.add(R.raw.horse_french);
            voices.add(R.raw.pig_french);
            voices.add(R.raw.goat_french);
            voices.add(R.raw.fox_french);
            voices.add(R.raw.sheep_french);
            voices.add(R.raw.chicken_french);
            voices.add(R.raw.owl_french);
            voices.add(R.raw.elephant_french);
            voices.add(R.raw.giraffe_french);
            voices.add(R.raw.lion_french);
            voices.add(R.raw.tiger_french);
            voices.add(R.raw.rhinoceros_french);
            voices.add(R.raw.penguin_french);
            voices.add(R.raw.bear_french);
            voices.add(R.raw.panda_french);
            voices.add(R.raw.monkey_french);
            voices.add(R.raw.hippo_french);
            voices.add(R.raw.kangaroo_french);
            voices.add(R.raw.dolphin_french);
            voices.add(R.raw.rabbit_french);
            voices.add(R.raw.koala_french);
        } else if (MainActivity.languageNumber == 4) {
            //ITALIAN LANGUAGE//

            voices.add(R.raw.dog_italian);
            voices.add(R.raw.cat_italian);
            voices.add(R.raw.cow_italian);
            voices.add(R.raw.horse_italian);
            voices.add(R.raw.pig_italian);
            voices.add(R.raw.goat_italian);
            voices.add(R.raw.fox_italian);
            voices.add(R.raw.sheep_italian);
            voices.add(R.raw.chicken_italian);
            voices.add(R.raw.owl_italian);
            voices.add(R.raw.elephant_italian);
            voices.add(R.raw.giraffe_italian);
            voices.add(R.raw.lion_italian);
            voices.add(R.raw.tiger_italian);
            voices.add(R.raw.rhinocerous_italian);
            voices.add(R.raw.penguin_italian);
            voices.add(R.raw.bear_italian);
            voices.add(R.raw.panda_italian);
            voices.add(R.raw.monkey_italian);
            voices.add(R.raw.hippo_italian);
            voices.add(R.raw.kangaroo_italian);
            voices.add(R.raw.dolphin_italian);
            voices.add(R.raw.rabbit_italian);
            voices.add(R.raw.koala_italian);
        } else if (MainActivity.languageNumber == 5) {
            //UKRAINIAN LANGUAGE//

            voices.add(R.raw.dog_ukrainian);
            voices.add(R.raw.cat_ukrainian);
            voices.add(R.raw.cow_ukrainian);
            voices.add(R.raw.horse_ukrainian);
            voices.add(R.raw.pig_ukrainian);
            voices.add(R.raw.goat_ukrainian);
            voices.add(R.raw.fox_ukrainian);
            voices.add(R.raw.sheep_ukrainian);
            voices.add(R.raw.chicken_ukrainian);
            voices.add(R.raw.owl_ukrainian);
            voices.add(R.raw.elephant_ukrainian);
            voices.add(R.raw.giraffe_ukrainian);
            voices.add(R.raw.lion_ukrainian);
            voices.add(R.raw.tiger_ukrainian);
            voices.add(R.raw.rhinocerous_ukrainian);
            voices.add(R.raw.penguin_ukrainian);
            voices.add(R.raw.bear_ukrainian);
            voices.add(R.raw.panda_ukrainian);
            voices.add(R.raw.monkey_ukrainian);
            voices.add(R.raw.hippo_ukrainian);
            voices.add(R.raw.kangaroo_ukrainian);
            voices.add(R.raw.dolphin_ukrainian);
            voices.add(R.raw.rabbit_ukrainian);
            voices.add(R.raw.koala_ukrainian);
        }
        return voices;
    }
}
