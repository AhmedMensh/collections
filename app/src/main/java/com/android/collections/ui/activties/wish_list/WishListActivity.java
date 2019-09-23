package com.android.collections.ui.activties.wish_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.collections.R;
import com.android.collections.adapters.CollectionAdapter;
import com.android.collections.adapters.FavoriteAdapter;
import com.android.collections.helpers.PublicViewInf;
import com.android.collections.models.Favorite;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WishListActivity extends AppCompatActivity implements PublicViewInf ,WishListViewInf {


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
        favoriteAdapter = new FavoriteAdapter(this);
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
}
