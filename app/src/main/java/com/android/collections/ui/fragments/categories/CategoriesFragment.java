package com.android.collections.ui.fragments.categories;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.collections.R;
import com.android.collections.adapters.MainCategoryAdapter;
import com.android.collections.helpers.PublicViewInf;
import com.android.collections.helpers.Utilities;
import com.android.collections.models.Category;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment implements PublicViewInf ,CategoryViewInf {

    private static final String TAG = "CategoriesFragment";
    private Unbinder unbinder;
    private CategoryPresenter presenter;
    private MainCategoryAdapter categoryParentAdapter;

    @BindView(R.id.category_parent_lv)
    RecyclerView categoryParentRv;

    public CategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_categories, container, false);

        unbinder = ButterKnife.bind(this,view);


        initMainCategoryRv();
        presenter = new CategoryPresenter(this,this);

        presenter.getMainCategories();
        return view;
    }

    private void initMainCategoryRv(){

        categoryParentAdapter = new MainCategoryAdapter(getContext());
        categoryParentRv.setAdapter(categoryParentAdapter);
        categoryParentRv.setHasFixedSize(true);
        categoryParentRv.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void showMessage(String m) {
        Utilities.showToast(getContext(),m);
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void displaycategoryList(List<Category> categoryList) {

        Log.e(TAG, "displaycategoryList: "+categoryList.size());
        categoryParentAdapter.setCategoryList(categoryList);
    }
}
