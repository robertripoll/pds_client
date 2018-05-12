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
    Call<ConversacioChat> addChat(@Body Producte producte);

    @GET("/conversacio/{conversa_id}/missatges")
    Call<List<Missatge>> getChatID(@Path("conversa_id") Integer id_chat);

    @POST("/conversacio/{conversa_id}/missatges")
    Call<Void> sendMessage(@Path("conversa_id") int messageID, @Body Missatge message);
  
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

    @FormUrlEncoded
    @PUT("/usuaris/{usuari_id}")
    Call<UserLogged> updateUserInformation(@Path("usuari_id") int userID,
                                     @Field("nom") String nom,
                                     @Field("cognoms") String cognoms,
                                     @Field("telefon") String telefon); //Retorna response 307 :/

    @GET("/usuaris/jo/conversacions")
    Call<ItemsConversations> getConversations();
}
