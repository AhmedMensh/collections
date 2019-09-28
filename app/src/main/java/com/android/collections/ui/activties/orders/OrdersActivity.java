package com.android.collections.ui.activties.orders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.collections.R;
import com.android.collections.adapters.OrdersAdapter;
import com.android.collections.helpers.PublicViewInf;
import com.android.collections.helpers.Utilities;
import com.android.collections.models.Order;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OrdersActivity extends AppCompatActivity implements PublicViewInf ,OrdersViewInf {

    private Unbinder unbinder;
    private static final String TAG = "OrdersActivity";
    private OrdersAdapter ordersAdapter;
    private OrdersPresenter presenter;

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

        presenter = new OrdersPresenter(this ,this);
        presenter.getMyOrders();
    }

    private void initToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initOrdersRv() {
        ordersAdapter = new OrdersAdapter(this);
        ordersRv.setAdapter(ordersAdapter);
        ordersRv.setHasFixedSize(true);
        ordersRv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void showMessage(String m) {

        Utilities.showToast(this ,m);
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void displayOrders(List<Order> orderList) {

        Log.e(TAG, "displayOrders: "+orderList.size() );
        ordersAdapter.setOrderListData(orderList);
    }
}
