package com.example.woocommerce2.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.woocommerce2.MainActivity;
import com.example.woocommerce2.opcionesCRUD.AccionesUsuarios;
import com.example.woocommerce2.R;
import com.example.woocommerce2.validacionUsuario.Persona;

import java.util.ArrayList;

public class SegundoFragment extends Fragment {

    private EditText usuario, email, password, confirmarPassword;
    private Button crearUsuario, borrarContenido;

    public SegundoFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_segundo, container, false);

        usuario = v.findViewById(R.id.userNameRegistro);
        email = v.findViewById(R.id.userEmailRegistro);
        password = v.findViewById(R.id.userPassRegistro);
        confirmarPassword = v.findViewById(R.id.passConfirmarRegistro);
        crearUsuario = v.findViewById(R.id.accionCrear);
        borrarContenido = v.findViewById(R.id.accionBorrar);

        crearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String primerP = password.getText().toString();
                String segundoP = confirmarPassword.getText().toString();

                if (primerP.equals(segundoP)){
                    AccionesUsuarios au = new AccionesUsuarios(getContext());
                    String u = usuario.getText().toString();
                    String e = email.getText().toString();
                    String p = password.getText().toString();

                    au.registrarUsuario(u, e, p);

                    Toast.makeText(getContext(), "Registro completado", Toast.LENGTH_SHORT).show();

                    Persona persona = au.comprobarUsuario(u,p);
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.putIntegerArrayListExtra("cursosComprados", new ArrayList<Integer>());
                    intent.putExtra("persona", persona);

                    vaciarContenido();
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(), "Contraseña no coincide", Toast.LENGTH_SHORT).show();
                }
            }


        });

        return v;
    }

    private void vaciarContenido() {
        usuario.setText("");
        email.setText("");
        password.setText("");
        confirmarPassword.setText("");
    }
}
