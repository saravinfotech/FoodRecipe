package com.learning.foodrecipes.repositories;

import android.arch.lifecycle.LiveData;

import com.learning.foodrecipes.models.Recipe;

import java.util.List;

public class RecipeRepository {

    private static RecipeRepository instance;
    private RecipeRepository recipeRepository;

    private RecipeRepository() {
        recipeRepository = RecipeRepository.getInstance();
    }

    public static RecipeRepository getInstance() {
        if (instance == null) {
            instance = new RecipeRepository();
        }
        return instance;
    }

    public LiveData<List<Recipe>> getRecipes() {
        return recipeRepository.getRecipes();
    }

    public void searchRecipesApi(String query, int pageNumber) {
        if (pageNumber == 0) {
            pageNumber = 1;
        }

        recipeRepository.searchRecipesApi(query, pageNumber);
    }
}
