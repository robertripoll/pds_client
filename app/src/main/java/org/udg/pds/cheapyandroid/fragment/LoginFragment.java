package org.udg.pds.cheapyandroid.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.activity.AddUser;
import org.udg.pds.cheapyandroid.activity.Login;
import org.udg.pds.cheapyandroid.activity.UsuariValid;
import org.udg.pds.cheapyandroid.entity.User;
import org.udg.pds.cheapyandroid.entity.UserLogin;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    CheapyApi mCheapyService;

    private EditText etLoginUsername;
    private EditText etLoginPassword;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);

        Button b = (Button)view.findViewById(R.id.login_button);
        etLoginUsername = (EditText)view.findViewById(R.id.login_username);
        etLoginPassword = (EditText)view.findViewById(R.id.login_password);

        // Link per donar-se d'alta
        TextView link = (TextView)view.findViewById(R.id.link_signup);

        // This is teh listener that will be used when the user presses the "Login" button
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LoginFragment.this.checkCredentials(etLoginUsername.getText().toString(), etLoginPassword.getText().toString());
            }
        });

        link.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Login.this.startActivity(new Intent(Login.this, AddUser.class));
            }
        });


        return view;
    }

    // This method is called when the "Login" button is pressed in the Login fragment
    public void checkCredentials(String username, String password) {
        UserLogin ul = new UserLogin();
        ul.correu = username;
        ul.contrasenya = password;


        mCheapyService = ((CheapyApp)getActivity().getApplication()).getAPI();

        Call<User> call = mCheapyService.login(ul);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                switch (response.code()) {
                    case 200:
                        //Login.this.startActivity(new Intent(Login.this, UsuariValid.class));
                        break;
                    case 401:

                        break;
                    default:
                        Toast toast = Toast.makeText(getActivity(), "Error logging in " , Toast.LENGTH_SHORT);
                        toast.show();
                        break;
                }


            }

            // Invoked when a network exception occurred talking to the server or when an unexpected exception
            // occurred creating the request or processing the response.
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast toast = Toast.makeText(getActivity(), "Error logging in"+t.toString(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

}
