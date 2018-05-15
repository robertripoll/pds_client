package org.udg.pds.cheapyandroid.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.entity.LlistaProductes;
import org.udg.pds.cheapyandroid.entity.Producte;

import java.text.DecimalFormat;

public class LlistaProductesAdapter extends ArrayAdapter<Producte> {

    private LlistaProductes _dades;

    // Components visuals
    private TextView tvNomProducte;
    private TextView tvPreuProducte;
    private TextView tvDescProducte;
    private TextView tvUbicacioProducte;

    private static DecimalFormat df2 = new DecimalFormat("#.00");

    public LlistaProductesAdapter(Activity context, LlistaProductes productes) {
        super(context, R.layout.adapter_llista_productes, productes.getItems());
        _dades = productes;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View element = convertView;

        if (element == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            element = inflater.inflate(R.layout.adapter_llista_productes,null);

            tvNomProducte = (TextView) element.findViewById(R.id.nom_producte);
            tvPreuProducte = (TextView) element.findViewById(R.id.preu_producte);
            tvDescProducte = (TextView) element.findViewById(R.id.desc_producte);
            tvUbicacioProducte = (TextView) element.findViewById(R.id.ubicacio_producte);
        }

        Producte producte = _dades.getItems().get(position);

        tvNomProducte.setText(producte.getNom());
        tvDescProducte.setText(producte.getDescripcio());
        tvPreuProducte.setText(df2.format(producte.getPreu()) + " €");
        tvUbicacioProducte.setText(producte.getVenedor().getUbicacio().getCiutat() + ", " + producte.getVenedor().getUbicacio().getPais());

        return element;
    }

}
