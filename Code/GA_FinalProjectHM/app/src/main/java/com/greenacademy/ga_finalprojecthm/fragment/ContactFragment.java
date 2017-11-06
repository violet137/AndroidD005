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
public class ContactFragment extends Fragment implements View.OnClickListener {
    TextView tvCustomerService, tvContactForm;

    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        tvContactForm = (TextView) view.findViewById(R.id.tvContactForm);
        tvCustomerService = (TextView) view.findViewById(R.id.tvCustomerService);
        tvContactForm.setOnClickListener(this);
        tvCustomerService.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvContactForm:
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, new ContactFormFragment())
                        .commit();
                break;
            case R.id.tvCustomerService:
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, new CustomerServiceFragment())
                        .commit();
                break;

        }
    }
}
