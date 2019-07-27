package com.learning.foodrecipes;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.learning.foodrecipes.models.Recipe;
import com.learning.foodrecipes.requests.RecipeAPI;
import com.learning.foodrecipes.requests.ServiceGenerator;
import com.learning.foodrecipes.requests.response.RecipeSearchResponse;
import com.learning.foodrecipes.util.Constants;
import com.learning.foodrecipes.viewmodels.RecipeListViewModels;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class RecipeListActivity extends BaseActivity {

    private String TAG = "RecipeListActivity";
    private RecipeListViewModels mRecipeListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        mRecipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModels.class);
        subscribeObservers();
    }

    public void subscribeObservers() {
        mRecipeListViewModel.getRecipe().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {

            }
        });
    }


    public void testResponse() {
        RecipeAPI recipeAPI = ServiceGenerator.getRecipeAPI();

        Call<RecipeSearchResponse> responseCall = recipeAPI
                .searchRecipe(
                        Constants.API_KEY,
                        "cheese",
                        "1"
                );

        responseCall.enqueue(new Callback<RecipeSearchResponse>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<RecipeSearchResponse> call, Response<RecipeSearchResponse> response) {
                Log.d(TAG, "onResponse : Server Response" + response.toString());
                if (response.code() == 200) {
                    if (null != response.body() && null != response.body().getRecipes()) {
                        List<Recipe> recipeList = response.body().getRecipes();
                        for (Recipe recipe : recipeList) {
                            Log.d(TAG, recipe.getTitle());
                        }
                    }
                } else {
                    try {
                        if (null != response.errorBody()) {
                            Log.d(TAG, "onResponse :" + response.errorBody().string());
                        }
                    } catch (IOException e) {
                        Log.d(TAG, e.getLocalizedMessage());
                    }
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<RecipeSearchResponse> call, Throwable t) {
                Log.e(TAG, "API Failed");
            }
        });
    }
}
