package com.greenacademy.ga_finalprojecthm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.greenacademy.ga_finalprojecthm.R;

import java.util.ArrayList;

/**
 * Created by GIT on 11/5/2017.
 */

public class LoaiHoTroAdapter extends ArrayAdapter<String> {
    //lay man hinh giao dien tu ngoai dua vao
    Context con;
    //khai bao de lay item_music
    int layout;
    //quan ly danh sach loai ho tro
    ArrayList<String> arrLoaiHoTroString;
    public LoaiHoTroAdapter(Context context, int resource, ArrayList<String> arrloaihotro) {
        super(context, resource);
        con = context;
        layout = resource;
        arrLoaiHoTroString = arrloaihotro;
    }

    @Override
    public int getCount() {
        return arrLoaiHoTroString.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return arrLoaiHoTroString.get(position);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(con);//lay khung vien mainactivity
        convertView = inflater.inflate(layout,null);//dua layout (iem_music)
        TextView tvloaihotro = convertView.findViewById(R.id.tvloaihotro);//textview item for list
        //hiển thị data Item
        tvloaihotro.setText(arrLoaiHoTroString.get(position));
        return convertView;
    }

}