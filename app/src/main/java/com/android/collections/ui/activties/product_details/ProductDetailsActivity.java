package com.android.collections.ui.activties.product_details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.collections.R;
import com.android.collections.adapters.ImagesAdapter;
import com.android.collections.helpers.PublicViewInf;
import com.android.collections.helpers.Utilities;
import com.android.collections.models.product_detalis.ProductDetails;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProductDetailsActivity extends AppCompatActivity implements View.OnClickListener,
        PublicViewInf ,ProductDetailsViewInf {

    //vars
    private static final String TAG = "ProductDetailsActivity";
    private ImagesAdapter imagesAdapter;
    private Unbinder unbinder;
    private ProductDetailsPresenter presenter;

    //widgets
    @BindView(R.id.product_images_rv)
    RecyclerView imagesRv;
    @BindView(R.id.size_btn)
    Button sizeBtn;
    @BindView(R.id.product_category_tv)
    TextView productCatTv;
    @BindView(R.id.product_name_tv)
    TextView productNameTv;
    @BindView(R.id.product_details_tv) TextView productDetailsTv;
    @BindView(R.id.rate_number_tv) TextView productRateNumberTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        unbinder = ButterKnife.bind(this);
        presenter = new ProductDetailsPresenter(this ,this);
        presenter.getProductDetails();
        sizeBtn.setOnClickListener(this::onClick);
        initImagesRv();
        initToolbar();
    }

    private void initToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initImagesRv() {

        imagesAdapter = new ImagesAdapter(this);
        imagesRv.setLayoutManager(new LinearLayoutManager(this));
        imagesRv.setHasFixedSize(true);
        imagesRv.setAdapter(imagesAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void setSizeBottomSheet() {
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_size, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);

        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.size_btn:
                setSizeBottomSheet();
                break;
        }
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
    public void displayProductDetails(ProductDetails productDetails) {

        imagesAdapter.setProductImages(productDetails.getData().getImages());
        productCatTv.setText(productDetails.getCatName().trim());
        productNameTv.setText(productDetails.getData().getName().trim());
        productDetailsTv.setText(productDetails.getData().getDetails().trim());
        productRateNumberTv.setText("("+productDetails.getData().getVoting().getAllVoting()+")");
    }
}
