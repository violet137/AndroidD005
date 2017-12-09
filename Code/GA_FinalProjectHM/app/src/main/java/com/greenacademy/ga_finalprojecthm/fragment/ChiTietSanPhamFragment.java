package com.greenacademy.ga_finalprojecthm.fragment;


import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.greenacademy.ga_finalprojecthm.R;

import java.sql.BatchUpdateException;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChiTietSanPhamFragment extends Fragment{
    TextView tvSanPhamAndMau, tvPrice, tvPosition;
    ImageView imgShare,imgChiTiet;
    Button btnSanPhamPhuHop,btnColor,btnSize,btnChoSanPhamVaoGioHang;
    ViewPager viewpagerSanPham;
    public ChiTietSanPhamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chi_tiet_san_pham, container, false);
        tvSanPhamAndMau = (TextView) view.findViewById(R.id.tvSanPhamAndMau);
        tvPrice = (TextView) view.findViewById(R.id.tvPrice);
        tvPosition = (TextView) view.findViewById(R.id.tvPosition);
        imgShare = (ImageView) view.findViewById(R.id.imgShare);
        imgChiTiet = (ImageView) view.findViewById(R.id.imgChiTiet);
        btnSanPhamPhuHop =(Button) view.findViewById(R.id.btnSanPhamPhuHop);
        btnColor = (Button) view.findViewById(R.id.btnColor);
        btnSize = (Button) view.findViewById(R.id.btnSize);
        btnChoSanPhamVaoGioHang = (Button) view.findViewById(R.id.btnChoSanPhamVaoGioHang);
        viewpagerSanPham = (ViewPager) view.findViewById(R.id.viewpagerSanPham);


        return view;
    }
}
