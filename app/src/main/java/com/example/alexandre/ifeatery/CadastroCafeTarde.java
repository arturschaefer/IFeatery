package com.example.alexandre.ifeatery;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.Firebase;

/**
 * Created by Alexandre on 10/06/2016.
 */
public class CadastroCafeTarde extends Activity {
    private EditText refeicao;
    private EditText bebidas;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrocafetarde);
        refeicao = (EditText) findViewById(R.id.etRefeicao);
        bebidas = (EditText) findViewById(R.id.etBebida);
    }

    public void startLogin(View view) {
        Firebase firebase = new Firebase("https://ifeatery2.firebaseio.com/Cardapio");
        firebase.child("CafeDaTarde").child("Refeicao").setValue(refeicao.getText());
        firebase.child("CafeDaTarde").child("Bebida").setValue(bebidas.getText());
    }



}
