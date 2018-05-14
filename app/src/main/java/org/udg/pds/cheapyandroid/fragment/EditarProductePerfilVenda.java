package org.udg.pds.cheapyandroid.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.entity.UserLogged;
import org.udg.pds.cheapyandroid.rest.CheapyApi;

public class EditarProductePerfilVenda extends Fragment {

    private CheapyApi mCheapyService;
    private Button button_photo;
    private Button button_update;
    private ImageView photo;
    private EditText preu;
    private EditText descripcio;
    private EditText categoria;

    private boolean fotoActualitzada;
    private UserLogged user;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_modificar_perfilusuari, container, false);
        mCheapyService = ((CheapyApp) getActivity().getApplication()).getAPI();

        inicialitzarVariables(view);

        eventOnButtons();

        editPreuFocus(preu);
        editTextFocus(descripcio);
        editTextFocus(categoria);

        return view;
    }

    private void editTextFocus(final EditText editText) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean b) {
                if (editText.getText().length()<1)
                    editText.setError("No pots deixar els camps buits!");
            }
        };
    }

    private void editPreuFocus(final EditText preu) {
        preu.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean b) {
                if(preu.getText().length()<0){
                    preu.setError("No pots posar un preu negatiu!");
                }
            }
        });
    }

    private void eventOnButtons() {
        button_update.setOnClickListener(new View.OnClickListener(){
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

        button_photo.setOnClickListener(new View.OnClickListener() {
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
                preu.setText("");
                categoria.setText("");
                descripcio.setText("");
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
        button_photo = (Button) view.findViewById(R.id.botoFotoActualitzada);
        button_update = (Button) view.findViewById(R.id.botoActualitzarInformacio);
        photo = (ImageView) view.findViewById(R.id.fotoActualitzada);
        fotoActualitzada = false;
        Bundle bundle = getArguments();
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
            photo.setImageURI(elMeuPath);
            fotoActualitzada = true;
        }
    }

}
