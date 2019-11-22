package com.android.dev.ahmed.collections.ui.fragments.address;


import android.app.Activity;
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
import com.android.dev.ahmed.collections.models.Address;
import com.android.dev.ahmed.collections.ui.activties.shipping_address.ShippingAddressActivity;
import com.android.dev.ahmed.collections.ui.fragments.map.MapAddressFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddressFragment extends DialogFragment {

    private static final String TAG = "AddressFragment";
    private Address address;
    AddAddressRequest addAddressRequest;
    private Unbinder unbinder;
    @BindView(R.id.save_btn)
    Button saveBtn;
    @BindView(R.id.address_name_et)
    EditText addressName;
    @BindView(R.id.person_mobile_et) EditText mobileET;
    @BindView(R.id.country_et) EditText countryET;
    @BindView(R.id.city_et) EditText cityET;
    @BindView(R.id.building_number_et) EditText buildingNumberET;
    @BindView(R.id.floor_number_et) EditText floorNumberET;
    @BindView(R.id.street_name_et) EditText streetNameET;
    @BindView(R.id.default_address_cb)
    CheckBox defaultAddressCB;

    public AddressFragment() {
        // Required empty public constructor
    }

    public static AddressFragment newInstance() {
        AddressFragment f = new AddressFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_address, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        unbinder = ButterKnife.bind(this , view);

        if (getArguments() != null){
            address = getArguments().getParcelable("address");
            addressName.setText(address.getAddress());
            countryET.setText(address.getCountry());
            cityET.setText(address.getCity());
            buildingNumberET.setText(address.getBuildingNo());
            floorNumberET.setText(address.getFloorNo());
            mobileET.setText(address.getMobile());
            streetNameET.setText(address.getFullName());
            if (address.getDefaultAddress().equals("yes")) defaultAddressCB.setChecked(true);
        }

        saveBtn.setOnClickListener(view1 -> {

            if (address != null){

                addAddressRequest = new AddAddressRequest(
                        addressName.getText().toString(),
                        countryET.getText().toString(),
                        cityET.getText().toString(),
                        buildingNumberET.getText().toString(),
                        floorNumberET.getText().toString(),
                        mobileET.getText().toString(),
                        streetNameET.getText().toString(),
                        Integer.parseInt(address.getID()),
                        "edit",
                        defaultAddressCB.isChecked() ? "yes" : "no",
                        CollectionApp.getLanguage(),
                        CollectionApp.isIsRegisterd(),
                        CollectionApp.getMacAddress(),
                        "",
                        ""
                );
            }else {
                addAddressRequest = new AddAddressRequest(
                        addressName.getText().toString(),
                        countryET.getText().toString(),
                        cityET.getText().toString(),
                        buildingNumberET.getText().toString(),
                        floorNumberET.getText().toString(),
                        mobileET.getText().toString(),
                        streetNameET.getText().toString(),
                        0,
                        "add",
                        defaultAddressCB.isChecked() ? "yes" : "no",
                        CollectionApp.getLanguage(),
                        CollectionApp.isIsRegisterd(),
                        CollectionApp.getMacAddress(),
                        "",
                        ""
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
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
