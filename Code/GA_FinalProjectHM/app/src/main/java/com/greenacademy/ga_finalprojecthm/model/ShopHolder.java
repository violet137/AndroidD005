package com.greenacademy.ga_finalprojecthm.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.greenacademy.ga_finalprojecthm.R;

/**
 * Created by xuanson on 11/7/2017.
 */

public class ShopHolder extends RecyclerView.ViewHolder {
    public TextView name,distance,address,style,evaluate;
    public ShopHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tvName);
        distance = itemView.findViewById(R.id.tvDistance);
        address = itemView.findViewById(R.id.tvAddress);
        style = itemView.findViewById(R.id.tvStyle);
        evaluate = itemView.findViewById(R.id.tvEvaluate);
    }
}
