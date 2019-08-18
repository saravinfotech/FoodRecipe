package com.learning.foodrecipes.util;

import android.util.Log;

import com.learning.foodrecipes.models.Recipe;

import java.util.List;

public class Testing {

    public static void printRecipes(String tag, List<Recipe> list) {
        for (Recipe recipe : list) {
            Log.d(tag, "printRecipes: " + recipe.getRecipe_id() + ", " + recipe.getTitle());
        }
    }
}
