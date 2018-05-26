package br.com.caelum.cadastrocaelum;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.caelum.cadastrocaelum.dao.AlunoDAO;
import br.com.caelum.cadastrocaelum.helper.FormularioHelper;
import br.com.caelum.cadastrocaelum.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);


        this.helper = new FormularioHelper(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.formulario_menu, menu);

        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.formulario_menu_salvar:


                Aluno aluno = helper.pegaAlunoDoFormulario();


                if (helper.camposValidados()){

                    AlunoDAO alunoDAO = new AlunoDAO(this);

                    alunoDAO.salva(aluno);

                    alunoDAO.close();

                    finish();
                } else {
                    helper.mostraErro();
                }







                break;

            case android.R.id.home:

                finish();

                break;


        }

        return true;
    }
}
