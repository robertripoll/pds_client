package org.udg.pds.cheapyandroid.fragment;

import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.activity.Conversa;
import org.udg.pds.cheapyandroid.entity.*;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    }

    private void addActualConversations() {
        Call<LlistaConversacions> call = mCheapyService.getConversations();
        call.enqueue(new Callback<LlistaConversacions>() {
            @Override
            public void onResponse(Call<LlistaConversacions> call, Response<LlistaConversacions> response) {

                if (response.isSuccessful()) {
                    if(response.body().getItems().size() > 0) mostrarConverses(response.body());
                } else if (response==null){
                    Toast toast = Toast.makeText(getActivity(), "ERROR: The conversations couldn't load correctly", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<LlistaConversacions> call, Throwable t) {
                Toast toast = Toast.makeText(getActivity(), "ERROR: Check your internet connection", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void mostrarConverses(final LlistaConversacions llistaConverses) {
        final ArrayAdapter<ConversacioChat> itemsAdapter =
                new ArrayAdapter<ConversacioChat>(getActivity(), android.R.layout.activity_list_item,llistaConverses.getItems());
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
            public void registerDataSetObserver(DataSetObserver dataSetObserver) {}
            @Override
            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {}
            @Override
            public int getCount() {
                return llistaConverses.getItems().size();
            }
            @Override
            public Object getItem(int i) {
                return null;
            }
            @Override
            public long getItemId(int i) { return llistaConverses.getItems().get(i).getId();}
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

                ConversacioChat conv = llistaConverses.getItems().get(position);

                userName.setText(conv.getCompradorConversa().getNom());
                if(conv.getUltimMissatge() == null) userLastMessage.setText("");
                else userLastMessage.setText(conv.getUltimMissatge().toString());

                if(llistaConverses.getItems().get(position).getMissatgesPerLlegir()){
                    TextView userNameTextview = (TextView) rowView.findViewById(R.id.userNameTextview);
                    TextView userMessageTextview = (TextView) rowView.findViewById(R.id.userMessageTextview);
                    userNameTextview.setTypeface(null, Typeface.BOLD);
                    userMessageTextview.setTypeface(null, Typeface.BOLD);
                    userName.setTypeface(null, Typeface.BOLD);
                    userLastMessage.setTypeface(null, Typeface.BOLD);

                    userLastMessage.setTag(position);
                    // Attach the click event handler
                    userLastMessage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int position = (Integer) view.getTag();


                            ConversacioChat conversaAmostrar = itemsAdapter.getItem(position);
                            //Toast.makeText(getActivity(), conversaAmostrar.getId() + "//" + conversaAmostrar.getProducte().getId(), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getActivity(), Conversa.class);
                            intent.putExtra("ConversaAmostrarID", conversaAmostrar.getId());
                            intent.putExtra("Producte_id", conversaAmostrar.getProducte().getId());
                            startActivity(intent);
                        }
                    });
                }

                return rowView;
            }

            @Override
            public int getItemViewType(int i) {
                return 0;
            }
            @Override
            public int getViewTypeCount() {
                return llistaConverses.getItems().size();
            }
            @Override
            public boolean isEmpty() {
                return false;
            }
        });

    }
}
