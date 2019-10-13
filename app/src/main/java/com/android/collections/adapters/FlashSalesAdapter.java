package com.android.collections.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.collections.R;
import com.android.collections.helpers.Constants;
import com.android.collections.models.FlashSale;
import com.android.collections.models.TopOffer;
import com.android.collections.ui.activties.product_details.ProductDetailsActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class FlashSalesAdapter extends RecyclerView.Adapter<FlashSalesAdapter.ProductViewHolder> {


    private Context context;

    private List<FlashSale> mFlashSales = new ArrayList<>();



    public FlashSalesAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flash_sale_item, parent, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.place_holder).error(R.drawable.place_holder);



            Glide.with(context).setDefaultRequestOptions(requestOptions)
                    .load(mFlashSales.get(position).getMainImg())
                    .into(holder.productIv);
            holder.productPrice.setText(mFlashSales.get(position).getPrice() + " $");
            holder.productName.setText(mFlashSales.get(position).getName());
            holder.productCategory.setText(mFlashSales.get(position).getCatName());



    }


    @Override
    public int getItemCount() {

        return mFlashSales.size();
    }

    public void setFlashSalesData(List<FlashSale> flashSales) {

        mFlashSales = flashSales;
        notifyDataSetChanged();
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView productCategory, productName, productPrice;
        ImageView productIv;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productCategory = itemView.findViewById(R.id.product_category_tv);
            productName = itemView.findViewById(R.id.product_name_tv);
            productPrice = itemView.findViewById(R.id.product_price_tv);
            productIv = itemView.findViewById(R.id.product_iv);

            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
            context.startActivity(new Intent(context , ProductDetailsActivity.class));
        }
    }
}
