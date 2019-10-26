package com.android.dev.ahmed.collections.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.dev.ahmed.collections.R;
import com.android.dev.ahmed.collections.models.Slider;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.ProductViewHolder> {


    private Context context;

    private List<Slider> mSliderImages = new ArrayList<>();



    public SliderAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item, parent, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.place_holder).error(R.drawable.place_holder);



            Glide.with(context).setDefaultRequestOptions(requestOptions)
                    .load(mSliderImages.get(position).getImg())
                    .into(holder.productIv);



    }


    @Override
    public int getItemCount() {

        return mSliderImages.size();
    }

    public void setSliderImages(List<Slider> sliderImages) {
        mSliderImages =sliderImages;
        notifyDataSetChanged();
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView productIv;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productIv = itemView.findViewById(R.id.product_iv);



        }


    }
}
