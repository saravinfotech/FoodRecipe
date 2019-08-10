package com.learning.foodrecipes.requests;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.learning.foodrecipes.AppExecutors;
import com.learning.foodrecipes.models.Recipe;
import com.learning.foodrecipes.requests.response.RecipeSearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

import static com.learning.foodrecipes.util.Constants.BASE_URL;
import static com.learning.foodrecipes.util.Constants.NETWORK_TIME_OUT;

public class RecipeApiClient {

    private static final String TAG = "RecipeApiClient";
    private static RecipeApiClient instance;
    private MutableLiveData<List<Recipe>> liveDataRecipeList;
    private RetrieveRecipesRunnable retrieveRecipesRunnable;

    private RecipeApiClient() {
        liveDataRecipeList = new MutableLiveData<>();
    }

    public static RecipeApiClient getInstance() {
        if (instance == null) {
            instance = new RecipeApiClient();
        }
        return instance;
    }

    public LiveData<List<Recipe>> getRecipe() {
        return liveDataRecipeList;
    }

    public void searchRecipesAPI(String query, int pageNumber) {
        if (retrieveRecipesRunnable != null) {
            retrieveRecipesRunnable = null;
        }
        retrieveRecipesRunnable = new RetrieveRecipesRunnable(query, pageNumber);
        final Future handler = AppExecutors.getInstance().networkIO().submit(retrieveRecipesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //Let the user know that it is timed out
                handler.cancel(true);
            }
        }, NETWORK_TIME_OUT, TimeUnit.SECONDS);
    }

    private class RetrieveRecipesRunnable implements Runnable {
        boolean cancelRequest;
        private String query;
        private int pageNumber;

        public RetrieveRecipesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = getRecipes(query, pageNumber).execute();
                if (cancelRequest) {
                    return;
                }
                if (response.code() == 200) {
                    List<Recipe> list = new ArrayList<>(((RecipeSearchResponse) response.body()).getRecipes());
                    if (pageNumber == 1) {
                        liveDataRecipeList.postValue(list);
                    } else {
                        List<Recipe> currentRecipes = liveDataRecipeList.getValue();
                        currentRecipes.addAll(list);
                        liveDataRecipeList.postValue(currentRecipes);
                    }
                } else {
                    String error = response.errorBody().toString();
                    Log.e(TAG, "run: " + error);
                    liveDataRecipeList.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                liveDataRecipeList.postValue(null);
            }

        }

        private Call<RecipeSearchResponse> getRecipes(String query, int pageNumber) {
            return new ServiceGenerator().getRecipeAPI().searchRecipe(
                    //API_KEY,
                    BASE_URL,
                    query,
                    String.valueOf(pageNumber)
            );
        }

        private void cancelRequest() {
            Log.d(TAG, "cancelRequest: cancelling the search request.");
            cancelRequest = true;
        }
    }
}
