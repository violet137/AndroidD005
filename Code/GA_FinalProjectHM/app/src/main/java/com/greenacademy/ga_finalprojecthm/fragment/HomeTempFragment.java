package com.greenacademy.ga_finalprojecthm.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.adapter.FashionSetAdapter;
import com.greenacademy.ga_finalprojecthm.adapter.FashionTypeAdapter;
import com.greenacademy.ga_finalprojecthm.asynctask.FashionSetListAsynctask;
import com.greenacademy.ga_finalprojecthm.model.fashionset.FashionSet;
import com.greenacademy.ga_finalprojecthm.model.fashionset.FashionSetList;
import com.greenacademy.ga_finalprojecthm.server.ParsingToModelFromJSON;
import com.greenacademy.ga_finalprojecthm.util.IReceiverJSON;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeTempFragment extends Fragment implements IReceiverJSON, View.OnClickListener {
//    RecyclerView rvTFashion;
//    ListView lvTFashion;
    ImageView ivMen,ivWomen,ivKids;
    FashionTypeAdapter fashionTypeAdapter;
    FashionSetList fashionSetList = new FashionSetList();
    FashionSetListAsynctask fashionSetListAsynctask ;

    public HomeTempFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_temp, container, false);
//        lvTFashion = view.findViewById(R.id.lvTFashion);
        ivWomen = view.findViewById(R.id.ivWomen);
        ivMen = view.findViewById(R.id.ivMen);
        ivKids = view.findViewById(R.id.ivKids);

        Picasso.with(getContext()).load("http://35.227.90.131:9001/images/nam_thethao.jpg").into(ivMen);
        Picasso.with(getContext()).load("http://35.227.90.131:9001/images/nu_ren.jpg").into(ivWomen);
//        Picasso.with(getContext()).load("").into(ivKids);

        fashionSetListAsynctask = new FashionSetListAsynctask();

        fashionSetListAsynctask.iCallBack(this);
        //nếu click banner là nữ thì truyền Nu vào
        ivMen.setOnClickListener(this);
        ivWomen.setOnClickListener(this);
        return view;
    }

    @Override
    public void getStringJSON(String s) {

        this.fashionSetList = ParsingToModelFromJSON.parseToFashionSetList(s);
        //
//        for (int i = 0; i < fashionSetList.getXuHuongTrangTranfers().size(); i++) {
//            FashionSet fashionSet = fashionSetList.getXuHuongTrangTranfers().get(i);

//        fashionTypeAdapter = new FashionTypeAdapter(getContext(),R.layout.item_fashion_type,fashionSetList.getXuHuongTrangTranfers());
//        lvTFashion.setAdapter(fashionTypeAdapter);
//        lvTFashion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (position == 0)
//                    fashionSetListAsynctask.execute("Nam");
//                else if (position == 1)
//                    fashionSetListAsynctask.execute("Nu");
//                else if (position == 2)
//                    fashionSetListAsynctask.execute("TreEm");
//
//                getActivity().getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.content_frame, new FashionSetFragment())
//                        .commit();
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivMen:
                fashionSetListAsynctask.execute("Nam");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, new FashionSetFragment())
                        .commit();
                break;
            case R.id.ivWomen:
                fashionSetListAsynctask.execute("Nu");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, new FashionSetFragment())
                        .commit();
                break;
//            case R.id.ivKids:
//                fashionSetListAsynctask.execute("TreEm");
//                getActivity().getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.content_frame, new FashionSetFragment())
//                        .commit();
//                break;
        }
    }
}
