package com.learning.foodrecipes.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.learning.foodrecipes.models.Recipe;

import java.util.List;

public class RecipeListViewModels extends ViewModel {

    private MutableLiveData<List<Recipe>> mRecipes = new MutableLiveData<>();

    public RecipeListViewModels() {
    }

    public LiveData<List<Recipe>> getRecipe() {
        return mRecipes;
    }
}
