package com.android.dev.ahmed.collections.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.dev.ahmed.collections.R;
import com.android.dev.ahmed.collections.models.Favorite;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private List<Favorite> mFavoriteList;
    private Context context;
    public ItemClickListener listener;

    public interface ItemClickListener{
        void onItemClickListener(int id);
        void onLikeIconClickListener(int id);
    }
    public FavoriteAdapter(Context context , ItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        mFavoriteList = new ArrayList<>();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.place_holder).error(R.drawable.place_holder);


        Glide.with(context).setDefaultRequestOptions(requestOptions)
                .load(mFavoriteList.get(position).getMainImg())
                .into(holder.productIv);

        holder.productPriceTv.setText(mFavoriteList.get(position).getPrice()+"");
    }

    public void setFavoriteList(List<Favorite> mFavoriteList) {
        this.mFavoriteList = mFavoriteList;
        notifyDataSetChanged();
    }

    private void removeItemFromWishList(int position){
        mFavoriteList.remove(position);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mFavoriteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView productIv , likeIv;
        TextView productPriceTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productIv = itemView.findViewById(R.id.product_iv);
            productPriceTv = itemView.findViewById(R.id.product_price_tv);
            likeIv = itemView.findViewById(R.id.like_iv);

            likeIv.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            switch (view.getId()){
                case R.id.like_iv:
                    listener.onLikeIconClickListener(mFavoriteList.get(getAdapterPosition()).getID());
                    removeItemFromWishList(getAdapterPosition());
                    break;

                    default:listener.onItemClickListener(mFavoriteList.get(getAdapterPosition()).getID());
            }

        }
    }
}
