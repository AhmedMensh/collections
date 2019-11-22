package com.android.dev.ahmed.collections.ui.fragments.map;


import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.dev.ahmed.collections.CollectionApp;
import com.android.dev.ahmed.collections.R;
import com.android.dev.ahmed.collections.models.AddAddressRequest;
import com.android.dev.ahmed.collections.ui.activties.shipping_address.ShippingAddressActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapAddressFragment extends DialogFragment {


    private static final String TAG = "MapAddressFragment";

    private AddAddressRequest addAddressRequest;
    private com.android.dev.ahmed.collections.models.Address address;
    private double lat = 0.0;
    private double lng = 0.0;
    private String cityName = "";
    private Unbinder unbinder;
    @BindView(R.id.save_btn)
    Button saveBtn;
    @BindView(R.id.address_name_et)
    EditText addressName;
    @BindView(R.id.person_mobile_et) EditText mobileET;
    @BindView(R.id.building_number_et) EditText buildingNumberET;
    @BindView(R.id.floor_number_et) EditText floorNumberET;
    @BindView(R.id.default_address_cb)
    CheckBox defaultAddressCB;
    @BindView(R.id.mapView) MapView mapView;
    private FusedLocationProviderClient fusedLocationClient;
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

        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        unbinder = ButterKnife.bind(this ,view);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(Objects.requireNonNull(getContext()));

        fusedLocationClient.getLastLocation().addOnCompleteListener(task -> {

            if (task != null){
                lat = task.getResult().getLatitude();
                lng = task.getResult().getLongitude();
                Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                List<Address> addresses = null;
                try {
                    addresses = geocoder.getFromLocation(lat, lng, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                 cityName = addresses.get(0).getAddressLine(0);
//                String stateName = addresses.get(0).getAddressLine(1);
//                String countryName = addresses.get(0).getAddressLine(2);



            }
        });
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(mapboxMap ->
                mapboxMap.setStyle(Style.MAPBOX_STREETS, style -> {

                                    // Logic to handle location object



                                    CameraPosition old = mapboxMap.getCameraPosition();
                                    CameraPosition pos = new CameraPosition.Builder()
                                            .target(new LatLng(lat,lng))
                                            .zoom(12)
                                            .tilt(old.tilt)
                                            .build();

                                    mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(pos),1000);


                }));

        if (getArguments() != null){
            address = getArguments().getParcelable("address");
            addressName.setText(address.getAddress());
            buildingNumberET.setText(address.getBuildingNo());
            floorNumberET.setText(address.getFloorNo());
            mobileET.setText(address.getMobile());
            if (address.getDefaultAddress().equals("yes")) defaultAddressCB.setChecked(true);
        }

        saveBtn.setOnClickListener(view1 -> {
            if (address != null){

                addAddressRequest = new AddAddressRequest(
                        addressName.getText().toString(),
                        cityName,
                        cityName,
                        buildingNumberET.getText().toString(),
                        floorNumberET.getText().toString(),
                        mobileET.getText().toString(),
                        " ",
                        Integer.parseInt(address.getID()),
                        "edit",
                        defaultAddressCB.isChecked() ? "yes" : "no",
                        CollectionApp.getLanguage(),
                        CollectionApp.isIsRegisterd(),
                        CollectionApp.getMacAddress(),
                        lat+"",
                        lng+""
                );
            }else {
                addAddressRequest = new AddAddressRequest(
                        addressName.getText().toString(),
                        cityName,
                        cityName,
                        buildingNumberET.getText().toString(),
                        floorNumberET.getText().toString(),
                        mobileET.getText().toString(),
                        " ",
                        0,
                        "add",
                        defaultAddressCB.isChecked() ? "yes" : "no",
                        CollectionApp.getLanguage(),
                        CollectionApp.isIsRegisterd(),
                        CollectionApp.getMacAddress(),
                        lat+"",
                        lng+""
                );
            }


            if (validateInputs(addAddressRequest))
                ((ShippingAddressActivity) getActivity()).addNewAddress(addAddressRequest);
        });
    }

    private boolean validateInputs(AddAddressRequest aar){


        if (aar.getAddressName().isEmpty() || aar.getBuildingNumber().isEmpty() || aar.getCountry().isEmpty()
                || aar.getCity().isEmpty() || aar.getFloorNumber().isEmpty() || aar.getFullName().isEmpty()
                || aar.getMobile().isEmpty()){
            Toast.makeText(getContext(),"Please fill all fields",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
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
        unbinder.unbind();
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
