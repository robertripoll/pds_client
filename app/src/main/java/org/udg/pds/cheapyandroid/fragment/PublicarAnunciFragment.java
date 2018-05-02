package org.udg.pds.cheapyandroid.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.entity.Categoria;
import org.udg.pds.cheapyandroid.entity.Category;
import org.udg.pds.cheapyandroid.entity.LlistaCategories;
import org.udg.pds.cheapyandroid.entity.LlistaProductes;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
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

    private List<Category> categories;


    public PublicarAnunciFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_publicar_anunci, container, false);

        // Carraguem el servei i les categories.
        mCheapyService = ((CheapyApp)getActivity().getApplication()).getAPI();
        carregarCategories();

        // Inicialitzem l'Spinner de categories.
        spinnerCategories = (Spinner)view.findViewById(R.id.spinner_categoria);
        crearOpcionsSpinnerCategories();



        return view;
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
