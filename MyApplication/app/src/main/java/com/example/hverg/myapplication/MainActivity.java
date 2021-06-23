package com.example.hverg.myapplication;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.graphics.drawable.ArgbEvaluator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView texto=findViewById(R.id.texto);
        texto.setBackgroundColor(Color.);
        new ArgbEvaluator().evaluate(0.75, 0x00ff00, 0xff0000);

        calculo(30);



    }

