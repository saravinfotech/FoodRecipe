package com.learning.foodrecipes;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.learning.foodrecipes.models.Recipe;
import com.learning.foodrecipes.viewmodels.RecipeListViewModels;

import java.util.List;

public class RecipeListActivity extends BaseActivity {

    private String TAG = "RecipeListActivity";
    private RecipeListViewModels mRecipeListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        mRecipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModels.class);
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testResponse();
            }
        });
        subscribeObservers();
    }

    public void subscribeObservers() {
        mRecipeListViewModel.getRecipe().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                if (recipes != null) {
                    for (Recipe recipe : recipes) {
                        Log.d(TAG, "onChanged: " + recipe.getTitle());
                    }
                }
            }
        });
    }

    public void searchRecipesAPI(String query, int pageNumber) {
        mRecipeListViewModel.searchRecipesAPI(query, pageNumber);
    }

    public void testResponse() {
        searchRecipesAPI("chicker Breast", 1);
    }
}
