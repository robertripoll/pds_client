package org.udg.pds.cheapyandroid.activity;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
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

import static org.udg.pds.cheapyandroid.MyFirebaseInstanceIDService.TOKEN_RECEIVER;

public class Conversa extends AppCompatActivity  {

    private CheapyApi mCheapyService;

    public static Producte producte;
    Venedor venedor;
    public static ConversacioChat conversacio = new ConversacioChat();

    private TextView nomProducte;
    private EditText textChat;
    private Button buttonEnviar;
    private MessageListAdapter mMessageAdapter;
    private RecyclerView mMessageRecycler;
    private Serializable conversaMostrarID;
    private Serializable producteID;
    public static List<Missatge> listMiss = new ArrayList<>();

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                String missatge = intent.getStringExtra("missatge_rebut");
                Long id = Long.valueOf(Integer.valueOf(intent.getStringExtra("id_rebut")));
                carregarMissatgeRebut(id, missatge);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver((receiver),
                new IntentFilter(MyFirebaseMessagingService.REQUEST_ACCEPT));
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversa);

        mCheapyService = ((CheapyApp)this.getApplication()).getAPI();

        conversaMostrarID = getIntent().getSerializableExtra("ConversaAmostrarID");
        if(conversaMostrarID!=null){
            conversacio.setId((Long) conversaMostrarID);
            producteID = getIntent().getSerializableExtra("Producte_id");
            carregarConversa((Long)producteID);
        }
        else{
            producte = (Producte) getIntent().getSerializableExtra("Producte");
            mostrarConversa();

            venedor = producte.getVenedor();
            nomProducte = (TextView) findViewById(R.id.nomProducteChat);
            nomProducte.setText(producte.getNom() + "\n" + producte.getPreu() + "€");

        }

        textChat = (EditText) findViewById(R.id.textMissatgeChat);
        buttonEnviar = (Button) findViewById(R.id.buttonEnviarChat);

        mMessageRecycler = (RecyclerView) findViewById(R.id.rvMissatges);
        mMessageAdapter = new MessageListAdapter(this.getApplication());
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMessageRecycler.setAdapter(mMessageAdapter);

        enviarMissatges();

        mMessageAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);

                mMessageRecycler.scrollToPosition(mMessageAdapter.getItemCount() - 1);
            }
        });
    }

    private void mostrarConversa() {

        Call<LlistaConversacions> call = mCheapyService.getConversations();
        call.enqueue(new Callback<LlistaConversacions>() {
            @Override
            public void onResponse(Call<LlistaConversacions> call, Response<LlistaConversacions> response) {

                if (response.isSuccessful()) {
                    recorregutConversa(response.body());
                } else {
                    Toast toast = Toast.makeText(Conversa.this, "ERROR: The conversations couldn't load correctly", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<LlistaConversacions> call, Throwable t) {
                Toast toast = Toast.makeText(Conversa.this, "ERROR: Check your internet connection", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void recorregutConversa(LlistaConversacions llistaConversacions) {

        List<ConversacioChat> conversacionsItems = llistaConversacions.getItems();

        boolean trobat = false;
        Iterator it_conChat = conversacionsItems.iterator();
        while(!trobat && it_conChat.hasNext()){

            ConversacioChat con_chat = (ConversacioChat) it_conChat.next();
            trobat = con_chat.getProducte().getId().equals(producte.getId());

            if(trobat){
                conversacio.setId(con_chat.getId());
                buscarChat();
            }
        }
        if(!trobat) crearNovaConversa(producte);
    }


    public static class R_Conversa {

        public ID producte;

        R_Conversa(ID producte){
            this.producte = producte;
        }
    }


    public static class ID {

        public Long id;
        public ID(Long id)
        {
            this.id = id;
        }

    }

    private void crearNovaConversa(Producte producte) {

        ID id = new ID(producte.getId());
        R_Conversa conv = new R_Conversa(id);

        Call<ConversacioChat> call = mCheapyService.addChat(conv);
        call.enqueue(new Callback<ConversacioChat>() {
            @Override
            public void onResponse(Call<ConversacioChat> call, Response<ConversacioChat> response) {

                if (response.isSuccessful()) {
                    conversacio = response.body();

                } else {
                    Toast toast = Toast.makeText(Conversa.this, "Error new chat " + response.errorBody() + " // " + response.toString(), Toast.LENGTH_SHORT);
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

    private void carregarConversa(Long id_producte) {

        Call<Producte> call = mCheapyService.getProducte(id_producte);
        call.enqueue(new Callback<Producte>() {
            @Override
            public void onResponse(Call<Producte> call, Response<Producte> response) {

                if(response.isSuccessful()){
                    producte = response.body();

                    venedor = producte.getVenedor();
                    nomProducte = (TextView) findViewById(R.id.nomProducteChat);
                    nomProducte.setText(producte.getNom() + "\n" + producte.getPreu() + "€");

                    buscarChat();
                }
                else{
                    Toast toast = Toast.makeText(Conversa.this, "Error no alhora de buscar un producte per l'id " , Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<Producte> call, Throwable t) {
                Toast toast = Toast.makeText(Conversa.this, "Error internet per getChatID -> "+  t.toString()  , Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void buscarChat() {

        Call<LlistaMissatges> call = mCheapyService.getChatID(conversacio.getId());
        call.enqueue(new Callback<LlistaMissatges>() {
            @Override
            public void onResponse(Call<LlistaMissatges> call, Response<LlistaMissatges> response) {
                listMiss = response.body().getItems();

                List<Missatge> reverseList = new ArrayList<>();
                for(Integer i = listMiss.size() - 1 ; i >= 0; i--){
                    reverseList.add(listMiss.get(i));
                }
                listMiss.clear();
                listMiss.addAll(reverseList);
                mMessageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<LlistaMissatges> call, Throwable t) {
                Toast toast = Toast.makeText(Conversa.this, "Error internet per getChatID -> "+  t.toString()  , Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    public static class R_Missatge{

        String text;

        R_Missatge(String text){
            this.text = text;
        }
    }


    private void enviarMissatges() {

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final EditText editTextChat = (EditText) findViewById(R.id.textMissatgeChat);
                String message = editTextChat.getText().toString();
                if (message.matches("")) {
                    Toast.makeText(Conversa.this, "You did not enter a message", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{

                    //Call<Missatge>  call = mCheapyService.sendMessage(conversacio.getId(), message);
                    Call<Missatge>  call = mCheapyService.sendMessage(conversacio.getId(), new R_Missatge(message));
                    call.enqueue(new Callback<Missatge>() {
                        @Override
                        public void onResponse(Call<Missatge> call, Response<Missatge> response) {

                            if(response.isSuccessful()){

                                Toast toast = Toast.makeText(Conversa.this, "Missatge enviat correctament" , Toast.LENGTH_SHORT);
                                toast.show();
                                mMessageAdapter.add(response.body());
                                editTextChat.setText("");
                            }
                            else{
                                Toast toast = Toast.makeText(Conversa.this, "ERROR: " + response.toString() , Toast.LENGTH_SHORT);
                                toast.show();
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

    private void carregarMissatgeRebut(Long id, String missatge) {

        Emisor em = new Emisor(venedor.getId(), venedor.getNom(), venedor.getSexe());
        Receptor rec = new Receptor(Login.userID_connected, Login.userName_connected, Login.userSexe_connected);
        String estat = "no llegit";
        String dataProva = "11/11/1111";
        Missatge missatgeRebut = new Missatge(id, em, rec, estat, missatge, dataProva);

        mMessageAdapter.add(missatgeRebut);
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
