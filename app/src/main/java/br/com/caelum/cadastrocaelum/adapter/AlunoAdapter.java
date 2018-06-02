package br.com.caelum.cadastrocaelum.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.caelum.cadastrocaelum.R;
import br.com.caelum.cadastrocaelum.modelo.Aluno;

public class AlunoAdapter extends BaseAdapter {

    private List<Aluno> alunos;
    private Activity contexto;

    public AlunoAdapter(List<Aluno> alunos, Activity contexto) {
        this.alunos = alunos;
        this.contexto = contexto;
    }


    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int posicao) {
        return alunos.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return alunos.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup pai) {

        View item = criaOuReutilizaView(view, pai);
        ViewHolder holder = (ViewHolder) item.getTag();

        Aluno aluno = alunos.get(posicao);

        holder.nome.setText(aluno.getNome());

        //todo fazer bitmap e colocar na foto

        return item;
    }

    private View criaOuReutilizaView(View view, ViewGroup pai) {
        LayoutInflater inflater = LayoutInflater.from(contexto);

        View item;

        if (view == null) {
            item = inflater.inflate(R.layout.item_aluno, pai, false);
            ViewHolder holder = new ViewHolder(item);
            item.setTag(holder);

        } else {
            item = view;
        }
        return item;
    }

    class ViewHolder {

        ImageView foto;
        TextView nome;

        ViewHolder(View view) {
            foto = view.findViewById(R.id.item_foto);
            nome = view.findViewById(R.id.item_nome);
        }

    }
}
