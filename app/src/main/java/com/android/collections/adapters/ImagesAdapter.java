package com.android.collections.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.collections.R;
import com.android.collections.models.ProductDetails;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImageViewHolder> {

    private List<ProductDetails.Data.Image> mImageList;
    private Context context;
    public ImagesAdapter(Context context) {
        mImageList = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item,parent,false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        Glide.with(context)
                .setDefaultRequestOptions(new RequestOptions())
                .load(mImageList.get(position))
                .placeholder(R.drawable.place_holder)
                .into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }

    public void setProductImages(List<ProductDetails.Data.Image> images) {

        mImageList = images;
        notifyDataSetChanged();
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
        }
    }
}
