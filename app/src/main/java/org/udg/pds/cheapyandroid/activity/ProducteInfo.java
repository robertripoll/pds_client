package org.udg.pds.cheapyandroid.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.entity.LlistaProductes;
import org.udg.pds.cheapyandroid.entity.Producte;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ProducteInfo extends AppCompatActivity {

    CheapyApi mCheapyService;
    Producte producte;

    private final static String TAG = "ProducteInfo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //value = posicio del producte en la llista de productes que es venen
        int value = -1; // or other values
        int defaultValue = 0;
        value = getIntent().getIntExtra("key_producte", defaultValue);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producte_info);
        mCheapyService = ((CheapyApp)this.getApplication()).getAPI();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //PART DEL CODI SEGUENT AMB UNA BDD no faria falta tornar a fer aquesta consulta a l'API
        //******************************************************************************
        Call<LlistaProductes> call = mCheapyService.getProductes();
        final int finalValue = value;
        call.enqueue(new Callback<LlistaProductes>() {
            @Override
            public void onResponse(Call<LlistaProductes> call, Response<LlistaProductes> response) {

                if (response.isSuccessful()) {
                    LlistaProductes llistaProductes = response.body();
                    producte = llistaProductes.getProductes().get(finalValue);
                    //Toast toast = Toast.makeText(ProducteInfo.this, producte.getProducte().getImatges().get(finalValue).getImatge().getRuta(), Toast.LENGTH_SHORT);
                    Toast toast = Toast.makeText(ProducteInfo.this, String.valueOf(finalValue), Toast.LENGTH_SHORT);
                    toast.show();
                    mostrarProducte();

                } else {
                    Toast toast = Toast.makeText(ProducteInfo.this, "Error alhora de buscar els productes", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            private void mostrarProducte() {

                //Carrega Imatge
                ImageView viewProducte = (ImageView)findViewById(R.id.imageViewplaces);
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                try {
                    URL url = new URL(producte.getProducte().getImatges().get(0).getImatge().getRuta());
                    viewProducte.setImageBitmap(BitmapFactory.decodeStream((InputStream)url.getContent()));
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage());
                }

                //Carrega Info
                TextView viewInfoProducte = (TextView)findViewById(R.id.infoProducte);
                viewInfoProducte.setText(producte.getProducte().toString());
            }

            @Override
            public void onFailure(Call<LlistaProductes> call, Throwable t) {
                Toast toast = Toast.makeText(ProducteInfo.this, "ERROR: Revisa la connexió a Internet.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        //******************************************************************************

    }
}
