package org.udg.pds.cheapyandroid.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.google.firebase.database.*;
import org.udg.pds.cheapyandroid.CheapyApp;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.entity.*;
import org.udg.pds.cheapyandroid.rest.CheapyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.*;

public class Conversa extends AppCompatActivity  {

    private CheapyApi mCheapyService;

    Producte producte;
    Venedor venedor;
    ConversacioChat conversacio = new ConversacioChat();
    List<Missatge> listMiss = new ArrayList<>();

    private TextView nomProducte;
    private EditText textChat;
    private Button buttonEnviar;
    private MessageListAdapter mMessageAdapter;
    private RecyclerView mMessageRecycler;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversa);

        mCheapyService = ((CheapyApp)this.getApplication()).getAPI();

        producte = (Producte) getIntent().getSerializableExtra("Producte");
        venedor = producte.getProducte().getVenedor();
        conversacio.setId(Login.userID_connected + producte.getProducte().getId()); //"algoritme" per definir quien id li toca -> id_login + id_producte

        nomProducte = (TextView) findViewById(R.id.nomProducteChat);
        nomProducte.setText(producte.getProducte().getNom() + "\n" + producte.getProducte().getPreu() + "€");
        textChat = (EditText) findViewById(R.id.textMissatgeChat);
        buttonEnviar = (Button) findViewById(R.id.buttonEnviarChat);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chatCheapy");

        mMessageRecycler = (RecyclerView) findViewById(R.id.rvMissatges);
        mMessageAdapter = new MessageListAdapter(this.getApplication());
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMessageRecycler.setAdapter(mMessageAdapter);

        buscarChat();

        if(listMiss.isEmpty()){
            crearNovaConversa(producte);
        }

        enviarMissatges();

        mMessageAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);

                mMessageRecycler.scrollToPosition(mMessageAdapter.getItemCount() - 1);
            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Missatge m = dataSnapshot.getValue(Missatge.class);
                mMessageAdapter.add(m);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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

                    final Missatge m = new Missatge(message, Login.userID_connected, Login.userName_connected);
                    Call<Void>  call = mCheapyService.sendMessage(m.getId(), m);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {

                            if(response.isSuccessful()){

                                databaseReference.push().setValue(m);
                                textChat.setText("");
                            }

                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast toast = Toast.makeText(Conversa.this, "Error enviar missatge " , Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });
                }
            }
        });


    }

    private void buscarChat() {

        Call<List<Missatge>> call = mCheapyService.getChatID(conversacio.getId());
        call.enqueue(new Callback<List<Missatge>>() {
            @Override
            public void onResponse(Call<List<Missatge>> call, Response<List<Missatge>> response) {
                listMiss = response.body();
                mMessageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Missatge>> call, Throwable t) {
                Toast toast = Toast.makeText(Conversa.this, "Error internet per getChatID -> "+  t.toString()  , Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }

    private void crearNovaConversa(final Producte producte) {

        Call<ConversacioChat> call = mCheapyService.addChat(producte);
        call.enqueue(new Callback<ConversacioChat>() {
            @Override
            public void onResponse(Call<ConversacioChat> call, Response<ConversacioChat> response) {

                if (response.isSuccessful()) {
                    conversacio = response.body();
                    conversacio.setId(Login.userID_connected + producte.getProducte().getId());

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
                messageText.setText(message.getText());
                nameText.setText(message.getNom_emisor());

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
                messageText.setText(message.getText());
                nameText.setText(message.getNom_emisor());

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

            if (message.getId_emisor() == Login.userID_connected) {
                // If the current user is the sender of the message
                return VIEW_TYPE_MESSAGE_SENT;
            } else {
                // If some other user sent the message
                return VIEW_TYPE_MESSAGE_RECEIVED;
            }
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
