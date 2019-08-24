package com.android.collections.ui.activties.home;

import android.os.Bundle;

import com.android.collections.R;
import com.android.collections.ui.fragments.cart.CartFragment;
import com.android.collections.ui.fragments.categories.CategoriesFragment;
import com.android.collections.ui.fragments.home.HomeFragment;
import com.android.collections.ui.fragments.more.MoreFragment;
import com.android.collections.ui.fragments.trend.TrendFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeActivity extends AppCompatActivity {

    //vars
    private Unbinder unbinder;
    //widgets
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar_title) TextView appBarTitle;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        attachHomeFragment();
                        return true;

                    case R.id.navigation_categories:
                        attachCategoriesFragment();
                        return true;

                    case R.id.navigation_trend:
                        attachTrendFragment();
                        return true;

                    case R.id.navigation_cart:
                        attachCartFragment();
                        return true;

                    case R.id.navigation_more:
                        attachMoreFragment();
                        return true;

                }
                return false;
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        unbinder = ButterKnife.bind(this);

        initToolbar();
        attachHomeFragment();
    }

    private void initToolbar() {

        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_bar_menu,menu);

        menu.findItem(R.id.heart_action).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public void attachMoreFragment(){

        appBarTitle.setText(R.string.more);
        MoreFragment moreFragment = new MoreFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction  fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,moreFragment);
        fragmentTransaction.commit();
    }
    public void attachHomeFragment(){

        appBarTitle.setText(R.string.collections);
        HomeFragment homeFragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction  fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,homeFragment);
        fragmentTransaction.commit();
    }

    public void attachCategoriesFragment(){

        appBarTitle.setText(R.string.categories);
        CategoriesFragment categoriesFragment = new CategoriesFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction  fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,categoriesFragment);
        fragmentTransaction.commit();
    }

    public void attachTrendFragment(){

        TrendFragment trendFragment = new TrendFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction  fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,trendFragment);
        fragmentTransaction.commit();
    }

    public void attachCartFragment(){

        appBarTitle.setText(R.string.cart);
        CartFragment cartFragment = new CartFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction  fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,cartFragment);
        fragmentTransaction.commit();
    }

}
