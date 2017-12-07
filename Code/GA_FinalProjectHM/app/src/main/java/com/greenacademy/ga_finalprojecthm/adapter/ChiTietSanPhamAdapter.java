package com.greenacademy.ga_finalprojecthm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.model.ChiTietSanPham.SanPhamTranfers;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Hoang Tin on 07-Dec-17.
 */

public class ChiTietSanPhamAdapter extends RecyclerView.Adapter<ChiTietSanPhamAdapter.DataViewHolder> {
    ArrayList<SanPhamTranfers> sanPhamTranfers;
    Context context;
    ArrayList<String> linkHinh=new ArrayList<>();
    ArrayList<String> listTenSP =new ArrayList<>();
    int[] listGiaTienSP;

    public ChiTietSanPhamAdapter(Context con,ArrayList<SanPhamTranfers> sanPhamTranfers) {
        this.sanPhamTranfers = sanPhamTranfers;
        this.context=con;
        this.listTenSP = getListTenSP();
        this.listGiaTienSP = getListGiaTienSP();
        this.linkHinh=getLinkHinh();

    }
    public ArrayList<String> getLinkHinh(){
           ArrayList<String> linkHinh=new ArrayList<>();
        for (int i=0;i<sanPhamTranfers.size();i++){
            linkHinh.add(sanPhamTranfers.get(i).getHinhByColors().get(0).getLinkHinh().get(0));
        }
        return linkHinh;
    }
    public ArrayList<String> getListTenSP(){
        ArrayList<String> listTenSP=new ArrayList<>();
        for (int i=0;i<sanPhamTranfers.size();i++){
            listTenSP.add(sanPhamTranfers.get(i).getTen());
        }
        return listTenSP;
    }
    public int[] getListGiaTienSP(){
        int[] listGiaTienGiam=new int[sanPhamTranfers.size()];
        for (int i=0;i<sanPhamTranfers.size();i++){
            listGiaTienGiam[i]=sanPhamTranfers.get(i).getGiaTien();
        }
        return listGiaTienGiam;
    }

    @Override
    public ChiTietSanPhamAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chi_tiet_san_pham,parent,false);
        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {


        String giaTienSP=String.valueOf(listGiaTienSP[position])+" VND";
        holder.tvTenSP.setText(listTenSP.get(position));
        holder.tvGiaTienSP.setText(giaTienSP);
        Picasso.with(context).load(linkHinh.get(position)).into(holder.imvChiTietSP);
    }

    @Override
    public int getItemCount() {
        return sanPhamTranfers.size();
    }



    public static class DataViewHolder extends RecyclerView.ViewHolder{
        TextView tvTenSP, tvGiaTienSP;
        ImageView imvChiTietSP;
        RecyclerView recyclerView;
        public DataViewHolder(View itemView) {
            super(itemView);
            tvTenSP =itemView.findViewById(R.id.tvTenSP);
            tvGiaTienSP =itemView.findViewById(R.id.tvGiaTienSP);
            imvChiTietSP=itemView.findViewById(R.id.imvChiTietSP);
            recyclerView=itemView.findViewById(R.id.rvListMau);
        }
    }
}
