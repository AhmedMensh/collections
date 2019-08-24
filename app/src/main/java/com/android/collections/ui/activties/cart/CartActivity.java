package com.android.collections.ui.activties.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.collections.R;
import com.android.collections.adapters.CartAdapter;
import com.android.collections.adapters.MayLikeAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CartActivity extends AppCompatActivity {

    private static final String TAG = "CartActivity";
    private CartAdapter cartAdapter;
    private Unbinder unbinder;
    private MayLikeAdapter mayLikeAdapter;

    @BindView(R.id.cart_rv)
    RecyclerView cartRv;

    @BindView(R.id.may_like_rv) RecyclerView mayLikeRv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        unbinder = ButterKnife.bind(this);
        initCartRv();
        initMayLikeRv();
    }

    private void initMayLikeRv() {

        mayLikeAdapter = new MayLikeAdapter();
        mayLikeRv.setAdapter(mayLikeAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mayLikeRv.setHasFixedSize(true);
        mayLikeRv.setLayoutManager(layoutManager);
    }

    private void initCartRv() {

        cartAdapter = new CartAdapter();
        cartRv.setAdapter(cartAdapter);
        cartRv.setHasFixedSize(true);
        cartRv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
