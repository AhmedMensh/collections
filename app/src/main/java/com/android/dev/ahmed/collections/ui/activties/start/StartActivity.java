package com.android.dev.ahmed.collections.ui.activties.start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.dev.ahmed.collections.R;
import com.android.dev.ahmed.collections.helpers.Constants;
import com.android.dev.ahmed.collections.helpers.SharedPreferencesManager;

import com.android.dev.ahmed.collections.network.Service;
import com.android.dev.ahmed.collections.ui.activties.home.HomeActivity;
import com.android.dev.ahmed.collections.ui.activties.login.LoginActivity;
import com.android.dev.ahmed.collections.ui.activties.register.RegisterActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

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

    @BindView(R.id.skip_login_tv)
    TextView skipLoginTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        unbinder = ButterKnife.bind(this);
        loginBtn.setOnClickListener(this::onClick);
        registerBtn.setOnClickListener(this::onClick);
        skipLoginTV.setOnClickListener(this);









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

            case R.id.skip_login_tv:
                SharedPreferencesManager.setBooleanValue(this ,Constants.IS_REGISTERD,false);
                SharedPreferencesManager.setIntValue(this,Constants.USER_ID,0);
                startActivity(new Intent(this , HomeActivity.class));

        }
    }
}
