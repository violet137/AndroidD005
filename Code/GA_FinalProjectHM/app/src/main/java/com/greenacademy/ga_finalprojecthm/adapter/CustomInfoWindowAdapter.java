package com.greenacademy.ga_finalprojecthm.adapter;

import android.app.Fragment;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.greenacademy.ga_finalprojecthm.fragment.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.model.FashionShop;
import com.greenacademy.ga_finalprojecthm.model.FashionShopList;

import java.util.ArrayList;

/**
 * Created by xuanson on 11/20/2017.
 */

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    MapFragment context;
    ArrayList<FashionShop> fashionShopList;

    public CustomInfoWindowAdapter(MapFragment context,ArrayList<FashionShop> fashionShopList){
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
        View v = context.getLayoutInflater().inflate(R.layout.item_map_info_window,null);
        // Getting the position from the marker

        ivApp = v.findViewById(R.id.ivApp);
        tviName = v.findViewById(R.id.tviName);
        tviAddress = v.findViewById(R.id.tviAddress);
        tviStyle = v.findViewById(R.id.tviStyle);
        tviDistance = v.findViewById(R.id.tviDistance);
        //
//          FashionShop store = marker.getPosition();
          ivApp.setImageResource(R.drawable.app_logo);
          tviName.setText(marker.getTitle());
          tviAddress.setText(marker.getSnippet());
//          tviStyle.setText(store.getStyle());
//          tviDistance.setText(store.getDistance());
    return v;
    }
}
