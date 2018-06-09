package org.udg.pds.cheapyandroid.activity;

import android.app.*;
import android.content.*;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.Window;
import android.widget.*;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.MyFirebaseInstanceIDService;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.entity.Ubicacio;
import org.udg.pds.cheapyandroid.entity.User;
import org.udg.pds.cheapyandroid.entity.UserLogged;
import org.udg.pds.cheapyandroid.entity.UserLogin;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import org.udg.pds.cheapyandroid.util.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.nio.DoubleBuffer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.udg.pds.cheapyandroid.MyFirebaseInstanceIDService.TOKEN_RECEIVER;
import static org.udg.pds.cheapyandroid.activity.LlistaProductesActivity.PREFS_NAME;

public class AddUser extends Activity implements Callback<User> {


    CheapyApi mCheapyService;

    BroadcastReceiver tokenReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String token = intent.getStringExtra("token");
            enviarToken(token);
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver((tokenReceiver),
                new IntentFilter(MyFirebaseInstanceIDService.TOKEN_RECEIVER));
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(tokenReceiver);
    }
    
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

        LocalBroadcastManager.getInstance(this).registerReceiver(tokenReceiver,
                new IntentFilter(TOKEN_RECEIVER));

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
                final String dataNaixament_string = dataNaixament.getText().toString();
                final String pais_ = pais.getText().toString();
                final String ciutat_ = ciutat.getText().toString();
                Double coordLat_ = 0.0;
                Double coordLng_ = 0.0;
                if(!coordLat.getText().toString().matches("") && !coordLng.getText().toString().matches("")) {
                    coordLat_ = Double.valueOf(coordLat.getText().toString());
                    coordLng_ = Double.valueOf(coordLng.getText().toString());
                }

                if (correu_.matches("") || contrasenya_.matches("") || sexe_.matches("") || nom_.matches("") || cognoms_.matches("") || telefon_.toString().matches("") || !dataValida(dataNaixament_string) || dataNaixament_string.matches("")) {
                    Toast.makeText(AddUser.this, "Emplena tots els camps correctament, siusplau", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    try {

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date dataNaixament_ = sdf.parse(dataNaixament_string);

                        User u = new User(correu_, contrasenya_, User.Sexe.create(sexe_), nom_, cognoms_, telefon_, dataNaixament_, new Ubicacio(pais_, ciutat_, coordLat_, coordLng_));
                        Call<UserLogged> call = mCheapyService.addUser(u);
                        call.enqueue(new Callback<UserLogged>() {
                            @Override
                            public void onResponse(Call<UserLogged> call, Response<UserLogged> response) {

                                if (response.isSuccessful()) {
                                    UserLogged usuari = response.body();

                                    Toast toast = Toast.makeText(AddUser.this, "Nou usuari registrat correctament.", Toast.LENGTH_SHORT);
                                    toast.show();

                                    ferLogin(new UserLogin(usuari.getCorreu(), contrasenya_));

                                    MyFirebaseInstanceIDService myFireBaseInsID = new MyFirebaseInstanceIDService();
                                    myFireBaseInsID.onTokenRefresh();


                                    Login.userID_connected = usuari.getId();
                                    Login.userName_connected = usuari.getNom();
                                    Login.userCorreu_connected = usuari.getCorreu();
                                    Login.userSexe_connected = usuari.getSexe();
                                    String user_name = String.valueOf(usuari.getCorreu());

                                    //Afegeix noves preferencies per usuari i la seva contrasenya correctes
                                    SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = prefs.edit();
                                    editor.putLong("usuari_id", Login.userID_connected);
                                    editor.putString("usuari_nom", Login.userName_connected);
                                    editor.putString("usuari_correo", Login.userCorreu_connected);
                                    editor.commit();

                                    Login.logged = true;
                                    AddUser.this.startActivity(new Intent(AddUser.this, LlistaProductesActivity.class));

                                    finish();


                                }
                                else{
                                    Toast toast = Toast.makeText(AddUser.this, "Error al fer una nova compte " + response.toString(), Toast.LENGTH_SHORT);
                                    toast.show();
                                }
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

    private boolean dataValida(String dataNaixament_string) {

        return (dataNaixament_string.length() == 10 &&
                Character.isDigit(dataNaixament_string.charAt(0)) && Character.isDigit(dataNaixament_string.charAt(1)) &&
                Character.isDigit(dataNaixament_string.charAt(2)) && Character.isDigit(dataNaixament_string.charAt(3)) &&
                Character.isDigit(dataNaixament_string.charAt(5)) && Character.isDigit(dataNaixament_string.charAt(6)) &&
                Character.isDigit(dataNaixament_string.charAt(8)) && Character.isDigit(dataNaixament_string.charAt(9)) &&
                dataNaixament_string.charAt(4) == '-' && dataNaixament_string.charAt(7) == '-');
    }

    private void ferLogin(UserLogin userLogin) {

        Call<UserLogged> call = mCheapyService.login(userLogin);
        call.enqueue(new Callback<UserLogged>() {
            @Override
            public void onResponse(Call<UserLogged> call, Response<UserLogged> response) {

                if (response.isSuccessful()) {

                    Toast toast = Toast.makeText(AddUser.this, "Nou usuari creat i autenticat", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    Toast toast = Toast.makeText(AddUser.this, "ERROR: al autenticar el nou registre", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<UserLogged> call, Throwable t) {
                Toast toast = Toast.makeText(AddUser.this, "Error autenticar", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void enviarToken(String token) {

        mCheapyService = ((CheapyApp)this.getApplication()).getAPI();
        Call<Void> call = mCheapyService.sendToken(token);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if(response.isSuccessful()){
                    Toast toast = Toast.makeText(AddUser.this, "Token enviat OK", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    Toast toast = Toast.makeText(AddUser.this, "Token enviat ERROR", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Toast toast = Toast.makeText(AddUser.this, "Error, revisa la connexió a internet", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
