package com.android.collections.ui.activties.wish_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.collections.R;
import com.android.collections.adapters.CollectionAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WishListActivity extends AppCompatActivity {


    private static final String TAG = "WishListActivity";
    private Unbinder unbinder;
    private CollectionAdapter wishListAdapter;

    @BindView(R.id.wish_list_rv)
    RecyclerView wishListRv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);
        unbinder = ButterKnife.bind(this);
        initCollectionRv();
    }

    private void initCollectionRv(){
        wishListRv.setHasFixedSize(true);
        wishListRv.setLayoutManager(new GridLayoutManager(this,2, RecyclerView.VERTICAL,false));
        wishListAdapter = new CollectionAdapter();
        wishListRv.setAdapter(wishListAdapter);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
