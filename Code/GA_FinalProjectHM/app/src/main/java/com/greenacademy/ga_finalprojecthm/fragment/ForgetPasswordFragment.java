package com.greenacademy.ga_finalprojecthm.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.asynctask.ForgetPasswordASynctask;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForgetPasswordFragment extends Fragment implements View.OnClickListener{
    EditText etEmailForget;
    Button btnForget;
    TextView tvThongBao;
    Button btnCall;
    ForgetPasswordASynctask forgetPasswordAsynctask;


    public ForgetPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forget_password, container, false);
        //Ánh xạ
        etEmailForget = view.findViewById(R.id.etEmailForget);
        btnForget = view.findViewById(R.id.btnForget);
        tvThongBao = view.findViewById(R.id.tvThongBao);

        ForgetPasswordFragment forgetPassword = new ForgetPasswordFragment();

        btnCall = view.findViewById(R.id.btnCall);

        btnForget.setOnClickListener(this);
        btnCall.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnForget:
                if (etEmailForget.length() == 0) {
                    tvThongBao.setText("Vui lòng nhập địa chỉ email");
                } else if (etEmailForget.length() > 0) {
                    tvThongBao.setText("");
                }
                forgetPasswordAsynctask = new ForgetPasswordASynctask();
                forgetPasswordAsynctask.execute();
                break;

            case R.id.btnCall:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: "+"0899333132"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }
}
