package com.greenacademy.ga_finalprojecthm.fragment.ChiTietSanPham;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.adapter.ChiTietSanPhamAdapter;
import com.greenacademy.ga_finalprojecthm.asynctask.ChiTietSanPhamAsynctask;
import com.greenacademy.ga_finalprojecthm.model.ChiTietSanPham.ChiTietSanPham;
import com.greenacademy.ga_finalprojecthm.model.ChiTietSanPham.SanPhamTranfers;
import com.greenacademy.ga_finalprojecthm.server.ParsingToModelFromJSON;
import com.greenacademy.ga_finalprojecthm.util.IReceiverJSON;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChiTietSanPhamFragment extends Fragment implements IReceiverJSON {

    RecyclerView rvRecyclerView;
    String Click;
    ChiTietSanPham chiTietSanPham=new ChiTietSanPham();
    ChiTietSanPhamAdapter chiTietSanPhamAdapter;
    ArrayList<SanPhamTranfers> sanPhamTranfers=new ArrayList<>();
    public void getClick(String click) {
        Click = click;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_chi_tiet_san_pham, container, false);

        rvRecyclerView=(RecyclerView)view.findViewById(R.id.rvChiTietSanPham);
        rvRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        ChiTietSanPhamAsynctask chiTietSanPhamAsynctask=new ChiTietSanPhamAsynctask();
        chiTietSanPhamAsynctask.iCallBack(this);
        chiTietSanPhamAsynctask.execute(Click);
        return view;
    }

    @Override
    public void getStringJSON(String strJSON) {
        chiTietSanPham= ParsingToModelFromJSON.parseToChiTietSanPham(strJSON);
        sanPhamTranfers=chiTietSanPham.getSanPhamTranfers();
        chiTietSanPhamAdapter=new ChiTietSanPhamAdapter(getActivity(),sanPhamTranfers);
        rvRecyclerView.setAdapter(chiTietSanPhamAdapter);


    }
}
