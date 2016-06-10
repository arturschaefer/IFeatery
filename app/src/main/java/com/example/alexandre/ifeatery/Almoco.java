package com.example.alexandre.ifeatery;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Almoco extends Activity{
    private ListView lwMostraTodos;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_almoco);//Chama a view mostra livros
        lwMostraTodos = (ListView) findViewById(R.id.lwMostraTodosOsLivros);
        NotificationManager mn = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        mn.cancel(R.drawable.ic_launcher);

    }


    @Override
    protected void onResume(){
        super.onResume();

        Firebase refeicao = new Firebase("https://ifeatery2.firebaseio.com/Cardapio");
        refeicao.child("Almoco").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List dados = new ArrayList();
                dados.add(String.valueOf(dataSnapshot.child("Basico").getValue()));
                dados.add(String.valueOf(dataSnapshot.child("Bebida").getValue()));
                dados.add(String.valueOf(dataSnapshot.child("Principal").getValue()));
                dados.add(String.valueOf(dataSnapshot.child("Salada").getValue()));
                dados.add(String.valueOf(dataSnapshot.child("Sobremesa").getValue()));
                gerarNotificacao();
                listview2(dados);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    public void listview2(List lista){
        ArrayAdapter adp = new ArrayAdapter(this,android.R.layout.simple_list_item_1,lista);
        lwMostraTodos.setAdapter(adp);
    }

    public void gerarNotificacao(){
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(this,0,new Intent(this,CafeTarde.class),0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setTicker("Cardápio Atualizado");
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.icone));
        builder.setContentTitle("Cardápio Atualizado");
        builder.setContentText("Veja a atualização do cardápio");
        builder.setSmallIcon(R.drawable.icone);
        Notification n =  builder.build();

        n.vibrate = new long[]{150,300,150,600};
        nm.notify(R.drawable.ic_launcher,n);

        try{
            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone toque = RingtoneManager.getRingtone(this,som);
            toque.play();
        }catch (Exception e){}

    }

    public void verificarNotificacao(){
        Firebase refeicao = new Firebase("https://ifeatery2.firebaseio.com/Cardapio");
        refeicao.child("Almoco").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List dados = new ArrayList();
                dados.add(String.valueOf(dataSnapshot.child("Basico").getValue()));
                dados.add(String.valueOf(dataSnapshot.child("Bebida").getValue()));
                dados.add(String.valueOf(dataSnapshot.child("Principal").getValue()));
                dados.add(String.valueOf(dataSnapshot.child("Salada").getValue()));
                dados.add(String.valueOf(dataSnapshot.child("Sobremesa").getValue()));

                listview2(dados);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

}
