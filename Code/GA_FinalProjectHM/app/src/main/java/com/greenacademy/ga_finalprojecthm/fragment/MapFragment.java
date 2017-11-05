package com.greenacademy.ga_finalprojecthm.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ViewSwitcher;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.greenacademy.ga_finalprojecthm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback{
    ImageButton btnMap;
    RecyclerView rcvList;
    ViewSwitcher vsMap;
    GoogleMap mGoogleMap;
    MapView mMapview;
    View mView;

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
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vsMap.showNext();
            }
        });
        mMapview = mView.findViewById(R.id.mapView);
//        mMapview.onCreate(savedInstanceState);
        return mView;
    }

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        if (mMapview !=null){
//            mMapview.onCreate(savedInstanceState);
//            mMapview.onResume();
//            mMapview.getMapAsync(this);
//        }
//
//    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(10.7,106.6))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker)));
        CameraPosition cameraPosition = CameraPosition.builder().target(new LatLng(10.7,106.6)).zoom(10).bearing(0).tilt(45).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
