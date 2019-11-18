package com.android.dev.ahmed.collections.ui.fragments.more;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.dev.ahmed.collections.MyApp;
import com.android.dev.ahmed.collections.R;
import com.android.dev.ahmed.collections.helpers.Constants;
import com.android.dev.ahmed.collections.helpers.SharedPreferencesManager;
import com.android.dev.ahmed.collections.models.UserCounts;
import com.android.dev.ahmed.collections.ui.activties.notifications.NotificationsActivity;
import com.android.dev.ahmed.collections.ui.activties.orders.OrdersActivity;
import com.android.dev.ahmed.collections.ui.activties.profile.ProfileActivity;
import com.android.dev.ahmed.collections.ui.activties.shipping_address.ShippingAddressActivity;
import com.android.dev.ahmed.collections.ui.activties.start.StartActivity;
import com.android.dev.ahmed.collections.ui.activties.wish_list.WishListActivity;
import com.facebook.login.LoginManager;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MoreFragment extends Fragment implements View.OnClickListener, MoreViewInf {

    private static final String TAG = "MoreFragment";

    private Unbinder unbinder;
    private MorePresenter presenter;
    AlertDialog languageDialog;


    @BindView(R.id.my_profile_layout)
    ConstraintLayout myProfileLayout;
    @BindView(R.id.log_out_layout)
    ConstraintLayout logoutLayout;
    @BindView(R.id.contact_us_layout)
    ConstraintLayout contactUsLayout;
    @BindView(R.id.my_order_layout)
    ConstraintLayout myOrdersLayout;
    @BindView(R.id.wish_list_layout)
    ConstraintLayout wishListLayout;
    @BindView(R.id.notifications_layout)
    ConstraintLayout notificationsLayout;
    @BindView(R.id.language_layout)
    ConstraintLayout languageLayout;
    @BindView(R.id.shipping_address_layout) ConstraintLayout shippingAddressLayout;
    @BindView(R.id.order_counts_tv)
    TextView orderCountsTv;
    @BindView(R.id.wish_list_counts_tv)
    TextView wishListCountsTv;


    public MoreFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);


        unbinder = ButterKnife.bind(this, view);
        setViewsListener();
        presenter = new MorePresenter(this, getContext());

        return view;
    }

    private void setViewsListener() {

        myProfileLayout.setOnClickListener(this);
        logoutLayout.setOnClickListener(this);
        contactUsLayout.setOnClickListener(this);
        myOrdersLayout.setOnClickListener(this);
        wishListLayout.setOnClickListener(this);
        notificationsLayout.setOnClickListener(this);
        languageLayout.setOnClickListener(this);
        shippingAddressLayout.setOnClickListener(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.getUserCounts();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void setContactUsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.one_input_dialog, null);

        TextView dialogTitle = view.findViewById(R.id.dialog_title_tv);
        dialogTitle.setText(getResources().getString(R.string.contact_us));

        EditText inputEt = view.findViewById(R.id.dialog_et);
        inputEt.setHint(R.string.message);
        builder.setView(view);


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showLanguageDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.language_dialog, null);

        Button changeLanguageButton = view.findViewById(R.id.language_button);
        changeLanguageButton.setOnClickListener(view1 -> changeAppLanguage());
        builder.setView(view);


        languageDialog = builder.create();
        languageDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        languageDialog.show();
    }

    private void changeAppLanguage() {

        if (SharedPreferencesManager.getStringValue(getContext(), Constants.LANGUAGE).equals(Constants.ENGLISH)) {
            SharedPreferencesManager.setStringValue(getContext(), Constants.LANGUAGE, Constants.ARABIC);
            setLocale(Constants.ARABIC);
        } else {
            SharedPreferencesManager.setStringValue(getContext(), Constants.LANGUAGE, Constants.ENGLISH);
            setLocale(Constants.ENGLISH);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.my_profile_layout:
                startActivity(new Intent(getContext(), ProfileActivity.class));
                break;

            case R.id.log_out_layout:
                startActivity(new Intent(getContext(), StartActivity.class));
                SharedPreferencesManager.setIntValue(getContext(), Constants.USER_ID, 0);
                LoginManager.getInstance().logOut();
                getActivity().finish();
                break;

            case R.id.contact_us_layout:
                setContactUsDialog();
                break;

            case R.id.my_order_layout:
                startActivity(new Intent(getContext(), OrdersActivity.class));
                break;

                case R.id.shipping_address_layout:
                startActivity(new Intent(getContext(), ShippingAddressActivity.class));
                break;

            case R.id.wish_list_layout:
                startActivity(new Intent(getContext(), WishListActivity.class));
                break;

            case R.id.notifications_layout:
                startActivity(new Intent(getContext(), NotificationsActivity.class));
                break;
            case R.id.language_layout:
                showLanguageDialog();
                break;
        }
    }

    private void setLocale(String lang) {
        languageDialog.dismiss();
//        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            conf.setLocale(new Locale(lang)); // API 17+ only.
        } else {
            conf.locale = new Locale(lang);
        }
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(getActivity(), StartActivity.class);
        getActivity().finish();
        startActivity(refresh);

    }

    @Override
    public void displayUserCounts(UserCounts userCounts) {

        orderCountsTv.setText(userCounts.getOrders().getOrdersCount() + "");
        wishListCountsTv.setText(userCounts.getWishList().getWishListCount() + "");
    }
}
