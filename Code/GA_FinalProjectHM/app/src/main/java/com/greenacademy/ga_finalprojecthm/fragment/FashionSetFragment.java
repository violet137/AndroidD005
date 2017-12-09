package com.greenacademy.ga_finalprojecthm.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.adapter.FashionSetAdapter;
import com.greenacademy.ga_finalprojecthm.asynctask.FashionSetListAsynctask;
import com.greenacademy.ga_finalprojecthm.model.fashionset.FashionSet;
import com.greenacademy.ga_finalprojecthm.model.fashionset.FashionSetList;
import com.greenacademy.ga_finalprojecthm.model.fashionset.SetDoTranfer;
import com.greenacademy.ga_finalprojecthm.server.ParsingToModelFromJSON;
import com.greenacademy.ga_finalprojecthm.util.IReceiverJSON;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FashionSetFragment extends Fragment implements IReceiverJSON {
    ListView lvSet;
    FashionSetAdapter fashionSetAdapter;
    FashionSetListAsynctask fashionSetListAsynctask ;
    FashionSetList fashionSetList = new FashionSetList();
    FashionSet fashionSet = new FashionSet();
    ArrayList<SetDoTranfer> setDoTranferArrayList = new ArrayList<>();

    public FashionSetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fashion_set, container, false);
        lvSet = v.findViewById(R.id.lvSet);


        fashionSetListAsynctask = new FashionSetListAsynctask();
        fashionSetListAsynctask.iCallBack(this);
        fashionSetListAsynctask.execute();
        //
        return v;
    }

    @Override
    public void getStringJSON(String s) {
        this.fashionSetList = ParsingToModelFromJSON.parseToFashionSetList(s);
        //
        for (int i = 0; i < fashionSetList.getXuHuongTrangTranfers().size(); i++) {
            fashionSet = fashionSetList.getXuHuongTrangTranfers().get(i);
            //
            fashionSetAdapter = new FashionSetAdapter(getContext(), R.layout.item_fashion_set,fashionSet.getSetDoTranferArrayList());
            lvSet.setAdapter(fashionSetAdapter);
            lvSet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    fashionSetAdapter.fashionDialog(setDoTranferArrayList.get(position)).show();
                }
            });
        }
    }
}
