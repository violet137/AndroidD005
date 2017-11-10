package com.greenacademy.ga_finalprojecthm.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greenacademy.ga_finalprojecthm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SupportFragment extends Fragment implements View.OnClickListener {
    TextView tvContact, tvFollow, tvFindstore, tvCustomer;

    public SupportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_support, container, false);
        tvContact = view.findViewById(R.id.tvContact);
        tvCustomer = view.findViewById(R.id.tvCustomer);
        tvFindstore = view.findViewById(R.id.tvFindstore);
        tvFollow = view.findViewById(R.id.tvFollow);
        tvCustomer.setOnClickListener(this);
        tvFindstore.setOnClickListener(this);
        tvFollow.setOnClickListener(this);
        tvContact.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvContact:
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, new ContactFragment())
                        .commit();
                break;
            case  R.id.tvCustomer:
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, new CustomerFragment())
                        .commit();
                break;
            case R.id.tvFollow:

                break;
            case R.id.tvFindstore:
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, new MapFragment())
                        .commit();
                break;
            default:
                break;
        }
    }
}
