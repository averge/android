package com.example.hverg.prueba_ubicacion;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.support.graphics.drawable.ArgbEvaluator;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainActivity extends FragmentActivity {
    TextView coordenadas;
    Location casa = null;
    Location trabajo = null;
    Location facultad = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordenadas = (TextView) findViewById(R.id.txtCoordenadas);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            locationStart();
        }
    }

    private void locationStart() {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();
        Local.setMainActivity(this);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) Local);

        coordenadas.setText("Localización agregada");

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationStart();
                return;
            }
        }
    }

    public void colores(){
        TextView fond_casa=findViewById(R.id.f_casa);
        TextView fond_casa2=findViewById(R.id.txt_casa);
        fond_casa.setBackgroundColor(Color.RED);
        fond_casa2.setBackgroundColor(Color.red(3));
    }
    /*public void setLocation(Location loc) {
        //Obtener la direccion de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);
                    direccion.setText("Mi direccion es: \n"
                            + DirCalle.getAddressLine(0));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /* Aqui empieza la Clase Localizacion */

    public double calcularDistancia(Location StartP, Location EndP) {
        int Radius = 6371;// radio de la tierra en  kilómetros
        double lat1 = StartP.getLatitude();
        double lat2 = EndP.getLatitude();
        double lon1 = StartP.getLongitude();
        double lon2 = EndP.getLongitude();
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return Radius * c;
    }

    public class Localizacion implements LocationListener {
        MainActivity mainActivity;

        public MainActivity getMainActivity() {
            return mainActivity;
        }

        public void setMainActivity(MainActivity mainActivity) {
            this.mainActivity = mainActivity;
        }

        public void pres_casa(View view) {

        }


        /*private void guardar() {

            double lat_casa=casa.getLatitude();
            double lat_trabajo=trabajo.getLatitude();
            double lat_facultad=facultad.getLatitude();
            double lon_casa=casa.getLongitude();
            double lon_trabajo=trabajo.getLongitude();
            double lon_facultad=facultad.getLatitude();


        }



/*/

        @SuppressLint("RestrictedApi")
        public void func_casa(final Location loc) {
            int colores;
            float num;
            ArgbEvaluator evaludor = new ArgbEvaluator();
            final Button button = findViewById(R.id.bt_casa);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    casa = loc;
                }
            });
            if (casa != null) {
                TextView tv_casa = findViewById(R.id.txt_casa);
                TextView tcasa= findViewById(R.id.f_casa);
                double distancia = calcularDistancia(loc, casa) * 1000;
                if (distancia < 0.5) {
                    distancia = 0.0;
                }
                distancia = Math.round(distancia * 100) / 100d;
                num=(float)(distancia*1)/500;
                colores= (int) evaludor.evaluate( num , Color.GREEN, Color.RED);
                if (distancia<500) {
                    tv_casa.setBackgroundColor(colores);
                    tcasa.setBackgroundColor(colores);
                }
                else{
                    tv_casa.setBackgroundColor(Color.RED);
                    tcasa.setBackgroundColor(Color.RED);
                }
                String texto_casa = "Estas a " + Double.toString(distancia) + " metros";
                tv_casa.setText(texto_casa);
            }
        }

        @SuppressLint("RestrictedApi")
        public void func_trabajo(final Location loc) {
            int colores;
            float num;
            ArgbEvaluator evaludor = new ArgbEvaluator();
            final Button button = findViewById(R.id.bt_trabajo);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    trabajo = loc;
                }
            });
            if (trabajo != null) {
                TextView tv_trabajo = findViewById(R.id.fondo_trabajo);
                TextView ttrabajo = findViewById(R.id.txt_trabajo);
                double distancia = calcularDistancia(loc, trabajo) * 1000;
                if (distancia < 0.5) {
                    distancia = 0.0;
                }
                distancia = Math.round(distancia * 100) / 100d;
                num=(float)(distancia*1)/500;
                colores= (int) evaludor.evaluate( num , Color.GREEN, Color.RED);
                if (distancia<500) {
                    tv_trabajo.setBackgroundColor(colores);
                    ttrabajo.setBackgroundColor(colores);
                }
                else{
                    tv_trabajo.setBackgroundColor(Color.RED);
                    ttrabajo.setBackgroundColor(Color.RED);
                }
                String texto_casa = "Estas a " + Double.toString(distancia) + " metros";
                ttrabajo.setText(texto_casa);
            }
        }

        @SuppressLint("RestrictedApi")
        public void func_facultad(final Location loc) {
            int colores;
            float num;
            ArgbEvaluator evaludor = new ArgbEvaluator();
            final Button button = findViewById(R.id.bt_facultad);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    facultad = loc;
                }
            });
            if (facultad != null) {
                TextView tv_facultad = findViewById(R.id.txt_facultad);
                TextView tfacultad = findViewById(R.id.fondo_facultad);
                double distancia = calcularDistancia(loc, facultad) * 1000;
                if (distancia < 0.5) {
                    distancia = 0.0;
                }
                distancia = Math.round(distancia * 100) / 100d;
                num=(float)(distancia*1)/500;
                colores= (int) evaludor.evaluate( num , Color.GREEN, Color.RED);
                if (distancia<500) {
                    tv_facultad.setBackgroundColor(colores);
                    tfacultad.setBackgroundColor(colores);
                }
                else{
                    tv_facultad.setBackgroundColor(Color.RED);
                    tfacultad.setBackgroundColor(Color.RED);
                }
                String texto_casa = "Estas a " + Double.toString(distancia) + " metros";
                tv_facultad.setText(texto_casa);
            }
        }
/*
        public int calculo (float p) {
            int c0;
            int c1;
            if (p <= 0.5f) {
                p *= 2;
                c0 = Color.GREEN;
                c1 = Color.YELLOW;
            } else {
                p = (p - 0.5f) * 2;
                c0 = Color.YELLOW;
                c1 = Color.RED;
            }
            int a = ave(Color.alpha(c0), Color.alpha(c1), p);
            int r = ave(Color.red(c0), Color.red(c1), p);
            int g = ave(Color.green(c0), Color.green(c1), p);
            int b = ave(Color.blue(c0), Color.blue(c1), p);
            return Color.argb(a, r, g, b);
        }

        private int ave(int src, int dst, float p) {
            return src + java.lang.Math.round(p * (dst - src));
        }*/

        @Override
        public void onLocationChanged(final Location loc) {
            // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la deteccion de un cambio de ubicacion

            loc.getLatitude();
            loc.getLongitude();
            func_casa(loc);
            func_trabajo(loc);
            func_facultad(loc);

            String Text = "Mi ubicacion actual es: " + "\n Lat = "
                    + loc.getLatitude() + "\n Long = " + loc.getLongitude();
            coordenadas.setText(Text);
            //this.mainActivity.setLocation(loc);
        }

        @Override
        public void onProviderDisabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es desactivado
            coordenadas.setText("GPS Desactivado");
        }

        @Override
        public void onProviderEnabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es activado
            coordenadas.setText("GPS Activado");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
        }
    }
}


