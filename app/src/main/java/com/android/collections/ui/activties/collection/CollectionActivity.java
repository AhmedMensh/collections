package com.android.collections.ui.activties.collection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import com.android.collections.R;
import com.android.collections.adapters.CollectionAdapter;
import com.android.collections.adapters.NewArrivalsAdapter;
import com.android.collections.helpers.Constants;
import com.android.collections.helpers.SharedPreferencesManager;
import com.android.collections.models.Collection;
import com.android.collections.models.NewArrival;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CollectionActivity extends AppCompatActivity implements CollectionViewInf {

    private static final String TAG = "CollectionActivity";
    private Unbinder unbinder;
    private CollectionAdapter collectionAdapter;
    private CollectionPresenter presenter;
    private int branchId=0 , userId;
    private NewArrivalsAdapter newArrivalsAdapter;
    @BindView(R.id.new_arrival_rv) RecyclerView newArrivalRv;

    @BindView(R.id.collection_rv)
    RecyclerView collectionRv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collection_name_tv)
    TextView collectionNameTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);


        unbinder = ButterKnife.bind(this);
        presenter = new CollectionPresenter(this);

        userId =SharedPreferencesManager.getIntValue(this,Constants.USER_ID);
        branchId = getIntent().getIntExtra(Constants.PRODUCT_ID,0);
        if (branchId == 0){
            presenter.getNewArrivals(userId,"en",1);
        }else {
            presenter.getProductsByCategory(branchId ,userId);
            collectionNameTv.setText(getIntent().getStringExtra(Constants.PRODUCT_NAME));

        }
        initCollectionRv();
        initNewArrivalRv();
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    private void initNewArrivalRv() {

        newArrivalsAdapter = new NewArrivalsAdapter(this);
        newArrivalRv.setHasFixedSize(true);
        newArrivalRv.setAdapter(newArrivalsAdapter);
        newArrivalRv.setLayoutManager(new GridLayoutManager(this,2,RecyclerView.VERTICAL,false));
    }
    private void initCollectionRv(){
        collectionRv.setHasFixedSize(true);
        collectionRv.setLayoutManager(new GridLayoutManager(this,2, RecyclerView.VERTICAL,false));
        collectionAdapter = new CollectionAdapter(this);
        collectionRv.setAdapter(collectionAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_bar_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void displayNewArrivalsData(List<NewArrival> newArrivalList) {


        newArrivalRv.setVisibility(View.VISIBLE);
        newArrivalsAdapter.setNewArrivalsData(newArrivalList);
    }

    @Override
    public void displayCategoryProducts(Collection collectionList) {

        collectionAdapter.setCollectionData(collectionList.getProducts());
        collectionRv.setVisibility(View.VISIBLE);
    }
}
