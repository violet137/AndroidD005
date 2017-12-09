package com.greenacademy.ga_finalprojecthm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.model.fashionset.SanPhamTranfer;

import java.util.ArrayList;

/**
 * Created by xuanson on 12/7/2017.
 */

public class ProductDialogAdapter extends ArrayAdapter<SanPhamTranfer>{
    Context context;
    int layout;
    ArrayList<SanPhamTranfer> sanPhamTranferArrayList = new ArrayList<>();


    public ProductDialogAdapter(@NonNull Context con, int resource, @NonNull ArrayList<SanPhamTranfer> objects) {
        super(con, resource, objects);
        context = con;
        layout = resource;
        sanPhamTranferArrayList = objects;
    }

    @Override
    public int getCount() {
        return sanPhamTranferArrayList.size();
    }

    @Nullable
    @Override
    public SanPhamTranfer getItem(int position) {
        return sanPhamTranferArrayList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_products,null);
        ImageView ivProduct = view.findViewById(R.id.ivProduct);
        TextView tvPrice = view.findViewById(R.id.tvPrice);
        //
        SanPhamTranfer sp = sanPhamTranferArrayList.get(position);
        ivProduct.setImageResource(Integer.parseInt(sp.getLinkhinh()));
        tvPrice.setText((int) sp.getGiatien());
        return view;
    }
}
