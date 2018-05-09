package org.udg.pds.cheapyandroid.fragment;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.entity.LlistaProductes;
import org.udg.pds.cheapyandroid.entity.Producte;
import org.udg.pds.cheapyandroid.entity.Producte_;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LlistaProductesPerfilVendesFragment extends Fragment {

    private CheapyApi mCheapyService;
    private ListView llistaProductesVendaPerfilView;
    private LlistaProductes llistaProductesVendaPerfil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_llista_productes_venda_perfil, container, false);

        mCheapyService = ((CheapyApp)getActivity().getApplication()).getAPI();

        llistaProductesVendaPerfilView = (ListView) view.findViewById(R.id.llista_productes);

        carregarProductesVendaPerfil();

        return view;
    }

    private void carregarProductesVendaPerfil() {

        Call<LlistaProductes> call = mCheapyService.getProductesVendaPerfil();
        call.enqueue(new Callback<LlistaProductes>() {
            @Override
            public void onResponse(Call<LlistaProductes> call, Response<LlistaProductes> response) {

                if (response.isSuccessful()) {
                    llistaProductesVendaPerfil = response.body();
                    mostrarProductes(llistaProductesVendaPerfil);
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

    private void mostrarProductes(final LlistaProductes llistaProductes) {

        final ArrayAdapter<Producte> itemsAdapter =
                new ArrayAdapter<Producte>(getActivity(), android.R.layout.activity_list_item,llistaProductes.getProductes());


        llistaProductesVendaPerfilView.setAdapter(new ListAdapter() {

            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }

            @Override
            public boolean isEnabled(int i) {
                return false;
            }

            @Override
            public void registerDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public int getCount() {
                return llistaProductesVendaPerfil.getProductes().size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return llistaProductesVendaPerfil.getProductes().get(i).getProducte().getId();
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getView(int position, View view, ViewGroup viewGroup) {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View rowView = inflater.inflate(R.layout.adapter_llista_productes_venda_perfil, null);

                TextView nomView = (TextView) rowView.findViewById(R.id.nom_producte);
                TextView preuView = (TextView) rowView.findViewById(R.id.preu_producte);

                Producte_ producte = llistaProductesVendaPerfil.getProductes().get(position).getProducte();

                nomView.setText(producte.getNom());
                preuView.setText(producte.getPreu().toString());




                return rowView;
            }

            @Override
            public int getItemViewType(int i) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return llistaProductesVendaPerfil.getProductes().size();
            }

            @Override
            public boolean isEmpty() {
                return false;
            }


        });

    }
}
