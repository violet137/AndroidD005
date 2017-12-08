package com.greenacademy.ga_finalprojecthm.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.adapter.LoaiTapChiAdapter;
import com.greenacademy.ga_finalprojecthm.asynctask.AsynTaskLoaiTapChi;
import com.greenacademy.ga_finalprojecthm.model.RootLoaiTapChi;
import com.greenacademy.ga_finalprojecthm.server.ParsingToModelFromJSON;
import com.greenacademy.ga_finalprojecthm.util.IReceiverJSON;
import com.greenacademy.ga_finalprojecthm.util.IReceiverLoaiTapChi;
import com.greenacademy.ga_finalprojecthm.util.OnTapChiSelected;

/**
 * A simple {@link Fragment} subclass.
 */
public class MagazineFragment extends Fragment implements IReceiverJSON, IReceiverLoaiTapChi, OnTapChiSelected {
    //Loại Tạp Chí
    RecyclerView rvLoaiTapChi;
    AsynTaskLoaiTapChi asynTaskLoaiTapChi;
    RootLoaiTapChi rootLoaiTapChi = new RootLoaiTapChi();
    LoaiTapChiAdapter adapter;
    TapChiFragment tapChiFragment = new TapChiFragment();

    public MagazineFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        tapChiFragment.loadData("ThoiTrang");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_magazine, container, false);
        //Loại Tạp Chí
        rvLoaiTapChi = view.findViewById(R.id.rvLoaiTapChi);
        loadLoaiTapChi();

        asynTaskLoaiTapChi = new AsynTaskLoaiTapChi();
        asynTaskLoaiTapChi.iCallBack(this);
        asynTaskLoaiTapChi.execute();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.child_fragment_container, tapChiFragment).commit();

        return view;
    }

    public void loadLoaiTapChi() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvLoaiTapChi.setHasFixedSize(true);
        rvLoaiTapChi.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvLoaiTapChi.getContext(), DividerItemDecoration.HORIZONTAL);
        Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.rv_magazine_custom);
        dividerItemDecoration.setDrawable(drawable);
        rvLoaiTapChi.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void getStringJSON(String strJSON) {
        //Lưu biến toàn cục
        this.rootLoaiTapChi = ParsingToModelFromJSON.parseToLoaiTapChiList(strJSON);
        //set data
        adapter = new LoaiTapChiAdapter(rootLoaiTapChi, getContext(), R.layout.item_loaitapchi);
        //lay loai tap chi khi nhan vao loai tap chi tren recycler view
        adapter.setLoaiTapChi(this);
        rvLoaiTapChi.setAdapter(adapter);
    }

    //Nhảy qua fragment Tạp Chí, được bắn từ Loại Tạp chí Adapter
    @Override
    public void getLoaiTapChi(String loaiTapChi) {
        tapChiFragment.loadData(loaiTapChi);
    }

    @Override
    public void onTapChiSelected(int idTapChi) {
        ChiTietTapChiFragment chiTietTapChiFragment = new ChiTietTapChiFragment();
        chiTietTapChiFragment.setIdTapChi(idTapChi);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, chiTietTapChiFragment).addToBackStack("")
                .commit();
    }

}
