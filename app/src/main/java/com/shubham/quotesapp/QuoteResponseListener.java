package com.shubham.quotesapp;

import com.shubham.quotesapp.models.QuotesModel;

import java.util.List;

public interface QuoteResponseListener {
    void didFetch(List<QuotesModel> responses , String message);
    void didError(String message);

}
