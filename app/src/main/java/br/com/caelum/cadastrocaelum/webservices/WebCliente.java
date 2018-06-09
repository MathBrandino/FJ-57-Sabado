package br.com.caelum.cadastrocaelum.webservices;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class WebCliente {

    public static final String URL = "https://www.caelum.com.br/mobile";

    public String buscaMedia(String json) {

        try {
            OkHttpClient client = new OkHttpClient();

            MediaType type = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(type, json);
            Request request = new Request.Builder()
                    .url(URL)
                    .post(body)
                    .build();


            Call call = client.newCall(request);

            Response response = call.execute();

            ResponseBody corpoDaResposta = response.body();

            String media = corpoDaResposta.string();

            return media;


        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

    }

}
