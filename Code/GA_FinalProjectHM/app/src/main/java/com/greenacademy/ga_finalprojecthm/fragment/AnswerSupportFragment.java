package com.greenacademy.ga_finalprojecthm.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.asynctask.QuestionSupportAsyncTask;
import com.greenacademy.ga_finalprojecthm.util.IQuestionSupport;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnswerSupportFragment extends Fragment implements IQuestionSupport {
    TextView tvQuestionAnswer,tvAnswer;
    CustomerFragment customerFragment;
    public AnswerSupportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_answersupport, container, false);
        tvAnswer = (TextView) view.findViewById(R.id.tvAnswer);
        tvQuestionAnswer = (TextView) view.findViewById(R.id.tvQuestionAnswer);
        customerFragment = new CustomerFragment();
        customerFragment.setIAnswerQuestion(this);
        return view;
    }

    @Override
    public void GetQuestionSupport(ArrayList<String> stringArrayList) {
        tvQuestionAnswer.setText(stringArrayList.get(0));
        tvAnswer.setText(stringArrayList.get(1));
    }
}
