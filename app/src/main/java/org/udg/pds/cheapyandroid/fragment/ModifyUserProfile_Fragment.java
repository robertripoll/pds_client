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
    private ImageView foto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_modificar_perfilusuari, container, false);
        mCheapyService = ((CheapyApp) getActivity().getApplication()).getAPI();
        btnFoto = (Button) view.findViewById(R.id.botoFotoActualitzada);
        foto = (ImageView) view.findViewById(R.id.fotoActualitzada);

        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarOpcions();
            }
        });

        return view;
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