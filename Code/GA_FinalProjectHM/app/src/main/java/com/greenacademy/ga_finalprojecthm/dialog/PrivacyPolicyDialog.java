package com.greenacademy.ga_finalprojecthm.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.greenacademy.ga_finalprojecthm.R;

/**
 * Created by GIT on 11/3/2017.
 */

public class PrivacyPolicyDialog extends DialogFragment {
    String title;
    String content;
    TextView tvDialogSecurity;
    public PrivacyPolicyDialog() {
    }

    public void setData(String ti, String con){
        this.content = con;
        this.title = ti;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater =getActivity().getLayoutInflater();//lấy quyền giao diện hiển thị
        builder.setTitle("title nè mấy má");
        View dialog = layoutInflater.inflate(R.layout.dialog_privacypolicy, null);
        tvDialogSecurity = (TextView) dialog.findViewById(R.id.tvDialogSecurity);
        tvDialogSecurity.setText(R.string.privacy_policy);
        builder.setView(dialog);
        return builder.create();
    }
}
