package org.udg.pds.cheapyandroid.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.entity.LlistaProductes;
import org.udg.pds.cheapyandroid.entity.Producte;
import org.udg.pds.cheapyandroid.fragment.LlistaProductesPerfilFragment;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LlistaProductesVendaPerfil extends AppCompatActivity {

    CheapyApi mCheapyService;
    Producte producte;
    String user_, pass_;
    private DrawerLayout mDrawerLayout;

    public static final String PREFS_NAME = "MisPreferencias";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mCheapyService = ((CheapyApp)this.getApplication()).getAPI();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Llegeix l'usuari actual que hi ha a l'app
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        user_ = prefs.getString("usuari_nom", "usuari_prova"); //getString(identificador, default)
        pass_ = prefs.getString("contrasenya_nom", "contrasenya_prova"); //getString(identificador, default)

        // Carrega els productes
        carregarProductesVendaPerfil(); // carrega els productes que l'usuari te a la venda

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void carregarProductesVendaPerfil() {

        Call<LlistaProductes> call = mCheapyService.getProductesVendaPerfil();
        call.enqueue(new Callback<LlistaProductes>() {
            @Override
            public void onResponse(Call<LlistaProductes> call, Response<LlistaProductes> response) {

                if (response.isSuccessful()) {
                    LlistaProductes llistaProductes = response.body();
                    Fragment fragment = new LlistaProductesPerfilFragment(llistaProductes);
                    FragmentTransaction fragmentManager = getSupportFragmentManager().beginTransaction();
                    fragmentManager.replace(R.id.frame_layout, fragment);
                    fragmentManager.commit();

                } else {
                    Toast toast = Toast.makeText(LlistaProductesVendaPerfil.this, "ERROR: No té productes a la venta.", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<LlistaProductes> call, Throwable throwable) {
                Toast toast = Toast.makeText(LlistaProductesVendaPerfil.this, "ERROR: Revisa la connexió a Internet.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}