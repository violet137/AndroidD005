package com.greenacademy.ga_finalprojecthm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.model.RootKhuyenMai;
import com.greenacademy.ga_finalprojecthm.util.IReceiverJSON;
import com.greenacademy.ga_finalprojecthm.util.IReceiverKhuyenMai;
import com.squareup.picasso.Picasso;

/**
 * Created by HoangHai Nguyen on 11/16/2017.
 */

public class KhuyenMaiAdapter extends RecyclerView.Adapter<KhuyenMaiAdapter.ViewHolder> {
    private RootKhuyenMai rootKhuyenMai;
    private Context context;
    private int resource;
    private IReceiverKhuyenMai iReceiverKhuyenMai;


    public KhuyenMaiAdapter(RootKhuyenMai rootKhuyenMai, Context context, int resource) {
        this.rootKhuyenMai = rootKhuyenMai;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_khuyenmai, parent, false);
        return new  ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvListKhuyenMai.setText(rootKhuyenMai.getKhuyenMaiTranfers().get(position).getTenKhuyenMai());
        Picasso.with(context)
                .load(rootKhuyenMai.getKhuyenMaiTranfers().get(position).getHinhDaiDien())
                .placeholder(R.drawable.icon_camera)
                .error(R.drawable.icon_error)
                .into(holder.imgListKhuyenMai);
        holder.lnKhuyenMai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iReceiverKhuyenMai.getKhuyenMai(rootKhuyenMai.getKhuyenMaiTranfers().get(position).getIdKhuyenMai());
            }
        });

    }

    public void setKhuyenMai(IReceiverKhuyenMai iReceiverKhuyenMai){
        this.iReceiverKhuyenMai = iReceiverKhuyenMai;
    }

    @Override
    public int getItemCount() {
        return rootKhuyenMai.getKhuyenMaiTranfers().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgListKhuyenMai;
        TextView tvListKhuyenMai;
        LinearLayout lnKhuyenMai;

        public ViewHolder(View itemView) {
            super(itemView);
            imgListKhuyenMai = itemView.findViewById(R.id.imgListKhuyenMai);
            tvListKhuyenMai = itemView.findViewById(R.id.tvListKhuyenMai);
            lnKhuyenMai = itemView.findViewById(R.id.lnKhuyenMai);
        }
    }
}
