package com.example.usuario.a2daentrega;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/* Aqui empieza la Clase Localizacion */
public class Localizacion implements LocationListener {
    MainActivity mainActivity;
    public MainActivity getMainActivity() {
        return mainActivity;
    }
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
    @Override
    public void onLocationChanged(Location loc) {
        // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
        // debido a la deteccion de un cambio de ubicacion
        loc.getLatitude();
        loc.getLongitude();
        this.mainActivity.setLocation(loc);
    }
    @Override
    public void onProviderDisabled(String provider) {
        // Este metodo se ejecuta cuando el GPS es desactivado
        TextView mensaje1 = null;
        mensaje1.findViewById(R.id.coordenadasU);
        mensaje1.setText("GPS Desactivado");
    }
    @Override
    public void onProviderEnabled(String provider) {
        // Este metodo se ejecuta cuando el GPS es activado
        TextView mensaje1 = null;
        mensaje1.findViewById(R.id.coordenadasU);
        mensaje1.setText("GPS Activado");
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
