package com.android.collections.ui.activties.collection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.android.collections.R;
import com.android.collections.adapters.CollectionAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CollectionActivity extends AppCompatActivity {

    private static final String TAG = "CollectionActivity";
    private Unbinder unbinder;
    private CollectionAdapter collectionAdapter;

    @BindView(R.id.collection_rv)
    RecyclerView collectionRv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);


        unbinder = ButterKnife.bind(this);
        initCollectionRv();
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initCollectionRv(){
        collectionRv.setHasFixedSize(true);
        collectionRv.setLayoutManager(new GridLayoutManager(this,2, RecyclerView.VERTICAL,false));
        collectionAdapter = new CollectionAdapter();
        collectionRv.setAdapter(collectionAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_bar_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
