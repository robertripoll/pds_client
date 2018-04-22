package org.udg.pds.cheapyandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.entity.Conversacio;
import org.udg.pds.cheapyandroid.entity.ConversationList;
import org.udg.pds.cheapyandroid.entity.LlistaConversacion;
import org.udg.pds.cheapyandroid.entity.LlistaProductes;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chats_Fragment extends Fragment {

    CheapyApi mCheapyService;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_converses, container, false);
        mCheapyService = ((CheapyApp)getActivity().getApplication()).getAPI();

        iniVariables(view);
        addActualConversations();

        return view;
    }

    private void addActualConversations() {
        Call<ConversationList> call = mCheapyService.getConversations();
        call.enqueue(new Callback<ConversationList>() {
            @Override
            public void onResponse(Call<ConversationList> call, Response<ConversationList> response) {

                if (response.isSuccessful()) {
                    showConversation(response.body());
                } else {
                    Toast toast = Toast.makeText(getActivity(), "ERROR: The conversations couldn't load correctly", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<ConversationList> call, Throwable t) {
                Toast toast = Toast.makeText(getActivity(), "ERROR: Check your internet connection", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void showConversation(ConversationList body) {

    }

    private void iniVariables(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.chats_recyclerview);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        //adapter = new
    }

}



