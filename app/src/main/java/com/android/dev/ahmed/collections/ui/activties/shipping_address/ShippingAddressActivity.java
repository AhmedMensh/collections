package com.android.dev.ahmed.collections.ui.activties.shipping_address;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.android.dev.ahmed.collections.R;
import com.android.dev.ahmed.collections.adapters.ShippingAddressAdapter;
import com.android.dev.ahmed.collections.helpers.PublicViewInf;
import com.android.dev.ahmed.collections.models.Address;
import com.android.dev.ahmed.collections.ui.fragments.map.MapAddressFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShippingAddressActivity extends AppCompatActivity implements PublicViewInf ,AddressViewInf {

    private static final String TAG = "ShippingAddressActivity";
    private ShippingAddressAdapter adapter;
    private Unbinder unbinder;
    private AddressPresener presener;
    @BindView(R.id.shipping_address_rv)
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_address);

        unbinder = ButterKnife.bind(this);

        adapter = new ShippingAddressAdapter();
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        showMapDialogFragment();
        presener = new AddressPresener(this ,this);
        presener.getUserAddress();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void showMapDialogFragment(){

        MapAddressFragment dialog = MapAddressFragment.newInstance();
        dialog.show(getSupportFragmentManager(), "MapFragment");
    }

    @Override
    public void showMessage(String m) {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void displayAddressList(List<Address> addressList) {
     adapter.setData(addressList);
    }
}
