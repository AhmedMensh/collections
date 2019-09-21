package com.android.collections.ui.fragments.trend;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.collections.R;
import com.android.collections.adapters.TrendAdapter;
import com.android.collections.helpers.PublicViewInf;
import com.android.collections.helpers.Utilities;
import com.android.collections.models.NewTrend;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrendFragment extends Fragment implements PublicViewInf ,TrendViewInf {


    private static final String TAG = "TrendFragment";
    private Unbinder unbinder;
    private TrendAdapter trendAdapter;
    private TrendPresenter presenter;

    @BindView(R.id.trend_rv)
    RecyclerView trendRv;

    public TrendFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_trend, container, false);

        unbinder = ButterKnife.bind(this,view);
        presenter = new TrendPresenter(this ,this);
        presenter.getTrends();
        initTrendRv();
        return view;
    }

    private void initTrendRv(){
        trendAdapter = new TrendAdapter(getContext());
        trendRv.setLayoutManager(new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false));
        trendRv.setHasFixedSize(true);
        trendRv.setAdapter(trendAdapter);
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
    public void displayNewTrends(List<NewTrend> newTrendList) {
        trendAdapter.setNewTrendList(newTrendList);

    }
}
