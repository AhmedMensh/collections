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
import com.android.collections.helpers.Constants;
import com.android.collections.models.NewArrival;
import com.android.collections.models.NewTrend;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class NewArrivalsAdapter extends RecyclerView.Adapter<NewArrivalsAdapter.ProductViewHolder> {


    private Context context;

    private List<NewArrival> mNewArrivalList = new ArrayList<>();



    public NewArrivalsAdapter(Context context) {
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

        RequestOptions requestOptions = new RequestOptions().placeholder(R.mipmap.img).error(R.mipmap.img);



            Glide.with(context).setDefaultRequestOptions(requestOptions)
                    .load(mNewArrivalList.get(position).getMainImg())
                    .into(holder.productIv);
            holder.productName.setText(mNewArrivalList.get(position).getName());
            holder.productPrice.setText(mNewArrivalList.get(position).getPrice()+" $");




    }


    @Override
    public int getItemCount() {

        return mNewArrivalList.size();
    }

    public void setNewArrivalsData(List<NewArrival> newArrivalList) {

        mNewArrivalList = newArrivalList;
        notifyDataSetChanged();
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView productCategory, productName , productPrice;
        ImageView productIv;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productCategory = itemView.findViewById(R.id.product_category_tv);
            productName = itemView.findViewById(R.id.product_name_tv);
            productPrice = itemView.findViewById(R.id.product_price_tv);
            productIv = itemView.findViewById(R.id.product_iv);


        }


    }
}
