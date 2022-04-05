package com.example.newswale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.newswale.Fregments.AllNews_Fregment;
import com.example.newswale.Fregments.business_Fragment;
import com.example.newswale.Fregments.entertainment_Fragment;
import com.example.newswale.Fregments.health_Fragment;
import com.example.newswale.Fregments.science_Fragment;
import com.example.newswale.Fregments.sports_Fragment;
import com.example.newswale.Fregments.technology_Fragment;

public class viewPagerNewsAdepter extends FragmentPagerAdapter {
    public viewPagerNewsAdepter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new AllNews_Fregment();
        }else if (position == 1){
            return new technology_Fragment();
        }else if (position == 2){
            return new sports_Fragment();
        }else if (position == 3){
            return new science_Fragment();
        }else if (position == 4){
            return new entertainment_Fragment();
        }else if (position == 5){
            return new business_Fragment();
        }else {
            return new health_Fragment();
        }
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return  "AllNews";
        }else if (position == 1){
            return "technology";
        }else if (position == 2){
            return "sports";
        }else if (position == 3){
            return "science";
        }else if (position == 4){
            return "entertainment";
        }else if (position == 5){
            return "business";
        }else {
            return "health";
        }
    }
}
