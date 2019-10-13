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
import com.android.collections.models.Favorite;
import com.android.collections.ui.activties.product_details.ProductDetailsActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private List<Favorite> mFavoriteList;
    private Context context;

    public FavoriteAdapter(Context context) {
        this.context = context;

        mFavoriteList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.place_holder).error(R.drawable.place_holder);


        Glide.with(context).setDefaultRequestOptions(requestOptions)
                .load(mFavoriteList.get(position).getMainImg())
                .into(holder.productIv);

        holder.productPriceTv.setText(mFavoriteList.get(position).getPrice()+"");
    }

    public void setFavoriteList(List<Favorite> mFavoriteList) {
        this.mFavoriteList = mFavoriteList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mFavoriteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView productIv;
        TextView productPriceTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productIv = itemView.findViewById(R.id.product_iv);
            productPriceTv = itemView.findViewById(R.id.product_price_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            context.startActivity(new Intent(context , ProductDetailsActivity.class));
        }
    }
}
