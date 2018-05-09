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
import org.udg.pds.cheapyandroid.activity.Login;
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

    private User userInformation;


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

        mostrarDadesPerfil();
        mirarSiInformacioActualitzada();

        editarPerfil = (Button) view.findViewById(R.id.botoEditarPerfil);
        editarPerfil.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ModifyUserProfile_Fragment fragmentModif = new ModifyUserProfile_Fragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("oldUser", userInformation);
                fragmentModif.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_usuari, fragmentModif).commit();
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
                fragmentManagerCompres.commit();
            }
        });
//ALEX END
        return view;
    }

    private void mirarSiInformacioActualitzada() {
        Bundle bundle = getArguments();
        if(bundle!=null){
            userInformation = (User) bundle.getSerializable("newUser");
            //System.out.println("NOM  "+userInformation.getNom());
            updateUserInformation();

        }
    }

    private void updateUserInformation() {
        System.out.println("-----------------------"+userInformation.getNom()+userInformation.getCognoms());
        Call<User> call = mCheapyService.updateUserInformation(userInformation.getI, userInformation.getNom(),userInformation.getCognoms(),userInformation.getTelefon());
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
        });
    }


    private void mostrarDadesPerfil() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Global.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mCheapyService = retrofit.create(CheapyApi.class);
        Call<User> call = mCheapyService.getSpecificUser(Login.userID_connected);
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
