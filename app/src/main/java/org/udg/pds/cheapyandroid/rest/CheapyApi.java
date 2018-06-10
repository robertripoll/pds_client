package org.udg.pds.cheapyandroid.rest;

import android.graphics.Bitmap;
import com.fasterxml.jackson.databind.node.BinaryNode;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.udg.pds.cheapyandroid.activity.Conversa;
import org.udg.pds.cheapyandroid.entity.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CheapyApi {

    @GET("usuaris/{usuari_id}/vendes")
    Call<LlistaProductes> getProductesVendaPerfil();

    @GET("usuaris/{usuari_id}/compres")
    Call<LlistaProductes> getProductesCompraPerfil();

    @GET("usuaris/{usuari_id}")
    Call<UserLogged> getSpecificUser(@Path("usuari_id") Long userID);

    @POST("usuaris/registrar")
    Call<UserLogged> addUser(@Body User user);

    @POST("usuaris/desautenticar")
    Call<Void> diconnect();

    @PUT("usuaris/jo")
    Call<Void> updateUserInformation(@Body UserLoggedUpdate update);

    @GET("usuaris/comprovar")
    Call<Boolean> checkAuth();

    @POST("usuaris/autenticar")
    Call<UserLogged> login(@Body UserLogin login);

    @PUT("usuaris/jo/token")
    Call<Void> sendToken(@Body String token);

    @GET("productes")
    Call<LlistaProductes> getProductes();

    @POST("productes")
    Call<Void> crearProducte(@Body ProducteCrear producte);

    @GET("productes/{producte_id}")
    Call<Producte> getProducte(@Path("producte_id") Long id_producte);

    @GET("categories")
    Call<ArrayList<Categoria>> getCategories();

    @POST("conversacions")
    Call<ConversacioChat> addChat(@Body Conversa.R_Conversa conv);

    @GET("conversacions")
    Call<LlistaConversacions> getConversations();

    @DELETE("conversacions/{conversa_id}")
    Call<Void> deleteConversa(@Path("conversa_id") Long id_chat);

    @DELETE("conversacions/{idConv}/missatges/{idMiss}")
    Call<Void> deleteMissatge(@Path("idConv") Long id_chat, @Path("idMiss") Long id_message);

    @GET("conversacions/{conversa_id}/missatges")
    Call<LlistaMissatges> getChatID(@Path("conversa_id") Long id_chat);

    @POST("conversacions/{conversa_id}/missatges")
    Call<Missatge> sendMessage(@Path("conversa_id") Long conversaID, @Body Conversa.R_Missatge message);

    @PUT("productes/producte_id")
    Call<Void> updateProductInformation(@Path("producte_id") Integer id_producte);

    @Multipart
    @POST("imatges")
    Call<List<String>> postImage(@Part("description") RequestBody description, @Part MultipartBody.Part image);
}