package com.android.collections.ui.activties.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.collections.R;
import com.android.collections.helpers.PublicViewInf;
import com.android.collections.helpers.Utilities;
import com.android.collections.ui.activties.home.HomeActivity;
import com.android.collections.ui.activties.register.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, PublicViewInf,LoginViewInf {

    //vars
    private static final String TAG = "LoginActivity";
    private Unbinder unbinder;
    private LoginPresenter presenter;
    //Widgets
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.forget_password_tv)
    TextView forgetPassword;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.sign_up_tv)
    TextView signUpTv;

    @BindView(R.id.email_et)
    EditText userEmailEt;
    @BindView(R.id.password_et)
    EditText userPasswordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        unbinder = ButterKnife.bind(this);
        presenter = new LoginPresenter(this,this);

        setViewsListener();

        initToolbar();
    }

    private void setViewsListener() {

        forgetPassword.setOnClickListener(this::onClick);
        loginBtn.setOnClickListener(this::onClick);
        signUpTv.setOnClickListener(this::onClick);
    }

    private void initToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    private void setForgetPasswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.one_input_dialog, null);

        TextView dialogTitle = view.findViewById(R.id.dialog_title_tv);
        dialogTitle.setText(getResources().getString(R.string.forget_password));

        EditText inputEt = view.findViewById(R.id.dialog_et);
        inputEt.setHint(R.string.email);
        builder.setView(view);


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.forget_password_tv:
                setForgetPasswordDialog();
                break;

            case R.id.login_btn:
                callLoginApi();
                break;

            case R.id.sign_up_tv:
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
                break;
        }
    }

    private void callLoginApi(){
        boolean isEmptyEmail = Utilities.isEmptyText(this,userEmailEt);
        boolean isEmptyPassword = Utilities.isEmptyText(this,userPasswordEt);

        if (!isEmptyEmail && !isEmptyPassword) return;

        presenter.login(userEmailEt.getText().toString() , userPasswordEt.getText().toString(),"","en");
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

    @Override
    public void startHomeActivity() {
        startActivity(new Intent(this , HomeActivity.class));
        finish();
    }
}
