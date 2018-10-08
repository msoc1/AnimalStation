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

public class Translations {

    public static List<String> getTranslations() {
        ArrayList<String> translations = new ArrayList<>();

        if (MainActivity.languageNumber == 0) {
            //POLISH LANGUAGE//

            translations.add("PIES");
            translations.add("KOT");
            translations.add("KROWA");
            translations.add("KOŃ");
            translations.add("ŚWINIA");
            translations.add("KOZA");
            translations.add("LIS");
            translations.add("OWCA");
            translations.add("KURA");
            translations.add("SOWA");
            translations.add("SŁOŃ");
            translations.add("ŻYRAFA");
            translations.add("LEW");
            translations.add("TYRGYS");
            translations.add("NOSOROŻEC");
            translations.add("PINGWIN");
            translations.add("NIEDŹWIEDŹ");
            translations.add("PANDA");
            translations.add("MAŁPA");
            translations.add("HIPOPOTAM");
            translations.add("KANGUR");
            translations.add("DELFIN");
            translations.add("KRÓLIK");
            translations.add("KOALA");

        } else if (MainActivity.languageNumber == 1) {
            //ENGLISH LANGUAGE//

            translations.add("DOG");
            translations.add("CAT");
            translations.add("cow");
            translations.add("horse");
            translations.add("pig");
            translations.add("goat");
            translations.add("fox");
            translations.add("sheep");
            translations.add("chicken");
            translations.add("owl");
            translations.add("elephant");
            translations.add("giraffe");
            translations.add("lion");
            translations.add("tiger");
            translations.add("rhinoceros");
            translations.add("penguin");
            translations.add("bear");
            translations.add("panda");
            translations.add("monkey");
            translations.add("hippo");
            translations.add("kangaroo");
            translations.add("dolphin");
            translations.add("rabbit");
            translations.add("KOALA");
        } else if (MainActivity.languageNumber == 2) {
            //SPANISH LANGUAGE//

            translations.add("perro");
            translations.add("gato");
            translations.add("vaca");
            translations.add("caballo");
            translations.add("cerdo");
            translations.add("cabra");
            translations.add("zorro");
            translations.add("oveja");
            translations.add("gallina");
            translations.add("búho");
            translations.add("elefante");
            translations.add("jirafa");
            translations.add("león");
            translations.add("tigre");
            translations.add("rinoceronte");
            translations.add("pingüino");
            translations.add("soportar");
            translations.add("panda");
            translations.add("mono");
            translations.add("hipopótamo");
            translations.add("canguro");
            translations.add("delfín");
            translations.add("conejo");
            translations.add("koala");
        } else if (MainActivity.languageNumber == 3) {
            //FRENCH LANGUAGE//

            translations.add("chien");
            translations.add("chat");
            translations.add("vache");
            translations.add("cheval");
            translations.add("porc");
            translations.add("chèvre");
            translations.add("renard");
            translations.add("mouton");
            translations.add("poule");
            translations.add("hibou");
            translations.add("éléphant");
            translations.add("girafe");
            translations.add("lion");
            translations.add("tigre");
            translations.add("rhinocéros");
            translations.add("manchot");
            translations.add("porter");
            translations.add("panda");
            translations.add("singe");
            translations.add("hippopotame");
            translations.add("kangourou");
            translations.add("dauphin");
            translations.add("lapin");
            translations.add("koala");
        } else if (MainActivity.languageNumber == 4) {
            //ITALIAN LANGUAGE//

            translations.add("cane");
            translations.add("gatto");
            translations.add("mucca");
            translations.add("cavallo");
            translations.add("maiale");
            translations.add("capra");
            translations.add("volpe");
            translations.add("pecora");
            translations.add("gallina");
            translations.add("gufo");
            translations.add("elefante");
            translations.add("giraffa");
            translations.add("leone");
            translations.add("tigre");
            translations.add("rinoceronte");
            translations.add("pinguino");
            translations.add("sopportare");
            translations.add("panda");
            translations.add("scimmia");
            translations.add("ippopotamo");
            translations.add("canguro");
            translations.add("delfino");
            translations.add("coniglio");
            translations.add("koala");
        } else if (MainActivity.languageNumber == 5) {
            //UKRAINIAN LANGUAGE//

            translations.add("собака");
            translations.add("кіт");
            translations.add("корова");
            translations.add("кінь");
            translations.add("свиня");
            translations.add("коза");
            translations.add("лисиця");
            translations.add("вівця");
            translations.add("курка");
            translations.add("пугач");
            translations.add("слон");
            translations.add("жираф");
            translations.add("лев");
            translations.add("тигр");
            translations.add("носоріг");
            translations.add("пінгвін");
            translations.add("нести");
            translations.add("панда");
            translations.add("мавпа");
            translations.add("гіпопотам");
            translations.add("кенгуру");
            translations.add("дельфін");
            translations.add("кролик");
            translations.add("коала");
        }


        return translations;
    }


}
