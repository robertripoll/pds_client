package org.udg.pds.cheapyandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.entity.Producte;
import org.udg.pds.cheapyandroid.rest.CheapyApi;

import java.io.Serializable;
import java.text.DecimalFormat;

public class ProducteInfo extends AppCompatActivity {

    CheapyApi mCheapyService;
    Producte producte;

    private final static String TAG = "ProducteInfo";
    private static DecimalFormat df2 = new DecimalFormat("#.00");

    // Components visuals
    private FloatingActionButton btnMissatge;
    private FloatingActionButton btnEditar;
    private TextView tvNomProducte;
    private TextView tvPreuProducte;
    private TextView tvDescProducte;
    private TextView tvUbicacioProducte;
    private TextView tvUsuariProducte;
    private TextView tvIntercanvi;
    private TextView tvPreuNegociable;
    private TextView tvCategoriaProducte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        producte = (Producte) getIntent().getSerializableExtra("Producte");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producte_info);

        mCheapyService = ((CheapyApp)this.getApplication()).getAPI();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnMissatge = (FloatingActionButton) findViewById(R.id.botoMissatge);
        btnEditar = (FloatingActionButton) findViewById(R.id.botoEditarProducte);
        tvNomProducte = (TextView) findViewById(R.id.nom_producte);
        tvPreuProducte = (TextView) findViewById(R.id.preu_producte);
        tvUbicacioProducte = (TextView) findViewById(R.id.ubicacio_producte);
        tvDescProducte = (TextView) findViewById(R.id.desc_producte);
        tvUsuariProducte = (TextView) findViewById(R.id.usuari_producte);
        tvIntercanvi = (TextView) findViewById(R.id.cb_intercanvi);
        tvPreuNegociable = (TextView) findViewById(R.id.cb_preu_negociable);
        tvCategoriaProducte = (TextView) findViewById(R.id.categoria_producte);

        inicialitzaBotoMissatge();

        mostrarProducte();

    }


    private void inicialitzaBotoMissatge() {
        btnMissatge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Login.userID_connected == Login.NO_REGISTRAT) {
                    Toast toast = Toast.makeText(ProducteInfo.this, "Has d'estar registrat." , Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    Intent intent = new Intent(ProducteInfo.this, Conversa.class);
                    intent.putExtra("Producte", (Serializable) producte);

                    startActivity(intent);
                }
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Login.userID_connected == Login.NO_REGISTRAT) {
                    Toast toast = Toast.makeText(ProducteInfo.this, "Has d'estar registrat." , Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    Intent intent = new Intent(ProducteInfo.this, EditarProductePerfilVenda.class);
                    intent.putExtra("Producte", (Serializable) producte);

                    startActivity(intent);
                }
            }
        });
    }

    private void mostrarProducte() {

        //Carrega Imatge
        /*
        ImageView viewProducte = (ImageView)findViewById(R.id.imageViewplaces);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            URL url = new URL(producte.getImatges().get(0).getImatge().getRuta());
            viewProducte.setImageBitmap(BitmapFactory.decodeStream((InputStream)url.getContent()));
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        */

        // Carrega Info
        tvNomProducte.setText(producte.getNom());
        tvPreuProducte.setText(df2.format(producte.getPreu()) + " €");
        tvUbicacioProducte.setText(producte.getVenedor().getUbicacio().getCiutat() + ", " + producte.getVenedor().getUbicacio().getPais());
        tvDescProducte.setText(producte.getDescripcio());
        tvUsuariProducte.setText(producte.getVenedor().getNom());
        tvCategoriaProducte.setText(producte.getCategoria().getNom());

        tvPreuNegociable.setText("Preu Negociable: " + (producte.getPreuNegociable() ? "Sí" : "No"));
        tvIntercanvi.setText("Intercanvi Acceptat: " + (producte.getIntercanviAcceptat() ? "Sí" : "No"));
    }
}
