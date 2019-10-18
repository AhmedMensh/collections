package com.android.collections.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.collections.R;

public class CollectionAdapter  extends RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder> {
    @NonNull
    @Override
    public CollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_item,parent,false);
       view.findViewById(R.id.like_iv).setVisibility(View.INVISIBLE);
       return  new CollectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class CollectionViewHolder extends RecyclerView.ViewHolder {
        public CollectionViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
