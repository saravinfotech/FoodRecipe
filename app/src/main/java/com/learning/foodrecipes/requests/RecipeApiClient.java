package com.learning.foodrecipes.requests;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.learning.foodrecipes.AppExecutors;
import com.learning.foodrecipes.models.Recipe;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static com.learning.foodrecipes.util.Constants.NETWORK_TIME_OUT;

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

    public void searchRecipesAPI() {
        final Future handler = AppExecutors.getInstance().networkIO().submit(new Runnable() {
            @Override
            public void run() {
                // retrive data from rest api
            }
        });

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //Let the user know that it is timed out
                handler.cancel(true);
            }
        }, NETWORK_TIME_OUT, TimeUnit.SECONDS);
    }
}
