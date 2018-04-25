package org.udg.pds.cheapyandroid.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.entity.User;
import org.udg.pds.cheapyandroid.entity.UserLogin;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import org.udg.pds.cheapyandroid.util.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.udg.pds.cheapyandroid.activity.LlistaProductesActivity.PREFS_NAME;

/**
 * Created with IntelliJ IDEA.
 * User: luisleon1894
 * Date: 18/03/13
 * Time: 10:28
 * To change this template use File | Settings | File Templates.
 */

// This is the Login fragment where the user enters the username and password and
// then a RESTResponder_RF is called to check the authentication
public class Login extends Activity {

    public static int userID_connected;
    CheapyApi mCheapyService;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mCheapyService = ((CheapyApp)this.getApplication()).getAPI();

        Button b = (Button)findViewById(R.id.login_button);

        // Link per donar-se d'alta
        TextView link = (TextView) findViewById(R.id.link_signup);

        // This is teh listener that will be used when the user presses the "Login" button
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText u = (EditText) Login.this.findViewById(R.id.login_username);
                EditText p = (EditText) Login.this.findViewById(R.id.login_password);
                Login.this.checkCredentials(u.getText().toString(), p.getText().toString());
            }
        });

        link.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Login.this.startActivity(new Intent(Login.this, AddUser.class));
            }
         });

    }
    // This method is called when the "Login" button is pressed in the Login fragment
    public void checkCredentials(final String username, final String password) {
        UserLogin ul = new UserLogin(username, password);

        Call<User> call = mCheapyService.login(ul);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.isSuccessful()) {
                    User usuari = response.body();
                    userID_connected=usuari.getId();
                    String user_name = String.valueOf(usuari.getNom());
                    String user_pass = String.valueOf(usuari.getContrasenya());
                    if(user_name.equals(username) && user_pass.equals(password)) {

                        //Afegeix noves preferencies per usuari i la seva contrasenya correctes
                        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("usuari_nom", username);
                        editor.putString("contrasenya_nom", password);
                        editor.commit();

                        Toast toast = Toast.makeText(Login.this, "Usuari OK", Toast.LENGTH_SHORT);
                        toast.show();
                        Login.this.startActivity(new Intent(Login.this, LlistaProductesActivity.class));
                    }
                    else{
                        Toast toast = Toast.makeText(Login.this, "Usuari o contrasenya no son correctes", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } else {
                    Toast toast = Toast.makeText(Login.this, "Error logging in", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast toast = Toast.makeText(Login.this, "Error logging in", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

}

