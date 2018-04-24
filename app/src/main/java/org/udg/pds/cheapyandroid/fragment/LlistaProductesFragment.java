package org.udg.pds.cheapyandroid.fragment;


import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.*;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.activity.Login;
import org.udg.pds.cheapyandroid.activity.ProducteInfo;
import org.udg.pds.cheapyandroid.entity.LlistaProductes;
import org.udg.pds.cheapyandroid.entity.Producte;
import org.udg.pds.cheapyandroid.entity.Producte_;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LlistaProductesFragment extends Fragment {


    private CheapyApi mCheapyService;
    private ListView llistaProductesView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_llista_productes, container, false);

        mCheapyService = ((CheapyApp)getActivity().getApplication()).getAPI();

        llistaProductesView = (ListView) view.findViewById(R.id.llista_productes);

        carregarProductes();

        return view;
    }

    private void carregarProductes() {
        Call<LlistaProductes> call = mCheapyService.getProductes();
        call.enqueue(new Callback<LlistaProductes>() {
            @Override
            public void onResponse(Call<LlistaProductes> call, Response<LlistaProductes> response) {

                if (response.isSuccessful()) {
                    mostrarProductes(response.body());
                } else {
                    Toast toast = Toast.makeText(getActivity(), "ERROR: Els productes no s'han pogut carregar correctament", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<LlistaProductes> call, Throwable t) {
                Toast toast = Toast.makeText(getActivity(), "ERROR: Revisa la connexió a Internet.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void mostrarProductes(final LlistaProductes llistaProductes) {

        final ArrayAdapter<Producte> itemsAdapter =
                new ArrayAdapter<Producte>(getActivity(), android.R.layout.activity_list_item,llistaProductes.getProductes());


        llistaProductesView.setAdapter(new ListAdapter() {

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
                return llistaProductes.getProductes().size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return llistaProductes.getProductes().get(i).getProducte().getId();
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getView(int position, View view, ViewGroup viewGroup) {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View rowView = inflater.inflate(R.layout.adapter_llista_productes, null);

                TextView nomView = (TextView) rowView.findViewById(R.id.nom_producte);
                TextView preuView = (TextView) rowView.findViewById(R.id.preu_producte);

                Producte_ producte = llistaProductes.getProductes().get(position).getProducte();

                nomView.setText(producte.getNom());
                preuView.setText(producte.getPreu().toString());


                //Mostra informacio del producte quan fas click al nom del producte
                //Hauria de mostra imatge del producte i quan es fes click llavors mostrar informacio
                TextView  clickProducte= (TextView) rowView.findViewById(R.id.nom_producte);
                // Cache row position inside the button using `setTag`
                clickProducte.setTag(position);
                // Attach the click event handler
                clickProducte.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = (Integer) view.getTag();
                        // Access the row position here to get the correct data item

                        //----> LES DUES LINIEAS SEGUENTS SERVEIXEN PER MOSTRAR QUIN PRODUCTE S'HA FET CLICK <-----//
                        Producte producteMostrar = itemsAdapter.getItem(position);
                        Toast.makeText(getActivity(), producteMostrar.getProducte().getNom(), Toast.LENGTH_SHORT).show();

                        //Intent s'afegeix un parametre, un valor enter (posició del producte en la llista)
                        Intent intent = new Intent(getActivity(), ProducteInfo.class);
                        intent.putExtra("Producte", producteMostrar);
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
                return llistaProductes.getProductes().size();
            }

            @Override
            public boolean isEmpty() {
                return false;
            }


        });

    }
}
