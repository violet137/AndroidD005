package com.greenacademy.ga_finalprojecthm.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.greenacademy.ga_finalprojecthm.server.ParsingToModelFromJSON;
import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.adapter.QuestionSupportAdapter;
import com.greenacademy.ga_finalprojecthm.asynctask.QuestionSupportAsyncTask;
import com.greenacademy.ga_finalprojecthm.model.QuestionSupport;
import com.greenacademy.ga_finalprojecthm.model.RootSupport;
import com.greenacademy.ga_finalprojecthm.util.IReceiverJSON;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerFragment extends Fragment implements IReceiverJSON {
    ListView lvHelp;
    QuestionSupportAdapter adapterQuestionSupport;
    QuestionSupportAsyncTask questionSupportAsyncTask;
    RootSupport rootSupport;
    ArrayList<QuestionSupport> questionArrayList;
    public CustomerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer, container, false);
        lvHelp = view.findViewById(R.id.lvHelp);
        questionSupportAsyncTask = new QuestionSupportAsyncTask(getActivity());
        questionSupportAsyncTask.setiQuestionSupport(this);
        questionSupportAsyncTask.execute();
        return view;
    }

    @Override
    public void getStringJSON(String strJSON) {
        rootSupport = ParsingToModelFromJSON.parseToQuestion(strJSON);
        questionArrayList = rootSupport.getCauHoiTranfers();
        adapterQuestionSupport = new QuestionSupportAdapter(getActivity(), R.layout.item_questionsupport, questionArrayList);
        lvHelp.setAdapter(adapterQuestionSupport);
        lvHelp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle b = new Bundle();
                b.putString("question", questionArrayList.get(i).getNoiDungCauHoi());
                b.putString("answer", questionArrayList.get(i).getTraLoi());
                AnswerSupportFragment answerSupportFragment = new AnswerSupportFragment();
                answerSupportFragment.setArguments(b);
                //chuyên tu activity qua DetailFragment
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, answerSupportFragment, "Answer").commit();
            }
        });
    }
}
