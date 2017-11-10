package com.greenacademy.ga_finalprojecthm.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.model.ShopHolder;
import com.greenacademy.ga_finalprojecthm.model.FashionShop;

import java.util.ArrayList;

/**
 * Created by xuanson on 11/7/2017.
 */

public class ShopAdapter extends RecyclerView.Adapter<ShopHolder> {
    ArrayList<FashionShop> fashionShopList = new ArrayList<>();

    public ShopAdapter(ArrayList<FashionShop> fashionShopList) {
        this.fashionShopList = fashionShopList;
    }

    @Override
    public ShopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop,parent,false);
        return new ShopHolder(view);
    }

    @Override
    public void onBindViewHolder(ShopHolder holder, int position) {
        FashionShop fashionShop = fashionShopList.get(position);
        holder.name.setText(fashionShop.getName());
        holder.address.setText(fashionShop.getAddress());
        holder.style.setText(fashionShop.getStyle());
        holder.evaluate.setText(fashionShop.getStyle());
    }

    @Override
    public int getItemCount() {
        return fashionShopList.size();
    }
}
