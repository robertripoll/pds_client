package org.udg.pds.cheapyandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.activity.Login;
import org.udg.pds.cheapyandroid.entity.UserLogged;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import org.udg.pds.cheapyandroid.util.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Ubication_Fragment extends Fragment implements OnMapReadyCallback{
    CheapyApi mCheapyService;
    private GoogleMap mygooglemap;
    private MapView mapView;
    private View myView;
    private UserLogged userAct;
    private double lat, lgn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_ubication, container, false);
        getActualUbication();
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
        googleMap.addMarker(new MarkerOptions().position(new LatLng(lat, lgn)).title("My position").snippet("It's the position I specified when signing up"));
        CameraPosition alu = CameraPosition.builder().target(new LatLng(lat, lgn)).zoom(16).bearing(0).tilt(45).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(alu));
    }

    public void getActualUbication() {
        /*Call<UserLogged> call = mCheapyService.getSpecificUser(Login.userID_connected);
        call.enqueue(new Callback<UserLogged>() {
            @Override
            public void onResponse(Call<UserLogged> call, Response<UserLogged> response) {
                userAct = response.body();
                lat = userAct.getUbicacio().getCoordLat();
                lgn = userAct.getUbicacio().getCoordLng();
                System.out.println("LATITUD -> "+lat+",  ----- "+ lgn);
            }

            @Override
            public void onFailure(Call<UserLogged> call, Throwable t) {
                Toast toast = Toast.makeText(getActivity(), "ERROR: Revisa la connexió a Internet.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });*/
        lat = 41.8638;
        lgn = 3.0726;
    }
}
