package com.android.dev.ahmed.collections.ui.activties.shipping_address;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.dev.ahmed.collections.R;
import com.android.dev.ahmed.collections.adapters.ShippingAddressAdapter;
import com.android.dev.ahmed.collections.helpers.PublicViewInf;
import com.android.dev.ahmed.collections.models.AddAddressRequest;
import com.android.dev.ahmed.collections.models.Address;
import com.android.dev.ahmed.collections.ui.fragments.address.AddressFragment;
import com.android.dev.ahmed.collections.ui.fragments.map.MapAddressFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShippingAddressActivity extends AppCompatActivity implements PublicViewInf ,
        AddressViewInf,ShippingAddressAdapter.ItemClickListener {

    private static final String TAG = "ShippingAddressActivity";
    private ShippingAddressAdapter adapter;
    private Unbinder unbinder;
    private AddressPresener presenter;
    @BindView(R.id.shipping_address_rv)
    RecyclerView recyclerView;
    @BindView(R.id.addNewAddressBtn)
    Button addNewAddressBtn;

    AlertDialog addressTypeDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_address);

        unbinder = ButterKnife.bind(this);

        addNewAddressBtn.setOnClickListener(view -> { showAddressOpitionsDialog();});
        adapter = new ShippingAddressAdapter(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        presenter = new AddressPresener(this ,this);
        presenter.getUserAddress();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }



    public void addNewAddress(AddAddressRequest aar){
        presenter.addAddress(aar);
    }
    @Override
    public void showMessage(String m) {
        Toast.makeText(this,m,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void displayAddressList(List<Address> addressList) {

        if (addressList != null)
        adapter.setData(addressList);
    }

    private void showMapDialogFragment(Bundle bundle){


            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{(Manifest.permission.ACCESS_FINE_LOCATION)}, 5
            );
                return;
            }


        MapAddressFragment dialog = MapAddressFragment.newInstance();
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), "MapFragment");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 5 && permissions.length >0 ){
            MapAddressFragment dialog = MapAddressFragment.newInstance();
            dialog.show(getSupportFragmentManager(), "MapFragment");
        }
    }


    private void showAddressDialogFragment(Bundle bundle){

        AddressFragment dialog = AddressFragment.newInstance();
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), "AddressFragment");
    }
    private void showAddressOpitionsDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dilaog_address_type, null);

        TextView mapTV = view.findViewById(R.id.mapTV);
        TextView textFieldTV = view.findViewById(R.id.textFieldTV);
        TextView cancelTV = view.findViewById(R.id.cancel);
        textFieldTV.setOnClickListener(view12 -> {
            showAddressDialogFragment(null);
            addressTypeDialog.dismiss();
        });

        cancelTV.setOnClickListener(view12 -> {
            addressTypeDialog.dismiss();
        });
        mapTV.setOnClickListener(view1 -> {
            showMapDialogFragment(null);
            addressTypeDialog.dismiss();

        });
        builder.setView(view);

        addressTypeDialog = builder.create();
        addressTypeDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        addressTypeDialog.show();
    }

    @Override
    public void onItemClicked(Address address) {

        Bundle bundle = new Bundle();
        bundle.putParcelable("address",address);
       if (address.getLatitude().equals("")){
           showAddressDialogFragment(bundle);
       }else {
           showMapDialogFragment(bundle);
       }
    }
}
