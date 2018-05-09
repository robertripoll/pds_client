package org.udg.pds.cheapyandroid.fragment;

import android.graphics.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.rest.CheapyApi;


public class Ubication_Fragment extends Fragment implements OnMapReadyCallback{
    CheapyApi mCheapyService;
    GoogleMap mygooglemap;
    MapView mapView;
    View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_ubication, container, false);
        return myView;
    }

    @Override
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState){
        super.onViewCreated(v,savedInstanceState);
        mapView = (MapView) myView.findViewById(R.id.mapUser);
        if(mapView != null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mygooglemap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.689247, -74.044502)).title("My position prova").snippet("alualu provant"));
        CameraPosition alu = CameraPosition.builder().target(new LatLng(40.689247, -74.044502)).zoom(16).bearing(0).tilt(45).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(alu));
    }
}
