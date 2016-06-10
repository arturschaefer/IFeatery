package com.example.alexandre.ifeatery;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Home extends Activity {
    private String login;
    private String senha;
    private TextView saldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent it = getIntent();
        if (it != null){
            Bundle params = it.getExtras();
            if (params != null){
                this.login = params.getString("Login");
                this.senha = params.getString("Senha");
                System.out.println("logina e senha "+""+senha);
            }
        }


        NotificationManager mn = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        mn.cancel(R.drawable.ic_launcher);

    }

    public void cafemanha(View view){
        Intent inte = new Intent(this,CafeManha.class);
        startActivity(inte);
    }

    public void almoco(View view){
        Intent inte = new Intent(this,Almoco.class);
        startActivity(inte);
    }

    public void cafetarde(View view){
        Intent inte = new Intent(this,CafeTarde.class);
        startActivity(inte);
    }

    public void janta(View view){
        Intent inte = new Intent(this,Janta.class);
        startActivity(inte);
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

    @Override
    protected void onResume(){
        super.onResume();

        Firebase saldo = new Firebase("https://ifeatery2.firebaseio.com");
        saldo.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List dados = new ArrayList();
                dados.add(String.valueOf(dataSnapshot.child("email").getValue()));
                dados.add(String.valueOf(dataSnapshot.child("nome").getValue()));
                setSaldo(String.valueOf(dataSnapshot.child("saldo").getValue()));
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void setSaldo(Object obj){
        saldo = (TextView) findViewById(R.id.saldo);
        saldo.setText("Saldo: "+String.valueOf(obj));
    }

}
