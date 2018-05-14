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
import org.udg.pds.cheapyandroid.entity.LlistaProductes;
import org.udg.pds.cheapyandroid.entity.Producte;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LlistaProductesPerfilCompresFragment extends Fragment {

    private CheapyApi mCheapyService;
    private ListView llistaProductesCompraPerfilView;
    private LlistaProductes llistaProductesCompraPerfil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_llista_productes_compra_perfil, container, false);

        mCheapyService = ((CheapyApp)getActivity().getApplication()).getAPI();

        llistaProductesCompraPerfilView = (ListView) view.findViewById(R.id.llista_productes);

        carregarProductesCompraPerfil();

        return view;
    }

    private void carregarProductesCompraPerfil() {

        Call<LlistaProductes> call = mCheapyService.getProductesCompraPerfil();
        call.enqueue(new Callback<LlistaProductes>() {
            @Override
            public void onResponse(Call<LlistaProductes> call, Response<LlistaProductes> response) {

                if (response.isSuccessful()) {
                    llistaProductesCompraPerfil = response.body();
                    mostrarProductes(llistaProductesCompraPerfil);
                } else {
                    Toast toast = Toast.makeText(getActivity(), "ERROR: No ha realitzat cap compra.", Toast.LENGTH_SHORT);
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

        final ArrayAdapter<Producte> itemsAdapter =
                new ArrayAdapter<Producte>(getActivity(), android.R.layout.activity_list_item, llistaProductesVendaPerfil.getItems());

        llistaProductesCompraPerfilView.setAdapter(new ListAdapter() {

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
                return llistaProductesCompraPerfil.getItems().size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return llistaProductesCompraPerfil.getItems().get(i).getId();
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getView(int position, View view, ViewGroup viewGroup) {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View rowView = inflater.inflate(R.layout.adapter_llista_productes_compra_perfil, null);

                TextView nomView = (TextView) rowView.findViewById(R.id.nom_producte);
                TextView preuView = (TextView) rowView.findViewById(R.id.preu_producte);

                Producte producte = llistaProductesCompraPerfil.getItems().get(position);

                nomView.setText(producte.getNom());
                preuView.setText(producte.getPreu().toString());


                TextView  clickProducte= (TextView) rowView.findViewById(R.id.nom_producte);
                // Cache row position inside the button using `setTag`
                clickProducte.setTag(position);
                // Attach the click event handler
                clickProducte.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = (Integer) view.getTag();
                        // Access the row position here to get the correct data item

                        //----> LES DUES LINIES SEGUENTS SERVEIXEN PER MOSTRAR QUIN PRODUCTE S'HA FET CLICK <-----//
                        Producte producteMostrar = itemsAdapter.getItem(position);
                        Toast.makeText(getActivity(), producteMostrar.getNom(), Toast.LENGTH_SHORT).show();

                        //Intent s'afegeix un parametre, un valor enter (posició del producte en la llista)
                        Intent intent = new Intent(getActivity(), ProducteInfo.class);
                        intent.putExtra("key_producte", position);
                        startActivity(intent);
                    }
                });

                return rowView;
            }

            @Override
            public int getItemViewType(int i) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return llistaProductesCompraPerfil.getItems().size();
            }

            @Override
            public boolean isEmpty() {
                return false;
            }


        });

    }
}

