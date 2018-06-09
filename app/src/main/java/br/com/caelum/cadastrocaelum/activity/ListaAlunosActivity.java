package br.com.caelum.cadastrocaelum.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.cadastrocaelum.R;
import br.com.caelum.cadastrocaelum.adapter.AlunoAdapter;
import br.com.caelum.cadastrocaelum.converter.AlunoConverter;
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


                Snackbar.make(lista, "Cliquei", Snackbar.LENGTH_LONG).show();
                //Toast.makeText(ListaAlunosActivity.this, "A posição é: " + posicao, Toast.LENGTH_SHORT).show();

                return false;
            }
        });


        FloatingActionButton fab = findViewById(R.id.lista_fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intecao = new Intent(self, FormularioActivity.class);
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
        AlunoAdapter adapter = new AlunoAdapter(alunos, this);
        lista.setAdapter(adapter);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {


        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        int posicao = info.position;

        final Aluno aluno = (Aluno) lista.getItemAtPosition(posicao);

        MenuItem excluir = menu.add("Excluir");
        MenuItem ligar = menu.add("Ligar");
        MenuItem msg = menu.add("SMS");
        MenuItem mapa = menu.add("Ver no mapa");
        MenuItem site = menu.add("Site");


        Intent irParaSms = new Intent(Intent.ACTION_VIEW);
        irParaSms.setData(Uri.parse("sms:" + aluno.getTelefone()));

        irParaSms.putExtra("sms_body", "aluno, você é vacilão");

        msg.setIntent(irParaSms);


        Intent irParaMapa = new Intent(Intent.ACTION_VIEW);

        irParaMapa.setData(Uri.parse("geo:0,0?q=" + aluno.getEndereco()));

        mapa.setIntent(irParaMapa);


        ligar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent fazLigacao = new Intent(Intent.ACTION_CALL);

                fazLigacao.setData(Uri.parse("tel:" + aluno.getTelefone()));

                if (ActivityCompat.checkSelfPermission(ListaAlunosActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    String[] permissoes = {Manifest.permission.CALL_PHONE};
                    if (ehSuperiorAVersao6DoAndroid()) {
                        requestPermissions(permissoes, 1);
                    }

                    return false;
                }
                startActivity(fazLigacao);

                return true;
            }

            private boolean ehSuperiorAVersao6DoAndroid() {
                return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
            }
        });



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


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.lista_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()){
            case R.id.lista_envia_notas:

                AlunoDAO alunoDAO = new AlunoDAO(this);
                List<Aluno> alunos = alunoDAO.getAlunos();
                alunoDAO.close();

                String json = new AlunoConverter().toJSON(alunos);

                Toast.makeText(this, json, Toast.LENGTH_SHORT).show();
        }


        return super.onOptionsItemSelected(item);
    }
}
