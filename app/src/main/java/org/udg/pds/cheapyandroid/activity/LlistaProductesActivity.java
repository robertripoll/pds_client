package org.udg.pds.cheapyandroid.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.entity.LlistaProductes;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LlistaProductesActivity extends Activity {

    private CheapyApi mCheapyService;
    private Button buttonTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llista_productes);

        mCheapyService = ((CheapyApp)this.getApplication()).getAPI();

        buttonTest = (Button)findViewById(R.id.button_test);
        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(LlistaProductesActivity.this, "click!.", Toast.LENGTH_SHORT);
                toast.show();
                carregarProductes();
            }
        });
    }

    private void carregarProductes() {
        Toast toast = Toast.makeText(LlistaProductesActivity.this, "carregar productes!.", Toast.LENGTH_SHORT);
        toast.show();
        Call<LlistaProductes> call = mCheapyService.getProductes();
        call.enqueue(new Callback<LlistaProductes>() {
            @Override
            public void onResponse(Call<LlistaProductes> call, Response<LlistaProductes> response) {

                if (response.isSuccessful()) {
                    Toast toast = Toast.makeText(LlistaProductesActivity.this, "Tenim productes.", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(LlistaProductesActivity.this, "Error agafant productes.", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<LlistaProductes> call, Throwable t) {
                Toast toast = Toast.makeText(LlistaProductesActivity.this, "Fail fort", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

}
