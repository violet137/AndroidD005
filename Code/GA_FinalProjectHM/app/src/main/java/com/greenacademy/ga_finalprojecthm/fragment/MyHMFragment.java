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
public class MyHMFragment extends Fragment implements View.OnClickListener {

    public MyHMFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_hm, container, false);

        TextView tvInbox = view.findViewById(R.id.tvInbox);
        TextView tvUserProfile = view.findViewById(R.id.tvUserProfile);
        TextView tvSettings = view.findViewById(R.id.tvSettings);

        tvInbox.setOnClickListener(this);
        tvUserProfile.setOnClickListener(this);
        tvSettings.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvInbox:
                break;
            case R.id.tvUserProfile:
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, new LoginFragment())
                        .commit();
                break;
            case R.id.tvSettings:
                break;
        }
    }
}
