package org.udg.pds.cheapyandroid.activity;

import android.app.Application;
import android.content.Context;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.google.firebase.database.*;
import de.hdodenhof.circleimageview.CircleImageView;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.MyFirebaseInstanceIDService;
import org.udg.pds.cheapyandroid.MyFirebaseMessagingService;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.entity.*;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.Serializable;
import java.util.*;

public class Conversa extends AppCompatActivity  {

    private CheapyApi mCheapyService;

    Producte producte;
    Venedor venedor;
    ConversacioChat conversacio = new ConversacioChat();

    private TextView nomProducte;
    private EditText textChat;
    private Button buttonEnviar;
    private MessageListAdapter mMessageAdapter;
    private RecyclerView mMessageRecycler;
    private Serializable conversaMostrarID;
    private boolean conversaExistent = false;
    public static List<Missatge> listMiss = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversa);

        mCheapyService = ((CheapyApp)this.getApplication()).getAPI();
        MyFirebaseInstanceIDService myFireBaseInsID = new MyFirebaseInstanceIDService();
        MyFirebaseMessagingService myFireBaseMess = new MyFirebaseMessagingService();

        myFireBaseInsID.onTokenRefresh();

        conversaMostrarID = getIntent().getSerializableExtra("ConversaAmostrarID");
        if(conversaMostrarID!=null){
            conversacio.setId((Integer)conversaMostrarID);
            conversaExistent = true;
            Toast toast = Toast.makeText(Conversa.this, "conversaID -> "+  conversaMostrarID , Toast.LENGTH_SHORT);
            toast.show();
        }

        producte = (Producte) getIntent().getSerializableExtra("Producte");
        if(producte != null) {

            venedor = producte.getVenedor();
            conversacio.setId(Login.userID_connected + producte.getId()); //"algoritme" per definir quien id li toca -> id_login + id_producte

            nomProducte = (TextView) findViewById(R.id.nomProducteChat);
            nomProducte.setText(producte.getNom() + "\n" + producte.getPreu() + "€");
        }

        textChat = (EditText) findViewById(R.id.textMissatgeChat);
        buttonEnviar = (Button) findViewById(R.id.buttonEnviarChat);

        mMessageRecycler = (RecyclerView) findViewById(R.id.rvMissatges);
        mMessageAdapter = new MessageListAdapter(this.getApplication());
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMessageRecycler.setAdapter(mMessageAdapter);

        buscarChat();

        if(listMiss.isEmpty() && !conversaExistent){
            crearNovaConversa(producte);
        }

        String missatgeRebut = (String) getIntent().getSerializableExtra("Missatge");

        if(missatgeRebut != null){
            mMessageAdapter.add(new Missatge(missatgeRebut));
        }

        enviarMissatges();

        mMessageAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);

                mMessageRecycler.scrollToPosition(mMessageAdapter.getItemCount() - 1);
            }
        });



    }

    private void buscarChat() {

        Call<LlistaMissatges> call = mCheapyService.getChatID(conversacio.getId());
        call.enqueue(new Callback<LlistaMissatges>() {
            @Override
            public void onResponse(Call<LlistaMissatges> call, Response<LlistaMissatges> response) {
                listMiss = response.body().getItems();
                mMessageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<LlistaMissatges> call, Throwable t) {
                Toast toast = Toast.makeText(Conversa.this, "Error internet per getChatID -> "+  t.toString()  , Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }


    private void enviarMissatges() {

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText editTextChat = (EditText) findViewById(R.id.textMissatgeChat);
                String message = editTextChat.getText().toString();
                if (message.matches("")) {
                    Toast.makeText(Conversa.this, "You did not enter a message", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{

                    Call<Missatge>  call = mCheapyService.sendMessage(conversacio.getId(), message);
                    call.enqueue(new Callback<Missatge>() {
                        @Override
                        public void onResponse(Call<Missatge> call, Response<Missatge> response) {

                            if(response.isSuccessful()){

                                Toast toast = Toast.makeText(Conversa.this, "Missatge enviat correctament" , Toast.LENGTH_SHORT);
                                toast.show();

                                mMessageAdapter.add(response.body());
                            }

                        }

                        @Override
                        public void onFailure(Call<Missatge> call, Throwable t) {
                            Toast toast = Toast.makeText(Conversa.this, "Error enviar missatge " , Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });
                }
            }
        });


    }


    private void crearNovaConversa(Producte producte) {

        Call<ConversacioChat> call = mCheapyService.addChat(producte.getId());
        call.enqueue(new Callback<ConversacioChat>() {
            @Override
            public void onResponse(Call<ConversacioChat> call, Response<ConversacioChat> response) {

                if (response.isSuccessful()) {
                    conversacio = response.body();

                } else {
                    Toast toast = Toast.makeText(Conversa.this, "Error new chat", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<ConversacioChat> call, Throwable t) {
                Toast toast = Toast.makeText(Conversa.this, "Error new chat - web" + t.toString()  , Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }


    public class MessageListAdapter extends RecyclerView.Adapter{

        private static final int VIEW_TYPE_MESSAGE_SENT = 1;
        private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
        Context context;

        public MessageListAdapter(Context context) {
            this.context = context;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view;

            if (viewType == VIEW_TYPE_MESSAGE_SENT) {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_message_sent, parent, false);
                return new SentMessageHolder(view);
            } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_message_received, parent, false);
                return new ReceivedMessageHolder(view);
            }

            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            Missatge message = (Missatge) listMiss.get(position);

            switch (holder.getItemViewType()) {
                case VIEW_TYPE_MESSAGE_SENT:
                    ((SentMessageHolder) holder).bind(message);
                    break;
                case VIEW_TYPE_MESSAGE_RECEIVED:
                    ((ReceivedMessageHolder) holder).bind(message);
            }

        }

        private class SentMessageHolder extends RecyclerView.ViewHolder {
            TextView messageText, nameText;

            SentMessageHolder(View itemView) {
                super(itemView);

                messageText = (TextView) itemView.findViewById(R.id.text_message_sent);
                nameText = (TextView) itemView.findViewById(R.id.text_message_name);
            }

            void bind(Missatge message) {
                messageText.setText(message.getMissatge());
                nameText.setText(message.getEmisor().getNom());
            }
        }


        private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
            TextView messageText, nameText;

            ReceivedMessageHolder(View itemView) {
                super(itemView);

                messageText = (TextView) itemView.findViewById(R.id.text_message_bodyRc);
                nameText = (TextView) itemView.findViewById(R.id.text_message_nameRc);
            }

            void bind(Missatge message) {
                messageText.setText(message.getMissatge());
                nameText.setText(message.getEmisor().getNom());
            }
        }

        @Override
        public int getItemCount() {
            return listMiss.size();
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {

            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public int getItemViewType(int position) {
            Missatge message = (Missatge) listMiss.get(position);

            if (message.getEmisor().getId() == Login.userID_connected) {
                // If the current user is the sender of the message
                return VIEW_TYPE_MESSAGE_SENT;
            } else {
                // If some other user sent the message
                return VIEW_TYPE_MESSAGE_RECEIVED;
            }
        }

        // Insert a new item to the RecyclerView
        public void insert(int position, Missatge message) {
            listMiss.add(position, message);
            notifyItemInserted(position);
        }
        // Remove a RecyclerView item containing the Data object
        public void remove(Missatge message) {
            int position = listMiss.indexOf(message);
            listMiss.remove(position);
            notifyItemRemoved(position);
        }


        public void add(Missatge m) {

            listMiss.add(m);
            this.notifyItemInserted(listMiss.size()-1);
        }

        public void clear() {
            int size = listMiss.size();
            listMiss.clear();
            this.notifyItemRangeRemoved(0, size);
        }
    }
}
