package com.android.dev.ahmed.collections.ui.activties.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.dev.ahmed.collections.R;
import com.android.dev.ahmed.collections.adapters.TopOffersAdapter;
import com.android.dev.ahmed.collections.helpers.PublicViewInf;
import com.android.dev.ahmed.collections.models.TopOffer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SearchActivity extends AppCompatActivity implements PublicViewInf ,SearchViewInf {

    private static final String TAG = "SearchActivity";
    private Unbinder unbinder;
    private SearchPresenter presenter;
    private TopOffersAdapter adapter;
    @BindView(R.id.searchView) SearchView searchView;
    @BindView(R.id.search_products_rv) RecyclerView searchRV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        unbinder = ButterKnife.bind(this);
        presenter = new SearchPresenter(this ,this);
        initSearchRv();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return true;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                presenter.getSearchResult(newText);
                return true;
            }
        });
    }

    private void initSearchRv() {

        adapter = new TopOffersAdapter(this);
        searchRV.setHasFixedSize(true);
        searchRV.setAdapter(adapter);

        searchRV.setLayoutManager(new GridLayoutManager(this,2, RecyclerView.VERTICAL,false));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void showMessage(String m) {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void dispalySearchResult(List<TopOffer> searchList) {

       adapter.setTopOffersData(searchList);
    }
}
