package com.learning.foodrecipes;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.learning.foodrecipes.adapters.OnRecipeListener;
import com.learning.foodrecipes.adapters.RecipeRecylerAdapter;
import com.learning.foodrecipes.models.Recipe;
import com.learning.foodrecipes.util.Testing;
import com.learning.foodrecipes.viewmodels.RecipeListViewModels;

import java.util.List;

public class RecipeListActivity extends BaseActivity implements OnRecipeListener {

    private String TAG = "RecipeListActivity";
    private RecipeListViewModels mRecipeListViewModel;
    RecyclerView recyclerView;
    RecipeRecylerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        recyclerView = findViewById(R.id.recipe_list);
        mRecipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModels.class);
        initRecyclerView();
        subscribeObservers();
        testResponse();
    }

    public void subscribeObservers() {
        mRecipeListViewModel.getRecipe().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                if (recipes != null) {
                    Testing.printRecipes("recipes test", recipes);
                    adapter.setRecipes(recipes);
                }
            }
        });
    }

    private void initRecyclerView() {
        adapter = new RecipeRecylerAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void searchRecipesAPI(String query, int pageNumber) {
        mRecipeListViewModel.searchRecipesAPI(query, pageNumber);
    }

    public void testResponse() {
        searchRecipesAPI("chicken", 1);
    }

    @Override
    public void onRecipeClick(int position) {
        Log.d(TAG, "onRecipeClick: clicked. " + position);
    }

    @Override
    public void onCategoryClick(String category) {

    }
}
