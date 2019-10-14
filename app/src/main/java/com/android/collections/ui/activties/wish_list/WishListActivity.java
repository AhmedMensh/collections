package com.android.collections.ui.activties.wish_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.collections.R;
import com.android.collections.adapters.CollectionAdapter;
import com.android.collections.adapters.FavoriteAdapter;
import com.android.collections.helpers.PublicViewInf;
import com.android.collections.models.Favorite;
import com.android.collections.ui.activties.product_details.ProductDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WishListActivity extends AppCompatActivity implements PublicViewInf ,WishListViewInf,FavoriteAdapter.ItemClickListener {


    private static final String TAG = "WishListActivity";
    private Unbinder unbinder;
    private FavoriteAdapter favoriteAdapter;
    private WishListPresenter presenter;

    @BindView(R.id.wish_list_rv)
    RecyclerView wishListRv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);
        unbinder = ButterKnife.bind(this);
        initCollectionRv();

        presenter = new WishListPresenter(this ,this);
        presenter.getWishList();
    }

    private void initCollectionRv(){
        wishListRv.setHasFixedSize(true);
        wishListRv.setLayoutManager(new GridLayoutManager(this,2, RecyclerView.VERTICAL,false));
        favoriteAdapter = new FavoriteAdapter(this,this);
        wishListRv.setAdapter(favoriteAdapter);
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
    public void displayWishList(List<Favorite> wishList) {

        favoriteAdapter.setFavoriteList(wishList);
    }

    @Override
    public void onItemClickListener(int id) {

        startActivity(new Intent(this , ProductDetailsActivity.class));
    }

    @Override
    public void onLikeIconClickListener(int id) {
        presenter.removeFromFavorite(id);
        Log.e(TAG, "onLikeIconClickListener: "+id);
    }
}
