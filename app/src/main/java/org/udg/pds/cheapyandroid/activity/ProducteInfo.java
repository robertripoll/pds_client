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

        producte = (Producte) getIntent().getSerializableExtra("Producte");
        Toast toast = Toast.makeText(ProducteInfo.this, producte.getProducte().getNom(), Toast.LENGTH_SHORT);
        toast.show();

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


        mostrarProducte();
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
}
