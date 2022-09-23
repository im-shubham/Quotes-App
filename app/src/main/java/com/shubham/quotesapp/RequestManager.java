package com.shubham.quotesapp;

import android.content.Context;

import com.sdsmdg.tastytoast.TastyToast;
import com.shubham.quotesapp.models.QuotesModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RequestManager {

    Context context;

    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://type.fit/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {

        this.context = context;
    }

    public void GetAllQuotes(QuoteResponseListener quoteResponseListener){
        CallQuotes callQuotes = retrofit.create(CallQuotes.class);
        Call<List<QuotesModel>> call = callQuotes.callQuotes();
        call.enqueue(new Callback<List<QuotesModel>>() {
            @Override
            public void onResponse(Call<List<QuotesModel>> call, Response<List<QuotesModel>> response) {
                if(response.isSuccessful()){
                    quoteResponseListener.didFetch(response.body(),response.message());
                }else{
                    TastyToast.makeText(context,"API request Failed!! ",TastyToast.LENGTH_LONG,TastyToast.ERROR);
                }
            }

            @Override
            public void onFailure(Call<List<QuotesModel>> call, Throwable t) {

                quoteResponseListener.didError(t.getMessage());
            }
        });
    }


    private interface CallQuotes{
        @GET("api/quotes")
        Call<List<QuotesModel>> callQuotes();

    }
}
