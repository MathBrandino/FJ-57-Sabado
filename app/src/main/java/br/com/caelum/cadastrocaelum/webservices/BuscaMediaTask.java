package br.com.caelum.cadastrocaelum.webservices;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.util.List;

import br.com.caelum.cadastrocaelum.converter.AlunoConverter;
import br.com.caelum.cadastrocaelum.dao.AlunoDAO;
import br.com.caelum.cadastrocaelum.interfaces.MediaDelegate;
import br.com.caelum.cadastrocaelum.modelo.Aluno;

public class BuscaMediaTask extends AsyncTask<Void, Void, String> {


    private MediaDelegate delegate;
    private ProgressDialog dialog;

    public BuscaMediaTask(MediaDelegate delegate) {
        this.delegate = delegate;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        dialog = ProgressDialog.show(delegate.getContext(), "Segura ai", "carregando", true, false);

    }

    @Override
    protected String doInBackground(Void... objects) {

        AlunoDAO alunoDAO = new AlunoDAO(delegate.getContext());
        List<Aluno> alunos = alunoDAO.getAlunos();
        alunoDAO.close();

        final String json = new AlunoConverter().toJSON(alunos);


        WebCliente webCliente = new WebCliente();
        String media = webCliente.buscaMedia(json);


        return media;
    }


    @Override
    protected void onPostExecute(String media) {
        dialog.cancel();

        delegate.trata(media);

    }
}
