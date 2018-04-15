package org.udg.pds.cheapyandroid.rest;

import org.udg.pds.cheapyandroid.entity.LlistaProductes;
import org.udg.pds.cheapyandroid.entity.User;
import org.udg.pds.cheapyandroid.entity.UserLogin;
import retrofit2.Call;
import retrofit2.http.*;

public interface CheapyApi {

    @POST("rest/users/auth")
    Call<User> login(@Body UserLogin login);

    @GET("/productes")
    Call<LlistaProductes> getProductes();

    @GET("usuaris/{usuari_id}")
    Call<User> getSpecificUser();

    @POST("/usuaris")
    Call<User> addUser(@Body User task);

    /*Opció Intent1
    @PUT("/usuari/{usuari_id}")
    Call<User> updateUserInformation(@Path(("id"))
                                    @Field("nom") String nom,
                                     @Field("cognoms") String cognoms);*//*, @Field String telefon);*/
    /*Opció Intent2
    @PUT("/usuari/{usuari_id}")
    Call<User> updateUserInformation(@Body User userInformation);*/

}
