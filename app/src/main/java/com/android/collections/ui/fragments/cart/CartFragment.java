
package com.android.collections.ui.fragments.cart;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.collections.R;
import com.android.collections.adapters.CartAdapter;
import com.android.collections.adapters.MayLikeAdapter;
import com.android.collections.ui.activties.home.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment implements View.OnClickListener{


    private static final String TAG = "CartActivity";
    private CartAdapter cartAdapter;
    private Unbinder unbinder;
    private MayLikeAdapter mayLikeAdapter;

    @BindView(R.id.cart_rv)
    RecyclerView cartRv;

    @BindView(R.id.code_tv)
    TextView codeBtn;
    @BindView(R.id.check_out_btn)
    Button checkOutBtn;

    @BindView(R.id.may_like_rv) RecyclerView mayLikeRv;


    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_cart, container, false);

        unbinder = ButterKnife.bind(this,view);
        initViews();
        initCartRv();
        initMayLikeRv();
        return view;
    }

    private void initViews() {

        codeBtn.setOnClickListener(this::onClick);
        checkOutBtn.setOnClickListener(this::onClick);
    }

    private void initMayLikeRv() {

        mayLikeAdapter = new MayLikeAdapter();
        mayLikeRv.setAdapter(mayLikeAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mayLikeRv.setHasFixedSize(true);
        mayLikeRv.setLayoutManager(layoutManager);
    }

    private void initCartRv() {

        cartAdapter = new CartAdapter();
        cartRv.setAdapter(cartAdapter);
        cartRv.setHasFixedSize(true);
        cartRv.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void setPromoteCodeDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.one_input_dialog,null);

        TextView dialogTitle = view.findViewById(R.id.dialog_title_tv);
        dialogTitle.setText(getResources().getString(R.string.promote_code));

        EditText inputEt = view.findViewById(R.id.dialog_et);
        inputEt.setHint(R.string.promote_code);
        builder.setView(view);


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void setCheckOutDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.check_out_order_dialog,null);

        Button doneBtn = view.findViewById(R.id.done_btn);
        doneBtn.setOnClickListener(this::onClick);
        builder.setView(view);


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id){
            case R.id.code_tv:
                setPromoteCodeDialog();
                break;

            case R.id.check_out_btn:
                setCheckOutDialog();
                break;

            case R.id.done_btn:
                startActivity(new Intent(getContext(), HomeActivity.class));
                getActivity().finish();
                break;
        }
    }
}
