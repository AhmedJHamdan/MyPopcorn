package com.udacity.ahmed.mypopcorn.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;

import com.udacity.ahmed.mypopcorn.R;
import com.udacity.ahmed.mypopcorn.fragment.MovieFragment;
import com.udacity.ahmed.mypopcorn.fragment.TvShowFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        getSupportActionBar().setHomeButtonEnabled(true);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        MobileAds.initialize(getApplicationContext(), "pub-9118327582693953");

        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);
        bottomNavigationView.setSelectedItemId(R.id.tv_shows);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.movies:
                                MovieFragment mFragment = null;
                                mFragment = new MovieFragment();
                                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                                fragmentManager.beginTransaction()
                                        .replace(R.id.frameLayout, mFragment).commit();
//                                item.setIcon(R.drawable.fav);
                                break;
                            case R.id.tv_shows:
                                TvShowFragment tvShowFragment = new TvShowFragment();
                                android.support.v4.app.FragmentManager fragmentManager1 = getSupportFragmentManager();
                                fragmentManager1.beginTransaction()
                                        .replace(R.id.frameLayout, tvShowFragment).commit();
//                                item.setIcon(R.drawable.fav);

                                break;
                        }
                        return true;
                    }
                });
        bottomNavigationView.setSelectedItemId(R.id.tv_shows);

        SharedPreferences prefs = this.getSharedPreferences(
                "com.udacity.ahmed.mypopcorn", Context.MODE_PRIVATE);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Fresco.initialize(this);

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(MainActivity.this, SearchResultsActivity.class);
                intent.putExtra("search", query);
                startActivity(intent);
                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);

        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gallery) {
            Intent intent = new Intent(this, FavouriteActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);  // OPEN DRAWER
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}


