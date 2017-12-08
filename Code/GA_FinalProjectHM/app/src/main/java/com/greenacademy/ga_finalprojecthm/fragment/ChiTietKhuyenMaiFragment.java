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
import com.greenacademy.ga_finalprojecthm.adapter.ChiTietKhuyenMaiAdapter;
import com.greenacademy.ga_finalprojecthm.asynctask.ChiTietKhuyenMaiAsynsTask;
import com.greenacademy.ga_finalprojecthm.model.RootChiTietKhuyenMai;
import com.greenacademy.ga_finalprojecthm.server.ParsingToModelFromJSON;
import com.greenacademy.ga_finalprojecthm.util.IReceiverJSON;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChiTietKhuyenMaiFragment extends Fragment implements IReceiverJSON {
    RecyclerView rvCTKM;
    RootChiTietKhuyenMai rootChiTietKhuyenMai = new RootChiTietKhuyenMai();
    ChiTietKhuyenMaiAdapter chiTietKhuyenMaiAdapter;
//    ChiTietKhuyenMaiAsynsTask chiTietKhuyenMaiAsynsTask;

    private int idKhuyenMai;
    //ArrayList<RootChiTietKhuyenMai> arrayListCTKM = new ArrayList<>() ;

    public ChiTietKhuyenMaiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chi_tiet_khuyen_mai, container, false);
        rvCTKM = view.findViewById(R.id.rvCTKM);
        loadCTKM();

        ChiTietKhuyenMaiAsynsTask chiTietKhuyenMaiAsynsTask = new ChiTietKhuyenMaiAsynsTask();
        chiTietKhuyenMaiAsynsTask.setiReceiverJSON(this);
        //kiểu dữ liệu idKhuyenMai theo kiểu dữ liệu đầu tiên bên Asynctasks
        chiTietKhuyenMaiAsynsTask.execute(idKhuyenMai);

        return view;
    }

    public void loadCTKM(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvCTKM.setHasFixedSize(true);
        rvCTKM.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvCTKM.getContext(), DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.rv_magazine_custom);
        dividerItemDecoration.setDrawable(drawable);
        rvCTKM.addItemDecoration(dividerItemDecoration);
    }

//    public void loadData(int idKhuyenMai){
//        ChiTietKhuyenMaiAsynsTask chiTietKhuyenMaiAsynsTask = new ChiTietKhuyenMaiAsynsTask();
//        chiTietKhuyenMaiAsynsTask.setiReceiverJSON(this);
//        //kiểu dữ liệu idKhuyenMai theo kiểu dữ liệu đầu tiên bên Asynctasks
//        chiTietKhuyenMaiAsynsTask.execute(idKhuyenMai);
//    }

    public void setIdKhuyenMai(int idKhuyenMai){
        this.idKhuyenMai = idKhuyenMai;
    }

    @Override
    public void getStringJSON(String strJSON) {
        this.rootChiTietKhuyenMai = ParsingToModelFromJSON.parseToChiTietKhuyenMai(strJSON);

        chiTietKhuyenMaiAdapter = new ChiTietKhuyenMaiAdapter(rootChiTietKhuyenMai, getActivity() , R.layout.item_chitietkhuyenmai);

        rvCTKM.setAdapter(chiTietKhuyenMaiAdapter);


    }


}

