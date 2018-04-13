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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.rest.CheapyApi;

public class ModifyUserProfile_Fragment extends Fragment {

    private static final int CODI_SELECCIO = 2;
    private static final int CODI_FOTO = 1;
    private CheapyApi mCheapyService;

    private Button btnFoto;
    private Button btnActualitzar;
    private ImageView foto;
    private EditText nom;
    private EditText cognom;
    private EditText email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_modificar_perfilusuari, container, false);
        mCheapyService = ((CheapyApp) getActivity().getApplication()).getAPI();

        inicialitzarVariables(view);

        System.out.println("NOOOOOM ACTUALIIIIITZAAAAT----->"+nom.getText());

        eventOnButtons();

        editTextFocus(nom);
        editTextFocus(cognom);
        editTextFocus(email);

        return view;
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
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarOpcions();
            }
        });

        btnActualitzar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(comprovarSiInfoPlena()){
                    //mostrar missatge de confirmacio
                    Toast.makeText(getContext(),"Perfect! NICE AND SWEET",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getContext(),"ERROR: Missing Required Fields",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private Boolean comprovarSiInfoPlena() {
        return nom.getText().length()>=3 && cognom.getText().length()>=3
                && email.getText().length()>=3;
    }

    private void inicialitzarVariables(View view) {
        btnFoto = (Button) view.findViewById(R.id.botoFotoActualitzada);
        btnActualitzar = (Button) view.findViewById(R.id.botoActualitzarInformacio);
        foto = (ImageView) view.findViewById(R.id.fotoActualitzada);
        nom = (EditText) view.findViewById(R.id.nomActualitzat);
        cognom = (EditText) view.findViewById(R.id.cognomActualitzat);
        email = (EditText) view.findViewById(R.id.emailActualitzat);
    }

    private void mostrarOpcions() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Choose an action");
        final String opcio1 = "Take a photo";
        final String opcio2 = "Choose a photo";
        final String opcio3 = "Cancel";
        final CharSequence [] diversesOpcions = {opcio1,opcio2,opcio3};
        builder.setItems(diversesOpcions, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(diversesOpcions[i].equals(opcio1)){
                    Toast.makeText(getContext(), "TAKE A PHOTO",Toast.LENGTH_SHORT).show();
                }
                else if(diversesOpcions[i].equals(opcio2)){
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
        System.out.println("-----resultCode-----" + resultCode);
        if (requestCode == CODI_SELECCIO){
            Uri elMeuPath = data.getData();
            foto.setImageURI(elMeuPath);
        }
    }

}