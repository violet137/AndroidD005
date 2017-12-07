package com.greenacademy.ga_finalprojecthm.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenacademy.ga_finalprojecthm.ICallBack;
import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.adapter.TapChiAdapter;
import com.greenacademy.ga_finalprojecthm.asynctask.AsynTaskTapChi;
import com.greenacademy.ga_finalprojecthm.model.RootLoaiTapChi;
import com.greenacademy.ga_finalprojecthm.model.RootTapChi;
import com.greenacademy.ga_finalprojecthm.model.TapChiJson;

/**
 * A simple {@link Fragment} subclass.
 */
public class TapChiFragment extends Fragment implements ICallBack {
    //Tạp Chí
    RecyclerView rvTapChi;
    RootTapChi rootTapChi;
    TapChiAdapter adapter;

    public TapChiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tap_chi, container, false);
        rvTapChi = view.findViewById(R.id.rvTapChi);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvTapChi.setHasFixedSize(true);
        rvTapChi.setLayoutManager(linearLayoutManager);

        //Set các đường ngăn cách giữa các Item của RecyclerView
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvTapChi.getContext(), DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.rv_magazine_custom);
        dividerItemDecoration.setDrawable(drawable);
        rvTapChi.addItemDecoration(dividerItemDecoration);

        rootTapChi = new RootTapChi();

        return view;
    }

    public void loadData(String idLoaiTapChi) {
        //Phải khai báo cục bộ vì không thể execute 2 lần, mỗi lần phải new lại
        AsynTaskTapChi asynTaskTapChi = new AsynTaskTapChi();
        asynTaskTapChi.ICallBack(this);
        asynTaskTapChi.execute(idLoaiTapChi);
    }

    @Override
    public void GetLoaiThoiTrang(RootLoaiTapChi rootLoaiTapChi) {

    }

    @Override
    public void GetTapChi(RootTapChi rootTapChi) {
        //lưu biến toàn cục
        this.rootTapChi = rootTapChi;
        //set data

        adapter = new TapChiAdapter(rootTapChi, getParentFragment(), R.layout.item_tapchi);
        rvTapChi.setAdapter(adapter);
    }

    @Override
    public void GetChiTietTapChi(TapChiJson tapChiJson) {

    }
}
