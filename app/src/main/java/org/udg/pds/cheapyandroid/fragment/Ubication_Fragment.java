package org.udg.pds.cheapyandroid.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import com.google.android.gms.maps.OnMapReadyCallback;



public class Ubication_Fragment extends Fragment implements OnMapReadyCallback{
    CheapyApi mCheapyService;
    MapView mapView;
    View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_ubication, container, false);
        return myView;
    }
    

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
