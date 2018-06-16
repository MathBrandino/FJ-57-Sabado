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

import java.util.Arrays;
import java.util.List;

import br.com.caelum.cadastrocaelum.R;
import br.com.caelum.cadastrocaelum.modelo.Prova;

public class ListaProvasFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_provas, container, false);


        Prova matematica = new Prova("10/10/2018", "Matematica");
        Prova portugues = new Prova("11/10/2018", "Portugues");

        List<Prova> provas = Arrays.asList(matematica, portugues);


        ListView lista = view.findViewById(R.id.lista_provas_listview);

        ArrayAdapter<Prova> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, provas);
        lista.setAdapter(adapter);


        return view;
    }

}
