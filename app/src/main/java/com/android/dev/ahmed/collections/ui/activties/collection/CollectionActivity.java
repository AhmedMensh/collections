package com.android.dev.ahmed.collections.ui.activties.collection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import com.android.dev.ahmed.collections.R;
import com.android.dev.ahmed.collections.adapters.CollectionAdapter;
import com.android.dev.ahmed.collections.adapters.NewArrivalsAdapter;
import com.android.dev.ahmed.collections.helpers.Constants;
import com.android.dev.ahmed.collections.helpers.SharedPreferencesManager;
import com.android.dev.ahmed.collections.models.NewArrival;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CollectionActivity extends AppCompatActivity implements CollectionViewInf {

    private static final String TAG = "CollectionActivity";
    private Unbinder unbinder;
    private CollectionAdapter collectionAdapter;
    private CollectionPresenter presenter;
    private int branchId=0;
    private NewArrivalsAdapter newArrivalsAdapter;
    @BindView(R.id.new_arrival_rv) RecyclerView newArrivalRv;

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


        branchId = getIntent().getIntExtra(Constants.PRODUCT_ID,0);
        if (branchId == 0){
            presenter.getNewArrivals("en",1);
        }else {
            presenter.getProductsByCategory(branchId );
            collectionNameTv.setText(getIntent().getStringExtra(Constants.PRODUCT_NAME));

        }

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
    public void displayData(List<NewArrival> newArrivalList) {


        newArrivalRv.setVisibility(View.VISIBLE);
        newArrivalsAdapter.setNewArrivalsData(newArrivalList);
    }


}
