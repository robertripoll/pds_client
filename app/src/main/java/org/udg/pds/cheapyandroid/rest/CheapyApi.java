package org.udg.pds.cheapyandroid.rest;

import org.udg.pds.cheapyandroid.entity.*;
import retrofit2.Call;
import retrofit2.http.*;

public interface CheapyApi {

    @POST("/usuaris/autenticar")
    Call<User> login(@Body UserLogin login);

    @GET("/productes")
    Call<LlistaProductes> getProductes();

    @POST("/usuaris/jo/conversacions")
    Call<Conversacio> addChat(@Body Producte producte);


    @POST("/conversacio/{conversa_id}/missatges")
    Call<Void> sendMessage(@Path("conversa_id") int messageID, @Body Missatge message);
  
    @GET("/usuaris/jo/productes_venda")
    Call<LlistaProductes> getProductesVendaPerfil();

    @GET("/usuaris/jo/productes_comprats")
    Call<LlistaProductes> getProductesCompraPerfil();

    @GET("usuaris/{usuari_id}")
    Call<User> getSpecificUser();

    @POST("/usuaris")
    Call<User> addUser(@Body User task);

    @GET("/usuaris/jo")
    Call<UserLogin> isConnected();

    @POST("/logout")
    Call<User> diconnect();

    @PUT("/usuari/{usuari_id}")
    Call<User> updateUserInformation(@Body User u);

}
