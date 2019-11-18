package com.android.dev.ahmed.collections.ui.activties.shipping_address;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import com.android.dev.ahmed.collections.R;
import com.android.dev.ahmed.collections.adapters.ShippingAddressAdapter;
import com.android.dev.ahmed.collections.ui.fragments.map.MapAddressFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShippingAddressActivity extends AppCompatActivity {

    private static final String TAG = "ShippingAddressActivity";
    private ShippingAddressAdapter adapter;
    private Unbinder unbinder;
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
}
