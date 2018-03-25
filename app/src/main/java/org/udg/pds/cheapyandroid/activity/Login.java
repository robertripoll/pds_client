package org.udg.pds.cheapyandroid.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.entity.User;
import org.udg.pds.cheapyandroid.entity.UserLogin;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    CheapyApi mCheapyService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mCheapyService = ((CheapyApp)this.getApplication()).getAPI();

        Button b = (Button)findViewById(R.id.login_button);
        // This is teh listener that will be used when the user presses the "Login" button
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText u = (EditText) Login.this.findViewById(R.id.login_username);
                EditText p = (EditText) Login.this.findViewById(R.id.login_password);
                Login.this.checkCredentials(u.getText().toString(), p.getText().toString());
            }
        });

    }
    // This method is called when the "Login" button is pressed in the Login fragment
    public void checkCredentials(String username, String password) {
        UserLogin ul = new UserLogin();
        ul.correu = username;
        ul.contrasenya = password;
        Call<User> call = mCheapyService.login(ul);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.isSuccessful()) {
                    Login.this.startActivity(new Intent(Login.this, NavigationActivity.class));
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

