package com.greenacademy.ga_finalprojecthm.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.model.fashionset.FashionSet;
import com.greenacademy.ga_finalprojecthm.model.fashionset.SetDoTranfer;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by xuanson on 12/7/2017.
 */

public class FashionSetAdapter extends ArrayAdapter<SetDoTranfer> {
    ArrayList<SetDoTranfer> setDoTranferArrayList = new ArrayList<>();
    Context con;
    int layout;
    ProductDialogAdapter productAdapter;


    public FashionSetAdapter(@NonNull Context context, int resource, @NonNull ArrayList<SetDoTranfer> objects) {
        super(context, resource, objects);
        con = context;
        layout = resource;
        setDoTranferArrayList = objects;
    }

    @Override
    public int getCount() {
        return setDoTranferArrayList.size();
    }

    @Nullable
    @Override
    public SetDoTranfer getItem(int position) {
        return setDoTranferArrayList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(con);
        View view = inflater.inflate(R.layout.item_fashion_set,null);
        ImageView ivSet = view.findViewById(R.id.ivSet);
        //
        SetDoTranfer sd = setDoTranferArrayList.get(position);
        Picasso.with(con).load(sd.getHinhSet()).into(ivSet);
        return view;
    }
    public AlertDialog fashionDialog(SetDoTranfer setDoTranfer){
        GridView gvProducts;

        AlertDialog.Builder builder = new AlertDialog.Builder(con);
        LayoutInflater inflater = LayoutInflater.from(con);
        View v = inflater.inflate(R.layout.dialog_view_products,null);
        gvProducts = v.findViewById(R.id.gvProducts);
        //
        productAdapter = new ProductDialogAdapter(con,R.layout.dialog_view_products,setDoTranfer.getSanPhamTranferArrayList());
        gvProducts.setAdapter(productAdapter);
        return builder.setTitle("View Product")
                .setView(v)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
    }
}



//public class FashionSetAdapter extends RecyclerView.Adapter<FashionSetAdapter.FashionSetHolder>{
//    ArrayList<SetDoTranfer> setDoTranferArrayList = new ArrayList<>();
//    Context context;
//    int layout;
//    SetDoTranfer setDoTranfer;
//    ProductDialogAdapter productAdapter;
//
//    public FashionSetAdapter(ArrayList<SetDoTranfer> setDoTranferArrayList, Context context, int layout) {
//        this.setDoTranferArrayList = setDoTranferArrayList;
//        this.context = context;
//        this.layout = layout;
//    }
//
//    @Override
//    public FashionSetHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fashion_set,parent,false);
//        return new FashionSetHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(FashionSetHolder holder, int position) {
//        setDoTranfer  = setDoTranferArrayList.get(position);
//        holder.ivSet.setImageResource(Integer.parseInt(setDoTranfer.getHinhSet()));
//        holder.ivSet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fashionDialog(setDoTranfer).show();
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return setDoTranferArrayList.size();
//    }
//
//    public class FashionSetHolder extends RecyclerView.ViewHolder{
//        ImageView ivSet;
//
//        public FashionSetHolder(View itemView) {
//            super(itemView);
//            ivSet = itemView.findViewById(R.id.ivSet);
//        }
//    }
//
//    public AlertDialog fashionDialog(SetDoTranfer setDoTranfer){
//        GridView gvProducts;
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View v = inflater.inflate(R.layout.dialog_view_products,null);
//        gvProducts = v.findViewById(R.id.gvProducts);
//        //
//        productAdapter = new ProductDialogAdapter(context,R.layout.dialog_view_products,setDoTranfer.getSanPhamTranferArrayList());
//        gvProducts.setAdapter(productAdapter);
//        return builder.setTitle("View Product")
//                .setView(v)
//                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                }).create();
//    }
//}
