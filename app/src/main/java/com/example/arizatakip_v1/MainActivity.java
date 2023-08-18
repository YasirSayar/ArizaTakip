package com.example.arizatakip_v1;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.arizatakip_v1.databinding.ActivityMainBinding;
import com.google.firebase.FirebaseApp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        NavHostFragment navHostFragment =(NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(binding.navigationView,navHostFragment.getNavController());

        binding.toolbar.setTitle("Arıza Takip");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,binding.drawer,binding.toolbar,0,0);
        binding.drawer.addDrawerListener(toggle);
        toggle.syncState();
        FirebaseApp.initializeApp(this);

    }
    @Override
    public void onBackPressed() {
        if(binding.drawer.isDrawerOpen(GravityCompat.START)){
            binding.drawer.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }
    }


}