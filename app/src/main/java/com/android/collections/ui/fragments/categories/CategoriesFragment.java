package com.android.collections.ui.fragments.categories;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.android.collections.R;
import com.android.collections.adapters.CategoryParentAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment {

    private static final String TAG = "CategoriesFragment";
    private Unbinder unbinder;

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

        categoryParentRv.setAdapter(new CategoryParentAdapter(getContext()));
        categoryParentRv.setHasFixedSize(true);
        categoryParentRv.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }


}
