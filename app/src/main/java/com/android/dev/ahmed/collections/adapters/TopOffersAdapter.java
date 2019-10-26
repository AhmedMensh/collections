package com.android.dev.ahmed.collections.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.dev.ahmed.collections.R;
import com.android.dev.ahmed.collections.helpers.Constants;
import com.android.dev.ahmed.collections.models.TopOffer;
import com.android.dev.ahmed.collections.ui.activties.product_details.ProductDetailsActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class TopOffersAdapter extends RecyclerView.Adapter<TopOffersAdapter.ProductViewHolder> {

    private static final String TAG = "TopOffersAdapter";


    private Context context;

    private List<TopOffer> mTopOfferList = new ArrayList<>();



    public TopOffersAdapter(Context context) {
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

        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.place_holder).error(R.drawable.place_holder);


            Glide.with(context).setDefaultRequestOptions(requestOptions)
                    .load(mTopOfferList.get(position).getMainImg())
                    .into(holder.productIv);
            holder.productPrice.setText(mTopOfferList.get(position).getPrice() + " $");
            holder.productName.setText(mTopOfferList.get(position).getName());
            holder.productCategory.setText(mTopOfferList.get(position).getCatName());

        Log.e(TAG, "onBindViewHolder: "+mTopOfferList.get(position).getId());


    }


    @Override
    public int getItemCount() {

        return mTopOfferList.size();
    }

    public void setTopOffersData(List<TopOffer> topOfferList) {

        mTopOfferList = topOfferList;
        notifyDataSetChanged();
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

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

            Intent i = new Intent(context , ProductDetailsActivity.class);
            i.putExtra(Constants.PRODUCT_ID,mTopOfferList.get(getAdapterPosition()).getId());
            context.startActivity(i);
        }


    }
}
