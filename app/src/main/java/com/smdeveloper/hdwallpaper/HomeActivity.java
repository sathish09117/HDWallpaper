package com.smdeveloper.hdwallpaper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smdeveloper.hdwallpaper.Inteface.ItemClickListener;
import com.smdeveloper.hdwallpaper.Model.Category;
import com.smdeveloper.hdwallpaper.ViewHolders.MenuViewHolder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class HomeActivity extends AppCompatActivity {

    private Toolbar mToolbar1;
    FirebaseDatabase database;
    DatabaseReference category;

    Button btnfeed;
    FirebaseRecyclerAdapter<Category,MenuViewHolder> adapter;

    RecyclerView recycler_menu;
    private AdView mAdView;

    LinearLayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        getSupportActionBar().setTitle(R.string.wall_category);


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Init Firebase
        database = FirebaseDatabase.getInstance();
        category = database.getReference("Category");

        //mStatusDatabase = FirebaseDatabase.getInstance().getReference().child("FeedBackModel");

        btnfeed = (Button)findViewById(R.id.btnfeed);

        //Load for Menu
        recycler_menu = (RecyclerView)findViewById(R.id.recycler_menu);
        recycler_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplication(),2);
        //recycler_menu.setLayoutManager(gridLayoutManager);
        loadMenu();



        btnfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent feedback = new Intent(HomeActivity.this,FeedBackActivity.class);
                startActivity(feedback);
            }
        });

    }

    private void loadMenu() {
        adapter = new FirebaseRecyclerAdapter<Category, MenuViewHolder>(
                Category.class,R.layout.menu_item,MenuViewHolder.class,category) {
            @Override
            protected void populateViewHolder(final MenuViewHolder viewHolder, final Category model, int position) {
                viewHolder.txtMenuName.setText(model.getName());
                Picasso.with(getBaseContext())
                        .load(model.getImage())
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .into(viewHolder.imageView, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {
                                Picasso.with(getBaseContext())
                                        .load(model.getImage())
                                        .error(R.drawable.ic_terrain_black_24dp)
                                        .into(viewHolder.imageView, new Callback() {
                                            @Override
                                            public void onSuccess() {

                                            }

                                            @Override
                                            public void onError() {
                                                Log.e("ERROR_SMTT","Couldn't fetch image");
                                            }
                                        });
                            }
                        });
                final Category clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongVlick) {

                        Intent BackgroundAc = new Intent(HomeActivity.this,ListWallPaperActivity.class);
                        BackgroundAc.putExtra("CategoryId",adapter.getRef(position).getKey());
                        startActivity(BackgroundAc);
                    }
                });
            }
        };
        recycler_menu.setAdapter(adapter);
    }

}
