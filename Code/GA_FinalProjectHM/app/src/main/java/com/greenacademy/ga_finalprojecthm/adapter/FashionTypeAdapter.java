package com.greenacademy.ga_finalprojecthm.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.fragment.FacebookLoginFragment;
import com.greenacademy.ga_finalprojecthm.model.fashionset.FashionSet;
import com.greenacademy.ga_finalprojecthm.model.fashionset.SanPhamTranfer;
import com.greenacademy.ga_finalprojecthm.util.OnFashionTypeSelected;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuanson on 12/7/2017.
 */


public class FashionTypeAdapter extends ArrayAdapter<FashionSet>{
    ArrayList<FashionSet> fashionSetArrayList = new ArrayList<>();
    Context con;
    int layout;


    public FashionTypeAdapter(@NonNull Context context, int resource, @NonNull ArrayList<FashionSet> objects) {
        super(context, resource, objects);
        con = context;
        layout = resource;
        fashionSetArrayList = objects;
    }

    @Override
    public int getCount() {
        return fashionSetArrayList.size();
    }

    @Nullable
    @Override
    public FashionSet getItem(int position) {
        return fashionSetArrayList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(con);
        View view = inflater.inflate(R.layout.item_fashion_type,null);
        ImageView ivType = view.findViewById(R.id.ivType);
        //
        FashionSet fs = fashionSetArrayList.get(position);
        Picasso.with(con).load(fs.getHinh()).into(ivType);
        return view;
    }
}

//public class FashionTypeAdapter extends RecyclerView.Adapter<FashionTypeAdapter.FashionTypeHolder> {
//    ArrayList<FashionSet> fashionSetArrayList = new ArrayList<>();
//    Context context;
//    int layout;
//    FashionSet fashionSet;
//    OnFashionTypeSelected onFashionTypeSelected;
//
//    public FashionTypeAdapter(ArrayList<FashionSet> fashionSetArrayList, Context context, int layout) {
//        this.fashionSetArrayList = fashionSetArrayList;
//        this.context = context;
//        this.layout = layout;
//    }
//
//    @Override
//    public FashionTypeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fashion_type,parent,false);
//        return new FashionTypeHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(FashionTypeHolder holder, final int position) {
//        fashionSet = fashionSetArrayList.get(position);
//        holder.ivType.setImageResource(Integer.parseInt(fashionSet.getHinh()));
////        holder.llType.setTag(fashionSet);
//        //
//        holder.llType.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                FashionSet fs = (FashionSet) v.getTag();
//                onFashionTypeSelected.onFashionTypeSelected(fashionSetArrayList.get(position).getIdXuHuong());
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return fashionSetArrayList.size()-2;
//    }
//
//    public class FashionTypeHolder extends RecyclerView.ViewHolder{
//        ImageView ivType;
//        LinearLayout llType;
//
//        public FashionTypeHolder(View itemView) {
//            super(itemView);
//            ivType = itemView.findViewById(R.id.ivType);
//            llType = itemView.findViewById(R.id.llType);
//        }
//    }
//}
