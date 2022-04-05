package com.example.newswale.Fregments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.newswale.ApiUtilities;
import com.example.newswale.Modals.articles;
import com.example.newswale.Modals.mainNews;
import com.example.newswale.NewsAdepter;
import com.example.newswale.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class technology_Fragment extends Fragment {

    RecyclerView recyclerView;
    NewsAdepter adepter;
    ArrayList<articles> articlesArrayList;
    String API_KEY= "e05591a2c2d94161b46c34f29bc82f7e";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_technology_, container, false);

        recyclerView = v.findViewById(R.id.technology_RecyclerView);
        recyclerView.setHasFixedSize(true);

        articlesArrayList = new ArrayList<>();
        adepter = new NewsAdepter(getContext(),articlesArrayList);

        recyclerView.setAdapter(adepter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getTechnology();

        return v;
    }

    private void getTechnology() {

        ApiUtilities.getApiInterface().getcategory("in","technology",100,API_KEY).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if (response.isSuccessful()){
                    articlesArrayList.addAll(response.body().getArticles());
                    adepter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}