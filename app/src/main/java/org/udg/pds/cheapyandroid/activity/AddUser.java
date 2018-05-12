package org.udg.pds.cheapyandroid.activity;

import android.app.*;
import android.content.Intent;
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
import org.udg.pds.cheapyandroid.entity.Ubicacio;
import org.udg.pds.cheapyandroid.entity.User;
import org.udg.pds.cheapyandroid.entity.UserLogged;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import org.udg.pds.cheapyandroid.util.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.nio.DoubleBuffer;
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
        final EditText pais = (EditText) AddUser.this.findViewById(R.id.signup_pais);
        final EditText ciutat = (EditText) AddUser.this.findViewById(R.id.signup_ciutat);
        final EditText coordLat = (EditText) AddUser.this.findViewById(R.id.signup_coordLat);
        final EditText coordLng = (EditText) AddUser.this.findViewById(R.id.signup_coordLng);



        Button s = (Button) findViewById(R.id.signup_button);
        // When the "Save" button is pressed, we make the call to the responder
        s.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final String correu_ = correu.getText().toString();
                final String contrasenya_ = contrasenya.getText().toString();
                final String sexe_ = sexe.getText().toString();
                final String nom_ = nom.getText().toString();
                final String cognoms_ = cognoms.getText().toString();
                final String telefon_ = telefon.getText().toString();
                final String dataNaixament_ = dataNaixament.getText().toString();
                final String pais_ = pais.getText().toString();
                final String ciutat_ = ciutat.getText().toString();
                final Double coordLat_ = Double.valueOf(coordLat.getText().toString());
                final Double coordLng_ = Double.valueOf(coordLng.getText().toString());

                if (correu_.matches("") || contrasenya_.matches("") || sexe_.matches("") || nom_.matches("") || cognoms_.matches("") || telefon_.toString().matches("") || dataNaixament_.matches("")) {
                    Toast.makeText(AddUser.this, "Emplena tots els camps, siusplau", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    try {

                        User u = new User(correu_, contrasenya_, sexe_, nom_, cognoms_, telefon_, dataNaixament_, new Ubicacio(pais_, ciutat_, coordLat_, coordLng_));
                        Call<UserLogged> call = mCheapyService.addUser(u);
                        call.enqueue(new Callback<UserLogged>() {
                            @Override
                            public void onResponse(Call<UserLogged> call, Response<UserLogged> response) {
                                Toast toast = Toast.makeText(AddUser.this, "Nou usuari registrat correctament.", Toast.LENGTH_SHORT);
                                toast.show();
                                AddUser.this.startActivity(new Intent(AddUser.this, LlistaProductesActivity.class));
                            }

                            @Override
                            public void onFailure(Call<UserLogged> call, Throwable t) {
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
