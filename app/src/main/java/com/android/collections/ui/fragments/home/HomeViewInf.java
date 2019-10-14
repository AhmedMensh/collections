package com.android.collections.ui.fragments.home;

import com.android.collections.models.FlashSale;
import com.android.collections.models.NewArrival;
import com.android.collections.models.NewTrend;
import com.android.collections.models.Slider;
import com.android.collections.models.TopOffer;

import java.util.List;

public interface HomeViewInf {

    void displayTopOffers(List<TopOffer> topOfferList);
    void displayFlashSale(List<FlashSale> flashSales);
    void displayNewTrends(List<NewTrend> newTrendList);
    void displayNewArrivals(List<NewArrival> newArrivalList);
    void displaySliderImages(List<Slider> sliderList);


}
