package com.learning.foodrecipes.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.learning.foodrecipes.R;
import com.learning.foodrecipes.models.Recipe;

import java.util.List;

public class RecipeRecylerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Recipe> recipiesList;
    private OnRecipeListener onRecipeListener;

    public RecipeRecylerAdapter(List<Recipe> recipiesList, OnRecipeListener onRecipeListener) {
        this.recipiesList = recipiesList;
        this.onRecipeListener = onRecipeListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_recipe_list_item, viewGroup, false);
        return new RecipeViewHolder(view, onRecipeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((RecipeViewHolder) viewHolder).title.setText(recipiesList.get(i).getTitle());
        ((RecipeViewHolder) viewHolder).publisher.setText(recipiesList.get(i).getPublisher());
        ((RecipeViewHolder) viewHolder).socialScore.setText(String.valueOf(Math.round(recipiesList.get(i).getSocial_rank())));
    }

    @Override
    public int getItemCount() {
        return recipiesList.size();
    }

    public void setRecipes(List<Recipe> recipes) {
        recipiesList = recipes;
        notifyDataSetChanged();
    }
}
