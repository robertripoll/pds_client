package org.udg.pds.cheapyandroid.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.rest.CheapyApi;

public class Usuari_Fragment extends Fragment {

    private CheapyApi mCheapyService;
    private BottomNavigationView bottomNavigation;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_usuari, container, false);

        mCheapyService = ((CheapyApp)getActivity().getApplication()).getAPI();

        /*bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_Perfil:
                        //canviarColorMenu(bottomNavigation, 0);
                        canviarFragment(0);
                        break;
                    case R.id.menu_Chats:
                        //canviarColorMenu(bottomNavigation, 1);
                        canviarFragment(1);
                        break;
                    case R.id.menu_Location:
                        //canviarColorMenu(bottomNavigation, 2);
                        canviarFragment(2);
                        break;

                }
                return true;
            }
        });*/

        return view;
    }

    private void canviarFragment(int i) {
        Fragment fragment = null;
        if (i == 0) {
            fragment = new PerfilUsuari_Fragment();
            //} else if (i % 2 != 0) {
            //    fragment = new FragmentTwo();
            //} else {
            //    fragment = new FragmentThree();
            //}

            getFragmentManager().beginTransaction().replace(
                    R.id.fragment_usuari, fragment)
                    .commit();
        }
    }

    private void canviarColorMenu(BottomNavigationView bottomNavigation, int i) {

    }


}

