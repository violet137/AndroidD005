package com.greenacademy.ga_finalprojecthm.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.asynctask.RegisterAsyncTask;
import com.greenacademy.ga_finalprojecthm.dialog.PrivacyPolicyDialog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {
    EditText etEmail, etPass, etConfirmPass;
    TextView tvSecurity;
    Button btnrgt;
    RegisterAsyncTask asyncTaskRegister;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etPass = (EditText) view.findViewById(R.id.etPass);
        etConfirmPass = (EditText) view.findViewById(R.id.etConfirmPass);
        tvSecurity = (TextView) view.findViewById(R.id.tvSecurity);
        btnrgt = (Button) view.findViewById(R.id.btnrgt);
        String underline = "<u>Privacy Policy </u>";
        tvSecurity.setText(Html.fromHtml(underline));
        asyncTaskRegister = new RegisterAsyncTask(getActivity());
        btnrgt.setOnClickListener(this);
        tvSecurity.setOnClickListener(this);
        return  view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnrgt:
                if (!dataEmail(etEmail.getText().toString())) {
                    Toast.makeText(getActivity(), "Email không phù hợp", Toast.LENGTH_LONG).show();
                } else if (!dataPass(etPass.getText().toString())) {
                    Toast.makeText(getActivity(), "Password không phù hợp", Toast.LENGTH_LONG).show();
                } else if (!etPass.getText().toString().equals(etConfirmPass.getText().toString())) {
                    Toast.makeText(getActivity(), "xác nhận mật khẩu sai! Mời nhập lại", Toast.LENGTH_LONG).show();
                } else {
                    asyncTaskRegister.execute(etEmail.getText().toString(), etPass.getText().toString());
                }
                break;
            case R.id.tvSecurity:
                dialogregister();
                break;
        }
    }

    private void dialogregister() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        PrivacyPolicyDialog dialog_registe = new PrivacyPolicyDialog();
        dialog_registe.setData("title nè mấy má", "new register");
        dialog_registe.show(fragmentManager, "sdf");
    }

    private boolean dataEmail(String strEmail) {
        String strquydinhEmail = "\\w+@\\w+.\\w+"; //giới hạn of email(username
        Pattern pattern = Pattern.compile(strquydinhEmail);
        Matcher matcher = pattern.matcher(strEmail);
        return matcher.matches();
    }
    private boolean dataPass(String strPass) {
        String strquydinhPass = "[A-Za-z0-9]{6,}";//giới hạn of pass
        Pattern pattern = Pattern.compile(strquydinhPass);
        Matcher matcher = pattern.matcher(strPass);
        return  matcher.matches();
    }
}
