package com.greenacademy.ga_finalprojecthm.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.LoaiHoTroMethod;
import com.greenacademy.ga_finalprojecthm.adapter.LoaiHoTroAdapter;
import com.greenacademy.ga_finalprojecthm.asynctask.LoaiHoTroAsyncTask;
import com.greenacademy.ga_finalprojecthm.asynctask.QuestionSupportAsyncTask;
import com.greenacademy.ga_finalprojecthm.model.LoaiHoTroJson;
import com.greenacademy.ga_finalprojecthm.model.RootLoaiHoTroJson;
import com.greenacademy.ga_finalprojecthm.util.ILoaiHoTro;
import com.greenacademy.ga_finalprojecthm.util.IQuestionSupport;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLDisplay;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFormFragment extends Fragment implements ILoaiHoTro {
    Spinner spinnerArea;
    EditText etQuestion,etName, etEmailContactForm;
    Button btnSend;
    LoaiHoTroAdapter loaiHoTroAdapter;
    ArrayList<LoaiHoTroJson>  loaiHoTroJsons;
    LoaiHoTroAsyncTask loaiHoTroAsyncTask;
    public ContactFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contactform, container, false);
        spinnerArea = (Spinner) view.findViewById(R.id.spinnerArea);
        etQuestion = (EditText) view.findViewById(R.id.etQuestion);
        etName = (EditText) view.findViewById(R.id.etName);
        etEmailContactForm = (EditText) view.findViewById(R.id.etEmailContactForm);
        btnSend = (Button) view.findViewById(R.id.btnSend);
        loaiHoTroAsyncTask = new LoaiHoTroAsyncTask(getActivity());
        loaiHoTroAsyncTask.setiLoaiHoTro(this);
        loaiHoTroAsyncTask.execute();
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }

    @Override
    public void GetLoaiHoTro(ArrayList<String> stringArrayLoaiHoTro) {
        RootLoaiHoTroJson rootLoaiHoTroJson = LoaiHoTroMethod.parsetoLoaiHoTro(stringArrayLoaiHoTro.get(0));
        ArrayList<LoaiHoTroJson> arrayList = rootLoaiHoTroJson.getLoaiHoTroTranfers();
        loaiHoTroJsons = new ArrayList<>();
        loaiHoTroAdapter = new LoaiHoTroAdapter(getActivity(), R.layout.item_loaihotro, arrayList);
        loaiHoTroAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArea.setAdapter(loaiHoTroAdapter);


    }
}
