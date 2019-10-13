package com.android.collections.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.collections.R;
import com.android.collections.models.product_detalis.ProSizeArabic;

import java.util.ArrayList;
import java.util.List;

public class ProductSizesAdapter extends RecyclerView.Adapter<ProductSizesAdapter.ViewHolder> {

    private List<ProSizeArabic> mProSizeArabic;
    public ProductSizesAdapter() {
        mProSizeArabic = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_size_item , parent ,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.productSizeNameTv.setText(mProSizeArabic.get(position).getSizeName());
    }

    @Override
    public int getItemCount() {
        return mProSizeArabic.size();
    }

    public void setProductSizedDate(List<ProSizeArabic> proSizeArabic) {

        mProSizeArabic = proSizeArabic;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productSizeNameTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productSizeNameTv = itemView.findViewById(R.id.product_size_tv);
        }
    }
}
