package com.example.woocommerce2.creacionPedido;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CreacionPedido {

    private int idProducto, idPersona;

    public CreacionPedido(int idProducto, int idPersona) {
        this.idProducto = idProducto;
        this.idPersona = idPersona;
    }

    public void pedidoEnCurso(){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "{\"customer_id\"}: " + this.idPersona + ", " +
                "\"lineItems\":[{\"product_id\": " + this.idProducto + "}], " +
                "\"status\": \"completed\"}");
        Request request = new Request.Builder()
                .url("https://fct6.essenzialgreen.com/wp-json/wp/v2/orders")
                .method("POST", body)
                .addHeader("Authorization", "Basic ZmN0MUBlc3NlbnppYWxjbG91ZC5lczpJcm9uaWEvMjIvMQ==")
                .addHeader("Cookie", "mailchimp_landing_site=https%3A%2F%2Ffct6.essenzialgreen.com%2Fwp-json%2Fwp%2Fv2%2Forders")
                .build();
        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
