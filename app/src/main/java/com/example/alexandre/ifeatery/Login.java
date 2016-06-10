package com.example.alexandre.ifeatery;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


public class Login extends Activity {
    private EditText etLoginPrincipal;
    private EditText etSenhaPrincipal;
    Firebase firebase ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void startLogin(View view) {
        int verificador =0;
        etLoginPrincipal = (EditText) findViewById(R.id.etloginPrincipal);
        etSenhaPrincipal = (EditText) findViewById(R.id.etsenhaPrincipal);

        if(etLoginPrincipal.getText().equals("") || etSenhaPrincipal.getText().equals("")){
            Toast.makeText(this, "Campo em branco", Toast.LENGTH_LONG).show();
        }else {
            Firebase.setAndroidContext(this);

            Firebase firebase = new Firebase("https://ifeatery2.firebaseio.com");

            firebase.authWithPassword(String.valueOf(etLoginPrincipal.getText()), String.valueOf(etSenhaPrincipal.getText()), new Firebase.AuthResultHandler() {
                @Override
                public void onAuthenticated(AuthData authData) {

                    logar();
                }

                @Override
                public void onAuthenticationError(FirebaseError firebaseError) {
                    error();
                }

            });
        }
    }
    public void logar(){
        Intent it = new Intent(this, Home.class);
        Bundle params = new Bundle();
        params.putString("Login", etLoginPrincipal.getText().toString());
        params.putString("Senha", etSenhaPrincipal.getText().toString());
        it.putExtras(params);
        this.startActivity(it);
    }

    public void error(){
        Toast.makeText(this, "Usuario ou senha Invalido", Toast.LENGTH_LONG).show();
    }

    public void admistrador(View view){
        Intent it = new Intent(this, admin.class);
        this.startActivity(it);
    }
}
