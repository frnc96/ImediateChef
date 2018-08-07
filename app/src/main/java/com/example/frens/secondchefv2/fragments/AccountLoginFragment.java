package com.example.frens.secondchefv2.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.frens.secondchefv2.R;
import com.example.frens.secondchefv2.activities.MainActivity;
import com.example.frens.secondchefv2.services.DataService;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountLoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountLoginFragment extends Fragment {

    Toolbar toolbar;
    private static final String EMAIL = "email";
    private CallbackManager callbackManager;
    private LoginButton fbLoginButton;
    private Button loginButton;
    private EditText email;
    private EditText pass;

    public AccountLoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     * @return A new instance of fragment AccountLoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountLoginFragment newInstance() {
        AccountLoginFragment fragment = new AccountLoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_account_login, container, false);

        toolbar = v.findViewById(R.id.login_toolbar);
        MainActivity.getMainActivity().loadToolBar(toolbar, "Profilo"); //Load the fragments toolbar

        email = v.findViewById(R.id.emailTxt);
        pass = v.findViewById(R.id.passTxt);

        loginButton = v.findViewById(R.id.loginBtn);

        fbLoginButton = v.findViewById(R.id.fbLogin);

        fbLoginButton.setReadPermissions(Arrays.asList(EMAIL));
        fbLoginButton.setFragment(this);

        fbLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            /**
             * The success scenario of the Facebook login button
             * @param loginResult
             */
            @Override
            public void onSuccess(LoginResult loginResult) {
                //Handle success
                Toast.makeText(getActivity(), "Login success" ,Toast.LENGTH_LONG).show();
            }

            /**
             * The Facebook login has been canceled
             */
            @Override
            public void onCancel() {
                //Handle failure
                Toast.makeText(getActivity(), "Login canceled" ,Toast.LENGTH_LONG).show();
            }

            /**
             * An error occoured while trying to login with facebook
             * @param error
             */
            @Override
            public void onError(FacebookException error) {
                //Handle error
                Toast.makeText(getActivity(), "Login error" ,Toast.LENGTH_LONG).show();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            /**
             * on click of the login button we validate the
             * email and password and after we request a authentication
             * to the provided API
             * @param view
             */
            @Override
            public void onClick(View view) {
                String userEmail = email.getText().toString().trim();
                String userPass = pass.getText().toString().trim();

                if(!userEmail.isEmpty() && !userPass.isEmpty()){
                    DataService.getInstance().logIn(userEmail, userPass);
                }else{
                    Toast.makeText(getContext(), "Please input a valid email and password", Toast.LENGTH_LONG).show();
                }
            }
        });

        return v;
    }

    /**
     * Once the server resonds we implementour own callback methods
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

}
