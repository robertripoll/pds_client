package org.udg.pds.cheapyandroid.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.activity.Login;
import org.udg.pds.cheapyandroid.entity.UserLogged;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Usuari_Fragment extends Fragment {

    private CheapyApi mCheapyService;
    private BottomNavigationView bottomNavigation;
    private UserLogged userInformation;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_usuari, container, false);
        mCheapyService = ((CheapyApp)getActivity().getApplication()).getAPI();
        bottomNavigation = (BottomNavigationView) view.findViewById(R.id.navigation);
        obtenirDadesPerfil();

        return view;
    }

    private void obtenirDadesPerfil() {
        Call<UserLogged> call = mCheapyService.getSpecificUser(Login.userID_connected);
        call.enqueue(new Callback<UserLogged>() {
            @Override
            public void onResponse(Call<UserLogged> call, Response<UserLogged> response) {
                if (response.isSuccessful()) {
                    userInformation = response.body();
                    canviarFragment(0);
                    bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
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
                    });
                }
                else{
                    Toast toast = Toast.makeText(getContext(), "ERROR: S'ha rebut una resposta erronia.", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<UserLogged> call, Throwable t) {
                Toast toast = Toast.makeText(getContext(), "ERROR: Revisa la connexió a Internet.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void canviarFragment(int i) {
        Fragment fragment;
        Bundle bundle = new Bundle();
        if (i == 0) {
            fragment = new PerfilUsuari_Fragment();
            bundle.putSerializable("allUserInformation", userInformation);
            fragment.setArguments(bundle);
        } else if (i == 1) {
            fragment = new Chats_Fragment();//canviar-ho pel fragment que toqui
        } else {
            fragment = new Ubication_Fragment();//canviar-ho pel fragment que toqui
            bundle.putSerializable("ubicationUser", userInformation.getUbicacio());
            fragment.setArguments(bundle);
        }

       getFragmentManager()
               .beginTransaction()
               .replace(R.id.fragment_usuari, fragment)
               .commit();
    }
}

