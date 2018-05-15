package org.udg.pds.cheapyandroid.fragment;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.activity.ProducteInfo;
import org.udg.pds.cheapyandroid.adapters.LlistaProductesAdapter;
import org.udg.pds.cheapyandroid.entity.LlistaProductes;
import org.udg.pds.cheapyandroid.entity.Producte;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LlistaProductesPerfilVendesFragment extends Fragment {

    private CheapyApi mCheapyService;
    private ListView llistaProductesView;
    private LlistaProductesAdapter adapterLlistaProductes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_llista_productes, container, false);

        mCheapyService = ((CheapyApp)getActivity().getApplication()).getAPI();

        llistaProductesView = (ListView) view.findViewById(R.id.llista_productes);
        inicialitzaLlista();

        carregarProductesVendaPerfil();

        return view;
    }

    private void inicialitzaLlista() {
        llistaProductesView.setClickable(true);
        llistaProductesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Producte producte = adapterLlistaProductes.getItem(i);
                Intent intent = new Intent(getActivity(), ProducteInfo.class);
                intent.putExtra("Producte", producte);
                startActivity(intent);
            }
        });
    }

    private void carregarProductesVendaPerfil() {

        Call<LlistaProductes> call = mCheapyService.getProductesVendaPerfil();
        call.enqueue(new Callback<LlistaProductes>() {
            @Override
            public void onResponse(Call<LlistaProductes> call, Response<LlistaProductes> response) {

                if (response.isSuccessful()) {
                    mostrarProductes(response.body());
                } else {
                    Toast toast = Toast.makeText(getActivity(), "ERROR: No té productes a la venta.", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<LlistaProductes> call, Throwable throwable) {
                Toast toast = Toast.makeText(getActivity(), "ERROR: Revisa la connexió a Internet.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void mostrarProductes(final LlistaProductes llistaProductesVendaPerfil) {

        adapterLlistaProductes = new LlistaProductesAdapter(this.getActivity(), llistaProductesVendaPerfil);
        llistaProductesView.setAdapter(adapterLlistaProductes);

    }
}
