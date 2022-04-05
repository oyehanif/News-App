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


public class AllNews_Fregment extends Fragment {

    String API_KEY= "e05591a2c2d94161b46c34f29bc82f7e";
    RecyclerView recyclerView;
    NewsAdepter adepter;
    ArrayList<articles> articlesArrayList;

    public AllNews_Fregment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     View v= inflater.inflate(R.layout.fragment_all_news__fregment,  null);

     recyclerView = v.findViewById(R.id.allNewsRecyclerView);
     articlesArrayList = new ArrayList<>();
     adepter = new NewsAdepter(getContext(),articlesArrayList);

     recyclerView.setAdapter(adepter);
     recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
     recyclerView.setHasFixedSize(true);


     getAllNews();
     return v;
    }

    private void getAllNews() {

        ApiUtilities.getApiInterface().getAllNews("in",100,API_KEY).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if (response.isSuccessful()){
                    articlesArrayList.addAll(response.body().getArticles());
                    adepter.notifyDataSetChanged();
                }else {
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