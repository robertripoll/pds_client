package org.udg.pds.cheapyandroid.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.udg.pds.cheapyandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LlistaProductesFragment extends Fragment {


    public LlistaProductesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_llista_productes, container, false);
    }

}
