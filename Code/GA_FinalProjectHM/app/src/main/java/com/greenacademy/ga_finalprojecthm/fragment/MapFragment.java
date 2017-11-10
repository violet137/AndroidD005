package com.greenacademy.ga_finalprojecthm.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ViewSwitcher;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.greenacademy.ga_finalprojecthm.server.ParsingToModelFromJSON;
import com.greenacademy.ga_finalprojecthm.util.IReceiverJSON;
import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.adapter.ShopAdapter;
import com.greenacademy.ga_finalprojecthm.asynctask.FashionShopListASyncTask;
import com.greenacademy.ga_finalprojecthm.model.FashionShopList;
import com.greenacademy.ga_finalprojecthm.model.FashionShop;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback,IReceiverJSON, GoogleMap.OnMarkerClickListener {
    ShopAdapter shopAdapter;
    ImageButton btnMap;
    RecyclerView rcvList;
    ViewSwitcher vsMap;
    GoogleMap mGoogleMap;
    MapView mMapview;
    View mView;
    FashionShopList fashionShopList;
    FashionShopListASyncTask fashionShopListASyncTask;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_map,container,false);
        btnMap = mView.findViewById(R.id.btnMap);
        rcvList = mView.findViewById(R.id.rcvList);
        vsMap = mView.findViewById(R.id.vsMap);
        mMapview = mView.findViewById(R.id.mapView);
        mMapview.onCreate(savedInstanceState);
        mMapview.getMapAsync(this);

        rcvList.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        //
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vsMap.showNext();
            }
        });
        return mView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        fashionShopListASyncTask = new FashionShopListASyncTask();
        fashionShopListASyncTask.iCallBack(this);
        fashionShopListASyncTask.execute();
        googleMap.setOnMarkerClickListener(this);
    }

    @Override
    public void getStringJSON(String s) {
        this.fashionShopList = ParsingToModelFromJSON.parseToFashionShopList(s);
        //setdata
        for (int i = 0; i< fashionShopList.getCuaHangTranfers().size(); i++){
            FashionShop cuaHang= fashionShopList.getCuaHangTranfers().get(i);
            //set sự kiện lắng nghe, khi marker đc click
            Marker marker = mGoogleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(cuaHang.getLat(), cuaHang.getLng()))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker))
                    .title(cuaHang.getName()));CameraPosition cameraPosition = CameraPosition.builder().target(new LatLng(10.7,106.6)).zoom(10).bearing(0).tilt(45).build();
            mGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            marker.setTag(i);
        }
        //
        shopAdapter = new ShopAdapter(fashionShopList.getCuaHangTranfers());
        rcvList.setAdapter(shopAdapter);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}
