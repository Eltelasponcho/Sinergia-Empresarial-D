package com.example.mauri.sede;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText txt1,txt2;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.entrar);
        txt1 = (EditText) findViewById(R.id.txtu);
        txt2 = (EditText) findViewById(R.id.txtc);
    }

    public void validar(View v) {


        Intent i = new Intent(getApplicationContext(), menufragment.class);
        startActivity(i);
        finish();
    }
}
