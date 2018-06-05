package org.udg.pds.cheapyandroid.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;
import com.squareup.picasso.Picasso;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.activity.Login;
import org.udg.pds.cheapyandroid.entity.User;
import org.udg.pds.cheapyandroid.entity.UserLogged;
import org.udg.pds.cheapyandroid.entity.UserLoggedUpdate;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import org.udg.pds.cheapyandroid.util.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilUsuari_Fragment extends Fragment {

    private CheapyApi mCheapyService;

    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private ImageView imageProfile;

    private Button editarPerfil;

    private UserLogged userInformation;

    public PerfilUsuari_Fragment(){}

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        mCheapyService = ((CheapyApp)getActivity().getApplication()).getAPI();

        textView = (TextView) view.findViewById(R.id.nom);
        textView2 = (TextView) view.findViewById(R.id.cognom);
        textView3 = (TextView) view.findViewById(R.id.vendes);
        textView4 = (TextView) view.findViewById(R.id.compres);
        textView5 = (TextView) view.findViewById(R.id.valoracions);
        imageProfile = (ImageView) view.findViewById(R.id.imageViewProfile);

        showUserInformation();

        editarPerfil = (Button) view.findViewById(R.id.botoEditarPerfil);
        editarPerfil.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ModifyUserProfile_Fragment fragmentModif = new ModifyUserProfile_Fragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("emailUser", userInformation.getCorreu());
                bundle.putSerializable("imageUser", userInformation.getImatge());
                fragmentModif.setArguments(bundle);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_usuari, fragmentModif)
                        .addToBackStack(null)
                        .commit();
            }
            });
//ALEX START
        //Implementa el click per veure els productes a la venda que tinc des del perfil
        ImageButton buttonVendes = (ImageButton) view.findViewById(R.id.imageButton2);
        buttonVendes.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragmentVendes = new LlistaProductesPerfilVendesFragment();
                FragmentTransaction fragmentManagerVendes = getFragmentManager().beginTransaction();
                fragmentManagerVendes.replace(R.id.frame_layout, fragmentVendes);
                fragmentManagerVendes.addToBackStack(null);
                fragmentManagerVendes.commit();
            }
        });

        //Implementa el click per veure els productes que he comprat des del perfil
        ImageButton buttonCompres = (ImageButton) view.findViewById(R.id.imageButton3);
        buttonCompres.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragmentCompres = new LlistaProductesPerfilCompresFragment();
                FragmentTransaction fragmentManagerCompres = getFragmentManager().beginTransaction();
                fragmentManagerCompres.replace(R.id.frame_layout, fragmentCompres);
                fragmentManagerCompres.addToBackStack(null);
                fragmentManagerCompres.commit();
            }
        });
//ALEX END
        return view;
    }

    public void showUserInformation() {
        Bundle bundle = getArguments();
        if(bundle!=null) {
            userInformation = (UserLogged) bundle.getSerializable("allUserInformation");

            String compres = String.valueOf(userInformation.getNombreCompres());
            String vendes = String.valueOf(userInformation.getNombreVendes());
            String valoracions = String.valueOf(userInformation.getNombreValoracions());

            textView.append(userInformation.getNom());
            textView2.append(userInformation.getCognoms());

            textView3.append(vendes);
            textView4.append(compres);
            textView5.append(valoracions);
            /*Picasso.get()
                    .load("http://i.imgur.com/DvpvklR.png")
                    //.load(userInformation.getImatge().getRuta())
                    .resize(50, 50)
                    .into(imageProfile);*/
            if(userInformation.getImatge()!=null){
                Picasso.get()
                        .load(userInformation.getImatge().getRuta())
                        .resize(50, 50)
                        .into(imageProfile);
            }
        }
    }
}
