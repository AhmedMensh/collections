package com.android.collections.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.collections.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryParentAdapter extends RecyclerView.Adapter<CategoryParentAdapter.CategoryParentViewHolder> {

    private Context context;

    public CategoryParentAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_parent_item, parent, false);
        return new CategoryParentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryParentViewHolder holder, int position) {

        holder.categoriesChildRv.setLayoutManager(new LinearLayoutManager(context));
        holder.categoriesChildRv.setHasFixedSize(true);
        holder.categoriesChildRv.setAdapter(new CategoryChildAdapter());


    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class CategoryParentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.category_child_lv)
        RecyclerView categoriesChildRv;

        @BindView(R.id.category_iv)
        ImageView categoryImage;
        public CategoryParentViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            categoryImage.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.category_iv:

                    if (categoriesChildRv.getVisibility() == View.GONE) {
                        categoriesChildRv.setVisibility(View.VISIBLE);
                    } else {
                        categoriesChildRv.setVisibility(View.GONE);
                    }
                    break;

            }
        }
    }
}
