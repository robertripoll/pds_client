package org.udg.pds.cheapyandroid.rest;

import org.udg.pds.cheapyandroid.entity.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.ArrayList;

public interface CheapyApi {

    @POST("rest/usuaris/autenticar")
    Call<UserLogged> login(@Body UserLogin login);

    @GET("rest/productes")
    Call<LlistaProductes> getProductes();

    @POST("/productes")
    Call<Void> crearProducte(@Body Producte producte);

    @GET("/categories")
    Call<ArrayList<Categoria>> getCategories();

    @POST("/usuaris/jo/conversacions")
    Call<ConversacioChat> addChat(@Body Integer producte_id);

    @GET("/conversacions/{conversa_id}/missatges")
    Call<LlistaMissatges> getChatID(@Path("conversa_id") Integer id_chat);

    @POST("/conversacions/{conversa_id}/missatges")
    Call<Missatge> sendMessage(@Path("conversa_id") int conversaID, @Body String message);
  
    @GET("/usuaris/{usuari_id}p/vendes")
    Call<LlistaProductes> getProductesVendaPerfil();

    @GET("/usuaris/{usuari_id}/compres")
    Call<LlistaProductes> getProductesCompraPerfil();

    @GET("usuaris/{usuari_id}")
    Call<UserLogged> getSpecificUser(@Path("usuari_id") int userID);

    @POST("/usuaris")
    Call<UserLogged> addUser(@Body User user);

    @POST("/usuaris/desautenticar")
    Call<Void> diconnect();

    @PUT("/usuaris/jo")
    Call<Void> updateUserInformation(@Body UserLoggedUpdate update);

    @GET("/usuaris/jo/conversacions")
    Call<ItemsConversations> getConversations();
}
