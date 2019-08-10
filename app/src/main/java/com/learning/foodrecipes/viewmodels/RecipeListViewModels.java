package com.learning.foodrecipes.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.learning.foodrecipes.models.Recipe;
import com.learning.foodrecipes.repositories.RecipeRepository;

import java.util.List;

public class RecipeListViewModels extends ViewModel {

    private RecipeRepository recipeRepository;

    public RecipeListViewModels() {
        recipeRepository = RecipeRepository.getInstance();
    }

    public LiveData<List<Recipe>> getRecipe() {
        return recipeRepository.getRecipes();
    }

    public void searchRecipesAPI(String query, int pageNumber) {
        recipeRepository.searchRecipesApi(query, pageNumber);
    }
}
