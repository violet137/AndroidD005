package com.greenacademy.ga_finalprojecthm.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.asynctask.AsynTaskChiTietTapChi;
import com.greenacademy.ga_finalprojecthm.model.TapChiJson;
import com.greenacademy.ga_finalprojecthm.server.ParsingToModelFromJSON;
import com.greenacademy.ga_finalprojecthm.util.IReceiverJSON;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChiTietTapChiFragment extends Fragment implements IReceiverJSON {

    TextView tvTenTC;
    WebView wvNoiDungTapChi;
    AsynTaskChiTietTapChi asynTaskChiTietTapChi;
    private int idTapChi;
    TapChiJson tapChiJson = new TapChiJson();

    public ChiTietTapChiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chi_tiet_tap_chi, container, false);
        tvTenTC = view.findViewById(R.id.tvTenTC);
        wvNoiDungTapChi = view.findViewById(R.id.wvNoiDungTapChi);

        asynTaskChiTietTapChi = new AsynTaskChiTietTapChi();
        asynTaskChiTietTapChi.execute(idTapChi);
        asynTaskChiTietTapChi.setiReceiverJSON(this);
        return view;
    }

    public void setIdTapChi(int idTapChi){
        this.idTapChi = idTapChi;
    }

    @Override
    public void getStringJSON(String strJSON) {
        tapChiJson = ParsingToModelFromJSON.parseToTapChi(strJSON);
        tvTenTC.setText(tapChiJson.getTen());
    }
}
