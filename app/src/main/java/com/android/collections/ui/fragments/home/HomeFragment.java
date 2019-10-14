package com.android.collections.ui.fragments.home;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.collections.R;
import com.android.collections.adapters.FlashSalesAdapter;
import com.android.collections.adapters.NewArrivalsAdapter;
import com.android.collections.adapters.NewTrendAdapter;
import com.android.collections.adapters.SliderAdapter;
import com.android.collections.adapters.TopOffersAdapter;
import com.android.collections.helpers.Constants;
import com.android.collections.helpers.PublicViewInf;
import com.android.collections.helpers.Utilities;
import com.android.collections.models.FlashSale;
import com.android.collections.models.NewArrival;
import com.android.collections.models.NewTrend;
import com.android.collections.models.Slider;
import com.android.collections.models.TopOffer;
import com.android.collections.ui.activties.collection.CollectionActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener, PublicViewInf,HomeViewInf {


    private static final String TAG = "HomeFragment";
    //vars
    private TopOffersAdapter topOffersAdapter;
    private FlashSalesAdapter flashSalesAdapter;
    private NewTrendAdapter newTrendAdapter;
    private NewArrivalsAdapter newArrivalsAdapter;
    private SliderAdapter sliderAdapter;
    private Unbinder unbinder;
    private HomePresenter presenter;
    //widgets
    @BindView(R.id.flash_sale_rv) RecyclerView flashSaleRv;
    @BindView(R.id.new_arrival_rv) RecyclerView newArrivalRv;
    @BindView(R.id.new_trend_rv) RecyclerView newTrendRv;
    @BindView(R.id.top_offer_rv) RecyclerView topOfferRv;
    @BindView(R.id.slider_rv) RecyclerView sliderRv;
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

        presenter = new HomePresenter(this,this);
        presenter.getFlashSale(1,"ar",1);
        presenter.getTopOffers(1,"ar",1);
        presenter.getNewTrends(1,"ar",1);
        presenter.getNewArrivals(1,"ar",1);
        presenter.getSliderImages();
        setListenerToViews();
        initFlashSaleRv();
        initNewArrivalRv();
        initNewTrendRv();
        initTpoOfferRv();
        initSliderRv();

        return view;
    }

    private void setListenerToViews() {
        flashSaleLayout.setOnClickListener(this::onClick);
        newArrivalLayout.setOnClickListener(this::onClick);
        newTrendLayout.setOnClickListener(this::onClick);
        topOfferLayout.setOnClickListener(this::onClick);
    }

    private void initTpoOfferRv() {

        topOffersAdapter = new TopOffersAdapter(getContext());
        topOfferRv.setHasFixedSize(true);
        topOfferRv.setAdapter(topOffersAdapter);

        topOfferRv.setLayoutManager(new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false));
    }

    private void initNewTrendRv() {
        newTrendAdapter = new NewTrendAdapter(getContext());
        newTrendRv.setHasFixedSize(true);
        newTrendRv.setAdapter(newTrendAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        newTrendRv.setLayoutManager(layoutManager);
    }

    private void initNewArrivalRv() {

        newArrivalsAdapter = new NewArrivalsAdapter(getContext());
        newArrivalRv.setHasFixedSize(true);
        newArrivalRv.setAdapter(newArrivalsAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        newArrivalRv.setLayoutManager(layoutManager);
    }

    private void initSliderRv() {

        sliderAdapter = new SliderAdapter(getContext());
        sliderRv.setHasFixedSize(true);
        sliderRv.setAdapter(sliderAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        sliderRv.setLayoutManager(layoutManager);
    }

    private void initFlashSaleRv() {

        flashSalesAdapter = new FlashSalesAdapter(getContext());
        flashSaleRv.setHasFixedSize(true);
        flashSaleRv.setAdapter(flashSalesAdapter);
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

    @Override
    public void showMessage(String m) {

        Utilities.showToast(getContext(),m);
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void displayTopOffers(List<TopOffer> topOfferList) {

        Log.e(TAG, "displayTopOffers: "+topOfferList.size() );
        topOffersAdapter.setTopOffersData(topOfferList);
    }

    @Override
    public void displayFlashSale(List<FlashSale> flashSales) {
        flashSalesAdapter.setFlashSalesData(flashSales);

    }

    @Override
    public void displayNewTrends(List<NewTrend> newTrendList) {

        newTrendAdapter.setNewTrendsData(newTrendList);
    }

    @Override
    public void displayNewArrivals(List<NewArrival> newArrivalList) {

        newArrivalsAdapter.setNewArrivalsData(newArrivalList);
    }

    @Override
    public void displaySliderImages(List<Slider> sliderList) {

        sliderAdapter.setSliderImages(sliderList);
        Log.e(TAG, "displaySliderImages: ");
    }
}
