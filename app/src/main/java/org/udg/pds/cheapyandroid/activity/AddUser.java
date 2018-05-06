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

        final EditText correu = (EditText) AddUser.this.findViewById(R.id.signup_email);
        final EditText contrasenya = (EditText) AddUser.this.findViewById(R.id.signup_password);
        final EditText sexe = (EditText) AddUser.this.findViewById(R.id.signup_sex);
        final EditText nom = (EditText) AddUser.this.findViewById(R.id.signup_name);
        final EditText cognoms = (EditText) AddUser.this.findViewById(R.id.signup_surname);
        final EditText telefon = (EditText) AddUser.this.findViewById(R.id.signup_telephone);
        final EditText dataNaixament = (EditText) AddUser.this.findViewById(R.id.signup_birthdate);




        Button s = (Button) findViewById(R.id.signup_button);
        // When the "Save" button is pressed, we make the call to the responder
        s.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final String correu_ = correu.getText().toString();
                final String contrasenya_ = contrasenya.getText().toString();
                final String sexe_ = sexe.getText().toString();
                final String nom_ = nom.getText().toString();
                final String cognoms_ = cognoms.getText().toString();
                final String telefon__ = telefon.getText().toString();
                final String dataNaixament_ = dataNaixament.getText().toString();

                if (correu_.matches("") || contrasenya_.matches("") || sexe_.matches("") || nom_.matches("") || cognoms_.matches("") || telefon__.toString().matches("") || dataNaixament_.matches("")) {
                    Toast.makeText(AddUser.this, "Emplena tots els camps, siusplau", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    try {

                        Integer telefon_ = Integer.parseInt(telefon.getText().toString());
                        User u = new User(correu_, contrasenya_, sexe_, nom_, cognoms_, telefon_, dataNaixament_);
                        Call<User> call = mCheapyService.addUser(u);
                        call.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                Toast toast = Toast.makeText(AddUser.this, "Nou usuari enviat correctament.", Toast.LENGTH_SHORT);
                                toast.show();
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Toast toast = Toast.makeText(AddUser.this, "Error al fer una nova compte " + t.toString(), Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        });

                    } catch (Exception ex) {
                        Toast toast = Toast.makeText(AddUser.this, "Error add user ", Toast.LENGTH_SHORT);
                        toast.show();
                        return;
                    }
                }

            }
        });

    }
}
