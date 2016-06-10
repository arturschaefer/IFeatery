package com.example.alexandre.ifeatery;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CafeTarde extends Activity{
    private ListView lwMostraTodos;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafetarde);//Chama a view mostra livros
        lwMostraTodos = (ListView) findViewById(R.id.lwMostraTodosOsLivros);
    }

    @Override
    protected void onResume(){
        super.onResume();

        Firebase refeicao = new Firebase("https://ifeatery2.firebaseio.com/Cardapio");
        refeicao.child("CafeDaManha").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List dados = new ArrayList();
                dados.add(String.valueOf(dataSnapshot.child("Refeicao").getValue()));
                dados.add(String.valueOf(dataSnapshot.child("Bebida").getValue()));

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

}
