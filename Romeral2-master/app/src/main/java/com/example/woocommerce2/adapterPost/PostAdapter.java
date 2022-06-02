package com.example.woocommerce2.adapterPost;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.woocommerce2.DetailActivity;
import com.example.woocommerce2.R;
import com.example.woocommerce2.validacionUsuario.Persona;
import com.squareup.picasso.Picasso;


import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<Post> allPosts;
    private Persona persona;

    public PostAdapter(List<Post> allPosts, Persona persona){
        this.allPosts = allPosts;
        this.persona = persona;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_view, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, final int position) {
        holder.inexistente = false;

        String imagen = allPosts.get(position).getFeature_image();

        if (imagen != null){
            Picasso.get().load(imagen).into(holder.postImage);
        }

        holder.postTitle.setText(allPosts.get(position).getTitle());


        String auxPrecio = allPosts.get(position).getPrecio();

        if (auxPrecio.isEmpty()){
            auxPrecio = "No hay valor";
            holder.postTitle.setTextColor(Color.GRAY);
            holder.postPrecio.setTextColor(Color.GRAY);
            holder.inexistente = true;
        }else{
            Float precio = Float.parseFloat(auxPrecio);
            precio = precio + precio*(21/100);//((21*precio)/100);
            allPosts.get(position).setPrecioIva(precio + "");
            precio = Float.parseFloat(auxPrecio) + precio;
            auxPrecio = "Precio: " + precio + "e";
        }

        holder.postPrecio.setText(auxPrecio);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.inexistente){
                Toast.makeText(v.getContext(), "No hay existencias", Toast.LENGTH_SHORT).show();
                }else{
                    Intent i = new Intent(v.getContext(), DetailActivity.class);
                        i.putExtra("title", allPosts.get(position).getTitle());
                        i.putExtra("curso actual", allPosts.get(position).getContent());
                    i.putExtra("persona", persona);
                    i.putExtra("curso actual", allPosts.get(position));
                    v.getContext().startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return allPosts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView postImage;
        TextView postTitle, postPrecio;
        View view;
        boolean inexistente;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;
            postImage = itemView.findViewById(R.id.post_image);
            postTitle = itemView.findViewById(R.id.post_title);
            postPrecio = itemView.findViewById(R.id.post_price);

        }
    }
}
