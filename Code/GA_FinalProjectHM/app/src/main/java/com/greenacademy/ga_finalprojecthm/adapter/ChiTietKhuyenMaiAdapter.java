package com.greenacademy.ga_finalprojecthm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.model.RootChiTietKhuyenMai;
import com.greenacademy.ga_finalprojecthm.util.IReceiverJSON;
import com.squareup.picasso.Picasso;

/**
 * Created by HoangHai Nguyen on 11/21/2017.
 */

public class ChiTietKhuyenMaiAdapter extends RecyclerView.Adapter<ChiTietKhuyenMaiAdapter.ViewHolder>{
    private RootChiTietKhuyenMai rootChiTietKhuyenMai;
    private IReceiverJSON iReceiverKhuyenMai;
    private Context context;
    private int resource;
    private IReceiverJSON iReceiverChiTietKhuyenMai;



    public ChiTietKhuyenMaiAdapter(RootChiTietKhuyenMai rootChiTietKhuyenMai, Context context, int resource ) {
        this.rootChiTietKhuyenMai = rootChiTietKhuyenMai;
        this.context = context;
        this.resource = resource;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_chitietkhuyenmai, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context)
                .load(rootChiTietKhuyenMai.getListSP().get(position).getLinkHinh())
                .placeholder(R.drawable.icon_camera)
                .error(R.drawable.icon_error)
                .into(holder.imgListCTKM);
        holder.tvTenCTKM.setText(rootChiTietKhuyenMai.getListSP().get(position).getTenSP());
        holder.tvMoTaCTKM.setText(rootChiTietKhuyenMai.getListSP().get(position).getMoTaSp());

    }

    public void setKhuyenMai(IReceiverJSON iReceiverKhuyenMai){
        this.iReceiverKhuyenMai = iReceiverKhuyenMai;
    }

    @Override
    public int getItemCount() {
        return rootChiTietKhuyenMai.getListSP().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgListCTKM;
        TextView tvTenCTKM;
        TextView tvMoTaCTKM;


        public ViewHolder(View itemView) {
            super(itemView);
            imgListCTKM = itemView.findViewById(R.id.imgListCTKM);
            tvTenCTKM = itemView.findViewById(R.id.tvTenCTKM);
            tvMoTaCTKM = itemView.findViewById(R.id.tvMoTaCTKM);

        }
    }
}
