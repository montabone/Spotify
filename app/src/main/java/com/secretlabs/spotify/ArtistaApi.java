package com.secretlabs.spotify;

/**
 * Created by Mirko on 17-03-2015.
 */
import android.content.Context;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ArtistaApi {

    Context c;
    public ArtistaApi(Context c){
        this.c = c;
    }

    @SuppressWarnings("finally")
    public Artista buscarArtista(final String nombreArtista){
        FutureTask<Artista> future =
                new FutureTask<Artista>(
                        new Callable<Artista>() {
                            @Override
                            public Artista call() throws Exception {
                                Artista artista = null;
                                String nombreArtistaFinal = nombreArtista.replace(" ", "+");
                                String respStr = requestGET("http://ws.spotify.com/search/1/album.json?q="+nombreArtistaFinal);

                                JSONObject respJSON = new JSONObject(respStr);
                                try {
                                    JSONObject artist = respJSON.getJSONObject("albums");

                                    String nombre = artist.getString("name");
                                    String descripcion = artist.getJSONObject("artists").getString("name");
                                    String popularity = artist.getString("popularity");
                                    artista = new Artista(nombre, descripcion, popularity);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                return artista;
                            }
                        });
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.execute(future);
        Artista artista = new Artista();

        try {
            artista = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
            return  artista;
        }
    }

    public static String requestGET(String url){

        String respStr="";

        HttpClient httpClient = new DefaultHttpClient();

        HttpGet get = new HttpGet(url);

        get.setHeader("content-type", "application/json");

        try {
            HttpResponse resp = httpClient.execute(get);
            respStr = EntityUtils.toString(resp.getEntity());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return respStr;

    }


}
