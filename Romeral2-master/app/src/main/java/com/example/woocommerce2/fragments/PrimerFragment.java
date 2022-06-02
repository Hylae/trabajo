package com.example.woocommerce2.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.woocommerce2.MainActivity;
import com.example.woocommerce2.OpcionActivity;
import com.example.woocommerce2.R;
import com.example.woocommerce2.opcionesCRUD.AccionesUsuarios;
import com.example.woocommerce2.validacionUsuario.Persona;

import java.util.ArrayList;

public class PrimerFragment extends Fragment {

    private Button accionEntrarMain;
    private EditText userName, userPass;
    private Persona persona;
    private TextView entrarTienda, crearCuenta;
    private Intent intent;
    private CheckBox guardarUsuario;

    public PrimerFragment(){ persona = new Persona();}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_primer, container, false);

        userName = v.findViewById(R.id.userLogin);
        userPass = v.findViewById(R.id.passLogin);
        accionEntrarMain = v.findViewById(R.id.accionEntrarMain);
        entrarTienda = v.findViewById(R.id.visualizarTienda);
        guardarUsuario = v.findViewById(R.id.recordatorioUsuario);

        accionEntrarMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = userName.getText().toString();
                String pass = userPass.getText().toString();

                AccionesUsuarios au = new AccionesUsuarios(getContext());
                persona = au.comprobarUsuario(user, pass);

                if (persona!=null){

                    intent = new Intent(getContext(), OpcionActivity.class);
                    intent.putExtra("persona", persona);
                    intent.putExtra("dentro", false);
                    if (guardarUsuario.isChecked()){
                        Toast.makeText(getContext(), "Usuario recordado", Toast.LENGTH_SHORT).show();
                        recordarUsuario(persona.getId());
                    }
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(), "Usuario no registrado", Toast.LENGTH_SHORT).show();
                }
            }


        });

        entrarTienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persona = new Persona();
                persona.setDentro(false);

                intent = new Intent(getContext(), MainActivity.class);
                intent.putIntegerArrayListExtra("cursosComprados", new ArrayList<Integer>());
                intent.putExtra("persona", persona);
                startActivity(intent);
            }
        });
        return v;
    }

    private void recordarUsuario(int id) {
        SharedPreferences preferences = getActivity().getSharedPreferences("usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("entrar", true);
        editor.putInt("id", id);
        editor.commit();
    }


}
