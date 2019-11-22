
package com.android.dev.ahmed.collections.ui.fragments.cart;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.dev.ahmed.collections.R;
import com.android.dev.ahmed.collections.adapters.CartAdapter;
import com.android.dev.ahmed.collections.adapters.MayLikeAdapter;
import com.android.dev.ahmed.collections.helpers.PublicViewInf;
import com.android.dev.ahmed.collections.helpers.Utilities;
import com.android.dev.ahmed.collections.models.ApiResponse;
import com.android.dev.ahmed.collections.models.CartItems;
import com.android.dev.ahmed.collections.models.PaymentResponse;
import com.android.dev.ahmed.collections.ui.activties.home.HomeActivity;
import com.android.dev.ahmed.collections.ui.activties.shipping_address.ShippingAddressActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment implements View.OnClickListener, PublicViewInf, CartViewInf, CartAdapter.ItemClickListener {


    private static final String TAG = "CartActivity";
    private CartAdapter cartAdapter;
    private Unbinder unbinder;
    private MayLikeAdapter mayLikeAdapter;
    private CartPresenter presenter;
    private int paymentType = 0, promoCode;

    @BindView(R.id.cart_rv)
    RecyclerView cartRv;
    @BindView(R.id.code_tv)
    TextView codeBtn;
    @BindView(R.id.check_out_btn)
    Button checkOutBtn;
    @BindView(R.id.products_like_me_rv)
    RecyclerView mayLikeRv;
    @BindView(R.id.sub_total_tv)
    TextView subTotalTv;
    @BindView(R.id.shipping_tv)
    TextView shippingTv;
    @BindView(R.id.total_tv)
    TextView totalTv;
    @BindView(R.id.no_cart_items_cl)
    ConstraintLayout noItemsCl;
    @BindView(R.id.cart_items_cl)
    ConstraintLayout cartItemsCl;
    @BindView(R.id.payment_method_rg)
    RadioGroup paymentMethodRg;
    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.editAddressImgV)
    ImageView editAddressImg;
    @BindView(R.id.shipping_address_tv) TextView shippingAddressTV;


    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        unbinder = ButterKnife.bind(this, view);
        initViews();
        initCartRv();
        initMayLikeRv();


        presenter = new CartPresenter(this, this,getContext());



        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        paymentMethodRg.setOnCheckedChangeListener((radioGroup, i) -> {
            paymentType = i;
            if (i % 2 == 0) paymentType = 2;
            else paymentType = 1;
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getCartItems();
        presenter.getUserDefaultAddress();
    }

    private void initViews() {

        codeBtn.setOnClickListener(this::onClick);
        checkOutBtn.setOnClickListener(this::onClick);
        editAddressImg.setOnClickListener(this);
    }

    private void initMayLikeRv() {

        mayLikeAdapter = new MayLikeAdapter(getContext());
        mayLikeRv.setAdapter(mayLikeAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mayLikeRv.setHasFixedSize(true);
        mayLikeRv.setLayoutManager(layoutManager);
    }

    private void initCartRv() {

        cartAdapter = new CartAdapter(getContext(), this);
        cartRv.setAdapter(cartAdapter);
        cartRv.setHasFixedSize(true);
        cartRv.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void setPromoteCodeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.one_input_dialog, null);

        TextView dialogTitle = view.findViewById(R.id.dialog_title_tv);
        dialogTitle.setText(getResources().getString(R.string.promote_code));

        EditText inputEt = view.findViewById(R.id.dialog_et);
        inputEt.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        inputEt.setHint(R.string.promote_code);
        builder.setView(view);

        promoCode = Integer.parseInt(inputEt.getText().toString());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

//    private void setCheckOutDialog(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        View view = getLayoutInflater().inflate(R.layout.check_out_order_dialog,null);
//
//        Button doneBtn = view.findViewById(R.id.done_btn);
//        doneBtn.setOnClickListener(this::onClick);
//        builder.setView(view);
//
//
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {
            case R.id.code_tv:
                setPromoteCodeDialog();
                break;

            case R.id.check_out_btn:
                if (paymentType == 0) {
                    Toast.makeText(getContext()
                            , "Please select payment", Toast.LENGTH_SHORT).show();
                    return;
                }
                presenter.paymentCheckout(paymentType, promoCode);
                break;

            case R.id.done_btn:
                startActivity(new Intent(getContext(), HomeActivity.class));
                getActivity().finish();
                break;

            case R.id.editAddressImgV:
                startActivity(new Intent(getContext(), ShippingAddressActivity.class));
                break;
        }
    }

    @Override
    public void showMessage(String m) {

        Utilities.showToast(getContext(), m);
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void displayCartItems(CartItems items) {


        Log.e(TAG, "displayCartItems: "+items.getItemList().size() );
        cartAdapter.setCartItemList(items.getItemList());
        mayLikeAdapter.setData(items.getDataLiked());
        try {
            subTotalTv.setText(items.getSubTotal() + "");
            shippingTv.setText(items.getShipping() + "");
            totalTv.setText(items.getTotal() + "");
        }catch (Exception e){}

        if (items.getItemList().size() > 0) {
            cartItemsCl.setVisibility(View.VISIBLE);
            noItemsCl.setVisibility(View.INVISIBLE);
        } else {
            cartItemsCl.setVisibility(View.INVISIBLE);
            noItemsCl.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public void checkoutResponse(ApiResponse<PaymentResponse> response) {

        if (!response.getPaymentUrl().equals("")) {

            webView.loadUrl(response.getPaymentUrl());
            webView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getDefaultAddress(String address) {

        shippingAddressTV.setText(address);
    }

    @Override
    public void onDeleteIconClicked(int cartId) {

        presenter.removeItemFromCart(cartId);

    }

    @Override
    public void updateItemQuantity(CartItems.CartItem item) {
        presenter.updateItemQuantity(item.getID(), item.getQuantity(), item.getSizeId(), item.getColorId());
    }
}
