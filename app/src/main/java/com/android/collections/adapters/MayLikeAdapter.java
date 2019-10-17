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
import com.android.collections.models.TopOffer;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class MayLikeAdapter extends RecyclerView.Adapter<MayLikeAdapter.ViewHolder> {


    private Context context;

    private List<TopOffer> mDataList = new ArrayList<>();



    public MayLikeAdapter(Context context) {
        this.context = context;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.may_like_item,parent,false);

      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.place_holder).error(R.drawable.place_holder);


        Glide.with(context).setDefaultRequestOptions(requestOptions)
                .load(mDataList.get(position).getMainImg())
                .into(holder.productIv);
        holder.productPrice.setText(mDataList.get(position).getPrice() + " $");
        holder.productName.setText(mDataList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return  mDataList.size();
    }

    public void setData(List<TopOffer> topOfferList) {

        mDataList = topOfferList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productCategory, productName, productPrice;
        ImageView productIv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);



            productName = itemView.findViewById(R.id.product_name_tv);
            productPrice = itemView.findViewById(R.id.product_price_tv);
            productIv = itemView.findViewById(R.id.product_iv);
        }
    }
}
