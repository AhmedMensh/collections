package com.android.dev.ahmed.collections.ui.activties.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.dev.ahmed.collections.R;
import com.android.dev.ahmed.collections.helpers.PublicViewInf;
import com.android.dev.ahmed.collections.helpers.Utilities;
import com.android.dev.ahmed.collections.ui.activties.home.HomeActivity;
import com.android.dev.ahmed.collections.ui.activties.register.RegisterActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.internal.ImageRequest;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.logging.LogManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.provider.ContactsContract.Intents.Insert.EMAIL;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, PublicViewInf,LoginViewInf {

    //vars
    private static final String TAG = "LoginActivity";
    private static final String EMAIL = "email";
    private Unbinder unbinder;
    private LoginPresenter presenter;
    private CallbackManager callbackManager;
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
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.facebook_login_button)
    ImageView facebookLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        unbinder = ButterKnife.bind(this);
        facebookLoginButton.setOnClickListener(this);
        presenter = new LoginPresenter(this,this,this);

        setViewsListener();

        initToolbar();

        LoginManager.getInstance().logOut();
        callbackManager = CallbackManager.Factory.create();



        // Callback registration
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                setFacebookData(loginResult);
            }

            @Override
            public void onCancel() {
                // App code
                Toast.makeText(LoginActivity.this, "Cancel", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(FacebookException exception) {

                // App code
                Toast.makeText(LoginActivity.this, "Exception", Toast.LENGTH_SHORT).show();

            }
        });
    }private void setFacebookData(final LoginResult loginResult) {
        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                (object, response) -> {
                    // Application code
                    try {
                        Log.i("Response", response.toString());

                        Log.e(TAG, "setFacebookData: "+loginResult.getAccessToken().getToken() );
                        String email = response.getJSONObject().getString("email");
                        String firstName = response.getJSONObject().getString("first_name");
                        String lastName = response.getJSONObject().getString("last_name");
//                        String profileURL = "";
//                        if (Profile.getCurrentProfile() != null) {
//                            profileURL = ImageRequest.getProfilePictureUri(Profile.getCurrentProfile().getId(), 400, 400).toString();
//                        }
                        String name= firstName + " "+lastName;
                        presenter.loginWithFacebook(name,email,loginResult.getAccessToken().getToken(),"");

                        //TODO put your code here
                    } catch (JSONException e) {
                        Log.e(TAG, "setFacebookData: "+e.getLocalizedMessage() );
                        Toast.makeText(LoginActivity.this, "Something went rong", Toast.LENGTH_SHORT).show();
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,email,first_name,last_name");
        request.setParameters(parameters);
        request.executeAsync();

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "onActivityResult:");
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
            case R.id.facebook_login_button:
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile","email"));
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

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void startHomeActivity() {
        startActivity(new Intent(this , HomeActivity.class));
        finish();
    }
}
