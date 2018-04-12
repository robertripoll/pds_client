package org.udg.pds.cheapyandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.entity.User;
import org.udg.pds.cheapyandroid.entity.UserLogin;
import org.udg.pds.cheapyandroid.fragment.LlistaProductesFragment;
import org.udg.pds.cheapyandroid.fragment.PerfilFragment;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import org.udg.pds.cheapyandroid.util.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LlistaProductesActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    CheapyApi mCheapyService;
    String user_, pass_;

    public static final String PREFS_NAME = "MisPreferencias";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mCheapyService = ((CheapyApp)this.getApplication()).getAPI();

        //Llegeix l'usuari actual que hi ha a l'app
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        user_ = prefs.getString("usuari_nom", "usuari_prova"); //getString(identificador, default)
        pass_ = prefs.getString("contrasenya_nom", "contrasenya_prova"); //getString(identificador, default)

        // Configurem el Toolbar.
        configurarToolbar();

        // Configurem el Navigation Menú.
        configurarNavigationView();

    }

    private void configurarNavigationView() {
        // Create Navigation drawer and inflate layout
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        comprovarLoginLogout(navigationView);

        View headerLayout = navigationView.getHeaderView(0);
        headerLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LlistaProductesActivity.this.startActivity(new Intent(LlistaProductesActivity.this, Login.class));
            }
        });

        // Set behavior of Navigation drawer
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    // This method will trigger on item Click of navigation menu
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Set item in checked state
                        menuItem.setChecked(true);

                        // Handle Navigation
                        Fragment fragment = null;
                        switch(menuItem.getItemId()){
                            case R.id.nav_item_llista_productes:
                                fragment = new LlistaProductesFragment();
                                break;
                            case R.id.nav_item_perfil:
                                fragment = new PerfilFragment();
                                break;
                            case R.id.log_out:
                                Toast.makeText(LlistaProductesActivity.this, "Has fet click a Log Out", Toast.LENGTH_SHORT).show();
                                posarUsuariLogout();
                                break;
                        }


                        if (fragment != null) {
                            FragmentTransaction fragmentManager = getSupportFragmentManager()
                                    .beginTransaction();

                            fragmentManager.replace(R.id.frame_layout, fragment);
                            fragmentManager.commit();
                        }


                        // Closing drawer on item click
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    private void comprovarLoginLogout(final NavigationView navigationView) {

        Call<UserLogin> call = mCheapyService.isConnected();
        call.enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {

                if (response.isSuccessful()) {

                    UserLogin usuari = response.body();
                    String user_name = String.valueOf(usuari.getUsuari());
                    String user_pass = String.valueOf(usuari.getContrasenya());
                    if(user_name.equals(user_) && user_pass.equals(pass_)) {
                        navigationView.getMenu().findItem(R.id.nav_item_perfil).setVisible(true);
                        navigationView.getMenu().findItem(R.id.log_out).setVisible(true);
                    }
                    else {
                        posarUsuariLogout();
                        Toast toast = Toast.makeText(LlistaProductesActivity.this, "Usuari No registrat", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                } else {
                    Toast toast = Toast.makeText(LlistaProductesActivity.this, "ERROR: Al intentar comprovar login d'usuari", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }


            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
                Toast toast = Toast.makeText(LlistaProductesActivity.this, "ERROR: Revisa la connexió a Internet.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void configurarToolbar() {
        // Agafem el Toolbar per la seva id.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Indiquem la icona del nostre toolbar per obrir el NavigationMenu.
        toolbar.setNavigationIcon(R.mipmap.ic_menu_white_24dp);
        // Configurem el Toolbar com a ActionBar de la nostra vista.
        setSupportActionBar(toolbar);
    }

    private void posarUsuariLogout(){

        // FA LA CRIDA LOGOUT I RETORNA OK
        Call<User> call = mCheapyService.diconnect();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.isSuccessful()) {
                    Toast toast = Toast.makeText(LlistaProductesActivity.this, "LOGOUT OK", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(LlistaProductesActivity.this, "Error logout ", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast toast = Toast.makeText(LlistaProductesActivity.this, "Error logout internet", Toast.LENGTH_SHORT);
                toast.show();
            }
        });


        //S'eliminen les preferencies que s'han afegit al fer Login
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear().commit();

        //S'amaga les opcions de perfil i log out, ja que ara l'estat actual es No registrat
        ((NavigationView) findViewById(R.id.nav_view)).getMenu().findItem(R.id.nav_item_perfil).setVisible(false);
        ((NavigationView) findViewById(R.id.nav_view)).getMenu().findItem(R.id.log_out).setVisible(false);

        //Navigation Header Buit
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header=navigationView.getHeaderView(0);
        TextView name = (TextView)header.findViewById(R.id.nameTxt);
        TextView email = (TextView)header.findViewById(R.id.emailTxt);
        name.setText("Nom Cognom");
        email.setText("nom.cognom@gmail.com");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == 1) {
            return true;
        } else if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

}
