package com.example.pchoapa_upv.examen2020estrella;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private TextView tvDiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tvDiv = (TextView) findViewById(R.id.textView2);
        try {
            String variable_num = getIntent().getStringExtra("numero");
            int numInt = Integer. parseInt(variable_num);
            tvDiv.setText(numInt);
        } catch (Exception err){
            Toast.makeText(this, "Introduce un valor correcto", Toast.LENGTH_SHORT).show();
        }
    }
    /*
    public void boton1(View view){
        try {
            Intent intent = new Intent(this, MainActivity2.class);
            intent.putExtra("numero", et.getText().toString());
            startActivity(intent);

        }  catch (Exception err){
            Toast.makeText(this, "Introduce un valor correcto", Toast.LENGTH_SHORT).show();
        }
    }*/

}