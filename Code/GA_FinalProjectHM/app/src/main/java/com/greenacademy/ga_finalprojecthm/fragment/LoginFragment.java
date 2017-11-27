package com.greenacademy.ga_finalprojecthm.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.SignInButton;
import com.greenacademy.ga_finalprojecthm.R;
import com.greenacademy.ga_finalprojecthm.asynctask.LoginAsyncTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    EditText userName, passWord;
    CheckBox cbRememberMe;
    TextView tvForgotPassword, txtWarring;
    Button btnLogin, btnRegister;
    LoginAsyncTask loginAsyncTask;
    //ta share preferences
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    //key cua share preferences
    String USER= "UserName";
    String PASS="Pass";
    LoginButton btnFbLogin;
    SignInButton btnGoogleSignin;


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        //anh xa
        userName = view.findViewById(R.id.etUserName);
        passWord = view.findViewById(R.id.etPassword);
        btnLogin = view.findViewById(R.id.btnLogin);
        btnRegister = view.findViewById(R.id.btnRegister);
        txtWarring = view.findViewById(R.id.txtWarring);
        tvForgotPassword = view.findViewById(R.id.tvForgotPassword);
        cbRememberMe = view.findViewById(R.id.cbRememberMe);
        btnFbLogin = view.findViewById(R.id.btnFbLogin);
        btnGoogleSignin = view.findViewById(R.id.btnGoogleSignin);

        sharedPreferences = getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        //do du lieu trong preferences vao trong 2 edit text khi mo lai ung dung
        userName.setText(sharedPreferences.getString(USER,""));
        passWord.setText(sharedPreferences.getString(PASS,""));

        btnLogin.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        btnFbLogin.setOnClickListener(this);
        btnGoogleSignin.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //xu li nut login
            case R.id.btnLogin:
                String name = userName.getText().toString();
                String pass = passWord.getText().toString();
                if(name.isEmpty() || pass.isEmpty()){
                    txtWarring.setText(R.string.log_in_warning);
                }
                else {
                    loginAsyncTask = new LoginAsyncTask(getActivity());
                    loginAsyncTask.execute(name, pass);
                }
                //kiem tra check box
                if(cbRememberMe.isChecked()){
                    editor = sharedPreferences.edit();
                    //luu du lieu vao share preferences
                    editor.putString(USER,userName.getText().toString());
                    editor.putString(PASS,passWord.getText().toString());
//                    editor.commit();
                    editor.apply();
                }
                break;
            // forgot password
            case R.id.tvForgotPassword:
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, new ForgetPasswordFragment())
                        .commit();
                break;
            // xu li nut register
            case R.id.btnRegister:
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, new RegisterFragment())
                        .commit();
                break;
            case R.id.btnFbLogin:
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, new FacebookLoginFragment())
                        .commit();
                break;
            case R.id.btnGoogleSignin:
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, new GoogleSignInFragment())
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }
}
