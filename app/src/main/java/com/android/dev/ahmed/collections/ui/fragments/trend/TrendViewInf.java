package com.android.dev.ahmed.collections.ui.fragments.trend;

import com.android.dev.ahmed.collections.models.NewTrend;
import com.android.dev.ahmed.collections.models.Slider;

import java.util.List;

public interface TrendViewInf {

    void displayNewTrends(List<NewTrend> newTrendList);

    void displaySliderImages(List<Slider> data);
}
