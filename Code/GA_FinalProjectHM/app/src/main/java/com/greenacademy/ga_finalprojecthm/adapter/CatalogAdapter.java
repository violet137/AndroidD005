package com.greenacademy.ga_finalprojecthm.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.model.Home.FashionCatalog;
import com.greenacademy.ga_finalprojecthm.util.OnCatalogSelected;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by thepa on 11/29/2017.
 */

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.ViewHolder> {

    private ArrayList<FashionCatalog> fashionCatalogs;
    private OnCatalogSelected onCatalogSelected;

    public CatalogAdapter(Context context, ArrayList<FashionCatalog> fashionCatalogs) {
        this.fashionCatalogs = fashionCatalogs;
        if (context instanceof OnCatalogSelected) {
            onCatalogSelected = (OnCatalogSelected) context;
        } else {
            throw new ClassCastException(context.toString() +
                    " must implement OnTapChiSelected.");
        }
        convert();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_catalog, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tvCatalog.setText(fashionCatalogs.get(position).getFashionCatalogName());
        Context context = holder.imgvCatalog.getContext();
        Picasso
                .with(context)
                .load(fashionCatalogs.get(position).getLinkImage())
                .error(R.drawable.not_found)
                .into(holder.imgvCatalog);

        holder.flCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCatalogSelected.onCatalogSelected(fashionCatalogs.get(holder.getAdapterPosition()).getFashionCatalog());
            }
        });
    }

    @Override
    public int getItemCount() {
        return fashionCatalogs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgvCatalog;
        TextView tvCatalog;
        FrameLayout flCatalog;

        ViewHolder(View itemView) {
            super(itemView);
            imgvCatalog = itemView.findViewById(R.id.imgvCatalog);
            tvCatalog = itemView.findViewById(R.id.tvCatalog);
            flCatalog = itemView.findViewById(R.id.flCatalog);
        }
    }

    //shit happen when server change but not in the link of images
    public String convertStr(String str) {
        return str.replaceFirst("tamod.vn:8050", "35.227.90.131:9001");
    }

    public void convert() {
        for (int i = 0; i < fashionCatalogs.size(); i++) {
            fashionCatalogs.get(i).setLinkImage(convertStr(fashionCatalogs.get(i).getLinkImage()));
        }
    }
}
