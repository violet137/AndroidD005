package com.greenacademy.ga_finalprojecthm.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.model.RootTapChi;
import com.greenacademy.ga_finalprojecthm.util.OnTapChiSelected;
import com.squareup.picasso.Picasso;

/**
 * Created by HoangHai Nguyen on 11/5/2017.
 */

public class TapChiAdapter extends RecyclerView.Adapter<TapChiAdapter.ViewHolder>{
    private RootTapChi rootTapChi;
    private int resource;
    private OnTapChiSelected onTapChiSelected;

    public TapChiAdapter(RootTapChi rootTapChi, Fragment fragment, int resource) {
        this.rootTapChi = rootTapChi;
        this.resource = resource;
        if (fragment instanceof OnTapChiSelected) {
            onTapChiSelected = (OnTapChiSelected) fragment;
        } else {
            throw new ClassCastException(fragment.toString() +
                    " must implement OnTapChiSelected.");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_tapchi, parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //holder.imgTapChi.setImage
        holder.tvTenTapChi.setText(rootTapChi.getTapChiTranfers().get(position).getTen());
        holder.tvMoTaTapChi.setText(rootTapChi.getTapChiTranfers().get(position).getMoTa());
//        Picasso.with()
//                .load(rootTapChi.getTapChiTranfers().get(position).getLinkHinh())
//                .placeholder(R.drawable.icon_camera)
//                .error(R.drawable.icon_error)
//                .into(holder.imgTapChi);
        holder.llTapChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTapChiSelected.onTapChiSelected(rootTapChi.getTapChiTranfers().get(position).getIdTapChi());
            }
        });
//        String imageUrl = "URL";
//        InputStream in = null;
//        try {
//            in = new URL(imageUrl).openStream();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Bitmap bmp = BitmapFactory.decodeStream(in);
//        holder.imgTapChi.setImageBitmap(bmp);

    }

    @Override
    public int getItemCount() {
        return rootTapChi.getTapChiTranfers().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgTapChi;
        TextView tvTenTapChi;
        TextView tvMoTaTapChi;
        //dung de setOnClick khi nhan vao tap chi de xem chi tiet
        LinearLayout llTapChi;

        ViewHolder(View itemView) {
            super(itemView);
            imgTapChi = itemView.findViewById(R.id.imgTapChi);
            tvTenTapChi = itemView.findViewById(R.id.tvTenTapChi);
            tvMoTaTapChi = itemView.findViewById(R.id.tvMoTaTapChi);
            llTapChi = itemView.findViewById(R.id.llTapChi);
        }
    }
}
