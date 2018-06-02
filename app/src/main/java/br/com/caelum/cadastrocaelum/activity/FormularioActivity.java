package br.com.caelum.cadastrocaelum.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.io.File;
import java.io.IOException;

import br.com.caelum.cadastrocaelum.R;
import br.com.caelum.cadastrocaelum.dao.AlunoDAO;
import br.com.caelum.cadastrocaelum.helper.FormularioHelper;
import br.com.caelum.cadastrocaelum.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;
    private String caminhoDaFoto;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        final ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);


        this.helper = new FormularioHelper(this);


        Intent intent = getIntent();

        if (intent.hasExtra("aluno")) {
            Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");

            try {
                helper.colocaAlunoNaTela(aluno);
            } catch (IOException ignored) {
            }
        }


        helper.getTiraFoto().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent vaiParaCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                caminhoDaFoto = getExternalFilesDir("foto") + "/" + System.currentTimeMillis() + ".jpg";
                File arquivo = new File(caminhoDaFoto);
                Uri localDaFoto = Uri.fromFile(arquivo);
                vaiParaCamera.putExtra(MediaStore.EXTRA_OUTPUT, localDaFoto);

                startActivityForResult(vaiParaCamera, 123);
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123){

            if (resultCode == RESULT_OK){
                try {
                    helper.colocaFotoNaTela(caminhoDaFoto);
                } catch (IOException ignored) {
                }
            }
        }

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

                    if (aluno.getId() == null) {

                        alunoDAO.salva(aluno);
                    } else {
                        alunoDAO.atualiza(aluno);
                    }
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
