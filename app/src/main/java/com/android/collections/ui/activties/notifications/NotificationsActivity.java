package com.android.collections.ui.activties.notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.collections.R;
import com.android.collections.adapters.NotificationsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NotificationsActivity extends AppCompatActivity {

    private static final String TAG = "NotificationsActivity";
    private Unbinder unbinder;
    private NotificationsAdapter notificationsAdapter;

    @BindView(R.id.notifications_rv)
    RecyclerView notificationsRv;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        unbinder = ButterKnife.bind(this);

        initNotificationsRv();
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initNotificationsRv() {

        notificationsAdapter = new NotificationsAdapter();
        notificationsRv.setLayoutManager(new LinearLayoutManager(this));
        notificationsRv.setHasFixedSize(true);
        notificationsRv.setAdapter(notificationsAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
