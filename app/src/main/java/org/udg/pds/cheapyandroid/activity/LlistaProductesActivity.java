package org.udg.pds.cheapyandroid.activity;

import android.content.Intent;
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
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.fragment.LlistaProductesFragment;
import org.udg.pds.cheapyandroid.fragment.PerfilFragment;

public class LlistaProductesActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // Configurem el Toolbar.
        configurarToolbar();

        // Configurem el Navigation Menú.
        configurarNavigationView();
    }

    private void configurarNavigationView() {
        // Create Navigation drawer and inflate layout
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);


        // Set behavior of Navigation drawer
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    // This method will trigger on item Click of navigation menu
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Set item in checked state
                        menuItem.setChecked(true);

                        // Handle Navigation
                        int id = menuItem.getItemId();
                        Fragment fragment = null;
                        if (id == R.id.nav_item_llista_productes)
                            fragment = new LlistaProductesFragment();
                        else if (id == R.id.nav_item_perfil)
                            fragment = new PerfilFragment();

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

        View headerLayout = navigationView.getHeaderView(0);
        headerLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LlistaProductesActivity.this.startActivity(new Intent(LlistaProductesActivity.this, Login.class));
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
