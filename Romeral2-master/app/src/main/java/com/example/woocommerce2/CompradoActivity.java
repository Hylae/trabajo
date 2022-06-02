package com.example.woocommerce2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.woocommerce2.AdapterComprado.CompletedAdapter;
import com.example.woocommerce2.AdapterComprado.CursosComprados;

import java.util.ArrayList;

public class CompradoActivity extends AppCompatActivity {

    CompletedAdapter adapter;
    ArrayList<CursosComprados> listaCursoComprado;
    RecyclerView recyclerCompleted;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objetos_comprados);

        listaCursoComprado = getIntent().getParcelableArrayListExtra("cursosComprados");

        recyclerCompleted = findViewById(R.id.listaCursosComprados);

        recyclerCompleted.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CompletedAdapter(listaCursoComprado);

        recyclerCompleted.setAdapter(adapter);
    }
}