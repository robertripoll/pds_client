package org.udg.pds.cheapyandroid.activity;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.entity.LlistaProductes;
import org.udg.pds.cheapyandroid.entity.Producte_;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LlistaProductesActivity extends Activity {

    private CheapyApi mCheapyService;
    private ListView llistaProductesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llista_productes);

        mCheapyService = ((CheapyApp)this.getApplication()).getAPI();

        llistaProductesView = (ListView) findViewById(R.id.llista_productes);

        carregarProductes();
    }

    private void carregarProductes() {
        Call<LlistaProductes> call = mCheapyService.getProductes();
        call.enqueue(new Callback<LlistaProductes>() {
            @Override
            public void onResponse(Call<LlistaProductes> call, Response<LlistaProductes> response) {

                if (response.isSuccessful()) {
                    mostrarProductes(response.body());
                } else {
                    Toast toast = Toast.makeText(LlistaProductesActivity.this, "ERROR: Els productes no s'han pogut carregar correctament", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<LlistaProductes> call, Throwable t) {
                Toast toast = Toast.makeText(LlistaProductesActivity.this, "ERROR: Revisa la connexió a Internet.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void mostrarProductes(final LlistaProductes llistaProductes) {

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
            public View getView(int i, View view, ViewGroup viewGroup) {
                LayoutInflater inflater = LlistaProductesActivity.this.getLayoutInflater();
                View rowView = inflater.inflate(R.layout.adapter_llista_productes, null);

                TextView nomView = (TextView) rowView.findViewById(R.id.nom_producte);
                TextView preuView = (TextView) rowView.findViewById(R.id.preu_producte);

                Producte_ producte = llistaProductes.getProductes().get(i).getProducte();

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
                return llistaProductes.getProductes().size();
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        });

    }

}
