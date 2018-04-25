package org.udg.pds.cheapyandroid.rest;

import org.udg.pds.cheapyandroid.entity.*;
import retrofit2.Call;
import retrofit2.http.*;

public interface CheapyApi {

    @POST("/usuaris/autenticar")
    Call<User> login(@Body UserLogin login);

    @GET("/productes")
    Call<LlistaProductes> getProductes();

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
