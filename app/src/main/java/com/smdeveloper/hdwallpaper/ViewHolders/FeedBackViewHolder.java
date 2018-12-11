package com.smdeveloper.hdwallpaper.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
//import android.widget.ImageView;
import android.widget.TextView;

import com.smdeveloper.hdwallpaper.Inteface.ItemClickListener;
import com.smdeveloper.hdwallpaper.R;

public class FeedBackViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtListFeed;
    private ItemClickListener itemClickListener;


    public FeedBackViewHolder(View itemView) {
        super(itemView);

        //txtListFeed = (TextView)itemView.findViewById(R.id.list_feed);
        itemView.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

}
