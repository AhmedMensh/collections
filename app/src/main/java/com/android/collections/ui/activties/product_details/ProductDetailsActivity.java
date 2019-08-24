package com.android.collections.ui.activties.product_details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.collections.R;
import com.android.collections.adapters.ImagesAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProductDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    //vars
    private static final String TAG = "ProductDetailsActivity";
    private ImagesAdapter imagesAdapter;
    private Unbinder unbinder;

    //widgets
    @BindView(R.id.product_images_rv)
    RecyclerView imagesRv;
    @BindView(R.id.size_btn)
    Button sizeBtn;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        unbinder = ButterKnife.bind(this);
        sizeBtn.setOnClickListener(this::onClick);
        initImagesRv();
        initToolbar();
    }

    private void initToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initImagesRv() {

        imagesAdapter = new ImagesAdapter();
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
}
