package com.android.collections.ui.activties.collection;

import com.android.collections.models.Collection;
import com.android.collections.models.NewArrival;

import java.util.List;

public interface CollectionViewInf {

    void displayNewArrivalsData(List<NewArrival> newArrivalList);
    void displayCategoryProducts(Collection collectionList);
}
