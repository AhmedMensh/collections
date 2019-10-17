package com.android.collections.ui.fragments.trend;

import com.android.collections.models.NewTrend;
import com.android.collections.models.Slider;

import java.util.List;

public interface TrendViewInf {

    void displayNewTrends(List<NewTrend> newTrendList);

    void displaySliderImages(List<Slider> data);
}
