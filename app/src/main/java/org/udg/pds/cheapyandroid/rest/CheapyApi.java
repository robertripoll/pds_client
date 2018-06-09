package org.udg.pds.cheapyandroid.rest;

import android.graphics.Bitmap;
import com.fasterxml.jackson.databind.node.BinaryNode;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.udg.pds.cheapyandroid.entity.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CheapyApi {

    @POST("usuaris/autenticar")
    Call<UserLogged> login(@Body UserLogin login);

    @GET("productes")
    Call<LlistaProductes> getProductes();

    @POST("productes")
    Call<Void> crearProducte(@Body Producte producte);

    @GET("categories")
    Call<ArrayList<Categoria>> getCategories();

    @POST("usuaris/jo/conversacions")
    Call<ConversacioChat> addChat(@Body Integer producte_id);

    @GET("conversacions/{conversa_id}/missatges")
    Call<LlistaMissatges> getChatID(@Path("conversa_id") Integer id_chat);

    @POST("conversacions/{conversa_id}/missatges")
    Call<Missatge> sendMessage(@Path("conversa_id") int conversaID, @Body String message);

    @GET("usuaris/{usuari_id}/vendes")
    Call<LlistaProductes> getProductesVendaPerfil();

    @GET("usuaris/{usuari_id}/compres")
    Call<LlistaProductes> getProductesCompraPerfil();

    @GET("usuaris/jo")
    Call<UserLogged> getSpecificUser();

    @POST("usuaris")
    Call<UserLogged> addUser(@Body User user);

    @POST("usuaris/desautenticar")
    Call<Void> diconnect();

    @PUT("usuaris/jo")
    Call<Void> updateUserInformation(@Body UserLoggedUpdate update);

    @GET("usuaris/jo/conversacions")
    Call<ItemsConversations> getConversations();

    @GET("usuaris/comprovar")
    Call<Boolean> checkAuth();

    @Multipart
    @POST("imatges")
    Call<List<String>> postImage(@Part("description") RequestBody description, @Part MultipartBody.Part image);
}
