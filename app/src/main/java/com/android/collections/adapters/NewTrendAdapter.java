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
import com.android.collections.models.NewTrend;
import com.android.collections.models.TopOffer;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class NewTrendAdapter extends RecyclerView.Adapter<NewTrendAdapter.ProductViewHolder> {


    private Context context;

    private List<NewTrend> mNewTrendList = new ArrayList<>();



    public NewTrendAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_offer_item, parent, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        RequestOptions requestOptions = new RequestOptions().placeholder(R.mipmap.img).error(R.mipmap.img);



            Glide.with(context).setDefaultRequestOptions(requestOptions)
                    .load(mNewTrendList.get(position).getMainImg())
                    .into(holder.productIv);
            holder.productName.setText(mNewTrendList.get(position).getName());
            holder.productCategory.setText(mNewTrendList.get(position).getCatName());


    }


    @Override
    public int getItemCount() {

        return mNewTrendList.size();
    }

    public void setNewTrendsData(List<NewTrend> newTrendList) {

        mNewTrendList = newTrendList;
        notifyDataSetChanged();
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView productCategory, productName;
        ImageView productIv;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productCategory = itemView.findViewById(R.id.product_category_tv);
            productName = itemView.findViewById(R.id.product_name_tv);

            productIv = itemView.findViewById(R.id.product_iv);


        }


    }
}
