package com.uninassau.listadetarefas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.uninassau.listadetarefas.R;
import com.uninassau.listadetarefas.banco.BancoControlador;
import com.uninassau.listadetarefas.banco.CriaBanco;

public class AlteraActivity extends AppCompatActivity {

    private EditText edt_tarefa;
    private EditText edt_obs;
    private Button btn_alterar, btn_apagar;
    private Cursor cursor;
    private BancoControlador bd;
    private String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera);

        Intent it = getIntent();
        codigo = it.getStringExtra("codigo");
        bd = new BancoControlador(AlteraActivity.this);

        edt_obs = findViewById(R.id.edt_obs);
        edt_tarefa = findViewById(R.id.edt_tarefa);
        btn_alterar = findViewById(R.id.btn_alterar);
        btn_apagar = findViewById(R.id.btn_apagar);

        cursor = bd.carregaDadoById(Integer.parseInt(codigo));
        edt_tarefa.setText(cursor.getString(cursor.getColumnIndex(CriaBanco.TAREFA)));
        edt_obs.setText(cursor.getString(cursor.getColumnIndex(CriaBanco.OBSERVACAO)));

        btn_alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String tarefa = edt_tarefa.getText().toString();
               String obs = edt_obs.getText().toString();
               bd.alteraRegistro(Integer.parseInt(codigo), tarefa, obs);
               Intent it = new Intent(AlteraActivity.this, ConsultaActivity.class);
               startActivity(it);
               finish();
            }
        });

        btn_apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bd.deletaRegistro(Integer.parseInt(codigo));
                Intent it = new Intent(AlteraActivity.this, ConsultaActivity.class);
                startActivity(it);
                finish();
            }
        });

    }
}