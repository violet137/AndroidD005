package com.greenacademy.ga_finalprojecthm.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.session.Session;
import com.greenacademy.ga_finalprojecthm.asynctask.LoginAsyncTask;
import com.greenacademy.ga_finalprojecthm.model.LoginDetails;
import com.greenacademy.ga_finalprojecthm.server.ParsingToModelFromJSON;
import com.greenacademy.ga_finalprojecthm.util.IReceiverJSON;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyHMFragment extends Fragment implements View.OnClickListener {

    private static final int MENU_ITEM = Menu.FIRST;

    public MyHMFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_hm, container, false);

        TextView tvInbox = view.findViewById(R.id.tvInbox);
        TextView tvUserProfile = view.findViewById(R.id.tvUserProfile);
        TextView tvSettings = view.findViewById(R.id.tvSettings);
        TextView tvGreeting = view.findViewById(R.id.tvGreeting);

        tvGreeting.setText("Hi, " + Session.username);
        tvInbox.setOnClickListener(this);
        tvUserProfile.setOnClickListener(this);
        tvSettings.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvInbox:
                break;
            case R.id.tvUserProfile:
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, new LoginFragment())
                        .commit();
                break;
            case R.id.tvSettings:
                break;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //in order to create option menu on tool bar (action bar)
        setHasOptionsMenu(true);
    }

    //in order to create menu item on tool bar (action bar)
    @Override
    public void onCreateOptionsMenu(final Menu menu, MenuInflater inflater) {
        menu.clear();
//        inflater.inflate(R.menu.menu_toolbar, menu);

        if (!Session.username.equals("Guest")) {
            menu.add(Menu.NONE, MENU_ITEM, Menu.NONE, R.string.log_out)
                    .setIcon(0)
                    .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            Toast.makeText(getActivity(), "Log out selected", Toast.LENGTH_SHORT).show();
                            Session.removeLoginState();
                            Session.isLogedIn = false;
                            Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("MyHMFragment");
                            getActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .detach(fragment)
                                    .attach(fragment)
                                    .commit();
                            return true;
                        }
                    })
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        } else {
            menu.add(Menu.NONE, MENU_ITEM, Menu.NONE, R.string.log_in)
                    .setIcon(0)
                    .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
//                            if(Session.username.equals("Guest")){
                            getActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.content_frame, new LoginFragment())
                                    .addToBackStack(null)
                                    .commit();
//                            }
//                            else {
//                                LoginAsyncTask loginAsyncTask = new LoginAsyncTask(getActivity());
//                                loginAsyncTask.execute(Session.getUsername(), Session.getPassword());
//                                loginAsyncTask.setiReceiverJSON(new IReceiverJSON() {
//                                    @Override
//                                    public void getStringJSON(String strJSON) {
//                                        if (strJSON.equals(getResources().getString(R.string.server_error))){
//                                            Toast.makeText(getActivity(), R.string.server_error, Toast.LENGTH_SHORT).show();
//                                        }
//                                        else {
//                                            LoginDetails login = ParsingToModelFromJSON.parseToLoginDetails(strJSON);
//                                            if (login.getStatus() == 1) {
//                                                Toast.makeText(getActivity(), R.string.log_in_successfully, Toast.LENGTH_SHORT).show();
//                                            } else {
//                                                Toast.makeText(getActivity(), R.string.log_in_failed, Toast.LENGTH_SHORT).show();
//                                                getActivity().getSupportFragmentManager()
//                                                        .beginTransaction()
//                                                        .replace(R.id.content_frame, new LoginFragment())
//                                                        .addToBackStack(null)
//                                                        .commit();
//                                            }
//                                        }
//                                    }
//                                });
//                            }
                            return true;
                        }
                    })
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
    }
}
