package com.example.newswale;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.newswale.Modals.mainNews;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TabLayout tab;
    ViewPager viewPager;
    ImageView logoutImg,searchImg;
    TextInputEditText etSearch;
    TextInputLayout filledTextField;
    LinearLayout searchLayout;
    ImageView searchImgBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tab = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewPager);
        logoutImg = findViewById(R.id.logoutImg);
        searchImg = findViewById(R.id.searchImg);
        filledTextField = findViewById(R.id.filledTextField);
        searchLayout = findViewById(R.id.searchLayout);

        viewPagerNewsAdepter adepter = new viewPagerNewsAdepter(getSupportFragmentManager());
        viewPager.setAdapter(adepter);

        searchImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (searchLayout.getVisibility() == View.VISIBLE ){
                    searchLayout.setVisibility(View.GONE);
                }else {
                    searchLayout.setVisibility(View.VISIBLE);
                }
            }
        });
        logoutImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Logout");
                builder.setIcon(R.drawable.questionmark);
                builder.setMessage("are you sure you want to logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(getApplicationContext(),Login.class));
                        finish();
                    }
                });


                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Operation Cancel!", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();
                }
        });

        tab.setupWithViewPager(viewPager);
    }

}