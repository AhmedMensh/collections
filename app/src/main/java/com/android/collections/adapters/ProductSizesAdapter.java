package com.android.collections.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.collections.R;
import com.android.collections.models.ProductDetails;


import java.util.ArrayList;
import java.util.List;

public class ProductSizesAdapter extends RecyclerView.Adapter<ProductSizesAdapter.ViewHolder> {

    private List<ProductDetails.Data.Size> mProSize;

    public ItemClickListener listener;

    public ProductSizesAdapter(ItemClickListener listener) {
        mProSize = new ArrayList<>();
        this.listener = listener;
    }



    public interface ItemClickListener{
        void onItemSizeClickListener(ProductDetails.Data.Size item);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_size_item , parent ,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.productSizeNameTv.setText(mProSize.get(position).getSizeName());
    }

    @Override
    public int getItemCount() {
        return mProSize.size();
    }

    public void setProductSizedDate(List<ProductDetails.Data.Size> productSizedDate) {

        mProSize = productSizedDate;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView productSizeNameTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productSizeNameTv = itemView.findViewById(R.id.product_size_tv);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            listener.onItemSizeClickListener(mProSize.get(getAdapterPosition()));
        }
    }
}
