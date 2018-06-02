package br.com.caelum.cadastrocaelum.helper;

import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.io.IOException;

import br.com.caelum.cadastrocaelum.R;
import br.com.caelum.cadastrocaelum.activity.FormularioActivity;
import br.com.caelum.cadastrocaelum.modelo.Aluno;
import br.com.caelum.cadastrocaelum.util.CarregadorDeFoto;

public class FormularioHelper {

    private Aluno aluno = new Aluno();

    private EditText campoNome;
    private EditText campoEmail;
    private EditText campoTelefone;
    private EditText campoEndereco;
    private RatingBar campoNota;

    private FloatingActionButton tiraFoto;
    private ImageView foto;


    private TextInputLayout tilNome;


    public FormularioHelper(FormularioActivity formulario) {

        this.campoNome = formulario.findViewById(R.id.formulario_nome);
        this.campoEmail = formulario.findViewById(R.id.formulario_email);
        this.campoTelefone = formulario.findViewById(R.id.formulario_telefone);
        this.campoEndereco = formulario.findViewById(R.id.formulario_endereco);
        this.campoNota = formulario.findViewById(R.id.formulario_nota);
        this.tilNome = formulario.findViewById(R.id.formulario_til_nome);

        this.tiraFoto = formulario.findViewById(R.id.formulario_foto_btn);
        this.foto = formulario.findViewById(R.id.formulario_foto);
    }

    public Aluno pegaAlunoDoFormulario() {

        aluno.setNome(campoNome.getText().toString());
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setEmail(campoEmail.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setNota(Double.valueOf(campoNota.getRating()));

        aluno.setCaminhoFoto((String) foto.getTag());


        return aluno;
    }


    public FloatingActionButton getTiraFoto() {
        return tiraFoto;
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

    public void colocaAlunoNaTela(Aluno aluno) throws IOException {

        campoNome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());
        campoEmail.setText(aluno.getEmail());
        campoEndereco.setText(aluno.getEndereco());
        campoNota.setRating(aluno.getNota().floatValue());

        if (aluno.getCaminhoFoto() != null)
            colocaFotoNaTela(aluno.getCaminhoFoto());

        this.aluno = aluno;
    }

    public void colocaFotoNaTela(String caminhoDaFoto) throws IOException {


        Bitmap bitmap = CarregadorDeFoto.carrega(caminhoDaFoto);

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, true);

        foto.setImageBitmap(scaledBitmap);

        foto.setTag(caminhoDaFoto);

        foto.setScaleType(ImageView.ScaleType.FIT_XY);


    }








}
