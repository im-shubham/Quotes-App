package com.shubham.quotesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import com.sdsmdg.tastytoast.TastyToast;
import com.shubham.quotesapp.models.QuotesModel;
import com.shubham.quotesapp.retrofit.ApiUtilities;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements CopyListener{
    RecyclerView recyclerView;
    RequestManager manager;
    QuoteRecyclerAdapter adapter;
    ProgressDialog dialog;

    ArrayList<QuotesModel> modelClassList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_home);
        manager = new RequestManager(this);
        manager.GetAllQuotes(listener);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching Data..");
        dialog.show();

    }
    QuoteResponseListener listener = new QuoteResponseListener() {
        @Override
        public void didFetch(List<QuotesModel> responses, String message) {
            showData(responses);
            dialog.dismiss();
        }

        @Override
        public void didError(String message) {
            dialog.dismiss();

            TastyToast.makeText(MainActivity.this,message,TastyToast.LENGTH_SHORT,TastyToast.ERROR);
        }
    };

    private void showData(List<QuotesModel> responses) {

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        adapter = new QuoteRecyclerAdapter(MainActivity.this,responses,this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onCopyClicked(String text) {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData data = ClipData.newPlainText("Copies Data",text);
        clipboardManager.setPrimaryClip(data);

        TastyToast.makeText(MainActivity.this,"Text Copied Successfully !!",TastyToast.LENGTH_LONG,TastyToast.SUCCESS);

    }
}