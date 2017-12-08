package com.greenacademy.ga_finalprojecthm.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.session.Session;
import com.greenacademy.ga_finalprojecthm.adapter.WishListProductAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class WishListFragment extends Fragment {

    RecyclerView rclvListProducts;

    public WishListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wish_list, container, false);
        rclvListProducts = view.findViewById(R.id.rclvListProducts);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rclvListProducts.setHasFixedSize(true);
        rclvListProducts.setLayoutManager(linearLayoutManager);

        WishListProductAdapter wishListProductAdapter = new WishListProductAdapter(Session.wishlistProducts);
        rclvListProducts.setAdapter(wishListProductAdapter);
        return view;
    }

}
