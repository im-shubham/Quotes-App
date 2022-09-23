package com.shubham.quotesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shubham.quotesapp.models.QuotesModel;

import java.util.List;


interface CopyListener{
    void onCopyClicked(String text);
}
public class QuoteRecyclerAdapter extends RecyclerView.Adapter<QuoteRecyclerAdapter.ViewHolder>{

    Context context;
    List<QuotesModel> list;

    CopyListener listener;

    public QuoteRecyclerAdapter(Context context, List<QuotesModel> list, CopyListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_quotes,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.quote.setText(list.get(position).getText());
        holder.author.setText(list.get(position).getAuthor());

        holder.copy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCopyClicked(list.get(holder.getAdapterPosition()).getText());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

       TextView quote,author;
       Button copy_btn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            quote = itemView.findViewById(R.id.textView_quote);
            author = itemView.findViewById(R.id.textView_author);
            copy_btn = itemView.findViewById(R.id.btn_copy);



        }
    }
}
