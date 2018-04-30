package org.udg.pds.cheapyandroid.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.activity.ProducteInfo;
import org.udg.pds.cheapyandroid.entity.*;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;


public class Chats_Fragment extends Fragment {
    CheapyApi mCheapyService;
    private ListView listChatsView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_converses, container, false);
        iniVariables(view);
        addActualConversations();

        return view;
    }

    private void iniVariables(View view) {
        mCheapyService = ((CheapyApp) getActivity().getApplication()).getAPI();
        listChatsView = (ListView) view.findViewById(R.id.llista_converses);
        //recyclerView = (RecyclerView) view.findViewById(R.id.chats_recyclerview);
        //mAdapter = new Adapter(view.getContext());
        //recyclerView.setAdapter(mAdapter);
        //recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    private void addActualConversations() {
        Call<ConversationList> call = mCheapyService.getConversations();
        call.enqueue(new Callback<ConversationList>() {
            @Override
            public void onResponse(Call<ConversationList> call, Response<ConversationList> response) {

                if (response.isSuccessful()) {
                    mostrarProductes(response.body());
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

    private void mostrarProductes(final ConversationList llistaConverses) {

        final ArrayAdapter<LlistaConversacion> itemsAdapter =
                new ArrayAdapter<LlistaConversacion>(getActivity(), android.R.layout.activity_list_item,llistaConverses.getLlistaConversacions());


        listChatsView.setAdapter(new ListAdapter() {

            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }

            @Override
            public boolean isEnabled(int i) {
                return false;
            }

            @Override
            public void registerDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public int getCount() {
                return llistaConverses.getLlistaConversacions().size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return llistaConverses.getLlistaConversacions().get(i).getConversacio().getId();
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getView(int position, View view, ViewGroup viewGroup) {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View rowView = inflater.inflate(R.layout.adapter_llista_converses, null);

                TextView userName = (TextView) rowView.findViewById(R.id.lastMessageUser);
                TextView userLastMessage = (TextView) rowView.findViewById(R.id.lastMessage);

                Conversacio conv = llistaConverses.getLlistaConversacions().get(position).getConversacio();

                userName.setText(conv.getUsuari().getNom());
                userLastMessage.setText(conv.getUltimMissatge().toString());

                if(llistaConverses.getLlistaConversacions().get(position).getConversacio().getMissatgesPerLlegir()){
                    TextView userNameTextview = (TextView) rowView.findViewById(R.id.userNameTextview);
                    TextView userMessageTextview = (TextView) rowView.findViewById(R.id.userMessageTextview);
                    userNameTextview.setTypeface(null, Typeface.BOLD);
                    userMessageTextview.setTypeface(null, Typeface.BOLD);
                    userName.setTypeface(null, Typeface.BOLD);
                    userLastMessage.setTypeface(null, Typeface.BOLD);
                }


                return rowView;
            }

            @Override
            public int getItemViewType(int i) {
                return 0;
            }


            @Override
            public int getViewTypeCount() {
                return llistaConverses.getLlistaConversacions().size();
            }

            @Override
            public boolean isEmpty() {
                return false;
            }


        });

    }
}
