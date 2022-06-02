package com.example.woocommerce2.opcionesCRUD;

import android.content.Context;

import com.example.woocommerce2.validacionUsuario.Persona;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AccionesUsuarios {



    private Context context;

    public AccionesUsuarios(Context context){
        this.context = context;
    }

    public void registrarUsuario(String us, String em, String pa){

        boolean resultado = false;

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "{\"" +
                "username\":\"" + us + "\", " +
                "\"email\": \"" + em + "\", "+
                "\"password\": \"" + pa + "\", "+
                "\"description\": \"" + pa + "\", ");


        Request request = new Request.Builder()
                .url("https://fct6.essenzialgreen.com/wp-json/wp/v2/users/")
                .method("POST", body)
                .addHeader("Authorization", "Basic ZmN0MUBlc3NlbnppYWxjbG91ZC5lczpJcm9uaWEvMjIvMQ==")
                .addHeader("Cookie", "mailchimp_landing_site=https%3A%2F%2Ffct6.essenzialgreen.com%2Fwp-json%2Fwp%2Fv2%2Fusers%2F")
                .build();

            try{
            client.newCall(request).execute();
            }catch (IOException e){
                e.printStackTrace();
            }
            }

    public Persona comprobarUsuario(String u, String p) {
        Persona persona = null;

        boolean resultado = false;

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://fct6.essenzialgreen.com/wp-json/wp/v2/users")
                .method("GET", body)
                .addHeader("Authorization", "Basic ZmN0MUBlc3NlbnppYWxjbG91ZC5lczpJcm9uaWEvMjIvMQ==")
                .addHeader("Cookie", "mailchimp_landing_site=https%3A%2F%2Ffct6.essenzialgreen.com%2Fwp-json%2Fwp%2Fv2%2Fusers%2F%3Fconsumer_key%3Dck_9529eca7e0f771e7066c144deb531a1ccafac871%26consumer_secret%3Dcs_1e60316ae123ec6b4131b2ea3eb197e01e1509ec")
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONArray jsonArray = new JSONArray(response.body().string());

            for (int i = 0; i < jsonArray.length() || resultado; i++) {
                JSONObject obj = jsonArray.getJSONObject(i);

                String user = obj.getString("name");
                String pass = obj.getString("description");

                if (u.equals(user) && p.equals(pass)){
                    persona = new Persona();
                    persona.setId(obj.getInt("id"));
                    persona.setDentro(true);
                    resultado = true;
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return persona;
    }



    public Persona busquedaUsuarioId(int id){
        Persona persona = null;

        boolean resultado = false;

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");

        Request request = new Request.Builder()
                .url("https://fct6.essenzialgreen.com/wp-json/wp/v2/users/")
                .get()
                .addHeader("Authorization", "Basic ZmN0MUBlc3NlbnppYWxjbG91ZC5lczpJcm9uaWEvMjIvMQ==")
                .addHeader("Cookie", "mailchimp_landing_site=https%3A%2F%2Ffct6.essenzialgreen.com%2Fwp-json%2Fwp%2Fv2%2Fusers%2F")
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONArray jsonArray = new JSONArray(response.body().string());

            for (int i = 0; i < jsonArray.length() || resultado; i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                int identificador = obj.getInt("id");
                if (identificador == id){
                    persona = new Persona();
                    persona.setId(identificador);
                    persona.setDentro(true);
                    resultado = true;
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

            return persona;

    }


}
