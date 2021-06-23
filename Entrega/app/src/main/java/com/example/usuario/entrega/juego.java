package com.example.usuario.entrega;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class juego extends AppCompatActivity {
    private ImageView imJugador;
    private ImageView imCPU;
    private ImageView imMedio;
    private TextView ju;
    private TextView rJ;
    private TextView rC;
    private int jugadas=0;
    private int gJ=0;
    private int gC=0;
    private ImageButton bPi;
    private ImageButton bPa;
    private ImageButton bTi;
    private TextView resultado;
    private Button bHi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        imCPU= findViewById(R.id.imCPU);
        imJugador=findViewById(R.id.imJugador);
        imMedio=findViewById(R.id.imMedio);
        ju=findViewById(R.id.jugadas);
        bPi=findViewById(R.id.bPiedra);
        bPa=findViewById(R.id.bPapel);
        bTi=findViewById(R.id.bTijera);
        resultado=findViewById(R.id.result);
        rJ=findViewById(R.id.rJugador);
        rC=findViewById(R.id.rCPU);
        bHi=findViewById(R.id.bHistorial);
    }

    public void resultado (View v){
        if (v.getId()==R.id.bPiedra){
            jugar (0);
        }
        if (v.getId()==R.id.bPapel){
            jugar (1);
        }
        if (v.getId()==R.id.bTijera){
            jugar (2);
        }
    }

    private void jugar (int resJ){
        Random r=new Random();
        int resC= r.nextInt(3);
        if (resJ==0) {
            if (resC == 2) {
                imJugador.setImageResource(R.drawable.piedra);
                imCPU.setImageResource(R.drawable.tijera);
                imMedio.setImageResource(R.drawable.gana);
                imJugador.setVisibility(View.VISIBLE);
                imCPU.setVisibility(View.VISIBLE);
                imMedio.setVisibility(View.VISIBLE);
                gJ++;
                jugadas++;
                rJ.setText(Integer.toString(gJ));
                ju.setText("Jugada " + Integer.toString(jugadas));
            }
            else {
                if (resC == 1) {
                    imJugador.setImageResource(R.drawable.piedra);
                    imCPU.setImageResource(R.drawable.papel);
                    imMedio.setImageResource(R.drawable.pierde);
                    imJugador.setVisibility(View.VISIBLE);
                    imCPU.setVisibility(View.VISIBLE);
                    imMedio.setVisibility(View.VISIBLE);
                    gC++;
                    jugadas++;
                    rC.setText(Integer.toString(gC));
                    ju.setText("Jugada " + Integer.toString(jugadas));
                }
                else {
                    imJugador.setImageResource(R.drawable.piedra);
                    imCPU.setImageResource(R.drawable.piedra);
                    imMedio.setImageResource(R.drawable.empate);
                    imJugador.setVisibility(View.VISIBLE);
                    imCPU.setVisibility(View.VISIBLE);
                    imMedio.setVisibility(View.VISIBLE);
                    jugadas++;
                    ju.setText("Jugada " + Integer.toString(jugadas));
                }
            }
        }
        if (resJ==1) {
            if (resC ==0) {
                imJugador.setImageResource(R.drawable.papel);
                imCPU.setImageResource(R.drawable.piedra);
                imMedio.setImageResource(R.drawable.gana);
                imJugador.setVisibility(View.VISIBLE);
                imCPU.setVisibility(View.VISIBLE);
                imMedio.setVisibility(View.VISIBLE);
                gJ++;
                jugadas++;
                rJ.setText(Integer.toString(gJ));
                ju.setText("Jugada " + Integer.toString(jugadas));
            }
            else {
                if (resC == 2) {
                    imJugador.setImageResource(R.drawable.papel);
                    imCPU.setImageResource(R.drawable.tijera);
                    imMedio.setImageResource(R.drawable.pierde);
                    imJugador.setVisibility(View.VISIBLE);
                    imCPU.setVisibility(View.VISIBLE);
                    imMedio.setVisibility(View.VISIBLE);
                    gC++;
                    jugadas++;
                    rC.setText(Integer.toString(gC));
                    ju.setText("Jugada " + Integer.toString(jugadas));
                }
                else {
                    imJugador.setImageResource(R.drawable.papel);
                    imCPU.setImageResource(R.drawable.papel);
                    imMedio.setImageResource(R.drawable.empate);
                    imJugador.setVisibility(View.VISIBLE);
                    imCPU.setVisibility(View.VISIBLE);
                    imMedio.setVisibility(View.VISIBLE);
                    jugadas++;
                    ju.setText("Jugada " + Integer.toString(jugadas));
                }
            }
        }
        if (resJ==2) {
            if (resC == 1) {
                imJugador.setImageResource(R.drawable.tijera);
                imCPU.setImageResource(R.drawable.papel);
                imMedio.setImageResource(R.drawable.gana);
                imJugador.setVisibility(View.VISIBLE);
                imCPU.setVisibility(View.VISIBLE);
                imMedio.setVisibility(View.VISIBLE);
                gJ++;
                jugadas++;
                rJ.setText(Integer.toString(gJ));
                ju.setText("Jugada " + Integer.toString(jugadas));
            }
            else {
                if (resC == 0) {
                    imJugador.setImageResource(R.drawable.tijera);
                    imCPU.setImageResource(R.drawable.piedra);
                    imMedio.setImageResource(R.drawable.pierde);
                    imJugador.setVisibility(View.VISIBLE);
                    imCPU.setVisibility(View.VISIBLE);
                    imMedio.setVisibility(View.VISIBLE);
                    gC++;
                    jugadas++;
                    rC.setText(Integer.toString(gC));
                    ju.setText("Jugada " + Integer.toString(jugadas));
                }
                else {
                    imJugador.setImageResource(R.drawable.tijera);
                    imCPU.setImageResource(R.drawable.tijera);
                    imMedio.setImageResource(R.drawable.empate);
                    imJugador.setVisibility(View.VISIBLE);
                    imCPU.setVisibility(View.VISIBLE);
                    imMedio.setVisibility(View.VISIBLE);
                    gJ++;
                    jugadas++;
                    ju.setText("Jugada " + Integer.toString(jugadas));
                }
            }
        }
        if (gJ==3){
            bPa.setVisibility(View.INVISIBLE);
            bPi.setVisibility(View.INVISIBLE);
            bTi.setVisibility(View.INVISIBLE);
            resultado.setVisibility(View.VISIBLE);
            imMedio.setVisibility(View.INVISIBLE);
            resultado.setText("GANASTE!");
            resultado.setBackgroundResource(R.color.verde);
            bHi.setVisibility(View.VISIBLE);
        }
        if (gC==3){
            bPa.setVisibility(View.INVISIBLE);
            bPi.setVisibility(View.INVISIBLE);
            bTi.setVisibility(View.INVISIBLE);
            resultado.setVisibility(View.VISIBLE);
            imMedio.setVisibility(View.INVISIBLE);
            resultado.setText("PERDISTE!");
            resultado.setBackgroundResource(R.color.rojo);
            bHi.setVisibility(View.VISIBLE);
        }
    }

    public void reset (View v){
        jugadas=0;
        gJ=0;
        gC=0;
        imJugador.setVisibility(View.INVISIBLE);
        imCPU.setVisibility(View.INVISIBLE);
        imMedio.setVisibility(View.INVISIBLE);
        resultado.setVisibility(View.INVISIBLE);
        bHi.setVisibility(View.INVISIBLE);
        bPi.setVisibility(View.VISIBLE);
        bPa.setVisibility(View.VISIBLE);
        bTi.setVisibility(View.VISIBLE);
        ju.setText("Jugada "+Integer.toString(jugadas));
        rC.setText(Integer.toString(gC));
        rJ.setText(Integer.toString(gJ));
    }
}
