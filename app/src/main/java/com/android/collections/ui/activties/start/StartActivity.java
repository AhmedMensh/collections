package com.android.collections.ui.activties.start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.collections.R;
import com.android.collections.ui.activties.cart.CartActivity;
import com.android.collections.ui.activties.collection.CollectionActivity;
import com.android.collections.ui.activties.login.LoginActivity;
import com.android.collections.ui.activties.product_details.ProductDetailsActivity;
import com.android.collections.ui.activties.register.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    //vars
    private static final String TAG = "StartActivity";
    private Unbinder unbinder;
    //widgets
    @BindView(R.id.login_btn)
    Button loginBtn;

    @BindView(R.id.register_btn)
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        unbinder = ButterKnife.bind(this);
        loginBtn.setOnClickListener(this::onClick);
        registerBtn.setOnClickListener(this::onClick);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.login_btn:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.register_btn:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }
}
