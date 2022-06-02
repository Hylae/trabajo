package com.example.woocommerce2.AdapterComprado;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.woocommerce2.R;

import java.util.ArrayList;

public class CompletedAdapter extends RecyclerView.Adapter<CompletedAdapter.ViewHolder> {

    ArrayList<CursosComprados> listaCursosComprados;

    public CompletedAdapter(ArrayList listaCursosComprados){
        this.listaCursosComprados = listaCursosComprados;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.completed_view, parent, false);

         return new CompletedAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nombreCursosComprados.setText(listaCursosComprados.get(position).getTitulo());
    }


    @Override
    public int getItemCount() {
        return listaCursosComprados.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreCursosComprados;
        public ViewHolder(@NonNull View v) {
            super(v);
            nombreCursosComprados = v.findViewById(R.id.nombreCursoComprado);
        }
    }

//    private Button tienda, cursosComprados, accionSalida;
//    private Persona persona;
//    private ArrayList<CursosComprados> listaCursosComprados;
//    private ArrayList<Integer> listaCursosCompradosId;
//    private Intent intent;
//    boolean dentro;
//
//
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_opcion);
//
//        tienda = findViewById(R.id.tienda);
//        cursosComprados = findViewById(R.id.cursosComprados);
//        accionSalida = findViewById(R.id.accionSalida);
//
//        dentro = getIntent().getExtras().getBoolean("dentro");
//
//        if (!dentro) {
//            persona = getIntent().getParcelableExtra("persona");
//        } else {
//            AccionesUsuarios au = new AccionesUsuarios(getApplicationContext());
//            int id = getIntent().getExtras().getInt("id");
//            Toast.makeText(this, id + "", Toast.LENGTH_SHORT).show();
//            persona = au.busquedaUsuarioId(id);
//        }
//
//        rellenoListaCursosComprados();
//
//        tienda.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                intent = new Intent(getApplicationContext(), MainActivity.class);
//                intent.putIntegerArrayListExtra("cursosComprados", listaCursosCompradosId);
//                intent.putExtra("persona", persona);
//            }
//        });
//
//
//        //public CompletedAdapter(ArrayList<CursosComprados> listaCursoComprado) {
//        //    }
//
//        accionSalida.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences preferences = getApplicationContext().getSharedPreferences("usuario", Context.MODE_PRIVATE);
//                preferences.edit().clear().commit();
//                startActivity(new Intent(getApplicationContext(), ContenedorFragments.class));
//            }
//        });
//
//    }
//
//    private void rellenoListaCursosComprados() {
//
//        String URl = "https://fct6.essenzialgreen.com/wp-json/wp/v2/orders/?consumer_key=" +
//                "ck_0ff3cc844b329efab98e246f2797f397e9012132" + "&consumer_secret=" + "cs_5a6e7390a635fb3cd73a00ecb25bbef88b35e62f";
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//        listaCursosComprados = new ArrayList<>();
//        listaCursosCompradosId = new ArrayList<>();
//
//        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URl, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                boolean terminado = false;
//                for (int i = 0; i < response.length() && !terminado; i++) {
//                    try {
//                        JSONObject jsonObjectData = response.getJSONObject(i);
//
//                        if (jsonObjectData.getInt("customerId") == persona.getId()) {
//                            if (jsonObjectData.getString("status").equals("completed")) {
//                                JSONArray jsonArray = jsonObjectData.getJSONArray("line_items");
//                                for (int j = 0; j < jsonArray.length(); j++) {
//                                    int id = jsonArray.getJSONObject(j).getInt("product_id");
//                                    CursosComprados cc = new CursosComprados();
//                                    cc.setId(id);
//                                    cc.setTitulo(jsonArray.getJSONObject(j).getString("name"));
//
//                                    listaCursosCompradosId.add(id);
//                                    listaCursosComprados.add(cc);
//                                }
//                            }
//                        } else {
//                            terminado = true;
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
////                Toast.makeText(OpcionActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(OpcionActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        queue.add(request);
//    }



}
