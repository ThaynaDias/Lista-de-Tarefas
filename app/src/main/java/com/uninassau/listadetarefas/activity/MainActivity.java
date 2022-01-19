package com.uninassau.listadetarefas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.uninassau.listadetarefas.R;
import com.uninassau.listadetarefas.banco.BancoControlador;

public class MainActivity extends AppCompatActivity {

    private EditText edt_tarefa, edt_obs;
    private Button btn_cadastrar, btn_consultar;
    private BancoControlador bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bd = new BancoControlador(MainActivity.this);
        edt_tarefa = findViewById(R.id.edt_tarefa);
        edt_obs = findViewById(R.id.edt_obs);
        btn_cadastrar = findViewById(R.id.btn_cadastrar);
        btn_consultar = findViewById(R.id.btn_consultar);


        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tarefa = edt_tarefa.getText().toString();
                String obs = edt_obs.getText().toString();
                String msg = bd.inserir(tarefa, obs);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        });

        btn_consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, ConsultaActivity.class);
                startActivity(it);
                finish();
            }
        });
    }
}