package com.android.dev.ahmed.collections.ui.activties.product_details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.dev.ahmed.collections.R;
import com.android.dev.ahmed.collections.adapters.ImagesAdapter;
import com.android.dev.ahmed.collections.adapters.ProductSizesAdapter;
import com.android.dev.ahmed.collections.helpers.Constants;
import com.android.dev.ahmed.collections.helpers.PublicViewInf;
import com.android.dev.ahmed.collections.helpers.SharedPreferencesManager;
import com.android.dev.ahmed.collections.helpers.Utilities;
import com.android.dev.ahmed.collections.models.ProductDetails;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ProductDetailsActivity extends AppCompatActivity implements View.OnClickListener,
        PublicViewInf ,ProductDetailsViewInf,ProductSizesAdapter.ItemClickListener {

    //vars
    private static final String TAG = "ProductDetailsActivity";
    private ImagesAdapter imagesAdapter;
    private Unbinder unbinder;
    private int productId ,userId;
    private ProductDetailsPresenter presenter;
    private ProductSizesAdapter productSizesAdapter;
    BottomSheetDialog productSizeBottomSheetDialog;
    private ProductDetails mProductDetails;
    int selectedColorId =-1 , selectedSizeId , productQuantity =0;

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
    @BindView(R.id.product_colors_group)
    ChipGroup productColorsCg;


    private RecyclerView productSizesRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        unbinder = ButterKnife.bind(this);
        userId = SharedPreferencesManager.getIntValue(this,Constants.USER_ID);
        productId = getIntent().getIntExtra(Constants.PRODUCT_ID,0);
        presenter = new ProductDetailsPresenter(this ,this);
        presenter.getProductDetails(productId,userId);
        sizeBtn.setOnClickListener(this::onClick);
        initImagesRv();
        initToolbar();
        productSizesAdapter = new ProductSizesAdapter(this);



        productColorsCg.setOnCheckedChangeListener((group, checkedId) -> selectedColorId =group.getCheckedChipId());

    }

    private void addProductColorsToChipGroup(List<ProductDetails.Data.Size.Color> productColors){

        productColorsCg.removeAllViews();
        for (int i =0 ; i < productColors.size() ;i++){
            Chip chip = new Chip(productColorsCg.getContext());
            chip.setText(productColors.get(i).getColorName());
            chip.setChipMinHeight(120f);
            chip.setId(productColors.get(i).getColorId());
//            chip.setChipBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.orange)));
            chip.setClickable(true);
            chip.setCheckable(true);
            productColorsCg.addView(chip);
        }

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
        productSizeBottomSheetDialog = new BottomSheetDialog(this);

        productSizesRv = view.findViewById(R.id.product_sizes_rv);
        productSizesRv.setAdapter(productSizesAdapter);
        productSizesRv.setHasFixedSize(true);
        productSizesRv.setLayoutManager(new LinearLayoutManager(this));
        productSizeBottomSheetDialog.setContentView(view);
        productSizeBottomSheetDialog.show();
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

        mProductDetails =productDetails;
        imagesAdapter.setProductImages(productDetails.getData().getImages());
        productCatTv.setText(productDetails.getCatName().trim());
        productNameTv.setText(productDetails.getData().getName().trim());
        productDetailsTv.setText(productDetails.getData().getDetails().trim());
        productRateNumberTv.setText("("+productDetails.getData().getVoting().getAllVoting()+")");

        productSizesAdapter.setProductSizedDate(productDetails.getData().getSize());
//        if (productDetails.getData().getSize().size() > 0){
//            mProductAvailableSizes = productDetails.getData().getSize().get(0);
//            addProductColorsToChipGroup(mProductAvailableSizes.getColor());
//        }

    }

    @OnClick(R.id.add_to_cart_btn)
    public void onAddToCartClicked(){
        if (selectedSizeId == 0){
            Toast.makeText(this, "Please select size", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selectedColorId == -1){
            Toast.makeText(this, "Please select color", Toast.LENGTH_SHORT).show();
            return;
        }
        presenter.addToCart(userId,productId,productQuantity,selectedSizeId,selectedColorId);
    }

    @OnClick(R.id.add_to_wish_list_btn)
    public void onAddToWishListClicked(){
        presenter.addToFavorite(productId,userId);
    }

    @Override
    public void onItemSizeClickListener(ProductDetails.Data.Size item) {
        sizeBtn.setText(item.getSizeName());
        productSizeBottomSheetDialog.dismiss();

        addProductColorsToChipGroup(item.getColor());
        selectedSizeId = item.getSizeId();
        productQuantity = item.getQuantity();

    }
}
