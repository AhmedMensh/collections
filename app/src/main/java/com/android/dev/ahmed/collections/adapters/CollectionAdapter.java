package com.android.dev.ahmed.collections.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.dev.ahmed.collections.R;
import com.android.dev.ahmed.collections.models.Collection;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder> {

    List<Collection.Product> mProducts;
    private Context context;

    public CollectionAdapter(Context  context) {
        this.mProducts = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public CollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flash_sale_item, parent, false);
        return new CollectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionViewHolder holder, int position) {


        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.place_holder).error(R.drawable.place_holder);



        Glide.with(context).setDefaultRequestOptions(requestOptions)
                .load(mProducts.get(position).getMainImg())
                .into(holder.productIv);
        holder.productNameTv.setText(mProducts.get(position).getName());
        holder.productPriceTv.setText(mProducts.get(position).getPrice()+" $");
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public void setCollectionData(List<Collection.Product> products) {

        mProducts = products;
        notifyDataSetChanged();
    }

    public class CollectionViewHolder extends RecyclerView.ViewHolder {

        ImageView productIv;
        TextView productCategoryTv, productNameTv, productPriceTv;

        public CollectionViewHolder(@NonNull View itemView) {
            super(itemView);

            productCategoryTv = itemView.findViewById(R.id.product_category_tv);
            productNameTv = itemView.findViewById(R.id.product_name_tv);
            productPriceTv = itemView.findViewById(R.id.product_price_tv);
            productIv = itemView.findViewById(R.id.product_iv);
        }
    }
}
