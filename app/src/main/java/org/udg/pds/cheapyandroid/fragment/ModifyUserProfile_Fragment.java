package org.udg.pds.cheapyandroid.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.squareup.picasso.Picasso;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okio.BufferedSink;
import org.udg.pds.cheapyandroid.CheapyApp;
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
    private Bitmap bitmap;
    private Uri imatgeUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_modificar_perfilusuari, container, false);
        mCheapyService = ((CheapyApp) getActivity().getApplication()).getAPI();

        inicialitzarVariables(view);

        eventOnButtons();

        editTextFocus(nom);
        editTextFocus(cognom);
        editNumberFocus();

        return view;
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
                updateInformation();
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

    private void updateInformation() {

        updateUserInformation();

}

    private void updateUserInformation() {
        String rutaImatge = null;
        if(fotoActualitzada){
            //Mirar de ficar la imatge de la galeria al server TT
            postTheInternalImage();
            //rutaImatge="https://i.imgur.com/BwMHDTBg.jpg";
        }
        UserLoggedUpdate update = new UserLoggedUpdate(nom.getText().toString(),cognom.getText().toString(), telefon.getText().toString(),rutaImatge);
        Call<Void> call = mCheapyService.updateUserInformation(update);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast toast = null;
                if(response.isSuccessful()){
                    System.out.println("Call correcte");
                    toast.makeText(getContext(), "INFORMACIO ACTUALITZADA!",toast.LENGTH_SHORT).show();
                    Fragment fragmentPerf = new Usuari_Fragment();
                    FragmentTransaction fragmentManager = getFragmentManager()
                            .beginTransaction();
                    fragmentManager.replace(R.id.frame_layout, fragmentPerf).addToBackStack(null);
                    fragmentManager.commit();
                }
                else{
                    System.out.println("Call incorrecte 1");
                    toast.makeText(getContext(), "Ha hagut un error!!!",toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("Call incorrecte 2");
                Toast toast = Toast.makeText(getContext(), "ERROR: No s'ha pogut actualitzar el perfil! Revisa la connexió a Internet.", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }





    private void postTheInternalImage() {
        File file = new File(imatgeUri.getPath());

        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

        Call<List<String>> call = mCheapyService.postImage(fileToUpload, filename);
        call.enqueue(new Callback<List<String>>(){
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(response.isSuccessful()){
                    imageUser.setRuta(response.body().get(0));
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast toast = null;
                toast.makeText(getContext(), "Ha hagut un error al guardar l'imatge al servidor!!",toast.LENGTH_SHORT).show();
            }
        });
    }
        /*
        Call<List<String>> call = mCheapyService.postImage(param);
        call.enqueue(new Callback<List<String>>(){
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(response.isSuccessful()){
                    imageUser.setRuta(response.body().get(0));
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast toast = null;
                toast.makeText(getContext(), "Ha hagut un error al guardar l'imatge al servidor!!",toast.LENGTH_SHORT).show();
            }
        });

    }*/






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
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),imatgeUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            fotoActualitzada = true;
        }
    }

}