package org.udg.pds.cheapyandroid.rest;

import org.udg.pds.cheapyandroid.entity.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface CheapyApi {

    @POST("/usuaris/autenticar")
    Call<UserLogged> login(@Body UserLogin login);

    @GET("/productes")
    Call<LlistaProductes> getProductes();

    @POST("/productes")
    Call<Void> crearProducte(@Body Producte producte);

    @GET("/categories")
    Call<LlistaCategories> getCategories();

    @POST("/usuaris/jo/conversacions")
    Call<ConversacioChat> addChat(@Body Integer producte_id);

    @GET("/conversacions/{conversa_id}/missatges")
    Call<LlistaMissatges> getChatID(@Path("conversa_id") Integer id_chat);

    @POST("/conversacions/{conversa_id}/missatges")
    Call<Missatge> sendMessage(@Path("conversa_id") int conversaID, @Body String message);
  
    @GET("/usuaris/jo/productes_venda")
    Call<LlistaProductes> getProductesVendaPerfil();

    @GET("/usuaris/jo/productes_comprats")
    Call<LlistaProductes> getProductesCompraPerfil();

    @GET("usuaris/{usuari_id}")
    Call<UserLogged> getSpecificUser(@Path("usuari_id") int userID);

    @POST("/usuaris")
    Call<UserLogged> addUser(@Body User user);

    @POST("/usuaris/desautenticar")
    Call<Void> diconnect();

    @PUT("/usuaris/{usuari_id}")
    Call<Void> updateUserInformation(@Path("usuari_id") int userID, @Body UserLoggedUpdate update);

    @GET("/usuaris/jo/conversacions")
    Call<ItemsConversations> getConversations();
}
