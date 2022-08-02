package com.example.newswale.Fregments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.newswale.ApiUtilities;
import com.example.newswale.Modals.articles;
import com.example.newswale.Modals.mainNews;
import com.example.newswale.NewsAdepter;
import com.example.newswale.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AllNews_Fregment extends Fragment {

    String API_KEY = "e05591a2c2d94161b46c34f29bc82f7e";
    RecyclerView recyclerView;
    NewsAdepter adepter;
    TextInputEditText etSearch;
    ImageView searchImgBtn;
    ArrayList<articles> articlesArrayList;

    public AllNews_Fregment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_all_news__fregment, null);

        recyclerView = v.findViewById(R.id.allNewsRecyclerView);
        etSearch = v.findViewById(R.id.etSearch);
        searchImgBtn = v.findViewById(R.id.searchImgBtn);
        articlesArrayList = new ArrayList<>();
        adepter = new NewsAdepter(getContext(), articlesArrayList);

        recyclerView.setAdapter(adepter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);


        getAllNews();
        searchImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchNews();
            }
        });
        return v;
    }

    public void searchNews(){
        String q = Objects.requireNonNull(etSearch.getText()).toString().trim();
        ApiUtilities.getApiInterface().getSearchNews(q,API_KEY).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                articlesArrayList.clear();
                if (response.isSuccessful()) {
                    articlesArrayList.addAll(response.body().getArticles());
                    adepter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Cheack Your Internet!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAllNews() {
        ApiUtilities.getApiInterface().getAllNews("in", 100, API_KEY).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if (response.isSuccessful()) {
                    articlesArrayList.addAll(response.body().getArticles());
                    adepter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Cheack Your Internet!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}