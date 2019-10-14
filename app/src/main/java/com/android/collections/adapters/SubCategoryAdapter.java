package com.android.collections.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.collections.R;
import com.android.collections.models.SubCat;

import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ChildViewHolder> {

    private List<SubCat> mSubCategories;
    public SubCategoryAdapter(List<SubCat> subCategories) {

        mSubCategories = subCategories;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_child_item,parent,false);

        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder holder, int position) {

        holder.subCategoryName.setText("- "+mSubCategories.get(position).getNameSub());
    }

    @Override
    public int getItemCount() {
        return mSubCategories.size();
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder {
        TextView subCategoryName;
        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);

            subCategoryName = itemView.findViewById(R.id.sub_category_name);
        }
    }
}
