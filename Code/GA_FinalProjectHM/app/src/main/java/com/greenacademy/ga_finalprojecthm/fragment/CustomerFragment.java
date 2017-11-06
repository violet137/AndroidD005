package com.greenacademy.ga_finalprojecthm.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.greenacademy.ga_finalprojecthm.SupportMethod;
import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.adapter.QuestionSupportAdapter;
import com.greenacademy.ga_finalprojecthm.asynctask.QuestionSupportAsyncTask;
import com.greenacademy.ga_finalprojecthm.model.QuestionJSON;
import com.greenacademy.ga_finalprojecthm.model.RootSupportJSON;
import com.greenacademy.ga_finalprojecthm.util.IQuestionSupport;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerFragment extends Fragment implements IQuestionSupport {
    ListView lvHelp;
    QuestionSupportAdapter adapterQuestionSupport;
    ArrayList<QuestionJSON> questionJSON;
    QuestionSupportAsyncTask questionSupportAsyncTask;
    RootSupportJSON rootSupportJSON;
    ArrayList<QuestionJSON> arrayList;
    IQuestionSupport iQuestionSupport;
    //contrustor pass data qua cho  AnswerSupportFragment
    public void setIAnswerQuestion(IQuestionSupport iAnswerQuestion){
        this.iQuestionSupport =iAnswerQuestion;
    }
    public CustomerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer, container, false);
        lvHelp = (ListView) view.findViewById(R.id.lvHelp);
        questionSupportAsyncTask = new QuestionSupportAsyncTask(getActivity());
        questionSupportAsyncTask.setiQuestionSupport(this);
        questionSupportAsyncTask.execute();
        return view;
    }

    @Override
    public void GetQuestionSupport(final ArrayList<String> stringArrayList) {
        //arraylist chua question
        rootSupportJSON = SupportMethod.parsetoQuestion(stringArrayList.get(0));
        arrayList = rootSupportJSON.getCauHoiTranfers();
        questionJSON = new ArrayList<QuestionJSON>();
        adapterQuestionSupport = new QuestionSupportAdapter(getActivity(), R.layout.item_questionsupport, arrayList);
        lvHelp.setAdapter(adapterQuestionSupport);
        lvHelp.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, new AnswerSupportFragment())
                        .commit();
                ArrayList<String> temp = new ArrayList<>();
                temp.add(arrayList.get(position).getNoiDungCauHoi());
                temp.add(arrayList.get(position).getTraLoi());
                iQuestionSupport.GetQuestionSupport(temp);
            }
        });
    }
}
