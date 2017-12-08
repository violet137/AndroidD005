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
import com.greenacademy.ga_finalprojecthm.model.Home.FashionCatalogResponse;
import com.greenacademy.ga_finalprojecthm.server.ParsingToModelFromJSON;
import com.greenacademy.ga_finalprojecthm.util.IReceiverJSON;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements IReceiverJSON {

    RecyclerView rclvCatalog;
    HomeASyncTask homeASyncTask;

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

        homeASyncTask = new HomeASyncTask();
        homeASyncTask.execute(getString(R.string.api_server), "DanhSachThoiTrang");
        homeASyncTask.setiReceiverJSON(this);
        return view;
    }

    @Override
    public void getStringJSON(String strJSON) {
        FashionCatalogResponse fashionCatalogResponse = ParsingToModelFromJSON.parseToFashionCatalog(strJSON);
        CatalogAdapter catalogAdapter = new CatalogAdapter(getActivity(), fashionCatalogResponse.getFashionCatalogs());

        rclvCatalog.setAdapter(catalogAdapter);
    }
}
