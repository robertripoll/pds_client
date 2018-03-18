package org.udg.pds.cheapyandroid;

import android.app.Application;

import com.cheapyteam.persistentcookiejar.ClearableCookieJar;
import com.cheapyteam.persistentcookiejar.PersistentCookieJar;
import com.cheapyteam.persistentcookiejar.cache.SetCookieCache;
import com.cheapyteam.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import org.udg.pds.cheapyandroid.util.Global;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheapyApp extends Application {

    CheapyApi mTodoService;

    @Override
    public void onCreate() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient(); //create OKHTTPClient

        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(this));

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .addInterceptor(interceptor)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(Global.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        mTodoService = retrofit.create(CheapyApi.class);
    }

    public CheapyApi getAPI() {
        return mTodoService;
    }
}

