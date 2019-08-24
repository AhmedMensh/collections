package com.android.collections.ui.activties.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.collections.R;
import com.android.collections.ui.activties.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Unbinder unbinder;
    private static final String TAG = "RegisterActivity";

    @BindView(R.id.login_tv)
    TextView loginTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        unbinder = ButterKnife.bind(this);
        loginTv.setOnClickListener(this::onClick);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.login_tv:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }
}
