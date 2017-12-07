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
import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.adapter.KhuyenMaiAdapter;
import com.greenacademy.ga_finalprojecthm.asynctask.KhuyenMaiAsyncTask;
import com.greenacademy.ga_finalprojecthm.model.RootChiTietKhuyenMai;
import com.greenacademy.ga_finalprojecthm.model.RootKhuyenMai;
import com.greenacademy.ga_finalprojecthm.server.ParsingToModelFromJSON;
import com.greenacademy.ga_finalprojecthm.util.IReceiverChiTietKhuyenMai;
import com.greenacademy.ga_finalprojecthm.util.IReceiverJSON;
import com.greenacademy.ga_finalprojecthm.util.IReceiverKhuyenMai;


/**
 * A simple {@link Fragment} subclass.
 */
public class KhuyenMaiFragment extends Fragment implements IReceiverJSON, IReceiverKhuyenMai {
    RecyclerView rvKhuyenMai;
    KhuyenMaiFragment khuyenMaiFragment;
    RootKhuyenMai rootKhuyenMai = new RootKhuyenMai();
    KhuyenMaiAdapter khuyenMaiAdapter;
    KhuyenMaiAsyncTask khuyenMaiAsyncTask;

    public KhuyenMaiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_khuyen_mai, container, false);
        rvKhuyenMai = view.findViewById(R.id.rvKhuyenMai);
        loadKhuyenMai();

        khuyenMaiAsyncTask = new KhuyenMaiAsyncTask();
        khuyenMaiAsyncTask.setiReceiverJSON(this);
        khuyenMaiAsyncTask.execute();


        return view;
    }


    public void loadKhuyenMai(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvKhuyenMai.setHasFixedSize(true);
        rvKhuyenMai.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvKhuyenMai.getContext(), DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.rv_magazine_custom);
        dividerItemDecoration.setDrawable(drawable);
        rvKhuyenMai.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void getStringJSON(String strJSON) {
        //Lưu biến toàn cục
        this.rootKhuyenMai = ParsingToModelFromJSON.parseToKhuyenMai(strJSON);
        //set data
        khuyenMaiAdapter = new KhuyenMaiAdapter(rootKhuyenMai, getContext(), R.layout.item_khuyenmai);

        //lay loai tap chi khi nhan vao loai tap chi tren recycler view
        khuyenMaiAdapter.setKhuyenMai(this);
        rvKhuyenMai.setAdapter(khuyenMaiAdapter);

    }



    @Override
    public void getKhuyenMai(int idKhuyenMai) {

        ChiTietKhuyenMaiFragment chiTietKhuyenMaiFragment = new ChiTietKhuyenMaiFragment();
        chiTietKhuyenMaiFragment.setIdKhuyenMai(idKhuyenMai);
        //chiTietKhuyenMaiFragment.loadData(idKhuyenMai);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, chiTietKhuyenMaiFragment).addToBackStack("")
                .commit();
    }

}


//Cần lấy được data Chi Tiết Khuyến Mãi