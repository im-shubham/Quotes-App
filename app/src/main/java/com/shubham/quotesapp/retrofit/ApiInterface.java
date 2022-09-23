package com.shubham.quotesapp.retrofit;

import com.shubham.quotesapp.models.QuotesModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("api/quotes")
    Call<List<QuotesModel>> callQuotes();
}
