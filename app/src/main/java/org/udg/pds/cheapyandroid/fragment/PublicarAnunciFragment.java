package org.udg.pds.cheapyandroid.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.*;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.activity.LlistaProductesActivity;
import org.udg.pds.cheapyandroid.entity.*;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import org.udg.pds.cheapyandroid.util.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PublicarAnunciFragment extends Fragment {

    private CheapyApi mCheapyService;

    private Spinner spinnerCategories;
    private Button buttonPublicar;
    private EditText editNomProducte;
    private EditText editDescProducte;
    private EditText editPreuProducte;
    private CheckBox cbIntercanvi;
    private CheckBox cbPreuNegociable;
    private TextView tvError;

    private List<Category> categories;

    private String _user_id;


    public PublicarAnunciFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_publicar_anunci, container, false);

        // Busquem els components de la vista.
        spinnerCategories = (Spinner)view.findViewById(R.id.spinner_categoria);
        buttonPublicar = (Button)view.findViewById(R.id.button_publicar);
        editNomProducte = (EditText)view.findViewById(R.id.et_nom_producte);
        editDescProducte = (EditText)view.findViewById(R.id.et_desc_producte);
        editPreuProducte = (EditText)view.findViewById(R.id.et_preu_producte);
        cbIntercanvi = (CheckBox)view.findViewById(R.id.cb_intercanvi);
        cbPreuNegociable = (CheckBox)view.findViewById(R.id.cb_preu_negociable);
        tvError = (TextView)view.findViewById(R.id.tv_error_falten_camps_publicar_anunci);

        // Llegeix l'usuari actual que hi ha a l'app
        SharedPreferences prefs = getActivity().getSharedPreferences(Global.PREFS_NAME, Context.MODE_PRIVATE);
        _user_id = prefs.getString("usuari_id", "0");

        // Posem l'error com a no visible.
        tvError.setVisibility(View.GONE);

        // Carraguem el servei i les categories.
        mCheapyService = ((CheapyApp)getActivity().getApplication()).getAPI();
        carregarCategories();

        // Inicialitzem l'Spinner de categories.
        crearOpcionsSpinnerCategories();

        // Configurem l'acció del botó publicar.
        buttonPublicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                publicarAnunci();
            }
        });

        return view;
    }

    private void publicarAnunci() {
        String nom = editNomProducte.getText().toString();
        String desc = editDescProducte.getText().toString();
        String preuTxt = editPreuProducte.getText().toString();
        Integer iCategoria = spinnerCategories.getSelectedItemPosition();
        Boolean intercanvi = cbIntercanvi.isChecked();
        Boolean negociable = cbPreuNegociable.isChecked();

        if (stringNullOrEmpty(nom) || stringNullOrEmpty(desc) || stringNullOrEmpty(preuTxt) || iCategoria == 0) {
            tvError.setVisibility(View.VISIBLE);
        }
        else {
            tvError.setVisibility(View.GONE);
            iCategoria = iCategoria - 1; // A la primera posició hi ha el "Selecciona una categoria...".

            // No hi ha error, creem el producte i fem el POST.
            Producte producte  = new Producte();
            Producte_ aux = new Producte_();
            aux.setNom(nom);
            aux.setDescripcio(desc);
            aux.setPreu(Double.parseDouble(preuTxt));
            aux.setCategoria(categories.get(iCategoria).getCategoria());
            aux.setPreuNegociable(negociable);
            aux.setIntercanviAcceptat(intercanvi);

            Venedor venedor = new Venedor();
            venedor.setId(Integer.parseInt(_user_id));
            aux.setVenedor(venedor);

            producte.setProducte(aux);

            // Fem el POST.
            postAnunciProducte(producte);
        }
    }

    private void postAnunciProducte(Producte producte) {
        Call<Void> call = mCheapyService.crearProducte(producte);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast toast = Toast.makeText(getActivity(), "Anunci enviat correctament.", Toast.LENGTH_SHORT);
                    toast.show();

                    LlistaProductesActivity activity = (LlistaProductesActivity)getActivity();
                    activity.carregarProductesALaVenda();

                } else {
                    Toast toast = Toast.makeText(getActivity(), "Error enviant anunci.", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast toast = Toast.makeText(getActivity(), "ERROR: Revisa la connexió a Internet.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private Boolean stringNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    private void crearOpcionsSpinnerCategories() {
        ArrayAdapter<String> adapterCategories = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item);
        adapterCategories.add("Selecciona una Categoria...");

        if (categories != null) {
            for (Category categoria : categories) {
                adapterCategories.add(categoria.getCategoria().getNom());
            }
        }

        spinnerCategories.setAdapter(adapterCategories);
    }

    private void carregarCategories() {
        Call<LlistaCategories> call = mCheapyService.getCategories();
        call.enqueue(new Callback<LlistaCategories>() {
            @Override
            public void onResponse(Call<LlistaCategories> call, Response<LlistaCategories> response) {

                if (response.isSuccessful()) {
                    LlistaCategories llista = response.body();
                    categories = llista.getCategories();
                    crearOpcionsSpinnerCategories();
                } else {
                    Toast toast = Toast.makeText(getActivity(), "ERROR: Les categories no s'han pogut carregar correctament", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<LlistaCategories> call, Throwable t) {
                Toast toast = Toast.makeText(getActivity(), "ERROR: Revisa la connexió a Internet.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

}
