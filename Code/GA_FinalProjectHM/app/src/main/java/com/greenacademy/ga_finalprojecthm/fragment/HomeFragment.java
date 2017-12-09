package com.greenacademy.ga_finalprojecthm.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.adapter.CatalogAdapter;
import com.greenacademy.ga_finalprojecthm.adapter.SalesSliderAdapter;
import com.greenacademy.ga_finalprojecthm.asynctask.HomeASyncTask;
import com.greenacademy.ga_finalprojecthm.asynctask.KhuyenMaiAsyncTask;
import com.greenacademy.ga_finalprojecthm.model.Home.FashionCatalog;
import com.greenacademy.ga_finalprojecthm.model.Home.FashionCatalogResponse;
import com.greenacademy.ga_finalprojecthm.model.RootKhuyenMai;
import com.greenacademy.ga_finalprojecthm.server.ParsingToModelFromJSON;
import com.greenacademy.ga_finalprojecthm.util.IReceiverJSON;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements IReceiverJSON {

    RecyclerView rclvCatalog;
    ArrayList<FashionCatalog> fashionCatalogs;
    ViewPager vpSales;
    CircleIndicator indicator;
    private static int currentPage = 0;
    KhuyenMaiAsyncTask khuyenMaiAsyncTask;
    private RootKhuyenMai rootKhuyenMai;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rclvCatalog = view.findViewById(R.id.rclvCatalog);
        vpSales = view.findViewById(R.id.vpSales);
        indicator = view.findViewById(R.id.indicatorSales);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//        rclvCatalog.setHasFixedSize(true);
        //disable scroll inside recycler view
        rclvCatalog.setNestedScrollingEnabled(false);
        rclvCatalog.setLayoutManager(linearLayoutManager);

        CatalogAdapter catalogAdapter = new CatalogAdapter(getActivity(), fashionCatalogs);
        rclvCatalog.setAdapter(catalogAdapter);

        khuyenMaiAsyncTask = new KhuyenMaiAsyncTask();
        khuyenMaiAsyncTask.setiReceiverJSON(this);
        khuyenMaiAsyncTask.execute();
        return view;
    }

    public void setData(ArrayList<FashionCatalog> fashionCatalogs){
        this.fashionCatalogs = fashionCatalogs;
    }

    public void autoStartViewPager(){
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == rootKhuyenMai.getKhuyenMaiTranfers().size()) {
                    currentPage = 0;
                }
                vpSales.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 5000, 2500);
    }

    @Override
    public void getStringJSON(String strJSON) {
        //Lưu biến toàn cục
        rootKhuyenMai = ParsingToModelFromJSON.parseToKhuyenMai(strJSON);
        //set data
        SalesSliderAdapter salesSliderAdapter = new SalesSliderAdapter(getActivity(), rootKhuyenMai);
        vpSales.setAdapter(salesSliderAdapter);

        indicator.setViewPager(vpSales);
        autoStartViewPager();
    }
}
