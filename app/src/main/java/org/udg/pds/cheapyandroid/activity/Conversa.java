package org.udg.pds.cheapyandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.entity.Conversacio;
import org.udg.pds.cheapyandroid.entity.Producte;
import org.udg.pds.cheapyandroid.entity.User;
import org.udg.pds.cheapyandroid.entity.Venedor;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Conversa extends AppCompatActivity {

    private CheapyApi mCheapyService;
    Producte producte;
    Venedor venedor;
    Conversacio conversacio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversa);

        mCheapyService = ((CheapyApp)this.getApplication()).getAPI();
        producte = (Producte) getIntent().getSerializableExtra("Producte");
        venedor = producte.getProducte().getVenedor();



        if(existeixConversa(producte)){

        }
        else{
            crearNovaConversa(producte);
        }


    }

    private boolean existeixConversa(Producte producte) {

        return false;
    }

    private void crearNovaConversa(Producte producte) {

        Call<Conversacio> call = mCheapyService.addChat(producte);
        call.enqueue(new Callback<Conversacio>() {
            @Override
            public void onResponse(Call<Conversacio> call, Response<Conversacio> response) {

                if (response.isSuccessful()) {
                    Toast toast = Toast.makeText(Conversa.this, "Conversa creada correctament", Toast.LENGTH_SHORT);
                    toast.show();
                    conversacio = response.body();

                } else {
                    Toast toast = Toast.makeText(Conversa.this, "Error new chat", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<Conversacio> call, Throwable t) {
                Toast toast = Toast.makeText(Conversa.this, "Error new chat - web" + t.toString()  , Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
