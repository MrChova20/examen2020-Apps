package com.example.pchoapa_upv.examen2020estrella;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTabHost;

import java.util.Map;

public class MainActivity extends FragmentActivity {
    private static final int SOLICITUD_PERMISO_ACCESS_FINE_LOCATION = 0;
    private FragmentTabHost tabHost;
    private int STORAGE_PERMISSION_CODE = 1;

    static public Map<Integer,Lectura> listaMapa;


    static long TIEMPO[]={0L, 2L, 3L, 6L, 9L, 12L, 14L, 16L};
    static String CIUDAD[]={"Gandia", "Oliva", "Ibi", "Gandia", "Ibi", "Oliva", "Ibi", "Ibi"};
    static int INTENSIDAD[]={1,2,2,1,2,3,1,2};

    String mensaje;
    EditText escribo;
    TextView textoMostrado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this,
                getSupportFragmentManager(), android.R.id.tabcontent);
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Lengüeta 1"),
                Tab1.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Lengüeta 2"),
                Tab2.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("Lengüeta 3"),
                Tab3.class, null);

        listaMapa = Lectura.creaMapa(TIEMPO, CIUDAD, INTENSIDAD);
        Log.d("xEXAMEN", listaMapa.toString());



    }

    public void llamar(View view){

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_CALL,
                    Uri.parse("tel:5555"));
            startActivity(intent);
        } else {
            requestStoragePermission();
        }

    }


    public void servicio(View v) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            startService(new Intent(MainActivity.this,
                    Servicio.class));
            Toast.makeText(this, "Servicio encendido", Toast.LENGTH_SHORT).show();
        } else {
            solicitarPermiso(Manifest.permission.ACCESS_FINE_LOCATION, "Sin el permiso" + " no se podrá leer el sensor de proximidad.", SOLICITUD_PERMISO_ACCESS_FINE_LOCATION, this);
        }
    }

    public void detenerServicio(View view){
        stopService(new Intent(MainActivity.this,
                Servicio.class));
        Toast.makeText(this, "Servicio detenido", Toast.LENGTH_SHORT).show();
    }

    public static void solicitarPermiso(final String permiso, String justificacion, final int requestCode, final Activity actividad) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(actividad, permiso)) {
            new android.app.AlertDialog.Builder(actividad)
                    .setTitle("Solicitud de permiso")
                    .setMessage(justificacion)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            ActivityCompat.requestPermissions(actividad,
                                    new String[]{permiso}, requestCode);
                        }
                    }).show();
        } else {
            ActivityCompat.requestPermissions(actividad, new String[]{permiso}, requestCode);
        }
    }
    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CALL_PHONE)) {
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[] {Manifest.permission.CALL_PHONE},  STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:5555"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void recyclerView(View v) {

        String aTiempo[] = new String[TIEMPO.length];
        String aCiudad[] = new String[CIUDAD.length];
        String aIntensidad[] = new String[INTENSIDAD.length];

        Intent e = new Intent(this, RecyclerActivity.class);
        e.putExtra("tiempos", aTiempo);
        e.putExtra("ciudades", aCiudad);
        e.putExtra("intensidades", aIntensidad);
        e.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(e);

    }



    //4.-Guardar estado
    @Override protected void onSaveInstanceState(Bundle guardarEstado) {
        super.onSaveInstanceState(guardarEstado);
        guardarEstado.putString("textoBoton", String.valueOf(escribo));
    }
    //escribe lo que hay en el otro boton
    @Override protected void onRestoreInstanceState(Bundle recEstado) {
        super.onRestoreInstanceState(recEstado);
        mensaje = recEstado.getString("textoBoton");
        textoMostrado.setText(mensaje);
        Log.d("pruebecita","el mensaje es"+ textoMostrado);
    }
}
