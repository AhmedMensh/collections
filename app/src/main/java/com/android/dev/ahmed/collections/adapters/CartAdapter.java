package com.android.dev.ahmed.collections.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.dev.ahmed.collections.R;
import com.android.dev.ahmed.collections.helpers.Constants;
import com.android.dev.ahmed.collections.models.CartItems;
import com.android.dev.ahmed.collections.ui.activties.product_details.ProductDetailsActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItems.CartItem> mCartItemList;
    private Context context;
    private ItemClickListener listener;

    public interface ItemClickListener{
        void onDeleteIconClicked(int id);
        void updateItemQuantity(CartItems.CartItem item);
    }
    public CartAdapter(Context context,ItemClickListener listener) {
        this.listener =listener;
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

    public void setCartItemList(List<CartItems.CartItem> cartItemList) {

        mCartItemList = cartItemList;
        notifyDataSetChanged();
    }

    private void increaseItemQuantity(int itemPosition){
        mCartItemList.get(itemPosition).setQuantity(mCartItemList.get(itemPosition).getQuantity()+1);
        listener.updateItemQuantity(mCartItemList.get(itemPosition));
    }

    private void decrementItemQuantity(int itemPosition){

        if (mCartItemList.get(itemPosition).getQuantity() > 1){

            mCartItemList.get(itemPosition).setQuantity(mCartItemList.get(itemPosition).getQuantity() -1);
            listener.updateItemQuantity(mCartItemList.get(itemPosition));
        }
    }

    public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView orderIv , deleteIv;
        TextView orderTypeTv , orderDescTv , orderSizeTv , orderQuantityTv , orderColorTv, orderPriceTv ,
                orderIncrementIv,orderDecrementIv;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            orderIv = itemView.findViewById(R.id.order_iv);
            orderTypeTv = itemView.findViewById(R.id.order_type_tv);
            orderDescTv = itemView.findViewById(R.id.order_desc_tv);
            orderSizeTv = itemView.findViewById(R.id.order_size_tv);
            orderQuantityTv = itemView.findViewById(R.id.order_quantity_tv);
            orderColorTv = itemView.findViewById(R.id.order_color_tv);
            orderPriceTv = itemView.findViewById(R.id.order_price_tv);
            orderIncrementIv =itemView.findViewById(R.id.order_increment_iv);
            orderDecrementIv = itemView.findViewById(R.id.order_decrement_iv);
            deleteIv = itemView.findViewById(R.id.delete_iv);

            orderIncrementIv.setOnClickListener(this);
            orderDecrementIv.setOnClickListener(this);
            deleteIv.setOnClickListener(this);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            switch (view.getId()){
                case R.id.order_increment_iv:
                    increaseItemQuantity(getAdapterPosition());
                    break;

                case R.id.order_decrement_iv:
                    decrementItemQuantity(getAdapterPosition());
                    break;

                case R.id.delete_iv:

                    listener.onDeleteIconClicked(mCartItemList.get(getAdapterPosition()).getCartId());
                    break;
                default:
                    Intent i = new Intent(context , ProductDetailsActivity.class);
                    i.putExtra(Constants.PRODUCT_ID,mCartItemList.get(getAdapterPosition()).getID());
                    context.startActivity(i);
                    break;

            }
        }
    }
}
