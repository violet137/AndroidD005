package com.greenacademy.ga_finalprojecthm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.model.RootLoaiTapChi;
import com.greenacademy.ga_finalprojecthm.util.IReceiverJSON;
import com.greenacademy.ga_finalprojecthm.util.IReceiverLoaiTapChi;

/**
 * Created by HoangHai Nguyen on 11/4/2017.
 */

public class LoaiTapChiAdapter extends RecyclerView.Adapter<LoaiTapChiAdapter.ViewHolder> {
    private RootLoaiTapChi rootLoaiTapChi;
    private Context context;
    private int resource;
    private IReceiverLoaiTapChi iReceiverLoaiTapChi;

    public LoaiTapChiAdapter(RootLoaiTapChi rootLoaiTapChi, Context context, int resource) {
        this.rootLoaiTapChi = rootLoaiTapChi;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Kết nối với item_loai_tap_chi
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_loaitapchi, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvLoaiTapChi.setText(rootLoaiTapChi.getLoaiTapChiTranfers().get(position).getTen());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iReceiverLoaiTapChi.getLoaiTapChi(rootLoaiTapChi.getLoaiTapChiTranfers().get(position).getLoaiTapChi());
            }
        });
    }

    public void setLoaiTapChi(IReceiverLoaiTapChi iReceiverLoaiTapChi) {
        this.iReceiverLoaiTapChi = iReceiverLoaiTapChi;
    }

    @Override
    public int getItemCount() {
        return rootLoaiTapChi.getLoaiTapChiTranfers().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvLoaiTapChi;
        LinearLayout linearLayout;

        ViewHolder(View itemView) {
            super(itemView);
            tvLoaiTapChi = itemView.findViewById(R.id.tvLoaiTapChi);
            linearLayout = itemView.findViewById(R.id.llLoaiTapChi);
        }
    }
}
