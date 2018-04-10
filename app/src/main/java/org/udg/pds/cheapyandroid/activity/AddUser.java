package org.udg.pds.cheapyandroid.activity;

import android.app.*;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.Window;
import android.widget.*;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.entity.User;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import org.udg.pds.cheapyandroid.util.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Calendar;
import java.util.Date;

public class AddUser extends Activity implements Callback<User> {

    CheapyApi mCheapyService;
    
    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        if (response.isSuccessful()) {
            finish();
        } else {
            Toast.makeText(this, "Error adding new user", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);

        mCheapyService = ((CheapyApp)this.getApplication()).getAPI();


        Button s = (Button) findViewById(R.id.signup_button);
        // When the "Save" button is pressed, we make the call to the responder
        s.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText correu = (EditText) AddUser.this.findViewById(R.id.signup_email);
                EditText contrasenya = (EditText) AddUser.this.findViewById(R.id.signup_password);
                EditText sexe = (EditText) AddUser.this.findViewById(R.id.signup_sex);
                EditText nom = (EditText) AddUser.this.findViewById(R.id.signup_name);
                EditText cognoms = (EditText) AddUser.this.findViewById(R.id.signup_surname);
                EditText telefon = (EditText) AddUser.this.findViewById(R.id.signup_telephone);
                EditText dataNaixament = (EditText) AddUser.this.findViewById(R.id.signup_birthdate);
                try {
                    User u = new User(correu.getText().toString(), contrasenya.getText().toString(), sexe.getText().toString(), nom.getText().toString(), cognoms.getText().toString(), Integer.parseInt(telefon.getText().toString()), dataNaixament.getText().toString());
                    Call<User> call = mCheapyService.addUser(u);
                    call.enqueue(AddUser.this);
                } catch (Exception ex) {
                    Toast toast = Toast.makeText(AddUser.this, "Error add user "+ex.toString(), Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
            }
        });

    }
}
