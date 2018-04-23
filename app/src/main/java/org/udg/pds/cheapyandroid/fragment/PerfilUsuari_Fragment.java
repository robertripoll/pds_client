package org.udg.pds.cheapyandroid.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.entity.User;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import org.udg.pds.cheapyandroid.util.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    private Button editarPerfil;
    // private User userInformation; Preguntar a en nacho si es pot passar tot lobjecte Responsee<User> al altre fragment
    // que actualitza el perfil i que aquell fagi el PUT o si el put l'hem de fer des d'aquí
    private User userInformation;


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        mCheapyService = ((CheapyApp)getActivity().getApplication()).getAPI();

        textView = (TextView) view.findViewById(R.id.nom);
        textView2 = (TextView) view.findViewById(R.id.cognom);
        textView3 = (TextView) view.findViewById(R.id.vendes);
        textView4 = (TextView) view.findViewById(R.id.compres);
        textView5 = (TextView) view.findViewById(R.id.valoracions);

        mostrarDadesPerfil();
        mirarSiInformacioActualitzada();

        editarPerfil = (Button) view.findViewById(R.id.botoEditarPerfil);
        editarPerfil.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ModifyUserProfile_Fragment fragmentModif = new ModifyUserProfile_Fragment();
                Bundle bundle = new Bundle();
                bundle.putString("Email", userInformation.getCorreu());
                fragmentModif.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_usuari, fragmentModif).commit();
            }
            });
//ALEX START
        ImageButton buttonVendes = (ImageButton) view.findViewById(R.id.imageButton2);
        buttonVendes.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new LlistaProductesPerfilFragment();
                FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();
                fragmentManager.replace(R.id.frame_layout, fragment);
                fragmentManager.commit();
                //PerfilUsuari_Fragment.this.startActivity(new Intent(PerfilUsuari_Fragment.this.getActivity(), LlistaProductesPerfilFragment.class));
            }
        });
//ALEX END
        return view;
    }

    private void mirarSiInformacioActualitzada() {
        Bundle bundle = getArguments();
        if(bundle!=null){
            String nom = bundle.getString("NomActualitzat");
            System.out.println("nom->"+nom);
            String cognoms = bundle.getString("CognomActualitzat");
            System.out.println("cognoms->"+cognoms);
            String telefon = bundle.getString("TelefonActualitzat");


            //S'HA DE TREURE AIXO!!!! ---------------------------------------------
            textView.clearComposingText();
            textView.setText(bundle.getString("NomActualitzat"));
            textView2.clearComposingText();
            textView2.setText(bundle.getString("CognomActualitzat"));
            //---------------------------------------------------------------------

            System.out.println("MIREM D'ACTUALITZAR EL SERVIDOR!");
            updateUserInformation(nom,cognoms,telefon);
            System.out.println("VALEEEE SH'A ACTUALITZAT TOT BE!");

        }
    }

    private void updateUserInformation(String nom, String cognoms, String telefon) {
        //System.out.println("nom->"+userInformation.getNom()); PREGUNTAR A EN NACHO PERQUE PETA SI INTENTO MOSTRAR EL NOM ***************************************

        /*Call<User> call = mCheapyService.updateUserInformation(userInformation);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast toast = null;
                if(response.isSuccessful()){
                    toast.makeText(getContext(), "INFORMACIO ACTUALITZADA!",toast.LENGTH_SHORT).show();
                }
                else{
                    toast.makeText(getContext(), "Ha hagut un error!!!",toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast toast = Toast.makeText(getContext(), "ERROR: No s'ha pogut actualitzar el perfil! Revisa la connexió a Internet.", Toast.LENGTH_LONG);
                toast.show();
            }
        });*/
    }


    private void mostrarDadesPerfil() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Global.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mCheapyService = retrofit.create(CheapyApi.class);
        Call<User> call = mCheapyService.getSpecificUser();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                userInformation = response.body();
                String compres = String.valueOf(userInformation.getNombreCompres());
                String vendes = String.valueOf(userInformation.getNombreVendes());
                String valoracions = String.valueOf(userInformation.getNombreValoracions());

                textView.append(userInformation.getNom());
                textView2.append(userInformation.getCognoms());

                textView3.append(vendes);
                textView4.append(compres);
                textView5.append(valoracions);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast toast = Toast.makeText(getActivity(), "ERROR: Revisa la connexió a Internet.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
