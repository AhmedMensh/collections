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
import com.android.collections.models.Order;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderViewHolder> {


    private static final String TAG = "OrdersAdapter";
    private List<Order> mOrderList;
    private Context context;

    public OrdersAdapter(Context context) {
        this.context = context;
        mOrderList = new ArrayList<>();
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item,parent,false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {

        holder.orderDescTv.setText(mOrderList.get(position).getProDetails());
        holder.orderTypeTv.setText(mOrderList.get(position).getProductName());
        holder.orderPriceTv.setText(mOrderList.get(position).getPrice()+"");
        holder.orderQualityTv.setText(mOrderList.get(position).getQuantity()+"");
        holder.orderSizeTv.setText(mOrderList.get(position).getSize());


        Glide.with(context).load(mOrderList.get(position).getImg()).into(holder.orderIv);
    }

    @Override
    public int getItemCount() {
        return mOrderList.size();
    }

    public void setOrderListData(List<Order> orderList) {

        this.mOrderList = orderList;
        notifyDataSetChanged();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView orderTypeTv ,orderDescTv ,orderSizeTv ,orderQualityTv ,orderColorTv ,shippingTv ,orderPriceTv;
        ImageView orderIv;
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderIv = itemView.findViewById(R.id.order_iv);

            orderTypeTv = itemView.findViewById(R.id.order_type_tv);
            orderDescTv = itemView.findViewById(R.id.order_desc_tv);
            orderSizeTv = itemView.findViewById(R.id.order_size_tv);
            orderQualityTv = itemView.findViewById(R.id.order_quality_tv);
            orderColorTv = itemView.findViewById(R.id.order_color_tv);
            shippingTv = itemView.findViewById(R.id.shipping_tv);
            orderPriceTv = itemView.findViewById(R.id.order_price_tv);
        }
    }
}
