package com.android.dev.ahmed.collections.ui.fragments.home;

import com.android.dev.ahmed.collections.models.FlashSale;
import com.android.dev.ahmed.collections.models.NewArrival;
import com.android.dev.ahmed.collections.models.NewTrend;
import com.android.dev.ahmed.collections.models.Slider;
import com.android.dev.ahmed.collections.models.TopOffer;

import java.util.List;

public interface HomeViewInf {

    void displayTopOffers(List<TopOffer> topOfferList);
    void displayFlashSale(List<FlashSale> flashSales);
    void displayNewTrends(List<NewTrend> newTrendList);
    void displayNewArrivals(List<NewArrival> newArrivalList);
    void displaySliderImages(List<Slider> sliderList);


}
