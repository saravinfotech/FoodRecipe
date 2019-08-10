package com.learning.foodrecipes.repositories;

import android.arch.lifecycle.LiveData;

import com.learning.foodrecipes.models.Recipe;
import com.learning.foodrecipes.requests.RecipeApiClient;

import java.util.List;

public class RecipeRepository {

    private static RecipeRepository instance;
    private RecipeApiClient mRecipeApiClient;

    private RecipeRepository() {
        mRecipeApiClient = RecipeApiClient.getInstance();
    }

    public static RecipeRepository getInstance() {
        if (instance == null) {
            instance = new RecipeRepository();
        }
        return instance;
    }

    public LiveData<List<Recipe>> getRecipes() {
        return mRecipeApiClient.getRecipe();
    }

    public void searchRecipesApi(String query, int pageNumber) {
        if (pageNumber == 0) {
            pageNumber = 1;
        }

        mRecipeApiClient.searchRecipesAPI(query, pageNumber);
    }
}
