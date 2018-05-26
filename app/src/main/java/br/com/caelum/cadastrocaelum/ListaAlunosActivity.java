package br.com.caelum.cadastrocaelum;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.cadastrocaelum.dao.AlunoDAO;
import br.com.caelum.cadastrocaelum.modelo.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        lista = findViewById(R.id.lista);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {

                Aluno aluno = (Aluno) lista.getItemAtPosition(posicao);

                Toast.makeText(ListaAlunosActivity.this, aluno.getNome(), Toast.LENGTH_SHORT).show();

            }
        });


        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long id) {


                Snackbar.make(lista,"Cliquei", Snackbar.LENGTH_LONG).show();
                //Toast.makeText(ListaAlunosActivity.this, "A posição é: " + posicao, Toast.LENGTH_SHORT).show();

                return true;
            }
        });


        FloatingActionButton fab = findViewById(R.id.lista_fab);


        final Context self = this;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intecao = new Intent(self,FormularioActivity.class);
                startActivity(intecao);

            }
        });



    }


    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    private void carregaLista() {
        AlunoDAO alunoDAO = new AlunoDAO(this);
        List<Aluno> alunos = alunoDAO.getAlunos();
        alunoDAO.close();

        ArrayAdapter<Aluno> adapter =
                new ArrayAdapter(this,android.R.layout.simple_list_item_1,alunos);

        lista.setAdapter(adapter);
    }
}
