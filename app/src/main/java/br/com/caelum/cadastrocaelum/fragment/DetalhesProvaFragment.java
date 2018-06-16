package br.com.caelum.cadastrocaelum.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;

import br.com.caelum.cadastrocaelum.R;
import br.com.caelum.cadastrocaelum.modelo.Prova;

public class DetalhesProvaFragment extends Fragment {

    private TextView data;
    private TextView materia;
    private ListView topicos;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_detalhes_prova, container, false);

        data = view.findViewById(R.id.detalhe_prova_data);
        materia = view.findViewById(R.id.detalhe_prova_materia);
        topicos = view.findViewById(R.id.detalhe_prova_topicos);

        Bundle arguments = getArguments();


        if (arguments != null){

            Prova prova = (Prova) arguments.getSerializable("prova");

            populaCamposCom(prova);

        }
        return view;

    }

    public void populaCamposCom(Prova prova) {

        data.setText(prova.getData());
        materia.setText(prova.getMateria());

        topicos.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, prova.getTopicos()));
    }
}
