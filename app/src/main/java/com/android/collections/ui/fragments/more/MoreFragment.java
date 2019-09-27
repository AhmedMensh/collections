package com.android.collections.ui.fragments.more;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.collections.R;
import com.android.collections.ui.activties.notifications.NotificationsActivity;
import com.android.collections.ui.activties.orders.OrdersActivity;
import com.android.collections.ui.activties.profile.ProfileActivity;
import com.android.collections.ui.activties.start.StartActivity;
import com.android.collections.ui.activties.wish_list.WishListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MoreFragment extends Fragment implements View.OnClickListener {


    private Unbinder unbinder;


    @BindView(R.id.my_profile_layout)
    LinearLayout myProfileLayout;
    @BindView(R.id.log_out_layout)
    LinearLayout logoutLayout;
    @BindView(R.id.contact_us_layout)
    LinearLayout contactUsLayout;
    @BindView(R.id.my_order_layout)
    LinearLayout myOrdersLayout;
    @BindView(R.id.wish_list_layout)
    LinearLayout wishListLayout;
    @BindView(R.id.notifications_layout)
    LinearLayout notificationsLayout;


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
        return view;
    }

    private void setViewsListener() {

        myProfileLayout.setOnClickListener(this::onClick);
        logoutLayout.setOnClickListener(this::onClick);
        contactUsLayout.setOnClickListener(this::onClick);
        myOrdersLayout.setOnClickListener(this::onClick);
        wishListLayout.setOnClickListener(this::onClick);
        notificationsLayout.setOnClickListener(this::onClick);
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

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.my_profile_layout:
                startActivity(new Intent(getContext(), ProfileActivity.class));
                break;

            case R.id.log_out_layout:
                startActivity(new Intent(getContext(), StartActivity.class));
                getActivity().finish();
                break;

            case R.id.contact_us_layout:
                setContactUsDialog();
                break;

            case R.id.my_order_layout:
                startActivity(new Intent(getContext(), OrdersActivity.class));
                break;

            case R.id.wish_list_layout:
                startActivity(new Intent(getContext(), WishListActivity.class));
                break;

            case R.id.notifications_layout:
                startActivity(new Intent(getContext(), NotificationsActivity.class));
                break;
        }
    }
}
