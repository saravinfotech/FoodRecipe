package com.learning.foodrecipes.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.learning.foodrecipes.R;
import com.learning.foodrecipes.models.Recipe;

import java.util.List;

public class RecipeRecylerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Recipe> recipiesList;
    private OnRecipeListener onRecipeListener;

    public RecipeRecylerAdapter(OnRecipeListener onRecipeListener) {
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

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);
        Glide.with(viewHolder.itemView.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(recipiesList.get(i).getImage_url())
                .into(((RecipeViewHolder) viewHolder).image);

        ((RecipeViewHolder) viewHolder).title.setText(recipiesList.get(i).getTitle());
        ((RecipeViewHolder) viewHolder).publisher.setText(recipiesList.get(i).getPublisher());
        ((RecipeViewHolder) viewHolder).socialScore.setText(String.valueOf(Math.round(recipiesList.get(i).getSocial_rank())));
    }

    @Override
    public int getItemCount() {
        if (recipiesList != null) {
            return recipiesList.size();
        }
        return 0;
    }

    public void setRecipes(List<Recipe> recipes) {
        recipiesList = recipes;
        notifyDataSetChanged();
    }
}
