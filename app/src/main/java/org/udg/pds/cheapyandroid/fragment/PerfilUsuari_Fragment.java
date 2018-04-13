package org.udg.pds.cheapyandroid.fragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
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
    private String email;


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

        editarPerfil = (Button) view.findViewById(R.id.botoEditarPerfil);
        editarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModifyUserProfile_Fragment fragmentModif = new ModifyUserProfile_Fragment();
                Bundle bundle = new Bundle();
                bundle.putString("Email",email);
                fragmentModif.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_usuari, fragmentModif).commit();
            }
            });
        return view;
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
                User usuari = response.body();

                String compres = String.valueOf(usuari.getNombreCompres());
                String vendes = String.valueOf(usuari.getNombreVendes());
                String valoracions = String.valueOf(usuari.getNombreValoracions());

                textView.append(usuari.getNom());
                textView2.append(usuari.getCognoms());

                textView3.append(vendes);
                textView4.append(compres);
                textView5.append(valoracions);

                email = usuari.getCorreu();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast toast = Toast.makeText(getActivity(), "ERROR: Revisa la connexió a Internet.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
