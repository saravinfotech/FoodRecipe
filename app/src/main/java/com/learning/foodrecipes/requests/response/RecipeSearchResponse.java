package com.learning.foodrecipes.requests.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.learning.foodrecipes.models.Recipe;

import java.util.List;

public class RecipeSearchResponse {

    @SerializedName("count")
    @Expose
    public int count;

    public List<Recipe> recipes;

    public int getCount() {
        return count;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    @Override
    public String toString() {
        return "RecipeSearchResponse{" +
                "count=" + count +
                ", recipes=" + recipes +
                '}';
    }
}
