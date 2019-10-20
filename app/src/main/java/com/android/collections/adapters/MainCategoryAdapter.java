package com.android.collections.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.collections.R;
import com.android.collections.helpers.Constants;
import com.android.collections.models.Category;
import com.android.collections.ui.activties.collection.CollectionActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainCategoryAdapter extends RecyclerView.Adapter<MainCategoryAdapter.CategoryParentViewHolder> {

    private static final String TAG = "MainCategoryAdapter";
    private Context context;
    private List<Category> mCategoryList = new ArrayList<>();

    public MainCategoryAdapter(Context context) {
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
        holder.categoriesChildRv.setAdapter(new SubCategoryAdapter(mCategoryList.get(position).getSubCategories(),context));

        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.place_holder).error(R.drawable.place_holder);


        Glide.with(context).setDefaultRequestOptions(requestOptions)
                .load(mCategoryList.get(position).getImg())
                .into(holder.categoryImage);

    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }

    public void setCategoryList(List<Category> categoryList) {
        mCategoryList = categoryList;
        notifyDataSetChanged();
    }

    public class CategoryParentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

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
                    Intent i = new Intent(context, CollectionActivity.class);
                    i.putExtra(Constants.PRODUCT_ID,mCategoryList.get(getAdapterPosition()).getId());
                    i.putExtra(Constants.PRODUCT_NAME,mCategoryList.get(getAdapterPosition()).getName());
                    context.startActivity(i);
                    break;

            }
        }
    }
}
