package com.android.collections.ui.activties.orders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.collections.R;
import com.android.collections.adapters.OrdersAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OrdersActivity extends AppCompatActivity {

    private Unbinder unbinder;
    private static final String TAG = "OrdersActivity";
    private OrdersAdapter ordersAdapter;

    @BindView(R.id.orders_rv)
    RecyclerView ordersRv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        unbinder = ButterKnife.bind(this);
        initOrdersRv();
        initToolbar();
    }

    private void initToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initOrdersRv() {
        ordersAdapter = new OrdersAdapter();
        ordersRv.setAdapter(ordersAdapter);
        ordersRv.setHasFixedSize(true);
        ordersRv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
