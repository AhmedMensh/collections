package com.android.collections.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.collections.R;
import com.android.collections.models.CartItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> mCartItemList;
    private Context context;

    public CartAdapter(Context context) {

        this.context = context;
        mCartItemList = new ArrayList<>();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {


        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.place_holder).error(R.drawable.place_holder);


        Glide.with(context).setDefaultRequestOptions(requestOptions)
                .load(mCartItemList.get(position).getMainImg())
                .into(holder.orderIv);

        holder.orderPriceTv.setText(mCartItemList.get(position).getPrice()+"");
        holder.orderTypeTv.setText(mCartItemList.get(position).getName());
        holder.orderDescTv.setText(mCartItemList.get(position).getSellerName());
        holder.orderSizeTv.setText(mCartItemList.get(position).getSizeName());
        holder.orderQuantityTv.setText(mCartItemList.get(position).getQuantity()+"");
        holder.orderColorTv.setText(mCartItemList.get(position).getColorName());
    }

    @Override
    public int getItemCount() {
        return mCartItemList.size();
    }

    public void setCartItemList(List<CartItem> cartItemList) {

        mCartItemList = cartItemList;
        notifyDataSetChanged();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView orderIv;
        TextView orderTypeTv , orderDescTv , orderSizeTv , orderQuantityTv , orderColorTv, orderPriceTv;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            orderIv = itemView.findViewById(R.id.order_iv);
            orderTypeTv = itemView.findViewById(R.id.order_type_tv);
            orderDescTv = itemView.findViewById(R.id.order_desc_tv);
            orderSizeTv = itemView.findViewById(R.id.order_size_tv);
            orderQuantityTv = itemView.findViewById(R.id.order_quantity_tv);
            orderColorTv = itemView.findViewById(R.id.order_color_tv);
            orderPriceTv = itemView.findViewById(R.id.order_price_tv);
        }
    }
}
