package org.udg.pds.cheapyandroid.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.entity.User;
import org.udg.pds.cheapyandroid.entity.UserLogged;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import org.udg.pds.cheapyandroid.util.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    private UserLogged userAct;

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
        PerfilUsuari_Fragment fragmentPerf = new PerfilUsuari_Fragment();
        Bundle bundle = new Bundle();
        userAct.setNom(String.valueOf(nom.getText()));
        userAct.setCognoms(String.valueOf(cognom.getText()));
        userAct.setTelefon(String.valueOf(telefon.getText()));
        bundle.putSerializable("newUser",userAct);
        fragmentPerf.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.fragment_modificarPerfil, fragmentPerf).commit();
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
        Bundle bundle = getArguments();
        if(bundle!=null){
            userAct = (UserLogged) bundle.getSerializable("oldUser");
            email.setText(userAct.getCorreu());
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
            Uri elMeuPath = data.getData();
            foto.setImageURI(elMeuPath);
            fotoActualitzada = true;
        }
    }

}