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
                EditText email = (EditText) AddUser.this.findViewById(R.id.signup_email);
                EditText pass = (EditText) AddUser.this.findViewById(R.id.signup_password);
                EditText sex = (EditText) AddUser.this.findViewById(R.id.signup_sex);
                EditText name = (EditText) AddUser.this.findViewById(R.id.signup_name);
                EditText surname = (EditText) AddUser.this.findViewById(R.id.signup_surname);
                EditText telephone = (EditText) AddUser.this.findViewById(R.id.signup_telephone);
                EditText birthdate = (EditText) AddUser.this.findViewById(R.id.signup_birthdate);
                try {
                    User u = new User(name.toString(), surname.toString(), birthdate.toString(), email.toString(), pass.toString(), sex.toString(), Double.parseDouble(telephone.toString()));
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
