package com.greenacademy.ga_finalprojecthm.adapter;


import android.app.AlertDialog;
import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.model.fashionshop.FashionShop;

import java.util.ArrayList;

/**
 * Created by xuanson on 11/7/2017.
 */

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopHolder> {
    ArrayList<FashionShop> fashionShopList = new ArrayList<>();
    Context context;
    int layout;
    FashionShop fashionShop;


    public ShopAdapter(ArrayList<FashionShop> fashionShopList, Context context, int resource) {
        this.context = context;
        this.layout = resource;
        this.fashionShopList = fashionShopList;
    }

    @Override
    public ShopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop,parent,false);
        return new ShopHolder(view);
    }

    @Override
    public void onBindViewHolder(ShopHolder holder, final int position) {
        fashionShop = fashionShopList.get(position);
        holder.name.setText(fashionShop.getName());
        holder.distance.setText(fashionShop.getDistance(context,fashionShop.getLat(),fashionShop.getLng()).toString().substring(0,4)+" km");
        holder.address.setText(fashionShop.getAddress());
        holder.style.setText(fashionShop.getStyle());
        holder.evaluate.setText(fashionShop.getEvaluate().toString());

        holder.lShop.setTag(fashionShop);
        //position k đổi vì thế phải tạo tag đưa cả item vào
        holder.lShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FashionShop fa = (FashionShop) v.getTag();
                storeDialog(fa).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return fashionShopList.size();
    }

    public class ShopHolder extends RecyclerView.ViewHolder {
        public TextView name,distance,address,style,evaluate;
        LinearLayout lShop;

        public ShopHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            distance = itemView.findViewById(R.id.tvDistance);
            address = itemView.findViewById(R.id.tvAddress);
            style = itemView.findViewById(R.id.tvStyle);
            evaluate = itemView.findViewById(R.id.tvEvaluate);
            lShop = itemView.findViewById(R.id.lShop);
        }
    }

    public AlertDialog storeDialog(final FashionShop shop) {
        TextView name,address,hours,style;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        LayoutInflater inflater = mapFragment.getActivity().getLayoutInflater();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_store_info,null);
        name = v.findViewById(R.id.tviName);
        address = v.findViewById(R.id.tviAddress);
        hours = v.findViewById(R.id.tviHours);
        style = v.findViewById(R.id.tviStyle);
        //
        name.setText(shop.getName());
        address.setText(shop.getAddress());
        hours.setText(shop.getHours());
        style.setText(shop.getStyle());
        return builder.setTitle("")
                .setView(v)
                .setPositiveButton("+84-2862858040", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel: "+"02862858040"));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                })
                .setNeutralButton("Directions", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).create();
    }

}
