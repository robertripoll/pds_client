package org.udg.pds.cheapyandroid.entity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OmdbAPI {
    @GET("/")
    Call<Producte> getProducte(@Query("t") int user);
}
