package br.com.caelum.cadastrocaelum.helper;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import android.widget.RatingBar;

import br.com.caelum.cadastrocaelum.FormularioActivity;
import br.com.caelum.cadastrocaelum.R;
import br.com.caelum.cadastrocaelum.modelo.Aluno;

public class FormularioHelper {

    private Aluno aluno = new Aluno();

    private EditText campoNome;
    private EditText campoEmail;
    private EditText campoTelefone;
    private EditText campoEndereco;
    private RatingBar campoNota;

    private TextInputLayout tilNome;


    public FormularioHelper(FormularioActivity formulario) {

        this.campoNome = formulario.findViewById(R.id.formulario_nome);
        this.campoEmail = formulario.findViewById(R.id.formulario_email);
        this.campoTelefone = formulario.findViewById(R.id.formulario_telefone);
        this.campoEndereco = formulario.findViewById(R.id.formulario_endereco);
        this.campoNota = formulario.findViewById(R.id.formulario_nota);
        this.tilNome = formulario.findViewById(R.id.formulario_til_nome);
    }

    public Aluno pegaAlunoDoFormulario() {

        aluno.setNome(campoNome.getText().toString());
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setEmail(campoEmail.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setNota(Double.valueOf(campoNota.getRating()));


        return aluno;
    }


    public boolean camposValidados() {

        String stringComNome = campoNome.getText().toString();

        return !stringComNome.isEmpty();

    }

    public void mostraErro() {

        carregaErro(tilNome, "Campo não pode estar vazio", campoNome.getText().toString().isEmpty());

    }

    private void carregaErro(TextInputLayout til, String mensagem, boolean mostraErro) {
        if (mostraErro)
            til.setError(mensagem);
    }
}
