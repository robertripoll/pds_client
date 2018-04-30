package org.udg.pds.cheapyandroid.rest;

import org.udg.pds.cheapyandroid.entity.*;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CheapyApi {

    @POST("/usuaris/autenticar")
    Call<User> login(@Body UserLogin login);

    @GET("/productes")
    Call<LlistaProductes> getProductes();

    @POST("/usuaris/jo/conversacions")
    Call<Conversacio> addChat(@Body Producte producte);
  
    @GET("/usuaris/jo/productes_venda")
    Call<LlistaProductes> getProductesVendaPerfil();

    @GET("/usuaris/jo/productes_comprats")
    Call<LlistaProductes> getProductesCompraPerfil();

    @GET("usuaris/{usuari_id}")
    Call<User> getSpecificUser(@Path("usuari_id") int userID);

    @POST("/usuaris")
    Call<User> addUser(@Body User task);

    @GET("/usuaris/jo")
    Call<UserLogin> isConnected();

    @POST("/logout")
    Call<User> diconnect();

    @FormUrlEncoded
    @PUT("/usuaris/{usuari_id}")
    Call<User> updateUserInformation(@Path("usuari_id") int userID,
                                     @Field("nom") String nom,
                                     @Field("cognoms") String cognoms,
                                     @Field("telefon") Integer telefon); //Retorna response 307 :/

    @GET("/usuaris/jo/conversacions")
    Call<ConversationList> getConversations();
}
