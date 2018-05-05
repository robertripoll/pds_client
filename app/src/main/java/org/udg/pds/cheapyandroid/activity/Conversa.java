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
    Conversacio conversacio;

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

        if(!existeixConversa(producte)){

        }
        else{
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

                    final Missatge m = new Missatge(message, new Emisor(1, "pepito")); //canviar id i nom del emisor, per usuari que està en login
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

    private boolean existeixConversa(Producte producte) {

        return true;
    }

    private void crearNovaConversa(Producte producte) {

        Call<Conversacio> call = mCheapyService.addChat(producte);
        call.enqueue(new Callback<Conversacio>() {
            @Override
            public void onResponse(Call<Conversacio> call, Response<Conversacio> response) {

                if (response.isSuccessful()) {
                    conversacio = response.body();

                } else {
                    Toast toast = Toast.makeText(Conversa.this, "Error new chat", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<Conversacio> call, Throwable t) {
                Toast toast = Toast.makeText(Conversa.this, "Error new chat - web" + t.toString()  , Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }


    static class MessageListAdapter extends RecyclerView.Adapter{

        private static final int VIEW_TYPE_MESSAGE_SENT = 1;
        private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
        List<Missatge> list = new ArrayList<>();
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

            Missatge message = (Missatge) list.get(position);

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
                messageText.setText(message.getText());
                nameText.setText(message.getEmisor().getNom());
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {

            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public int getItemViewType(int position) {
            Missatge message = (Missatge) list.get(position);

            if (message.getEmisor().getId() == 1) { // !!!!!!!!!!!!!   S'HA DE MIRAR QUI ES L'USUARI QUE ESTÀ EN LOGIN, EL SEU ID
                // If the current user is the sender of the message
                return VIEW_TYPE_MESSAGE_SENT;
            } else {
                // If some other user sent the message
                return VIEW_TYPE_MESSAGE_RECEIVED;
            }
        }

        // Insert a new item to the RecyclerView
        public void insert(int position, Missatge message) {
            list.add(position, message);
            notifyItemInserted(position);
        }
        // Remove a RecyclerView item containing the Data object
        public void remove(Missatge message) {
            int position = list.indexOf(message);
            list.remove(position);
            notifyItemRemoved(position);
        }


        public void add(Missatge m) {

            list.add(m);
            this.notifyItemInserted(list.size()-1);
        }

        public void clear() {
            int size = list.size();
            list.clear();
            this.notifyItemRangeRemoved(0, size);
        }
    }
}
