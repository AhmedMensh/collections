package com.android.dev.ahmed.collections.ui.activties.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.dev.ahmed.collections.R;
import com.android.dev.ahmed.collections.helpers.Constants;
import com.android.dev.ahmed.collections.helpers.PublicViewInf;
import com.android.dev.ahmed.collections.helpers.SharedPreferencesManager;
import com.android.dev.ahmed.collections.helpers.Utilities;
import com.android.dev.ahmed.collections.models.User;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity  implements ProfileViewInf , PublicViewInf {

    private static final String TAG = "ProfileActivity";
    private ProfilePresenter presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.user_pic_iv)
    ImageView userPicIv;
    @BindView(R.id.user_email_et) EditText userEmailEt;
    @BindView(R.id.user_password_et) EditText userPasswordEt;
    @BindView(R.id.user_name_et) EditText userNameEt;
    @BindView(R.id.user_phone_et) EditText userPhoneEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new ProfilePresenter(this ,this);
        presenter.getUserProfile(SharedPreferencesManager.getIntValue(this, Constants.USER_ID));
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
    public void displayProfileDetails(User user) {

        userEmailEt.setText(user.getEmail());
        userNameEt.setText(user.getUsername());
        userPasswordEt.setText(user.getUserPass());
        userPhoneEt.setText(user.getUserMobile());

        Glide.with(this).load(user.getImg()).placeholder(R.drawable.ic_user_profile).into(userPicIv);
    }
}
