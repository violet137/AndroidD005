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
public class AnswerSupportFragment extends Fragment {
    TextView tvQuestion, tvAnswer;
    String strQuestion, strAnswer;

    public AnswerSupportFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_answersupport, container, false);
        tvAnswer = view.findViewById(R.id.tvAnswer);
        tvQuestion = view.findViewById(R.id.tvQuestion);

        Bundle b = getArguments();
        strQuestion = b.getString("question");
        strAnswer = b.getString("answer");

        tvQuestion.setText(strQuestion);
        tvAnswer.setText(strAnswer);
        return view;
    }
}
