package com.example.project_tomorrowdiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.project_tomorrowdiary.R.id.menu_checkago;
import static com.example.project_tomorrowdiary.R.id.menu_today;
import static com.example.project_tomorrowdiary.R.id.menu_tomorrow;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Today today;
    Tomorrow tomorrow;
    Checkago checkago;
    Alreadydiary alreadydiary;
    Todaynull todaynull;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.navigation);
        today=new Today();
        tomorrow = new Tomorrow();
        checkago = new Checkago();
        alreadydiary = new Alreadydiary();
        todaynull = new Todaynull();


        final Calendar day = Calendar.getInstance ( );
        day.add ( Calendar.DATE, 1 );
        SimpleDateFormat format2 =new SimpleDateFormat("yyyyMMdd");
        final Date nextday = day.getTime ( );
        String numtomorrow = format2.format(nextday);
        String txttitle = "_diary";
        String tomtitle = numtomorrow+txttitle;

        Calendar today2 = Calendar.getInstance ( );
        Date date = today2.getTime ( );
        String today3 = format2.format(date);
        String dtitle = today3+txttitle;



        //저장 데이터 불러오기
        SharedPreferences spddiary = getSharedPreferences(dtitle, Context.MODE_PRIVATE);
        final String dbdtitle = spddiary.getString("title", "no");
        SharedPreferences sptomdiary = getSharedPreferences(tomtitle, Context.MODE_PRIVATE);
        final String dbtomtitle = sptomdiary.getString("title", "no");

        if (dbdtitle=="no"){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_place,todaynull).commitAllowingStateLoss();
        }
        else{
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_place,today).commitAllowingStateLoss();
        }

        //하단 네비게이션 눌렀을 때 프래그먼트 변경
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case menu_today:{
                        if (dbdtitle=="no"){
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_place,todaynull).commitAllowingStateLoss();
                            return true;
                        }
                        else{
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_place,today).commitAllowingStateLoss();
                            return true;
                        }

                    }
                    case menu_tomorrow:{

                        if (dbtomtitle=="no"){
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_place,tomorrow).commitAllowingStateLoss();
                            return true;
                        }
                        else {
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_place, alreadydiary).commitAllowingStateLoss();
                            return true;
                        }
                    }
                    case menu_checkago:{
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_place,checkago).commitAllowingStateLoss();
                        return true;
                    }

                    default:return false;
                }


            }
        });
    }
}