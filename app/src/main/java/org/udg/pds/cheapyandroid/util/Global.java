package org.udg.pds.cheapyandroid.util;

import android.widget.EditText;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.entity.OmdbAPI;
import org.udg.pds.cheapyandroid.entity.Producte;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class Global {

    public static final String BASE_URL = "http://private-5389b3-pdsrest.apiary-mock.com";

    static class APIClient {

        private static Retrofit retrofit = null;

        static Retrofit getClient() {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


            retrofit = new Retrofit.Builder()
                    .baseUrl("http://private-5389b3-pdsrest.apiary-mock.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            OmdbAPI service = retrofit.create(OmdbAPI.class);

            EditText title = (EditText)findViewById(R.id.editText);
            Call<Producte> call = service.getProducte(1);

            call.enqueue(MainActivity.this);
        }
    }

    @GET("/productes/1")
    Call<MultipleResource> doGetListResources() {
        return null;
    }
}
