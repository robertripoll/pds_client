package org.udg.pds.cheapyandroid.activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.entity.Categoria;
import org.udg.pds.cheapyandroid.entity.Producte;
import org.udg.pds.cheapyandroid.entity.ProducteCrear;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import org.udg.pds.cheapyandroid.util.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class EditarProductePerfilVenda extends AppCompatActivity {

    private CheapyApi mCheapyService;
    Producte producte;

    private final static String TAG = "ProducteInfo";
    private static DecimalFormat df2 = new DecimalFormat("#.00");

    private Spinner spinnerCategories;
    private Button botoDesarCanvis;
    private EditText editNomProducte;
    private EditText editDescProducte;
    private EditText editPreuProducte;
    private CheckBox cbIntercanvi;
    private CheckBox cbPreuNegociable;
    private TextView tvError;

    private Long editIdProducte;

    private List<Categoria> categories;

    private Long _user_id;

    @Override
    protected void  onCreate(Bundle savedInstanceState) {

        //Obtenir producte clicat per saber l'id del producte que modificarem
        producte = (Producte) getIntent().getSerializableExtra("Producte");
        editIdProducte = producte.getId();

        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_producte_editar);

        // Busquem els components de la vista.
        spinnerCategories = (Spinner) findViewById(R.id.spinner_categoria);
        botoDesarCanvis = (Button) findViewById(R.id.button_desar_canvis);
        editNomProducte = (EditText) findViewById(R.id.et_nom_producte);
        editDescProducte = (EditText) findViewById(R.id.et_desc_producte);
        editPreuProducte = (EditText) findViewById(R.id.et_preu_producte);
        cbIntercanvi = (CheckBox) findViewById(R.id.cb_intercanvi);
        cbPreuNegociable = (CheckBox) findViewById(R.id.cb_preu_negociable);
        tvError = (TextView) findViewById(R.id.tv_error_falten_camps_desar_canvis);

        // Llegeix l'usuari actual que hi ha a l'app
        SharedPreferences prefs = getSharedPreferences(Global.PREFS_NAME, Context.MODE_PRIVATE);
        _user_id = prefs.getLong("usuari_id", -1);

        // Posem l'error com a no visible.
        tvError.setVisibility(View.GONE);

        // Carreguem el servei i les categories.
        mCheapyService = ((CheapyApp) getApplication()).getAPI();
        carregarCategories();

        // Configurem l'acció del botó publicar.
        botoDesarCanvis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                desarCanvis();
            }
        });

    }

    private void mostrarDades() {

        // Carrega Info
        editNomProducte.setText(producte.getNom());
        editPreuProducte.setText(df2.format(producte.getPreu()));
        editDescProducte.setText(producte.getDescripcio());
        cbIntercanvi.setChecked(producte.getIntercanviAcceptat());
        cbPreuNegociable.setChecked(producte.getPreuNegociable());

        if (categories != null) {
            int iCategoria = -1;
            int i = 0;
            boolean trobat = false;
            while (i < categories.size() && !trobat) {
                if (categories.get(i).getId().equals(producte.getCategoria().getId())) {
                    trobat = true;
                    iCategoria = i;
                }
                i++;
            }

            if (iCategoria != -1)
                spinnerCategories.setSelection(iCategoria);
        }
    }

    private void desarCanvis() {
        String nom = editNomProducte.getText().toString();
        String desc = editDescProducte.getText().toString();
        String preuTxt = editPreuProducte.getText().toString();
        Integer iCategoria = spinnerCategories.getSelectedItemPosition();
        Boolean intercanvi = cbIntercanvi.isChecked();
        Boolean negociable = cbPreuNegociable.isChecked();

        if (stringNullOrEmpty(nom) || stringNullOrEmpty(desc) || stringNullOrEmpty(preuTxt) || iCategoria == 0) {
            tvError.setVisibility(View.VISIBLE);
        } else {
            tvError.setVisibility(View.GONE);
            iCategoria = iCategoria - 1; // A la primera posició hi ha el "Selecciona una categoria...".

            // No hi ha error, creem el producte i fem el POST.
            ProducteCrear producte = new ProducteCrear();
            producte.setNom(nom);
            producte.setDescripcio(desc);
            producte.setPreu(Double.parseDouble(preuTxt));
            Integer categoria = Integer.parseInt(categories.get(iCategoria).getId().toString());
            producte.setCategoria(categoria);
            producte.setPreuNegociable(negociable);
            producte.setIntercanviAcceptat(intercanvi);

            // Fem el POST.
            postProducteEditat(producte);
        }
    }

    private void postProducteEditat(ProducteCrear producteEditar) {
        Long producteId = producte.getId();
        Call<Void> call = mCheapyService.updateProductInformation(producteId, producteEditar);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast toast = Toast.makeText(EditarProductePerfilVenda.this, "Producte editat correctament.", Toast.LENGTH_SHORT);
                    toast.show();

                    Intent intent = new Intent(EditarProductePerfilVenda.this, LlistaProductesActivity.class);

                    startActivity(intent);

                } else {
                    Toast toast = Toast.makeText(EditarProductePerfilVenda.this, "Error editant el producte.", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast toast = Toast.makeText(EditarProductePerfilVenda.this, "ERROR: Revisa la connexió a Internet.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }


    private Boolean stringNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    private void crearOpcionsSpinnerCategories() {
        ArrayAdapter<String> adapterCategories = new ArrayAdapter<>(EditarProductePerfilVenda.this, R.layout.support_simple_spinner_dropdown_item);
        adapterCategories.add("Selecciona una Categoria...");

        if (categories != null) {
            for (Categoria categoria : categories) {
                adapterCategories.add(categoria.getNom());
            }
        }

        spinnerCategories.setAdapter(adapterCategories);
        mostrarDades();
    }

    private void carregarCategories() {
        Call<ArrayList<Categoria>> call = mCheapyService.getCategories();
        call.enqueue(new Callback<ArrayList<Categoria>>() {
            @Override
            public void onResponse(Call<ArrayList<Categoria>> call, Response<ArrayList<Categoria>> response) {

                if (response.isSuccessful()) {
                    List<Categoria> llista = response.body();
                    categories = llista;
                    crearOpcionsSpinnerCategories();
                } else {
                    Toast toast = Toast.makeText(EditarProductePerfilVenda.this, "ERROR: Les categories no s'han pogut carregar correctament", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Categoria>> call, Throwable t) {
                Toast toast = Toast.makeText(EditarProductePerfilVenda.this, "ERROR: Revisa la connexió a Internet.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

}
