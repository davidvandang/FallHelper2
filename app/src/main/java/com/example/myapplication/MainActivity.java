package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    ArrayList<String> COL_ID, COL_DATE, COL_TIME;



    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    JournalFragment journalFragment = new JournalFragment();
    SettingFragment settingFragment = new SettingFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView2);

        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.miHome:
                        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,homeFragment).commit();
                        return true;

                    case R.id.miJournal:
                        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,journalFragment).commit();
                        return true;

                    case R.id.miSetting:
                        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,settingFragment).commit();
                        return true;

                }
                return false;
            }
        });
        myDb = new DatabaseHelper(MainActivity.this);
        COL_ID = new ArrayList<>();
        COL_DATE = new ArrayList<>();
        COL_TIME = new ArrayList<>();

        storeDataInArrays();
    }
    void storeDataInArrays() {
        Cursor cursor = myDb.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(this,"no data.", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                COL_ID.add(cursor.getString(0));
                COL_DATE.add(cursor.getString(1));
                COL_TIME.add(cursor.getString(2));

            }
        }
    }
}