package com.greenacademy.ga_finalprojecthm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.model.QuestionSupport;

import java.util.ArrayList;

/**
 * Created by GIT on 11/4/2017.
 */

public class QuestionSupportAdapter extends ArrayAdapter<QuestionSupport> {
    //lay man hinh giao dien tu ngoai dua vao
    Context con;
    //khai bao de lay item_music
    int layout;
    //quan ly danh sach question
    ArrayList<QuestionSupport> arrquestionSupport;
    public QuestionSupportAdapter(Context context, int resource, ArrayList<QuestionSupport> arrquestion) {
        super(context, resource);
        con = context;
        layout = resource;
        arrquestionSupport = arrquestion;
    }
    @Override
    public int getCount() {
        return arrquestionSupport.size();
    }

    @Nullable
    @Override
    public QuestionSupport getItem(int position) {
        return arrquestionSupport.get(position);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(con);//lay khung vien mainactivity
        convertView = inflater.inflate(layout,null);//dua layout (iem_music)
        TextView tvQuestion = convertView.findViewById(R.id.tvQuestionSupport);//textview item for list
        //hiển thị data Item
        QuestionSupport temQuestion = arrquestionSupport.get(position);
        tvQuestion.setText(temQuestion.getNoiDungCauHoi());
        return convertView;
    }

}
