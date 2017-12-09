package com.greenacademy.ga_finalprojecthm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.greenacademy.ga_finalprojecthm.fragment.MapFragment;
import com.google.android.gms.maps.model.Marker;
import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.model.fashionshop.FashionShop;

import java.util.ArrayList;

/**
 * Created by xuanson on 11/20/2017.
 */

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    Context context;
    ArrayList<FashionShop> fashionShopList;
//    FashionShopList fashionShopList;
    public CustomInfoWindowAdapter(Context context,ArrayList<FashionShop> fashionShopList){
        this.context = context;
        this.fashionShopList = fashionShopList;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        ImageView ivApp;
        TextView tviName,tviAddress,tviStyle,tviDistance;
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_map_info_window,null);;
        // Getting the position from the marker

        ivApp = v.findViewById(R.id.ivApp);
        tviName = v.findViewById(R.id.tviName);
        tviAddress = v.findViewById(R.id.tviAddress);
        tviStyle = v.findViewById(R.id.tviStyle);
        tviDistance = v.findViewById(R.id.tviDistance);
        //
        FashionShop store = (FashionShop) marker.getTag();
        ivApp.setImageResource(R.drawable.app_logo);
        tviName.setText(store.getName());
        tviAddress.setText(store.getAddress());
        tviStyle.setText(store.getStyle());
        tviDistance.setText((store.getDistance(context,store.getLat(),store.getLng()).toString().substring(0,4)+" km"));
    return v;
    }
}
