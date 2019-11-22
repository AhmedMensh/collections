package com.android.dev.ahmed.collections.ui.activties.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.dev.ahmed.collections.R;
import com.android.dev.ahmed.collections.helpers.Constants;
import com.android.dev.ahmed.collections.helpers.PublicViewInf;
import com.android.dev.ahmed.collections.helpers.SharedPreferencesManager;
import com.android.dev.ahmed.collections.helpers.Utilities;
import com.android.dev.ahmed.collections.ui.activties.home.HomeActivity;
import com.android.dev.ahmed.collections.ui.activties.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, PublicViewInf,RegisterViewInf {

    private static final String TAG = "RegisterActivity";
    private String deviceToken = "";
    private Unbinder unbinder;
    private RegisterPresenter presenter;

    @BindView(R.id.login_tv)
    TextView loginTv;
    @BindView(R.id.name_et)
    EditText userNameEt;
    @BindView(R.id.email_et)
    EditText userEmailEt;
    @BindView(R.id.birthday_et)
    EditText userBirthDayEt;
    @BindView(R.id.mobile_et)
    EditText userMobileEt;
    @BindView(R.id.password_et)
    EditText userPasswordEt;
    @BindView(R.id.confirm_password_et)
    EditText userPasswordConfirmEt;
    @BindView(R.id.Sign_up_btn)
    Button registerBtn;
    @BindView(R.id.progress)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        unbinder = ButterKnife.bind(this);
        loginTv.setOnClickListener(this::onClick);
        registerBtn.setOnClickListener(this::onClick);
        presenter = new RegisterPresenter(this,this);



    }

    @Override
    protected void onResume() {
        super.onResume();
        getDeviceToken();

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
        boolean isEmptyBirthDay = Utilities.isEmptyText(this,userBirthDayEt);

        if (!isEmptyEmail && !isEmptyMobile && !isEmptyName && !isEmptyPassword && isEmptyBirthDay) return;
        if (!userPasswordEt.getText().toString().trim().equals(userPasswordConfirmEt.getText().toString().trim())){
            Utilities.showToast(this,"Password and confirmation password must be the same!!");
            return;
        }
        presenter.register(userNameEt.getText().toString(),userMobileEt.getText().toString(),userBirthDayEt.getText().toString(),
                userPasswordEt.getText().toString(),userEmailEt.getText().toString(),deviceToken);
    }
    @Override
    public void showMessage(String m) {

        Utilities.showToast(this,m);
    }

    @Override
    public void showProgressBar() {

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void startHomeActivity() {
        SharedPreferencesManager.setBooleanValue(this , Constants.IS_REGISTERD,true);
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }


    public void getDeviceToken(){
        FirebaseApp.initializeApp(this);
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "getInstanceId failed", task.getException());
                        return;
                    }

                    // Get new Instance ID token
                    deviceToken = task.getResult().getToken();
                });
    }
}
