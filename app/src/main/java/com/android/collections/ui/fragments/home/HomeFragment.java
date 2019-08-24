package com.android.collections.ui.fragments.home;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.collections.R;
import com.android.collections.adapters.FlashSaleAdapter;
import com.android.collections.adapters.ProductsAdapter;
import com.android.collections.helpers.Constants;
import com.android.collections.ui.activties.collection.CollectionActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{


    //vars
    private ProductsAdapter productAdapter;
    private Unbinder unbinder;
    //widgets
    @BindView(R.id.flash_sale_rv) RecyclerView flashSaleRv;
    @BindView(R.id.new_arrival_rv) RecyclerView newArrivalRv;
    @BindView(R.id.new_trend_rv) RecyclerView newTrendRv;
    @BindView(R.id.top_offer_rv) RecyclerView topOfferRv;
    @BindView(R.id.flash_sale_layout) LinearLayout flashSaleLayout;
    @BindView(R.id.new_arrival_layout) LinearLayout newArrivalLayout;
    @BindView(R.id.new_trend_layout) LinearLayout newTrendLayout;
    @BindView(R.id.top_offer_layout) LinearLayout topOfferLayout;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


       unbinder = ButterKnife.bind(this,view);

       setListenerToViews();
        initFlashSaleRv();
        initNewArrivalRv();
        initNewTrendRv();
        initTpoOfferRv();

        return view;
    }

    private void setListenerToViews() {
        flashSaleLayout.setOnClickListener(this::onClick);
        newArrivalLayout.setOnClickListener(this::onClick);
        newTrendLayout.setOnClickListener(this::onClick);
        topOfferLayout.setOnClickListener(this::onClick);
    }

    private void initTpoOfferRv() {

        productAdapter = new ProductsAdapter(getContext(),Constants.TOP_OFFER_LAYOUT);
        topOfferRv.setHasFixedSize(true);
        topOfferRv.setAdapter(productAdapter);

        topOfferRv.setLayoutManager(new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false));
    }

    private void initNewTrendRv() {
        productAdapter = new ProductsAdapter(getContext(),Constants.NEW_TREND_LAYOUT);
        newTrendRv.setHasFixedSize(true);
        newTrendRv.setAdapter(productAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        newTrendRv.setLayoutManager(layoutManager);
    }

    private void initNewArrivalRv() {

        productAdapter = new ProductsAdapter(getContext(),Constants.NEW_ARRIVAL_LAYOUT);
        newArrivalRv.setHasFixedSize(true);
        newArrivalRv.setAdapter(productAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        newArrivalRv.setLayoutManager(layoutManager);
    }

    private void initFlashSaleRv() {

        productAdapter = new ProductsAdapter(getContext(),Constants.FLASH_SALE_LAYOUT);
        flashSaleRv.setHasFixedSize(true);
        flashSaleRv.setAdapter(productAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        flashSaleRv.setLayoutManager(layoutManager);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id){
            case R.id.flash_sale_layout:
            case R.id.new_arrival_layout:
            case R.id.new_trend_layout:
            case R.id.top_offer_layout:
                getActivity().startActivity(new Intent(getContext(), CollectionActivity.class));
                break;
        }
    }
}
