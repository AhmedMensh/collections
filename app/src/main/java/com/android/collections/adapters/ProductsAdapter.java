package com.android.collections.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.collections.R;
import com.android.collections.helpers.Constants;
import com.android.collections.ui.activties.collection.CollectionActivity;
import com.android.collections.ui.activties.product_details.ProductDetailsActivity;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {


    private Context context;
    private int layout;


    public ProductsAdapter(Context context , int layout) {
        this.context = context;
        this.layout =layout;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder  {

        RecyclerView brandRv;
        LinearLayout layout;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            brandRv = itemView.findViewById(R.id.brand_images);
            layout = itemView.findViewById(R.id.layout);



        }



    }
}
