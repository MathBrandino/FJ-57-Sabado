package br.com.caelum.cadastrocaelum;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

        final Context self = this;

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {


                Aluno aluno = (Aluno) lista.getItemAtPosition(posicao);

                Intent edicao = new Intent(self, FormularioActivity.class);

                edicao.putExtra("aluno", aluno);

                startActivity(edicao);


            }
        });


        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long id) {


                Snackbar.make(lista,"Cliquei", Snackbar.LENGTH_LONG).show();
                //Toast.makeText(ListaAlunosActivity.this, "A posição é: " + posicao, Toast.LENGTH_SHORT).show();

                return false;
            }
        });


        FloatingActionButton fab = findViewById(R.id.lista_fab);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intecao = new Intent(self,FormularioActivity.class);
                startActivity(intecao);

            }
        });


        registerForContextMenu(lista);

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


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {


        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        int posicao = info.position;

        final Aluno aluno = (Aluno) lista.getItemAtPosition(posicao);

        MenuItem excluir = menu.add("Excluir");
        MenuItem ligar = menu.add("Ligar");
        MenuItem msg = menu.add("SMS");
        MenuItem mapa = menu.add("Ver no mapa");
        MenuItem site = menu.add("Site");

        excluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                final ListaAlunosActivity contexto = ListaAlunosActivity.this;

                new AlertDialog.Builder(contexto)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Atenção")
                        .setMessage("Deseja realmente excluir o aluno : " + aluno.getNome() + " ?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                AlunoDAO dao = new AlunoDAO(contexto);

                                dao.excluir(aluno);

                                dao.close();

                                carregaLista();
                            }
                        })
                        .setNegativeButton("Não", null)
                        .show();


                return true;
            }
        });


    }

}
