package com.greenacademy.ga_finalprojecthm.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

import android.Manifest;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.greenacademy.ga_finalprojecthm.adapter.CustomInfoWindowAdapter;
import com.greenacademy.ga_finalprojecthm.util.IReceiverJSON;
import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.adapter.ShopAdapter;
import com.greenacademy.ga_finalprojecthm.asynctask.AsyncTaskShop;
import com.greenacademy.ga_finalprojecthm.model.FashionShop;

import com.greenacademy.ga_finalprojecthm.server.ParsingToModelFromJSON;
import com.greenacademy.ga_finalprojecthm.asynctask.FashionShopListASyncTask;
import com.greenacademy.ga_finalprojecthm.model.FashionShopList;

import java.io.IOException;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */

public class MapFragment extends Fragment implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, IReceiverJSON, GoogleMap.OnInfoWindowClickListener,
        LocationListener {

    private static final int PERMISSION_REQUEST_CODE_LOCATION = 1;
    int REQUEST_CHECK_SETTINGS  = 1000;
    private static final int MENU_STORE = Menu.FIRST;
    ShopAdapter shopAdapter;
    RecyclerView rcvList;
    ViewSwitcher vsMap;
    GoogleMap mGoogleMap;
//    MapView mMapview;
    View mView;
    ImageButton btnMyLocate;
    SearchView svMap;
    //
    Location lastLocation;
    LocationRequest locationRequest;
    Marker currentLocationmMarker;
    double latitude, longitude;
    LocationManager locationManager;
    LocationListener locationListener;
    FashionShop store;
    FashionShopList fashionShopList = new FashionShopList();
    FashionShopListASyncTask fashionShopListASyncTask;
    GoogleApiClient googleApiClient;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_map, container, false);
        rcvList = mView.findViewById(R.id.rcvList);
        vsMap = mView.findViewById(R.id.vsMap);
//        mMapview = mView.findViewById(R.id.mapView);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapShop);
//        mMapview.onCreate(savedInstanceState);
        mapFragment.getMapAsync(this);
        btnMyLocate = mView.findViewById(R.id.btnMyLocate);
        //
        requestPermission(PERMISSION_REQUEST_CODE_LOCATION,getApplicationContext(), this);
        btnMyLocate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Button Pressed.!", Toast.LENGTH_SHORT).show();

                try {

                    googleApiClient = new GoogleApiClient.Builder(getContext())
                            .addApi(LocationServices.API)
                            .addConnectionCallbacks(MapFragment.this)
                            .addOnConnectionFailedListener(MapFragment.this)
                            .build();

                    locationRequest = LocationRequest.create();
                    locationRequest.setInterval(1000);

                    locationRequest.setFastestInterval(1000);
                    locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

                    checkForLocationReuqestSetting(locationRequest);
                    googleApiClient.connect();

                } catch (SecurityException ex) {
                    ex.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //
        rcvList.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        //
        return mView;
    }

    private void requestPermission(int permissionRequestCodeLocation, Context applicationContext, MapFragment mapFragment) {
        String fineLocationPermissionString = Manifest.permission.ACCESS_FINE_LOCATION;
        String coarseLocationPermissionString = Manifest.permission.ACCESS_COARSE_LOCATION;

        if  (   ContextCompat.checkSelfPermission(getContext(), fineLocationPermissionString) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getContext(), coarseLocationPermissionString) != PackageManager.PERMISSION_GRANTED
                ) {

            //user has already cancelled the permission prompt. we need to advise him.
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),fineLocationPermissionString)){
                Toast.makeText(applicationContext,"We must need your permission in order to access your reporting location.",Toast.LENGTH_LONG).show();
            }

            ActivityCompat.requestPermissions(getActivity(),new String[]{fineLocationPermissionString, coarseLocationPermissionString},permissionRequestCodeLocation);

        }
    }

    private void checkForLocationReuqestSetting(LocationRequest locationRequest) {
        try {
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationRequest);

            PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());

            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
                    final Status status = locationSettingsResult.getStatus();
                    final LocationSettingsStates locationSettingsStates = locationSettingsResult.getLocationSettingsStates();

                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.SUCCESS:
                            // All location settings are satisfied. The client can
                            // initialize location requests here.
                            Log.d("MainActivity", "onResult: SUCCESS");
                            break;
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            Log.d("MainActivity", "onResult: RESOLUTION_REQUIRED");
                            // Location settings are not satisfied, but this can be fixed
                            // by showing the user a dialog.
                            try {
                                // Show the dialog by calling startResolutionForResult(),
                                // and check the result in onActivityResult().
                                status.startResolutionForResult(
                                        (Activity) getContext(),
                                        REQUEST_CHECK_SETTINGS);
                            } catch (IntentSender.SendIntentException e) {
                                // Ignore the error.
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            Log.d("MainActivity", "onResult: SETTINGS_CHANGE_UNAVAILABLE");
                            // Location settings are not satisfied. However, we have no way
                            // to fix the settings so we won't show the dialog.

                            break;
                    }
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        for (String per : permissions) {
            System.out.println("permissions are  " + per);
        }

        switch (requestCode) {

            case PERMISSION_REQUEST_CODE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(getContext(), "Permission loaded...", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(getApplicationContext(),"Permission Denied, You cannot access location data.",Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        fashionShopListASyncTask = new FashionShopListASyncTask();
        fashionShopListASyncTask.iCallBack(this);
        fashionShopListASyncTask.execute();
        mGoogleMap.setOnMarkerClickListener(this);
        mGoogleMap.setOnInfoWindowClickListener(this);
        //
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        int position = (Integer) marker.getTag();
        store = fashionShopList.getCuaHangTranfers().get(position);
        store.getName();
        store.getAddress();
        return false;
    }

    public void loadShop() {
        for (int i = 0; i < fashionShopList.getCuaHangTranfers().size(); i++) {
            FashionShop cuaHang = fashionShopList.getCuaHangTranfers().get(i);
            //set sự kiện lắng nghe, khi marker đc click
            Marker marker = mGoogleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(cuaHang.getLat(), cuaHang.getLng()))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker))
                    .snippet(cuaHang.getAddress())
                    .title(cuaHang.getName()));
            CameraPosition cameraPosition = CameraPosition.builder()
                    .target(new LatLng(10.7, 106.6)).zoom(10).bearing(0).tilt(45).build();
            // tạo infoWindow cho marker
            CustomInfoWindowAdapter infoWindowAdapter = new CustomInfoWindowAdapter(this,fashionShopList.getCuaHangTranfers());
            mGoogleMap.setInfoWindowAdapter(infoWindowAdapter);
            marker.showInfoWindow();
            mGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            marker.setTag(i);
        }
        //tạo viền cho Recycler View
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rcvList.getContext(),
                DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.rv_magazine_custom);
        dividerItemDecoration.setDrawable(drawable);
        rcvList.addItemDecoration(dividerItemDecoration);
        //
        shopAdapter = new ShopAdapter(fashionShopList.getCuaHangTranfers(), getContext(), R.layout.item_shop);
        rcvList.setAdapter(shopAdapter);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        try {
            lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, (LocationListener) this);
            if (lastLocation != null) {
                CameraPosition lastPosition = CameraPosition.builder()
                        .target(new LatLng(lastLocation.getLatitude(),lastLocation.getLongitude())).zoom(10).bearing(0).tilt(45).build();
                mGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(lastPosition));
            }
        } catch (SecurityException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        System.out.println("onConnectionSuspended called...");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        System.out.println("onConnectionFailed called.");
        fashionShopListASyncTask = new FashionShopListASyncTask();
        fashionShopListASyncTask.iCallBack(this);
        fashionShopListASyncTask.execute();
        mGoogleMap.setOnMarkerClickListener(this);
    }

    public void getStringJSON(String s) {
        this.fashionShopList = ParsingToModelFromJSON.parseToFashionShopList(s);
        //setdata
        loadShop();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //in order to create option menu on tool bar (action bar)
        setHasOptionsMenu(true);
    }

    //in order to create menu item on tool bar (action bar)
    @Override
    public void onCreateOptionsMenu(final Menu menu, MenuInflater inflater) {
        listMenu(menu);
    }

    public void listMenu(final Menu menu) {
        menu.clear();
//        inflater.inflate(R.menu.menu_toolbar, menu);
        menu.add(Menu.NONE, MENU_STORE, Menu.NONE, "")
                .setIcon(R.drawable.store_locator_menu_list_icon_normal)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        vsMap.showNext();
                        mapMenu(menu);
                        return true;
                    }
                })
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    public void mapMenu(final Menu menu) {
        menu.clear();
        menu.add(Menu.NONE, MENU_STORE, Menu.NONE, "")
                .setIcon(R.drawable.store_locator_menu_map_icon_normal)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        vsMap.showNext();
                        listMenu(menu);
                        return true;
                    }
                })
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        shopAdapter.storeDialog(store).show();
    }

    @Override
    public void onLocationChanged(Location location) {

    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (requestCode == 10) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
//            else {
//                // Permission was denied. Display an error message.
//            }
//        }
//    }

//    @Override
//    public boolean onMyLocationButtonClick() {
//        Toast.makeText(getContext(), "MyLocation button clicked", Toast.LENGTH_SHORT).show();
//        // Return false so that we don't consume the event and the default behavior still occurs
//        // (the camera animates to the user's current position).
//        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
//                (getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//        }
//        locationManager.requestLocationUpdates("gps", 5000, 0, (android.location.LocationListener) locationListener);
//        return false;
//    }

//    @Override
//    public void onMyLocationClick(@NonNull Location location) {
//        Toast.makeText(getContext(), "Current location:\n" + location, Toast.LENGTH_LONG).show();
//    }
}
