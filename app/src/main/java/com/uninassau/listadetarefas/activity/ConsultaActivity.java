package com.uninassau.listadetarefas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.uninassau.listadetarefas.R;
import com.uninassau.listadetarefas.banco.BancoControlador;
import com.uninassau.listadetarefas.banco.CriaBanco;

public class ConsultaActivity extends AppCompatActivity {

    private ListView listaConsulta;
    BancoControlador bd;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        bd = new BancoControlador(ConsultaActivity.this);
        cursor = bd.carregaDados();
        String[] nomeCampos = new String[] {CriaBanco.ID, CriaBanco.TAREFA, CriaBanco.OBSERVACAO};
        int[] idViews = new int[] {R.id.id_tarefa, R.id.txt_tarefa, R.id.txt_obs};
        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(ConsultaActivity.this, R.layout.activity_consulta, cursor,
                nomeCampos, idViews, 0);
        listaConsulta = findViewById(R.id.listView);
        listaConsulta.setAdapter(adaptador);
        listaConsulta.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndex(CriaBanco.ID));
                Intent intent = new Intent(ConsultaActivity.this, AlteraActivity.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });
    }
}