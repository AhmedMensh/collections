package com.android.collections.adapters;

import android.content.Context;
import android.content.Intent;
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
import com.android.collections.ui.activties.product_details.ProductDetailsActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class TrendAdapter  extends RecyclerView.Adapter<TrendAdapter.ViewHolder> {

    private List<NewTrend> mNewTrendList;
    private Context context;

    public TrendAdapter(Context context) {
        this.context = context;
        mNewTrendList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trend_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.place_holder).error(R.drawable.place_holder);



        Glide.with(context).setDefaultRequestOptions(requestOptions)
                .load(mNewTrendList.get(position).getMainImg())
                .into(holder.trendPic);
        holder.trendName.setText(mNewTrendList.get(position).getName());
    }

    public void setNewTrendList(List<NewTrend> newTrendList){
        mNewTrendList = newTrendList;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mNewTrendList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView trendName;
        ImageView trendPic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            trendName = itemView.findViewById(R.id.trend_name);
            trendPic = itemView.findViewById(R.id.trend_pic);

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            Intent i = new Intent(context , ProductDetailsActivity.class);
            i.putExtra(Constants.PRODUCT_ID,mNewTrendList.get(getAdapterPosition()).getId());
            context.startActivity(i);
        }
    }
}
