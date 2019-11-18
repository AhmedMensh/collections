package com.android.dev.ahmed.collections.ui.fragments.map;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.dev.ahmed.collections.R;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapAddressFragment extends DialogFragment {


    private static final String TAG = "MapAddressFragment";

    private MapView mapView;

    public MapAddressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Mapbox.getInstance(getContext(), "pk.eyJ1IjoiYWhtZWRtZW5zaCIsImEiOiJjazM0c28zMGYwc2pwM2NtcXp0ZWpjYzM1In0.itRTVtmcOLmvvQIGW54JWw");

        return inflater.inflate(R.layout.fragment_map_address, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(mapboxMap ->
                mapboxMap.setStyle(Style.MAPBOX_STREETS, style -> {

//                    Log.e(TAG, "onViewCreated: "+mapboxMap.getLocationComponent().getLastKnownLocation().getLatitude());
//                    Log.e(TAG, "onViewCreated: "+mapboxMap.getLocationComponent().getLastKnownLocation().getLongitude());
                }));
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
    public static MapAddressFragment newInstance() {
        MapAddressFragment f = new MapAddressFragment();
        return f;
    }
}
