package org.udg.pds.cheapyandroid.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import org.udg.pds.cheapyandroid.util.Global;

public class Presentation extends AppCompatActivity {

    CheapyApi mCheapyService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);

        mCheapyService = ((CheapyApp)this.getApplication()).getAPI();

        //Botó per iniciar sessió
        Button l = (Button)findViewById(R.id.login_button);
        //Botó per donar-se d'alta
        Button s = (Button)findViewById(R.id.signup_button);
        //Botó per continuar com convidat
        Button i = (Button)findViewById(R.id.invited_button);


        // This is teh listener that will be used when the user presses the "Login" button
        l.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Presentation.this.startActivity(new Intent(Presentation.this, Login.class));
            }
        });

        // This is teh listener that will be used when the user presses the "Sign up" button
        s.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Presentation.this, AddUser.class);
                startActivityForResult(i, Global.RQ_ADD_TASK);
            }
        });

        // This is teh listener that will be used when the user presses the "Invited" button
        i.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //no comprova la compte i directament obre l'app, UsuariValid hauria de ser la classe de mostrar els productes
                Presentation.this.startActivity(new Intent(Presentation.this, UsuariValid.class));
            }
        });

    }
}
