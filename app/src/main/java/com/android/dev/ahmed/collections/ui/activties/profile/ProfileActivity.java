package com.android.dev.ahmed.collections.ui.activties.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    @BindView(R.id.editBtn)
    Button editBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new ProfilePresenter(this ,this);
        presenter.getUserProfile();
        editBtn.setOnClickListener(view -> {
            updateProfile();
        });
    }

    private void updateProfile(){

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
        presenter.updateProfile(userNameEt.getText().toString(),userMobileEt.getText().toString(),userBirthDayEt.getText().toString(),
                userPasswordEt.getText().toString(),userEmailEt.getText().toString());
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
        userMobileEt.setText(user.getUserMobile());
        userBirthDayEt.setText(user.getBirthday());

        Glide.with(this).load(user.getImg()).placeholder(R.drawable.ic_user_profile).into(userPicIv);
    }
}
