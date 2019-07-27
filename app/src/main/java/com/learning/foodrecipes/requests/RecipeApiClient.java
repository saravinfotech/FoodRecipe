package com.learning.foodrecipes.requests;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.learning.foodrecipes.models.Recipe;

import java.util.List;

public class RecipeApiClient {

    private static RecipeApiClient instance;
    private MutableLiveData<List<Recipe>> recipeList;

    private RecipeApiClient() {
        recipeList = new MutableLiveData<>();
    }

    public static RecipeApiClient getInstance() {
        if (instance == null) {
            instance = new RecipeApiClient();
        }
        return instance;
    }

    public LiveData<List<Recipe>> getRecipe() {
        return recipeList;
    }
}
