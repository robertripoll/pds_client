package org.udg.pds.cheapyandroid.activity;

import android.app.Dialog;
import android.app.FragmentManager;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.fragment.LlistaProductesFragment;
import org.udg.pds.cheapyandroid.fragment.PublicarAnunciFragment;
import org.udg.pds.cheapyandroid.fragment.Usuari_Fragment;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LlistaProductesActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    CheapyApi mCheapyService;
    String user_;
    String pass_;
    String correu_;
    Long id_;

    private Fragment fragment;

    public static final String PREFS_NAME = "MisPreferencias";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mCheapyService = ((CheapyApp)this.getApplication()).getAPI();

        //TextView politica_privacitat = (TextView) findViewById(R.layout.)
        //Llegeix l'usuari actual que hi ha a l'app
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        user_ = prefs.getString("usuari_nom", "usuari_prova"); //getString(identificador, default)
        pass_ = prefs.getString("usuari_pass", "pass_prova");
        id_ = prefs.getLong("usuari_id", -1); //getString(identificador, default)
        correu_ = prefs.getString("usuari_correo", "prova@mail.com");


        // Configurem el Toolbar.
        configurarToolbar();

        // Carrega els productes
        carregarProductesALaVenda(); // metode que carrega els productes "a lo bestia"

        // Configurem el Navigation Menú.
        configurarNavigationView();


    }


    public void carregarProductesALaVenda() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menuNav = navigationView.getMenu();
        MenuItem menuItem = menuNav.findItem(R.id.nav_item_llista_productes);
        menuItem.setChecked(true);

        fragment = new LlistaProductesFragment();
        FragmentTransaction fragmentManager = getSupportFragmentManager().beginTransaction();
        toolbar.setTitle(R.string.navmenu_item_llista_productes);
        fragmentManager.replace(R.id.frame_layout, fragment).commit();


    }


    private void configurarNavigationView() {
        // Create Navigation drawer and inflate layout
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        comprovarLoginLogout(navigationView);

        View headerLayout = navigationView.getHeaderView(0);
        headerLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if(Login.userID_connected == Login.NO_REGISTRAT) {
                    LlistaProductesActivity.this.startActivity(new Intent(LlistaProductesActivity.this, Login.class));
                }
                else{
                    Toast.makeText(LlistaProductesActivity.this, "Ja estàs registrat :-)", Toast.LENGTH_SHORT).show();
                }
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
                        fragment = null;
                        switch(menuItem.getItemId()){
                            case R.id.nav_item_nou_producte:
                                toolbar.setTitle(R.string.navmenu_item_nou_producte);
                                fragment = new PublicarAnunciFragment();
                                break;
                            case R.id.nav_item_llista_productes:
                                toolbar.setTitle(R.string.navmenu_item_llista_productes);
                                fragment = new LlistaProductesFragment();
                                break;
                            case R.id.nav_item_perfil:
                                toolbar.setTitle(R.string.navmenu_item_perfil);
                                fragment = new Usuari_Fragment();
                                break;
                            case R.id.log_out:
                                posarUsuariLogout();
                                break;
                        }


                        if (fragment != null) {
                            FragmentTransaction fragmentManager = getSupportFragmentManager()
                                    .beginTransaction();
                            fragmentManager.replace(R.id.frame_layout, fragment).addToBackStack(null);
                            fragmentManager.commit();
                        }
                        // Closing drawer on item click
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }


    private void comprovarLoginLogout(final NavigationView navigationView) {

        if(!Login.logged){
            setLogoutMenu();
        }
        else{
            View header=navigationView.getHeaderView(0);
            TextView name = (TextView)header.findViewById(R.id.nameTxt);
            TextView email = (TextView)header.findViewById(R.id.emailTxt);
            name.setText(Login.userName_connected);
            email.setText(Login.userCorreu_connected);
            navigationView.getMenu().findItem(R.id.nav_item_perfil).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_item_nou_producte).setVisible(true);
            navigationView.getMenu().findItem(R.id.log_out).setVisible(true);
        }

    }

    private void setLogoutMenu() {
        //S'eliminen les preferencies que s'han afegit al fer Login
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear().commit();

        //S'amaga les opcions de perfil i log out, ja que ara l'estat actual es No registrat
        ((NavigationView) findViewById(R.id.nav_view)).getMenu().findItem(R.id.nav_item_perfil).setVisible(false);
        ((NavigationView) findViewById(R.id.nav_view)).getMenu().findItem(R.id.log_out).setVisible(false);
        ((NavigationView) findViewById(R.id.nav_view)).getMenu().findItem(R.id.nav_item_nou_producte).setVisible(false);

        //Navigation Header Buit
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header=navigationView.getHeaderView(0);
        TextView name = (TextView)header.findViewById(R.id.nameTxt);
        TextView email = (TextView)header.findViewById(R.id.emailTxt);
        name.setText("Nom Cognom");
        email.setText("nom.cognom@gmail.com");



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

        Login.userID_connected = Login.NO_REGISTRAT;
        Login.logged = false;

        // FA LA CRIDA LOGOUT I RETORNA OK
        Call<Void> call = mCheapyService.diconnect();
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()) {
                    Toast toast = Toast.makeText(LlistaProductesActivity.this, "Usuari No registrat", Toast.LENGTH_SHORT);
                    toast.show();
                    setLogoutMenu();
                    returnToLogin();
                } else {
                    Toast toast = Toast.makeText(LlistaProductesActivity.this, "Error logout ", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast toast = Toast.makeText(LlistaProductesActivity.this, "Error logout internet " +t.toString(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }

    private void returnToLogin() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
        finish();
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
        if (id == R.id.action_search) {
            obrirDialogFiltresCerca();
        } else if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void obrirDialogFiltresCerca() {
        final Dialog dialog = new Dialog(LlistaProductesActivity.this);
        dialog.setContentView(R.layout.dialog_filtres_cerca);
        dialog.setTitle("Title...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        final EditText etNom = (EditText)dialog.findViewById(R.id.et_filtre_nom_producte);
        final EditText etPreuMin = (EditText)dialog.findViewById(R.id.et_filtre_preu_minim);
        final EditText etPreuMax = (EditText)dialog.findViewById(R.id.et_filtre_preu_maxim);
        final RadioButton rbPreuSi = (RadioButton) dialog.findViewById(R.id.rb_filtre_preu_si);
        final RadioButton rbPreuNo = (RadioButton) dialog.findViewById(R.id.rb_filtre_preu_no);
        final RadioButton rbInterSi = (RadioButton) dialog.findViewById(R.id.rb_filtre_inter_si);
        final RadioButton rbInterNo = (RadioButton) dialog.findViewById(R.id.rb_filtre_inter_no);

        Button btnFiltrar = (Button)dialog.findViewById(R.id.button_filtrar);
        btnFiltrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragment != null) {
                    if (fragment instanceof LlistaProductesFragment) {
                        LlistaProductesFragment f = (LlistaProductesFragment) fragment;
                        Boolean preuNegociable = null;
                        Boolean intercanvi = null;
                        if (rbPreuSi.isChecked()) preuNegociable = true;
                        else if (rbPreuNo.isChecked()) preuNegociable = false;
                        if (rbInterSi.isChecked()) intercanvi = true;
                        else if (rbInterNo.isChecked()) intercanvi = false;

                        String etPreuMinStr = etPreuMin.getText().toString();
                        String etPreuMaxStr = etPreuMax.getText().toString();
                        String preuStr = "{";
                        if (etPreuMinStr != null && !etPreuMinStr.isEmpty())
                            preuStr += "\"gt\" : " + Double.parseDouble(etPreuMinStr) + ", ";
                        else preuStr += "\"gt\" : -999999.0, ";
                        if (etPreuMaxStr != null && !etPreuMaxStr.isEmpty())
                            preuStr += "\"lt\" : " + Double.parseDouble(etPreuMaxStr) + " }";
                        else preuStr += "\"lt\" : 999999.0 }";

                        f.carregarProdcutesAmbFiltre(etNom.getText().toString(),
                                preuNegociable,
                                intercanvi,
                                preuStr);
                    }
                }
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
