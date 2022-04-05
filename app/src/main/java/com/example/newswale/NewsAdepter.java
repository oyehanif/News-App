package com.example.newswale;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newswale.Modals.articles;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class NewsAdepter extends RecyclerView.Adapter<NewsAdepter.viewHolder> {

    Context context;
    ArrayList<articles> articlesArrayList;

    public NewsAdepter(Context context, ArrayList<articles> articlesArrayList) {
        this.context = context;
        this.articlesArrayList = articlesArrayList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item,null,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
            holder.Title.setText(articlesArrayList.get(position).getTitle());
            holder.Description.setText(articlesArrayList.get(position).getDescription());
            holder.publishAt.setText("PublishedAt : "+articlesArrayList.get(position).getPublishedAt());
            Glide.with(context).load(articlesArrayList.get(position).getUrlToImage()).into(holder.imageView);

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context,webView.class);
                    i.putExtra("url",articlesArrayList.get(position).getUrl());
                    context.startActivity(i);
                }
            });

            Animation(holder.itemView,position);
    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView Title,Description,publishAt;
        MaterialCardView cardView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            Title = itemView.findViewById(R.id.newsTitle);
            Description = itemView.findViewById(R.id.description);
            publishAt = itemView.findViewById(R.id.PublishedAt);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }


    private void Animation(View viewtoAnimation,int position){

        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        viewtoAnimation.setAnimation(animation);
    }
}
