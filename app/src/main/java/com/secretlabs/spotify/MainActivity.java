package com.secretlabs.spotify;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.spotify.sdk.android.playback.ConnectionStateCallback;
import com.spotify.sdk.android.playback.PlayerNotificationCallback;
import com.spotify.sdk.android.playback.PlayerState;

public class MainActivity extends Activity implements PlayerNotificationCallback, ConnectionStateCallback {

public EditText editText;
public TextView textView;
    TextView buscarArtista, nombreArtista, descripcionArtista;
    Button buscar;

/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void buscar(View view) {

        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);
        /*Intent i = new Intent (MainActivity.this, resultados.class);
        i.putExtra("artista", editText.getText()+"");
        startActivity(i);*/
        /*String artista = editText.getText().toString();

        textView.setText(artista);


    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ArtistaApi api = new ArtistaApi(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buscarArtista = (TextView) findViewById(R.id.buscarArtista);
        nombreArtista = (TextView) findViewById(R.id.nombreArtista);
        descripcionArtista = (TextView) findViewById(R.id.descripcionArtista);
        buscar = (Button) findViewById(R.id.buscar);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Artista artista = api.buscarArtista(buscarArtista.getText().toString());
                nombreArtista.setText(artista.getNombre());
                descripcionArtista.setText(artista.getDescripcion());

            }
        });

    }


    @Override
    public void onLoggedIn() {

    }

    @Override
    public void onLoggedOut() {

    }

    @Override
    public void onLoginFailed(Throwable throwable) {

    }

    @Override
    public void onTemporaryError() {

    }

    @Override
    public void onConnectionMessage(String s) {

    }

    @Override
    public void onPlaybackEvent(EventType eventType, PlayerState playerState) {

    }

    @Override
    public void onPlaybackError(ErrorType errorType, String s) {

    }
}
