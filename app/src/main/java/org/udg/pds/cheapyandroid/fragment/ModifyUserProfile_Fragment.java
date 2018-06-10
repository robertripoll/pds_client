package org.udg.pds.cheapyandroid.fragment;

import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.ipaulpro.afilechooser.utils.FileUtils;
import com.squareup.picasso.Picasso;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.Manifest;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.entity.Imatge;
import org.udg.pds.cheapyandroid.entity.UserLoggedUpdate;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.graphics.Bitmap;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModifyUserProfile_Fragment extends Fragment {

    private static final int CODI_SELECCIO = 2;
    private static final int REQUEST_STORAGE = 0 ;
    private CheapyApi mCheapyService;

    private Button btnFoto;
    private Button btnActualitzar;
    private ImageView foto;
    private EditText nom;
    private EditText cognom;
    private TextView email;
    private EditText telefon;

    private boolean fotoActualitzada;
    private String emailUser;
    private Imatge imageUser;
    private Uri imatgeUri;
    private String rutaImatge;

    private Context mContext = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_modificar_perfilusuari, container, false);
        mCheapyService = ((CheapyApp) getActivity().getApplication()).getAPI();

        mContext = getActivity();

        comprovaPermisos();

        inicialitzarVariables(view);

        eventOnButtons();

        editTextFocus(nom);
        editTextFocus(cognom);
        editNumberFocus();

        return view;
    }

    private void comprovaPermisos() {

        if (ActivityCompat.checkSelfPermission(getContext(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(),
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_STORAGE);
        }
        else {
            Log.e("DB", "PERMISSION GRANTED");
        }
    }

    private void editNumberFocus() {
        telefon.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean b) {
                if(telefon.getText().length()<9){
                    telefon.setError("The number must contain 9 digits");
                }
            }
        });
    }

    private void editTextFocus(final EditText editText) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean b) {
                if(editText.getText().length()<3){
                    editText.setError("It's too short");
                }
            }
        });
    }

    private void eventOnButtons() {
        btnActualitzar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(comprovarSiInfoPlena()){
                    mostrarMenuConfirmacio();
                }
                else{
                    Toast.makeText(getContext(),"ERROR: Missing Required Fields",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarOpcions();
            }
        });
    }

    private void mostrarMenuConfirmacio() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("Are you sure that you have filled in the fields correctly?");
        alertDialog.setPositiveButton("I'm sure!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                updateUserInformation();
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                nom.setText("");
                cognom.setText("");
                telefon.setText("");
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }

    private void updateUserInformation() {

        if(fotoActualitzada){
            postTheInternalImage();
        }
        UserLoggedUpdate update = new UserLoggedUpdate(nom.getText().toString(),cognom.getText().toString(), telefon.getText().toString(),rutaImatge);
        Call<Void> call = mCheapyService.updateUserInformation(update);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast toast = null;
                if(response.isSuccessful()){

                    toast.makeText(getContext(), "INFORMACIO ACTUALITZADA!",toast.LENGTH_SHORT).show();
                    Fragment fragmentPerf = new Usuari_Fragment();
                    FragmentTransaction fragmentManager = getFragmentManager()
                            .beginTransaction();
                    fragmentManager.replace(R.id.frame_layout, fragmentPerf).addToBackStack(null);
                    fragmentManager.commit();
                }
                else{
                    toast.makeText(getContext(), "Ha hagut un error!!!",toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast toast = Toast.makeText(getContext(), "ERROR: No s'ha pogut actualitzar el perfil! Revisa la connexió a Internet.", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }


    private void postTheInternalImage() {

        RequestBody descriptionPart = RequestBody.create(MultipartBody.FORM, "Sample description");

        File originalFile = FileUtils.getFile(mContext, imatgeUri);

        RequestBody filePart = RequestBody.create(
                MediaType.parse(mContext.getContentResolver().getType(imatgeUri)),
                originalFile);

        MultipartBody.Part file = MultipartBody.Part.createFormData("image", originalFile.getName(), filePart);

        Call<List<String>> call = mCheapyService.postImage(descriptionPart, file);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {

                if(response.isSuccessful()){
                    rutaImatge = response.body().get(0);

                    imageUser = new Imatge(rutaImatge);

                    Toast toast = null;
                    toast.makeText(getContext(), "Imatge, OK! " + rutaImatge + " // " + response.body().get(0) ,toast.LENGTH_SHORT).show();

                }
                else{
                    Toast toast = null;
                    toast.makeText(getContext(), "Ha hagut un error al guardar l'imatge " + response.toString() ,toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast toast = null;
                toast.makeText(getContext(), "Ha hagut un error al guardar l'imatge al servidor!! " + t.toString(),toast.LENGTH_SHORT).show();
            }
        });

    }


    private Boolean comprovarSiInfoPlena() {
        return nom.getText().length()>=3 && cognom.getText().length()>=3
                && telefon.getText().length()>=9;
    }

    private void inicialitzarVariables(View view) {
        btnFoto = (Button) view.findViewById(R.id.botoFotoActualitzada);
        btnActualitzar = (Button) view.findViewById(R.id.botoActualitzarInformacio);
        foto = (ImageView) view.findViewById(R.id.fotoActualitzada);
        nom = (EditText) view.findViewById(R.id.nomActualitzat);
        cognom = (EditText) view.findViewById(R.id.cognomActualitzat);
        email = (TextView) view.findViewById(R.id.emailActualitzat);
        telefon = (EditText) view.findViewById(R.id.telefonActualitzat);
        fotoActualitzada = false;
        imatgeUri = null;
        rutaImatge = null;
        Bundle bundle = getArguments();
        if(bundle!=null){
            emailUser = bundle.get("emailUser").toString();
            imageUser = (Imatge) bundle.get("imageUser");
            if(imageUser!=null){
                Picasso.get()
                        .load(imageUser.getRuta())
                        .resize(50, 50)
                        .into(foto);
            }

            email.setText(emailUser);
        }
    }

    private void mostrarOpcions() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Choose an action");
        final String opcio1 = "Choose a photo";
        final String opcio2 = "Cancel";
        final CharSequence [] diversesOpcions = {opcio1,opcio2};
        builder.setItems(diversesOpcions, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(diversesOpcions[i].equals(opcio1)){
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/");
                    startActivityForResult(intent.createChooser(intent,"Choose"),CODI_SELECCIO);
                }
                else{
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();
    }

    //Com que hem utilitzat el startActivityForResults, hem de sobrescriure el següent mètode
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODI_SELECCIO){
            imatgeUri = data.getData();
            foto.setImageURI(imatgeUri);
            fotoActualitzada = true;
        }
    }

}