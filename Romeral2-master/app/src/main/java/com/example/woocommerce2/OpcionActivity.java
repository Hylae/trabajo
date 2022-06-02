package com.example.woocommerce2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.woocommerce2.AdapterComprado.CursosComprados;
import com.example.woocommerce2.opcionesCRUD.AccionesUsuarios;
import com.example.woocommerce2.validacionUsuario.Persona;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OpcionActivity extends AppCompatActivity {

    Button tienda, cursosComprado, accionSalida;
    private Persona persona;
    private ArrayList<CursosComprados> listaCursosComprado;
    private ArrayList<Integer> listaCursosCompradosId;
    private Intent intent;
    boolean dentro;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcion);

        tienda = findViewById(R.id.tienda);
        cursosComprado = findViewById(R.id.cursosComprados);
        accionSalida = findViewById(R.id.accionSalida);

        dentro = getIntent().getExtras().getBoolean("dentro");

        if (!dentro){
            persona = getIntent().getParcelableExtra("persona");
        }else {
            AccionesUsuarios au = new AccionesUsuarios(getApplicationContext());
            int id = getIntent().getExtras().getInt("id");
            Toast.makeText(this, id + "", Toast.LENGTH_SHORT).show();
            persona = au.busquedaUsuarioId(id);
        }

        rellenoListaCursosComprados();

        tienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putIntegerArrayListExtra("cursosComprados",listaCursosCompradosId);
                intent.putExtra("persona", persona);
                startActivity(intent);
            }
        });

        cursosComprado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!listaCursosComprado.isEmpty()){
                    intent = new Intent(getApplicationContext(), CompradoActivity.class);
                    intent.putParcelableArrayListExtra("cursosComprados", listaCursosComprado);
                    startActivity(intent);
                }else{
                    Toast.makeText(OpcionActivity.this, "No tienes ningun curso comprado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        accionSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getApplicationContext().getSharedPreferences("usuario", Context.MODE_PRIVATE);
                preferences.edit().commit();
                startActivity(new Intent(getApplicationContext(), ContenedorFragments.class));
            }
        });



    }

    private void rellenoListaCursosComprados() {

        String URl = "https://fct1.essenzialgreen.com/wp-json/wp/v2/orders/?consumer_key=" +
                "ck_9529eca7e0f771e7066c144deb531a1ccafac871" + "&consumer_secret=" + "cs_1e60316ae123ec6b4131b2ea3eb197e01e1509ec";

        RequestQueue queue = Volley.newRequestQueue(this);
        listaCursosComprado = new ArrayList<>();
        listaCursosCompradosId = new ArrayList<>();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                boolean terminado = false;
                for (int i = 0; i < response.length() && !terminado; i++) {
                    try {
                        JSONObject jsonObjectData = response.getJSONObject(i);

                        if (jsonObjectData.getInt("customerId") == persona.getId()) {
                            if (jsonObjectData.getString("status").equals("completed")) {
                                JSONArray jsonArray = jsonObjectData.getJSONArray("line_items");
                                for (int j = 0; j < jsonArray.length(); j++) {
                                    int id = jsonArray.getJSONObject(j).getInt("product_id");
                                    CursosComprados cc = new CursosComprados();
                                    cc.setId(id);
                                    cc.setTitulo(jsonArray.getJSONObject(j).getString("name"));

                                    listaCursosCompradosId.add(id);
                                    listaCursosComprado.add(cc);
                                }
                            }
                        } else {
                            terminado = true;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OpcionActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }

}
