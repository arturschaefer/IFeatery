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
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class admin extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void CadastroCafeManha(View view) {
        Intent it = new Intent(this,CadastroCafeManha.class);
        this.startActivity(it);
    }

    public void CadastroAlmoco(View view) {
        Intent it = new Intent(this, CadastroAlmoco.class);
        this.startActivity(it);
    }
    public void CadastroCafeTarde(View view) {
        Intent it = new Intent(this, CadastroCafeTarde.class);
        this.startActivity(it);
    }
    public void CadastroJanta(View view) {
        Intent it = new Intent(this, CadastroJanta.class);
        this.startActivity(it);
    }

}
