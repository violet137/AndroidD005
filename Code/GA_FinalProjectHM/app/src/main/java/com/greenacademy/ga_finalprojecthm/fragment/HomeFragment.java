package com.greenacademy.ga_finalprojecthm.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.adapter.CatalogAdapter;
import com.greenacademy.ga_finalprojecthm.asynctask.HomeASyncTask;
import com.greenacademy.ga_finalprojecthm.model.Home.FashionCatalog;
import com.greenacademy.ga_finalprojecthm.model.Home.FashionCatalogResponse;
import com.greenacademy.ga_finalprojecthm.server.ParsingToModelFromJSON;
import com.greenacademy.ga_finalprojecthm.util.IReceiverJSON;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    RecyclerView rclvCatalog;
    ArrayList<FashionCatalog> fashionCatalogs;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rclvCatalog = view.findViewById(R.id.rclvCatalog);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rclvCatalog.setHasFixedSize(true);
        rclvCatalog.setLayoutManager(linearLayoutManager);

        CatalogAdapter catalogAdapter = new CatalogAdapter(getActivity(), fashionCatalogs);
        rclvCatalog.setAdapter(catalogAdapter);
        return view;
    }

    public void setData(ArrayList<FashionCatalog> fashionCatalogs){
        this.fashionCatalogs = fashionCatalogs;
    }
}
