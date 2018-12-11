package com.smdeveloper.hdwallpaper;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CollapsingToolbarLayout;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import android.widget.RelativeLayout;

import com.smdeveloper.hdwallpaper.Common.Common;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;

public class ViewWallpaperActivity extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton fabWallPaper;
    ImageView imageViewWallpaper;
    RelativeLayout rootLayout;

    String backgroundsId="";

    //private Target(Picasso) target = new Target()

    private Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

            WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());

            try {
                wallpaperManager.setBitmap(bitmap);
                Snackbar.make(rootLayout,"Wallpaper Was Set",Snackbar.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_wallpaper);

        rootLayout =(RelativeLayout) findViewById(R.id.root_layout);
        //collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        //collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsebar);
        //collapsingToolbarLayout.setExpandedTitleTextAppearance(R.id.);
        //collapsingToolbarLayout.setTitle(Common.CATEGORY_SELECTED);
        imageViewWallpaper = (ImageView)findViewById(R.id.image_thumb);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //if (getIntent() != null)
  //          backgroundsId = getIntent().getStringExtra("BackgroundsId");
    //    if (!backgroundsId.isEmpty())
      //  {
        //}
        //
        //Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
       // if (getSupportActionBar() != null)
        //{
          //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //}
        //*/

        Picasso.with(this)
                .load(Common.Selected_background.getImagelink())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(imageViewWallpaper);

        fabWallPaper = (FloatingActionButton)findViewById(R.id.fab);
        fabWallPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.with(getBaseContext())
                        .load(Common.Selected_background.getImagelink())
                        .into(target);
            }
        });
    }
}
