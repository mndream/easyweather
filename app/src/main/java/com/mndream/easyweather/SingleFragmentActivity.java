package com.mndream.easyweather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by Administrator on 2017/11/30.
 * FragmentActivity 通用超类
 */

public abstract class SingleFragmentActivity extends FragmentActivity{

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null){
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container,fragment)
                    .commit();
        }

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherT = prefs.getString("weather1.2t",null);
        String weatherF = prefs.getString("weather1.2f",null);
        String weatherP = prefs.getString("weather1.2p",null);
        if(weatherT != null && weatherF != null && weatherP != null && fragment.getClass().equals(ChooseAreaFragment.class)){
            Intent intent = new Intent(this,WeatherActivity.class);
            startActivity(intent);
            finish();
        }
    }
}