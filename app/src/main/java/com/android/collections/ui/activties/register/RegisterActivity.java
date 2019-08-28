package com.android.collections.ui.activties.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.collections.R;
import com.android.collections.helpers.PublicViewInf;
import com.android.collections.helpers.Utilities;
import com.android.collections.ui.activties.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, PublicViewInf {

    private static final String TAG = "RegisterActivity";
    private Unbinder unbinder;
    private RegisterPresenter presenter;

    @BindView(R.id.login_tv)
    TextView loginTv;
    @BindView(R.id.name_et)
    EditText userNameEt;
    @BindView(R.id.email_et)
    EditText userEmailEt;
    @BindView(R.id.mobile_et)
    EditText userMobileEt;
    @BindView(R.id.password_et)
    EditText userPasswordEt;
    @BindView(R.id.confirm_password_et)
    EditText userPasswordConfirmEt;
    @BindView(R.id.Sign_up_btn)
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        unbinder = ButterKnife.bind(this);
        loginTv.setOnClickListener(this::onClick);
        registerBtn.setOnClickListener(this::onClick);
        presenter = new RegisterPresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.login_tv:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;

            case R.id.Sign_up_btn:
                callRegisterApi();
                break;

        }
    }

    private void callRegisterApi(){

        boolean isEmptyName = Utilities.isEmptyText(this,userNameEt);
        boolean isEmptyEmail = Utilities.isEmptyText(this,userEmailEt);
        boolean isEmptyPassword = Utilities.isEmptyText(this,userPasswordEt);
        boolean isEmptyMobile = Utilities.isEmptyText(this,userMobileEt);

        if (!isEmptyEmail && !isEmptyMobile && !isEmptyName && !isEmptyPassword) return;
        if (!userPasswordEt.getText().toString().trim().equals(userPasswordConfirmEt.getText().toString().trim())){
            Utilities.showToast(this,"Password and confirmation password must be the same!!");
            return;
        }
        presenter.register(userNameEt.getText().toString(),userMobileEt.getText().toString(),
                userPasswordEt.getText().toString(),userEmailEt.getText().toString(),"","ar");
    }
    @Override
    public void showMessage(String m) {

        Utilities.showToast(this,m);
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }
}
