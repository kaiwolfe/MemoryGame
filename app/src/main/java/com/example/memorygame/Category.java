package com.example.memorygame;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

class Category {
    static int cardBack;
    static int cardBlank;

    private ArrayList<Card> cardTextures;

    private ArrayList<ArrayList<Card>> animalCardSet;
    private ArrayList<ArrayList<Card>> foodCardSet;
    private ArrayList<ArrayList<Card>> natureCardSet;
    private ArrayList<ArrayList<Card>> wordCardSet;
    private ArrayList<ArrayList<Card>> additionCardSet;
    private ArrayList<ArrayList<Card>> subtractionCardSet;
    private ArrayList<ArrayList<Card>> randomCardSet;



    ArrayList<Word> easyWordList = new ArrayList<Word>();
    ArrayList<Word> normalWordList = new ArrayList<Word>();
    ArrayList<Word> hardWordList = new ArrayList<Word>();

    ArrayList<Equation> equationList = new ArrayList<Equation>();


    public Category(int cardBack, int cardBlank) {
        Category.cardBack = cardBack;
        Category.cardBlank = cardBlank;
    }

    public ArrayList<Card> getAnimalCards(int numSets, String difficulty) {
        ArrayList<Card> animalCards = null;
        populateAnimalCards();
        switch (difficulty) {
            case "easy":
                ArrayList<Card> easySet = animalCardSet.get(0);
                Log.d("GET ANIMAL CARDS:", " Start");
                Log.d("GET ANIMAL CARDS", "Number sets: " + numSets);
                animalCards = randomSubset(easySet, numSets);
                break;
            case "medium":
                if (numSets > 6) {
                    animalCards = randomSubset(animalCardSet.get(1), numSets);
                } else {
                    int pickSet = new Random().nextInt(2);
                    if (pickSet == 0)
                        animalCards = randomSubset(animalCardSet.get(1), numSets);
                    else
                        animalCards = randomSubset(animalCardSet.get(2), numSets);
                }
                break;
            case "hard":
                animalCards = randomSubset(animalCardSet.get(3), numSets);
                break;
        }
        return animalCards;
    }

    public ArrayList<Card> getFoodCards(int numSets, String difficulty) {
        ArrayList<Card> foodCards = null;
        populateFoodCards();
        switch (difficulty) {
            case "easy":
                foodCards = randomSubset(foodCardSet.get(0), numSets);
                break;
            case "medium":
                int pickSet = new Random().nextInt(2);
                if (pickSet == 0)
                    foodCards = randomSubset(foodCardSet.get(1), numSets);
                else
                    foodCards = randomSubset(foodCardSet.get(2), numSets);
                break;
            case "hard":
                foodCards = randomSubset(foodCardSet.get(3), numSets);
                break;
        }
        return foodCards;
    }

    public ArrayList<Card> getNatureCards(int numSets, String difficulty) {
        ArrayList<Card> natureCards = null;
        populateNatureCards();
        switch (difficulty) {
            case "easy":
                natureCards = randomSubset(natureCardSet.get(0), numSets);
                break;
            case "medium":
                natureCards = randomSubset(natureCardSet.get(1), numSets);
                break;
            case "hard":
                natureCards = randomSubset(natureCardSet.get(2), numSets);
                break;
        }
        return natureCards;
    }

    public ArrayList<Card> getWordCards(Context c, int numSets, String difficulty) throws IOException {
        ArrayList<Card> wordCards = null;
        populateWordCards(c);
        switch (difficulty) {
            case "easy":
                wordCards = randomSubset(wordCardSet.get(0), numSets);
                break;
            case "medium":
                wordCards = randomSubset(wordCardSet.get(1), numSets);
                break;
            case "hard":
                wordCards = randomSubset(wordCardSet.get(2), numSets);
                break;
        }
        return wordCards;
    }

    public ArrayList<Card> getAdditionCards(int numOfPairs, String difficulty){
        ArrayList<Card> mathCards = null;
        populateAdditionCards(numOfPairs);
        switch (difficulty) {
            case "easy":
                mathCards = randomSubset(additionCardSet.get(0), numOfPairs);
                break;
            case "medium":
                mathCards = randomSubset(additionCardSet.get(1), numOfPairs);
                break;
            case "hard":
                mathCards = randomSubset(additionCardSet.get(2), numOfPairs);
                break;
        }
        return mathCards;
    }

    public ArrayList<Card> getSubtracitonCards (int numOfPairs, String difficulty){
        ArrayList<Card> mathCards = null;
        populateSubtractionCards(numOfPairs);
        switch (difficulty) {
            case "easy":
                mathCards = randomSubset(subtractionCardSet.get(0), numOfPairs);
                break;
            case "medium":
                mathCards = randomSubset(subtractionCardSet.get(1), numOfPairs);
                break;
            case "hard":
                mathCards = randomSubset(subtractionCardSet.get(2), numOfPairs);
                break;
        }
        return mathCards;
    }

    public ArrayList<Card> getRandomCards (int numOfPairs, String difficulty){
        ArrayList<Card> mathCards = null;
        populateRandomCards(numOfPairs);
        switch (difficulty) {
            case "easy":
                mathCards = randomSubset(randomCardSet.get(0), numOfPairs);
                break;
            case "medium":
                mathCards = randomSubset(randomCardSet.get(1), numOfPairs);
                break;
            case "hard":
                mathCards = randomSubset(randomCardSet.get(2), numOfPairs);
                break;
        }
        return mathCards;
    }


    private ArrayList<Card> randomSubset(ArrayList<Card> arrayList, int numSets) {
        ArrayList<Card> pullFrom = arrayList;
        ArrayList<Card> subset = new ArrayList<>();
        ArrayList<Card> mixedSubset = new ArrayList<>();
        do {
            int random = new Random().nextInt(arrayList.size());
//            Log.d("RANDOM SUBSET: ", "Random integer = " + random);
//            Log.d("RANDOM SUBSET: ", "Array size = " + pullFrom.size());
            Card randomCard = pullFrom.get(random);
            //Add the English destined word
            subset.add(randomCard);
//            Log.d("RANDOM SUBSET: ", "Subset size = " + subset.size());
            //Add the destined translated word
            Word word = randomCard.getWord();
            Equation eq = randomCard.getEquation();
            Card langCard = new Card(randomCard.getBackImage(), randomCard.getFrontImage(), word, eq, false);
            subset.add(langCard);
//            Log.d("RANDOM SUBSET: ", "Subset size = " + subset.size());
            pullFrom.remove(random);
//            Log.d("RANDOM SUBSET: ", "New array size = " + pullFrom.size());

        } while ((subset.size() != numSets * 2));

        ArrayList<Integer> indexAdded = new ArrayList<>();
        do {
//            Log.d("RANDOM SUBSET MIXING", "Start");
//            Log.d("RANDOM SUBSET MIXING", "Subset Size: " + subset.size());
            int random = new Random().nextInt(subset.size());
//            Log.d("RANDOM SUBSET MIXING", "Random int = " + random);
            boolean alreadyAdded = false;
            if (!indexAdded.isEmpty()) {
                for (int i = 0; i < indexAdded.size(); i++) {

                    if (random == indexAdded.get(i)) {
                        alreadyAdded = true;
                        break;
                    }
                }
            }
            if (!alreadyAdded) {
                indexAdded.add(random);
                mixedSubset.add(subset.get(random));
            }
        } while (indexAdded.size() != numSets * 2);

        return mixedSubset;
    }


    public ArrayList<Card> getCardTextures() {
        cardTextures = new ArrayList<>();
        cardTextures.add(new Card(R.drawable.card_blank, R.drawable.cardback_bluestripes));
        cardTextures.add(new Card(R.drawable.card_blank, R.drawable.cardback_brick));
        cardTextures.add(new Card(R.drawable.card_blank, R.drawable.cardback_classic));
        cardTextures.add(new Card(R.drawable.card_blank, R.drawable.cardback_deer));
        cardTextures.add(new Card(R.drawable.card_blank, R.drawable.cardback_fingerprint));
        cardTextures.add(new Card(R.drawable.card_blank, R.drawable.cardback_flower));
        cardTextures.add(new Card(R.drawable.card_blank, R.drawable.cardback_green));
        cardTextures.add(new Card(R.drawable.card_blank, R.drawable.cardback_koi));
        cardTextures.add(new Card(R.drawable.card_blank, R.drawable.cardback_orange));
        cardTextures.add(new Card(R.drawable.card_blank, R.drawable.cardback_pink));
        cardTextures.add(new Card(R.drawable.card_blank, R.drawable.cardback_purple));
        cardTextures.add(new Card(R.drawable.card_blank, R.drawable.cardback_sakura));
        cardTextures.add(new Card(R.drawable.card_blank, R.drawable.cardback_stripes));
        cardTextures.add(new Card(R.drawable.card_blank, R.drawable.cardback_tiedye));
        cardTextures.add(new Card(R.drawable.card_blank, R.drawable.cardback_tile));
        return cardTextures;
    }

    private void populateAnimalCards() {
        animalCardSet = new ArrayList<>();
        ArrayList<Card> easyAnimalCards = new ArrayList<>();
        ArrayList<Card> normalSet1AnimalCards = new ArrayList<>();
        ArrayList<Card> normalSet2AnimalCards = new ArrayList<>();
        ArrayList<Card> hardAnimalCards = new ArrayList<>();

        easyAnimalCards.add(new Card(R.drawable.card_animal_easy_bear, cardBack));
        easyAnimalCards.add(new Card(R.drawable.card_animal_easy_bird, cardBack));
        easyAnimalCards.add(new Card(R.drawable.card_animal_easy_cat, cardBack));
        easyAnimalCards.add(new Card(R.drawable.card_animal_easy_elephant, cardBack));
        easyAnimalCards.add(new Card(R.drawable.card_animal_easy_fox, cardBack));
        easyAnimalCards.add(new Card(R.drawable.card_animal_easy_leopard, cardBack));
        easyAnimalCards.add(new Card(R.drawable.card_animal_easy_owl, cardBack));
        easyAnimalCards.add(new Card(R.drawable.card_animal_easy_panda, cardBack));
        easyAnimalCards.add(new Card(R.drawable.card_animal_easy_parrot, cardBack));
        easyAnimalCards.add(new Card(R.drawable.card_animal_easy_pig, cardBack));
        easyAnimalCards.add(new Card(R.drawable.card_animal_easy_rhino, cardBack));
        easyAnimalCards.add(new Card(R.drawable.card_animal_easy_toucan, cardBack));

        normalSet1AnimalCards.add(new Card(R.drawable.card_animal_normal_set1_cat1, cardBack));
        normalSet1AnimalCards.add(new Card(R.drawable.card_animal_normal_set1_cat2, cardBack));
        normalSet1AnimalCards.add(new Card(R.drawable.card_animal_normal_set1_cat3, cardBack));
        normalSet1AnimalCards.add(new Card(R.drawable.card_animal_normal_set1_cat4, cardBack));
        normalSet1AnimalCards.add(new Card(R.drawable.card_animal_normal_set1_cat5, cardBack));
        normalSet1AnimalCards.add(new Card(R.drawable.card_animal_normal_set1_dog1, cardBack));
        normalSet1AnimalCards.add(new Card(R.drawable.card_animal_normal_set1_dog2, cardBack));
        normalSet1AnimalCards.add(new Card(R.drawable.card_animal_normal_set1_giraffe, cardBack));
        normalSet1AnimalCards.add(new Card(R.drawable.card_animal_normal_set1_hawk, cardBack));
        normalSet1AnimalCards.add(new Card(R.drawable.card_animal_normal_set1_lion, cardBack));
        normalSet1AnimalCards.add(new Card(R.drawable.card_animal_normal_set1_rooster, cardBack));
        normalSet1AnimalCards.add(new Card(R.drawable.card_animal_normal_set1_tiger, cardBack));

        normalSet2AnimalCards.add(new Card(R.drawable.card_animal_normal_set2_shark1, cardBack));
        normalSet2AnimalCards.add(new Card(R.drawable.card_animal_normal_set2_shark2, cardBack));
        normalSet2AnimalCards.add(new Card(R.drawable.card_animal_normal_set2_shark3, cardBack));
        normalSet2AnimalCards.add(new Card(R.drawable.card_animal_normal_set2_whale1, cardBack));
        normalSet2AnimalCards.add(new Card(R.drawable.card_animal_normal_set2_whale2, cardBack));
        normalSet2AnimalCards.add(new Card(R.drawable.card_animal_normal_set2_whale3, cardBack));

        hardAnimalCards.add(new Card(R.drawable.card_animal_hard_cat_black1, cardBack));
        hardAnimalCards.add(new Card(R.drawable.card_animal_hard_cat_black2, cardBack));
        hardAnimalCards.add(new Card(R.drawable.card_animal_hard_cat_tabby1, cardBack));
        hardAnimalCards.add(new Card(R.drawable.card_animal_hard_cat_tabby2, cardBack));
        hardAnimalCards.add(new Card(R.drawable.card_animal_hard_cat_white1, cardBack));
        hardAnimalCards.add(new Card(R.drawable.card_animal_hard_cat_white2, cardBack));
        hardAnimalCards.add(new Card(R.drawable.card_animal_hard_cat_gray1, cardBack));
        hardAnimalCards.add(new Card(R.drawable.card_animal_hard_cat_gray2, cardBack));
        hardAnimalCards.add(new Card(R.drawable.card_animal_hard_cat_tortie1, cardBack));
        hardAnimalCards.add(new Card(R.drawable.card_animal_hard_cat_tortie2, cardBack));
        hardAnimalCards.add(new Card(R.drawable.card_animal_hard_cat_calico1, cardBack));
        hardAnimalCards.add(new Card(R.drawable.card_animal_hard_cat_calico2, cardBack));


        animalCardSet.add(easyAnimalCards);
        animalCardSet.add(normalSet1AnimalCards);
        animalCardSet.add(normalSet2AnimalCards);
        animalCardSet.add(hardAnimalCards);
    }

    private void populateFoodCards() {
        foodCardSet = new ArrayList<>();
        ArrayList<Card> easyFoodCards = new ArrayList<>();
        ArrayList<Card> normalSet1FoodCards = new ArrayList<>();
        ArrayList<Card> normalSet2FoodCards = new ArrayList<>();
        ArrayList<Card> hardFoodCards = new ArrayList<>();

        easyFoodCards.add(new Card(R.drawable.card_food_easy_candy, cardBack));
        easyFoodCards.add(new Card(R.drawable.card_food_easy_chocolate, cardBack));
        easyFoodCards.add(new Card(R.drawable.card_food_easy_cookie, cardBack));
        easyFoodCards.add(new Card(R.drawable.card_food_easy_cupcake, cardBack));
        easyFoodCards.add(new Card(R.drawable.card_food_easy_donut, cardBack));
        easyFoodCards.add(new Card(R.drawable.card_food_easy_fruitsalad, cardBack));
        easyFoodCards.add(new Card(R.drawable.card_food_easy_icecream, cardBack));
        easyFoodCards.add(new Card(R.drawable.card_food_easy_milkshake, cardBack));
        easyFoodCards.add(new Card(R.drawable.card_food_easy_pie, cardBack));
        easyFoodCards.add(new Card(R.drawable.card_food_easy_popsicle, cardBack));

        normalSet1FoodCards.add(new Card(R.drawable.card_food_normal_set1_apples, cardBack));
        normalSet1FoodCards.add(new Card(R.drawable.card_food_normal_set1_banana, cardBack));
        normalSet1FoodCards.add(new Card(R.drawable.card_food_normal_set1_berries, cardBack));
        normalSet1FoodCards.add(new Card(R.drawable.card_food_normal_set1_carrot, cardBack));
        normalSet1FoodCards.add(new Card(R.drawable.card_food_normal_set1_cataloupe, cardBack));
        normalSet1FoodCards.add(new Card(R.drawable.card_food_normal_set1_cherry, cardBack));
        normalSet1FoodCards.add(new Card(R.drawable.card_food_normal_set1_kiwi, cardBack));
        normalSet1FoodCards.add(new Card(R.drawable.card_food_normal_set1_coconut, cardBack));
        normalSet1FoodCards.add(new Card(R.drawable.card_food_normal_set1_lemon, cardBack));
        normalSet1FoodCards.add(new Card(R.drawable.card_food_normal_set1_lettuce, cardBack));
        normalSet1FoodCards.add(new Card(R.drawable.card_food_normal_set1_mushroom, cardBack));
        normalSet1FoodCards.add(new Card(R.drawable.card_food_normal_set1_orange, cardBack));
        normalSet1FoodCards.add(new Card(R.drawable.card_food_normal_set1_pepper, cardBack));
        normalSet1FoodCards.add(new Card(R.drawable.card_food_normal_set1_pineapple, cardBack));
        normalSet1FoodCards.add(new Card(R.drawable.card_food_normal_set1_strawberry, cardBack));
        normalSet1FoodCards.add(new Card(R.drawable.card_food_normal_set1_watermelon, cardBack));
        normalSet1FoodCards.add(new Card(R.drawable.card_food_normal_set1_potato, cardBack));

        normalSet2FoodCards.add(new Card(R.drawable.card_food_normal_set2_burger, cardBack));
        normalSet2FoodCards.add(new Card(R.drawable.card_food_normal_set2_cheese, cardBack));
        normalSet2FoodCards.add(new Card(R.drawable.card_food_normal_set2_coffee, cardBack));
        normalSet2FoodCards.add(new Card(R.drawable.card_food_normal_set2_croissant, cardBack));
        normalSet2FoodCards.add(new Card(R.drawable.card_food_normal_set2_egg, cardBack));
        normalSet2FoodCards.add(new Card(R.drawable.card_food_normal_set2_fish, cardBack));
        normalSet2FoodCards.add(new Card(R.drawable.card_food_normal_set2_granolabar, cardBack));
        normalSet2FoodCards.add(new Card(R.drawable.card_food_normal_set2_hotdog, cardBack));
        normalSet2FoodCards.add(new Card(R.drawable.card_food_normal_set2_juice, cardBack));
        normalSet2FoodCards.add(new Card(R.drawable.card_food_normal_set2_kebabs, cardBack));
        normalSet2FoodCards.add(new Card(R.drawable.card_food_normal_set2_milk, cardBack));
        normalSet2FoodCards.add(new Card(R.drawable.card_food_normal_set2_pancakes, cardBack));
        normalSet2FoodCards.add(new Card(R.drawable.card_food_normal_set2_pizza, cardBack));
        normalSet2FoodCards.add(new Card(R.drawable.card_food_normal_set2_quesadilla, cardBack));
        normalSet2FoodCards.add(new Card(R.drawable.card_food_normal_set2_rice, cardBack));
        normalSet2FoodCards.add(new Card(R.drawable.card_food_normal_set2_salad, cardBack));
        normalSet2FoodCards.add(new Card(R.drawable.card_food_normal_set2_sandwich, cardBack));
        normalSet2FoodCards.add(new Card(R.drawable.card_food_normal_set2_soda, cardBack));
        normalSet2FoodCards.add(new Card(R.drawable.card_food_normal_set2_taco, cardBack));
        normalSet2FoodCards.add(new Card(R.drawable.card_food_normal_set2_tea1, cardBack));
        normalSet2FoodCards.add(new Card(R.drawable.card_food_normal_set2_tea2, cardBack));
        normalSet2FoodCards.add(new Card(R.drawable.card_food_normal_set2_waffle, cardBack));
        normalSet2FoodCards.add(new Card(R.drawable.card_food_normal_set2_water, cardBack));

        hardFoodCards.add(new Card(R.drawable.card_food_hard_sushi1, cardBack));
        hardFoodCards.add(new Card(R.drawable.card_food_hard_sushi2, cardBack));
        hardFoodCards.add(new Card(R.drawable.card_food_hard_sushi3, cardBack));
        hardFoodCards.add(new Card(R.drawable.card_food_hard_sushi4, cardBack));
        hardFoodCards.add(new Card(R.drawable.card_food_hard_sushi5, cardBack));
        hardFoodCards.add(new Card(R.drawable.card_food_hard_sushi6, cardBack));
        hardFoodCards.add(new Card(R.drawable.card_food_hard_sushi7, cardBack));
        hardFoodCards.add(new Card(R.drawable.card_food_hard_sushi8, cardBack));
        hardFoodCards.add(new Card(R.drawable.card_food_hard_sushi9, cardBack));
        hardFoodCards.add(new Card(R.drawable.card_food_hard_sushi10, cardBack));
        hardFoodCards.add(new Card(R.drawable.card_food_hard_sushi11, cardBack));
        hardFoodCards.add(new Card(R.drawable.card_food_hard_sushi2, cardBack));

        foodCardSet.add(easyFoodCards);
        foodCardSet.add(normalSet1FoodCards);
        foodCardSet.add(normalSet2FoodCards);
        foodCardSet.add(hardFoodCards);
    }

    private void populateNatureCards() {
        natureCardSet = new ArrayList<>();
        ArrayList<Card> easyNatureCards = new ArrayList<>();
        ArrayList<Card> normalNatureCards = new ArrayList<>();
        ArrayList<Card> hardNatureCards = new ArrayList<>();

        easyNatureCards.add(new Card(R.drawable.card_nature_easy_branch1, cardBack));
        easyNatureCards.add(new Card(R.drawable.card_nature_easy_branch2, cardBack));
        easyNatureCards.add(new Card(R.drawable.card_nature_easy_branch3, cardBack));
        easyNatureCards.add(new Card(R.drawable.card_nature_easy_flowers1, cardBack));
        easyNatureCards.add(new Card(R.drawable.card_nature_easy_flowers2, cardBack));
        easyNatureCards.add(new Card(R.drawable.card_nature_easy_lily, cardBack));
        easyNatureCards.add(new Card(R.drawable.card_nature_easy_log, cardBack));
        easyNatureCards.add(new Card(R.drawable.card_nature_easy_mountain, cardBack));
        easyNatureCards.add(new Card(R.drawable.card_nature_easy_mushroom1, cardBack));
        easyNatureCards.add(new Card(R.drawable.card_nature_easy_mushroom2, cardBack));
        easyNatureCards.add(new Card(R.drawable.card_nature_easy_mushroom3, cardBack));
        easyNatureCards.add(new Card(R.drawable.card_nature_easy_mushroom4, cardBack));
        easyNatureCards.add(new Card(R.drawable.card_nature_easy_roses1, cardBack));
        easyNatureCards.add(new Card(R.drawable.card_nature_easy_roses2, cardBack));
        easyNatureCards.add(new Card(R.drawable.card_nature_easy_roses3, cardBack));
        easyNatureCards.add(new Card(R.drawable.card_nature_easy_shell, cardBack));
        easyNatureCards.add(new Card(R.drawable.card_nature_easy_stump1, cardBack));
        easyNatureCards.add(new Card(R.drawable.card_nature_easy_stump2, cardBack));
        easyNatureCards.add(new Card(R.drawable.card_nature_easy_tree1, cardBack));
        easyNatureCards.add(new Card(R.drawable.card_nature_easy_tree2, cardBack));
        easyNatureCards.add(new Card(R.drawable.card_nature_easy_tree3, cardBack));
        easyNatureCards.add(new Card(R.drawable.card_nature_easy_waterfall, cardBack));
        easyNatureCards.add(new Card(R.drawable.card_nature_easy_wheat, cardBack));

        normalNatureCards.add(new Card(R.drawable.card_nature_normal_flower1, cardBack));
        normalNatureCards.add(new Card(R.drawable.card_nature_normal_flower2, cardBack));
        normalNatureCards.add(new Card(R.drawable.card_nature_normal_flower3, cardBack));
        normalNatureCards.add(new Card(R.drawable.card_nature_normal_flower4, cardBack));
        normalNatureCards.add(new Card(R.drawable.card_nature_normal_flower5, cardBack));
        normalNatureCards.add(new Card(R.drawable.card_nature_normal_flower6, cardBack));
        normalNatureCards.add(new Card(R.drawable.card_nature_normal_flower7, cardBack));
        normalNatureCards.add(new Card(R.drawable.card_nature_normal_flower8, cardBack));
        normalNatureCards.add(new Card(R.drawable.card_nature_normal_flower9, cardBack));
        normalNatureCards.add(new Card(R.drawable.card_nature_normal_flower10, cardBack));
        normalNatureCards.add(new Card(R.drawable.card_nature_normal_flower11, cardBack));
        normalNatureCards.add(new Card(R.drawable.card_nature_normal_flower12, cardBack));

        hardNatureCards.add(new Card(R.drawable.card_nature_hard_scene1, cardBack));
        hardNatureCards.add(new Card(R.drawable.card_nature_hard_scene2, cardBack));
        hardNatureCards.add(new Card(R.drawable.card_nature_hard_scene3, cardBack));
        hardNatureCards.add(new Card(R.drawable.card_nature_hard_scene4, cardBack));
        hardNatureCards.add(new Card(R.drawable.card_nature_hard_scene5, cardBack));
        hardNatureCards.add(new Card(R.drawable.card_nature_hard_scene6, cardBack));
        hardNatureCards.add(new Card(R.drawable.card_nature_hard_scene7, cardBack));
        hardNatureCards.add(new Card(R.drawable.card_nature_hard_scene8, cardBack));
        hardNatureCards.add(new Card(R.drawable.card_nature_hard_scene9, cardBack));

        natureCardSet.add(easyNatureCards);
        natureCardSet.add(normalNatureCards);
        natureCardSet.add(hardNatureCards);
    }

    private void populateWordCards(Context c) throws IOException {
        wordCardSet = new ArrayList<>();
        ArrayList<Card> easyWordCards = new ArrayList<>();
        ArrayList<Card> normalWordCards = new ArrayList<>();
        ArrayList<Card> hardWordCards = new ArrayList<>();
        String[] words = null;

        String difficulty = "easy";
        String fileName = difficulty + ".txt";
        AssetManager am = c.getAssets();
        InputStream is = am.open(fileName);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
        String line;

        //Loop to read through file line by line
        while ((line = buffer.readLine()) != null) {
            //Split line of text into parts and put into array
            words = line.split("\t");

            //Use array of words to create a new word object and add it to the list
            easyWordList.add(new Word(words[0], words[1], words[2], words[3]));
        }
        buffer.close();

        for (int i = 0; i < easyWordList.size(); i++) {
            easyWordCards.add(new Card(cardBlank, cardBack, easyWordList.get(i), true));
        }

        difficulty = "medium";
        fileName = difficulty + ".txt";
        am = c.getAssets();
        is = am.open(fileName);
        buffer = new BufferedReader(new InputStreamReader(is));

        //Loop to read through file line by line
        while ((line = buffer.readLine()) != null) {
            //Split line of text into parts and put into array
            words = line.split("\t");

            //Use array of words to create a new word object and add it to the list
            normalWordList.add(new Word(words[0], words[1], words[2], words[3]));
        }
        buffer.close();

        for (int i = 0; i < normalWordList.size(); i++) {
            normalWordCards.add(new Card(cardBlank, cardBack, normalWordList.get(i), true));
        }

        difficulty = "hard";
        fileName = difficulty + ".txt";
        am = c.getAssets();
        is = am.open(fileName);
        buffer = new BufferedReader(new InputStreamReader(is));

        //Loop to read through file line by line
        while ((line = buffer.readLine()) != null) {
            //Split line of text into parts and put into array
            words = line.split("\t");

            //Use array of words to create a new word object and add it to the list
            hardWordList.add(new Word(words[0], words[1], words[2], words[3]));
        }
        buffer.close();
        for (int i = 0; i < hardWordList.size(); i++) {
            hardWordCards.add(new Card(cardBlank, cardBack, hardWordList.get(i), true));
        }

        wordCardSet.add(easyWordCards);
        wordCardSet.add(normalWordCards);
        wordCardSet.add(hardWordCards);
    }

    public void populateAdditionCards(int numOfPairs) {
        additionCardSet = new ArrayList<>();
        ArrayList<Card> easyAdditionCards = new ArrayList<>();
        ArrayList<Card> normalAdditionCards = new ArrayList<>();
        ArrayList<Card> hardAdditionCards = new ArrayList<>();

        equationList = makeEquations("easy", "addition", numOfPairs);
        for (int i = 0; i < equationList.size(); i++) {
            easyAdditionCards.add(new Card(cardBlank, cardBack, equationList.get(i), true));
        }
        equationList = makeEquations("medium", "addition", numOfPairs);
        for (int i = 0; i < equationList.size(); i++) {
            normalAdditionCards.add(new Card(cardBlank, cardBack, equationList.get(i), true));
        }
        equationList = makeEquations("hard", "addition", numOfPairs);
        for (int i = 0; i < equationList.size(); i++) {
            hardAdditionCards.add(new Card(cardBlank, cardBack, equationList.get(i), true));
        }

        additionCardSet.add(easyAdditionCards);
        additionCardSet.add(normalAdditionCards);
        additionCardSet.add(hardAdditionCards);
    }

    public void populateSubtractionCards(int numOfPairs) {
        subtractionCardSet = new ArrayList<>();
        ArrayList<Card> easySubtractionCards = new ArrayList<>();
        ArrayList<Card> normalSubtractionCards = new ArrayList<>();
        ArrayList<Card> hardSubtractionCards = new ArrayList<>();

        equationList = makeEquations("easy", "subtraction", numOfPairs);
        for (int i = 0; i < equationList.size(); i++) {
            easySubtractionCards.add(new Card(cardBlank, cardBack, equationList.get(i), true));
        }
        equationList = makeEquations("medium", "subtraction", numOfPairs);
        for (int i = 0; i < equationList.size(); i++) {
            normalSubtractionCards.add(new Card(cardBlank, cardBack, equationList.get(i), true));
        }
        equationList = makeEquations("hard", "subtraction", numOfPairs);
        for (int i = 0; i < equationList.size(); i++) {
            hardSubtractionCards.add(new Card(cardBlank, cardBack, equationList.get(i), true));
        }

        subtractionCardSet.add(easySubtractionCards);
        subtractionCardSet.add(normalSubtractionCards);
        subtractionCardSet.add(hardSubtractionCards);
    }

    public void populateRandomCards(int numOfPairs) {
        randomCardSet = new ArrayList<>();
        ArrayList<Card> easyRandomCards = new ArrayList<>();
        ArrayList<Card> normalRandomCards = new ArrayList<>();
        ArrayList<Card> hardRandomCards = new ArrayList<>();

        equationList = makeEquations("easy", "random", numOfPairs);
        for (int i = 0; i < equationList.size(); i++) {
            easyRandomCards.add(new Card(cardBlank, cardBack, equationList.get(i), true));
        }
        equationList = makeEquations("medium", "random", numOfPairs);
        for (int i = 0; i < equationList.size(); i++) {
            normalRandomCards.add(new Card(cardBlank, cardBack, equationList.get(i), true));
        }
        equationList = makeEquations("hard", "random", numOfPairs);
        for (int i = 0; i < equationList.size(); i++) {
            hardRandomCards.add(new Card(cardBlank, cardBack, equationList.get(i), true));
        }
        randomCardSet.add(easyRandomCards);
        randomCardSet.add(normalRandomCards);
        randomCardSet.add(hardRandomCards);
    }

    public ArrayList<Equation> makeEquations(String difficulty, String category, int numOfPairs) {
        ArrayList<Equation> equations = new ArrayList<>();
        Random rand = new Random();
        int[] answers = new int[numOfPairs];

        //Loop through until all cards are created
        int counter = (numOfPairs - 1);
        while (counter >= 0) {

            //Set range based on difficulty
            int range;
            if (difficulty.equals("easy"))
                range = 11;
            else if (difficulty.equals("hard"))
                range = 501;
            else
                range = 51;

            //Set the sign
            int signNum;
            String sign = "";

            if (category.equals("addition"))
                sign = "+";
            else if (category.equals("subtraction"))
                sign = "-";
            else if (category.equals("random")) {
                signNum = (int) rand.nextInt(2); //0 to 1
                if (signNum == 0)
                    sign = "+";
                else if (signNum == 1)
                    sign = "-";
            }
            //Create Equation object with generated numbers
            Equation equation = new Equation((int) rand.nextInt(range), sign, (int) rand.nextInt(range));

            //Cycle through answers array and compare answer against values to avoid duplicate cards
            boolean isTaken = false;
            for (int k = 0; k < answers.length; k++) {
                if (equation.getAnswerAsInt() == answers[k]) {
                    System.out.println("Equation already exists!");
                    isTaken = true;
                }
            }
            //If the answer isn't already on a card, add the equation and answer to the arrays
            if (!isTaken) {
                System.out.println("Equation is unique!");
                equations.add(equation);
                answers[counter] = equation.getAnswerAsInt();
                counter--;
            }
        }
        return equations;
    }
}