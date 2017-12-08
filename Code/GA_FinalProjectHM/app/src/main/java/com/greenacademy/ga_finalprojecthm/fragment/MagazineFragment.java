package com.greenacademy.ga_finalprojecthm.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

    private static final int MENU_LOGIN = Menu.FIRST;

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

    public void loadLoaiTapChi(){
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //in order to create option menu on tool bar (action bar)
        setHasOptionsMenu(true);
    }

    //in order to create menu item on tool bar (action bar)
    @SuppressLint("ResourceType")
    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        menu.clear();
//        inflater.inflate(R.menu.menu_toolbar, menu);

        menu.add(Menu.NONE, MENU_LOGIN, Menu.NONE, "")
                .setIcon(R.drawable.shop_item_details_button_info_pressed)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                        View v = layoutInflater.inflate(R.layout.dialog_view_i_magazine, null);
                        builder.setTitle("ABOUT")
                        .setMessage("Tạp chí H&M là phần biên tập của hm.com. Tạp chí H&M là một tạp chí trực tuyến dành cho thời trang, sắc đẹp và văn hoá. Chúng tôi cung cấp cho bạn cập nhật hàng ngày và nguồn cảm hứng từ khắp nơi trên thế giới.\n" +
                                "\n" +
                                "Nhà xuất bản\n" +
                                "Daniel Herrmann\n" +
                                "\n" +
                                "LIÊN HỆ\n" +
                                "Đối với các câu hỏi, phản hồi hoặc những ghi chú về tình yêu liên quan đến Tạp chí H&M, vui lòng gửi email đến\n" +
                                "magazine@hm.com\n" +
                                "\n" +
                                "ĐỊA CHỈ BƯU ĐIỆN\n" +
                                "H&M Hennes và Mauritz\n" +
                                "Tạp chí H&M\n" +
                                "106 38 Stockholm\n" +
                                "Thụy Điển\n" +
                                "\n" +
                                "GHÉ THĂM CHÚNG TÔI\n" +
                                "H&M Hennes và Mauritz\n" +
                                "Mäster Samuelsgatan 46A\n" +
                                "106 38 Stockholm\n" +
                                "Thụy Điển")
                        .setView(v)
                        .setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        return true;
                    }
                })
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }


























}
