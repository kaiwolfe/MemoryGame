package com.example.memorygame;

import android.graphics.drawable.Drawable;
import android.os.Debug;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

class Category {
    static int cardBack;
    static int cardBlank;

    private ArrayList<Card> cardTextures;

    private ArrayList<ArrayList<Card>> animalCardSet;
    private ArrayList<ArrayList<Card>> foodCardSet;
    private ArrayList<ArrayList<Card>> natureCardSet;


    public Category(int cardBack, int cardBlank){
        Category.cardBack = cardBack;
        Category.cardBlank = cardBlank;
    }

    public ArrayList<Card> getAnimalCards(int numSets, String difficulty){
        ArrayList<Card> animalCards = null;
        populateAnimalCards();
        switch (difficulty) {
            case "Easy":
                ArrayList<Card> easySet = animalCardSet.get(0);
                Log.d("GET ANIMAL CARDS:", " Start");
                Log.d("GET ANIMAL CARDS", "Number sets: " + numSets);
                animalCards = randomSubset(easySet, numSets);
                break;
            case "Medium":
                if (numSets > 6){
                    animalCards = randomSubset(animalCardSet.get(1), numSets);
                } else {
                    int pickSet = new Random().nextInt(2);
                    if (pickSet == 0)
                        animalCards = randomSubset(animalCardSet.get(1), numSets);
                    else
                        animalCards = randomSubset(animalCardSet.get(2), numSets);
                }
                break;
            case "Hard":
                animalCards = randomSubset(animalCardSet.get(3), numSets);
                break;
        }
        return animalCards;
    }

    public ArrayList<Card> getFoodCards(int numSets, String difficulty){
        ArrayList<Card> foodCards = null;
        populateFoodCards();
        switch (difficulty) {
            case "Easy":
                foodCards = randomSubset(foodCardSet.get(0), numSets);
                break;
            case "Medium":
                int pickSet = new Random().nextInt(2);
                if(pickSet == 0)
                    foodCards = randomSubset(foodCardSet.get(1), numSets);
                else
                    foodCards = randomSubset(foodCardSet.get(2), numSets);
                break;
            case "Hard":
                foodCards = randomSubset(foodCardSet.get(3), numSets);
                break;
        }
        return foodCards;
    }

    public ArrayList<Card> getNatureCards(int numSets, String difficulty){
        ArrayList<Card> natureCards = null;
        populateNatureCards();
        switch (difficulty) {
            case "Easy":
                natureCards = randomSubset(natureCardSet.get(0), numSets);
                break;
            case "Medium":
                natureCards = randomSubset(natureCardSet.get(1), numSets);
                break;
            case "Hard":
                natureCards = randomSubset(natureCardSet.get(2), numSets);
                break;
        }
        return natureCards;
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
            subset.add(randomCard);
//            Log.d("RANDOM SUBSET: ", "Subset size = " + subset.size());
            subset.add(randomCard);
//            Log.d("RANDOM SUBSET: ", "Subset size = " + subset.size());
            pullFrom.remove(random);
//            Log.d("RANDOM SUBSET: ", "New array size = " + pullFrom.size());

        } while((subset.size() != numSets * 2));

        ArrayList<Integer> indexAdded = new ArrayList<>();
        do {
//            Log.d("RANDOM SUBSET MIXING", "Start");
//            Log.d("RANDOM SUBSET MIXING", "Subset Size: " + subset.size());
            int random = new Random().nextInt(subset.size());
//            Log.d("RANDOM SUBSET MIXING", "Random int = " + random);
            boolean alreadyAdded = false;
            if(!indexAdded.isEmpty()) {
                for (int i = 0; i < indexAdded.size(); i++) {

                    if (random == indexAdded.get(i)) {
                        alreadyAdded = true;
                        break;
                    }
                }
            }
            if(!alreadyAdded){
                indexAdded.add(random);
                mixedSubset.add(subset.get(random));
            }
        } while(indexAdded.size() != numSets * 2);

        return mixedSubset;
    }

    public ArrayList<Card> getCardTextures(){
        cardTextures = new ArrayList<>();
        cardTextures.add(new Card(R.drawable.card_blank, R.drawable.texture_blue));
        cardTextures.add(new Card(R.drawable.card_blank, R.drawable.texture_brick));
        cardTextures.add(new Card(R.drawable.card_blank, R.drawable.texture_classic));
        cardTextures.add(new Card(R.drawable.card_blank, R.drawable.texture_deer));
        cardTextures.add(new Card(R.drawable.card_blank, R.drawable.texture_fingerprint));
        cardTextures.add(new Card(R.drawable.card_blank, R.drawable.texture_flower));
        cardTextures.add(new Card(R.drawable.card_blank, R.drawable.texture_green));
        cardTextures.add(new Card(R.drawable.card_blank, R.drawable.texture_koi));
        cardTextures.add(new Card(R.drawable.card_blank, R.drawable.texture_orange));
        cardTextures.add(new Card(R.drawable.card_blank, R.drawable.texture_pink));
        cardTextures.add(new Card(R.drawable.card_blank, R.drawable.texture_purple));
        cardTextures.add(new Card(R.drawable.card_blank, R.drawable.texture_sakura));
        cardTextures.add(new Card(R.drawable.card_blank, R.drawable.texture_stripes));
        cardTextures.add(new Card(R.drawable.card_blank, R.drawable.texture_tile));

        return cardTextures;
    }

    private void populateAnimalCards(){
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

    private void populateFoodCards(){
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

    private void populateNatureCards(){
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
}