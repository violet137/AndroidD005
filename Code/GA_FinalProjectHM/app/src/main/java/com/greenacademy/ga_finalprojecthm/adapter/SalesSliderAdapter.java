package com.greenacademy.ga_finalprojecthm.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.greenacademy.ga_finalprojecthm.MainActivity;
import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.fragment.KhuyenMaiFragment;
import com.greenacademy.ga_finalprojecthm.model.RootKhuyenMai;
import com.squareup.picasso.Picasso;

/**
 * Created by thepa on 12/9/2017.
 */

public class SalesSliderAdapter extends PagerAdapter {

    private RootKhuyenMai rootKhuyenMai;
    private LayoutInflater inflater;
    private Context context;

    public SalesSliderAdapter(Context context, RootKhuyenMai rootKhuyenMai) {
        this.context = context;
        this.rootKhuyenMai = rootKhuyenMai;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return rootKhuyenMai.getKhuyenMaiTranfers().size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.item_viewpager_slide_sales, container, false);
        ImageView imgvSaleItem = view.findViewById(R.id.imgvSaleItem);
        FrameLayout flSliderSales = view.findViewById(R.id.flSliderSales);

        Picasso
                .with(context)
                .load(rootKhuyenMai.getKhuyenMaiTranfers().get(position).getHinhDaiDien())
                .error(R.drawable.not_found)
                .into(imgvSaleItem);

        flSliderSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.content_frame, new KhuyenMaiFragment())
                        .commit();
            }
        });

        container.addView(view, 0);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}
