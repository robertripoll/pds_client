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
import org.udg.pds.cheapyandroid.entity.Producte_;
import org.udg.pds.cheapyandroid.rest.CheapyApi;

public class LlistaProductesPerfilFragment extends Fragment {

        private CheapyApi mCheapyService;
        private ListView llistaProductesVendaPerfilView;
        private LlistaProductes llistaProductesVendaPerfil;

    public LlistaProductesPerfilFragment(LlistaProductes llistaProductes) {

        llistaProductesVendaPerfil = llistaProductes;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_llista_productes_venda_perfil, container, false);

        mCheapyService = ((CheapyApp)getActivity().getApplication()).getAPI();

        llistaProductesVendaPerfilView = (ListView) view.findViewById(R.id.llista_productes);

        mostrarProductes(llistaProductesVendaPerfil);

        return view;
    }


    private void mostrarProductes(final LlistaProductes llistaProductesVendaPerfil) {

        final ArrayAdapter<Producte> itemsAdapter =
                new ArrayAdapter<Producte>(getActivity(), android.R.layout.activity_list_item,llistaProductesVendaPerfil.getProductes());


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


                //Mostra informacio del producte quan fas click al nom del producte
                //Hauria de mostra imatge del producte i quan es fes click llavors mostrar informacio
                TextView  clickProducte = (TextView) rowView.findViewById(R.id.nom_producte);
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
                return llistaProductesVendaPerfil.getProductes().size();
            }

            @Override
            public boolean isEmpty() {
                return false;
            }


        });

    }
}
