package com.greenacademy.ga_finalprojecthm.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.greenacademy.ga_finalprojecthm.server.ParsingToModelFromJSON;
import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.adapter.LoaiHoTroAdapter;
import com.greenacademy.ga_finalprojecthm.asynctask.LoaiHoTroAsyncTask;
import com.greenacademy.ga_finalprojecthm.asynctask.SendHoTroAsyncTask;
import com.greenacademy.ga_finalprojecthm.model.LoaiHoTro;
import com.greenacademy.ga_finalprojecthm.model.RootLoaiHoTro;
import com.greenacademy.ga_finalprojecthm.util.IReceiverJSON;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFormFragment extends Fragment implements IReceiverJSON {
    Spinner spinnerArea;
    EditText etQuestion,etName, etEmailContactForm;
    Button btnSend;
    LoaiHoTroAdapter loaiHoTroAdapter;
    ArrayList<LoaiHoTro> arrayListLoaiHoTro;
    RootLoaiHoTro rootLoaiHoTro;
    LoaiHoTroAsyncTask loaiHoTroAsyncTask;
    SendHoTroAsyncTask sendHoTroAsyncTask;
    public ContactFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contactform, container, false);
        spinnerArea = view.findViewById(R.id.spinnerArea);
        etQuestion = view.findViewById(R.id.etQuestion);
        etName = view.findViewById(R.id.etName);
        etEmailContactForm = view.findViewById(R.id.etEmailContactForm);
        btnSend = view.findViewById(R.id.btnSend);
        loaiHoTroAsyncTask = new LoaiHoTroAsyncTask(getActivity());
        loaiHoTroAsyncTask.setiLoaiHoTro(this);
        loaiHoTroAsyncTask.execute();
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etQuestion.getText().toString().equals(etQuestion)){
                    Toast.makeText(getActivity(), "vui lòng nhập câu hỏi", Toast.LENGTH_LONG).show();
                }else if (etName.getText().toString().equals(etName)){
                    Toast.makeText(getActivity(), "vui lòng nhập tên", Toast.LENGTH_LONG).show();
                }else if (dataEmail(etEmailContactForm.getText().toString())){
                    Toast.makeText(getActivity(), "Email không phù hợp", Toast.LENGTH_LONG).show();
                } else {
                    sendHoTroAsyncTask.execute(etQuestion.getText().toString(),
                            etName.getText().toString(),
                            etEmailContactForm.getText().toString());
                }
            }
        });

        return view;
    }

    @Override
    public void getStringJSON(String strJSON) {
        rootLoaiHoTro = ParsingToModelFromJSON.parseToLoaiHoTro(strJSON);
        arrayListLoaiHoTro = rootLoaiHoTro.getLoaiHoTroTranfers();
        //tạo 1 arratList kiểu String để chuyển
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (int i=0; i<arrayListLoaiHoTro.size(); i++){
            String temp = arrayListLoaiHoTro.get(i).getTenHoTro();
            stringArrayList.add(temp);
        }
        loaiHoTroAdapter = new LoaiHoTroAdapter(getActivity(), R.layout.item_loaihotro, stringArrayList);
        loaiHoTroAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArea.setAdapter(loaiHoTroAdapter);


    }
    private boolean dataEmail(String strEmail) {
        String strquydinhEmail = "\\w+@\\w+.\\w+"; //giới hạn of email(username
        Pattern pattern = Pattern.compile(strquydinhEmail);
        Matcher matcher = pattern.matcher(strEmail);
        return matcher.matches();
    }
}