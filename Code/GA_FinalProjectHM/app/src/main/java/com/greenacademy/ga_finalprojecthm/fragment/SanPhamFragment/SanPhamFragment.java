package com.greenacademy.ga_finalprojecthm.fragment.SanPhamFragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.asynctask.SanPhamAsynTask;
import com.greenacademy.ga_finalprojecthm.fragment.ChiTietSanPham.ChiTietSanPhamFragment;
import com.greenacademy.ga_finalprojecthm.model.ChiTietSanPham.ChiTietSanPham;
import com.greenacademy.ga_finalprojecthm.model.DanhMucHang.DanhMucHang;
import com.greenacademy.ga_finalprojecthm.model.DanhMucHang.DanhMucHangTranfers;
import com.greenacademy.ga_finalprojecthm.model.DanhMucHang.DanhMucList;
import com.greenacademy.ga_finalprojecthm.server.ParsingToModelFromJSON;
import com.greenacademy.ga_finalprojecthm.util.IClick;
import com.greenacademy.ga_finalprojecthm.util.IReceiverJSON;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SanPhamFragment extends Fragment implements IReceiverJSON, AdapterView.OnItemClickListener {

    ImageView imageView;
    ListView listView;
    SanPhamAsynTask sanPhamAsynTask;
    DanhMucHang danhMucHang = new DanhMucHang();
    DanhMucHangTranfers danhMucHangTranfers = new DanhMucHangTranfers();
    TextView textView;
    String Click;// truyen string ma nguoi dung click vao
    ArrayList<DanhMucList> danhMucList = new ArrayList<>();

    public void Click(String click) {
        this.Click = click;
    }// tao ham de truyen vao

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_san_pham, container, false);
        // khoi tao
        listView = (ListView) view.findViewById(R.id.lvListView);
        imageView = (ImageView) view.findViewById(R.id.imvImageView);
        // truyen du lieu tu asyntaskSanPham sang day
        sanPhamAsynTask = new SanPhamAsynTask();
        sanPhamAsynTask.iCallBack(this);
        sanPhamAsynTask.execute(Click);// truyen string nguoi dung click vao de gui le  server
        // ham xu li khi click vao item cua listView
        listView.setOnItemClickListener(this);

        return view;
    }

    // ham overive tu interface de truyen du lieu
// anh cahy debug di be bam cho
    @Override
    public void getStringJSON(String strJSON) {
        this.danhMucHang = ParsingToModelFromJSON.parseToDanhMucHang(strJSON);
        //bo data vao
        danhMucHangTranfers = danhMucHang.getDanhMucHangTranfers();
        danhMucList = danhMucHangTranfers.getDanhMucLists();
        ArrayList<String> arrTenSanPham = new ArrayList<>();
        for (int i = 0; i < danhMucList.size(); i++) {
            arrTenSanPham.add(danhMucList.get(i).getTenDanhMuc());
        }
        String[] arrSanPham = arrTenSanPham.toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, arrSanPham);
        listView.setAdapter(adapter);
        // set anh vao image view
        String linkAnh = danhMucHangTranfers.getXuHuongTtrangLink();
        if (!linkAnh.isEmpty() && linkAnh != null) {
            Picasso.with(getActivity()).load(linkAnh).into(imageView);
        } else {
            Picasso.with(getActivity()).load("http://afamily1.mediacdn.vn/k:thumb_w/600/Xp9X5HOVHqj12brXY0FaTFes1tq7/Image/2016/11/12-80b08/3.jpg").into(imageView);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ChiTietSanPhamFragment chiTietSanPham = new ChiTietSanPhamFragment();
        chiTietSanPham.getClick(Click);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, chiTietSanPham).commit();
    }
}
