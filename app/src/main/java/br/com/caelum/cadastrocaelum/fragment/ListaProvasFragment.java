package br.com.caelum.cadastrocaelum.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import br.com.caelum.cadastrocaelum.R;
import br.com.caelum.cadastrocaelum.activity.ProvaActivity;
import br.com.caelum.cadastrocaelum.modelo.Prova;

public class ListaProvasFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_provas, container, false);


        Prova matematica = new Prova("10/10/2018", "Matematica");
        matematica.setTopicos(Arrays.asList("X", "Y", "Z"));
        Prova portugues = new Prova("11/10/2018", "Portugues");
        portugues.setTopicos(Arrays.asList("A", "B", "C"));

        List<Prova> provas = Arrays.asList(matematica, portugues);


        final ListView lista = view.findViewById(R.id.lista_provas_listview);

        ArrayAdapter<Prova> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, provas);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long l) {
                Prova prova = (Prova) lista.getItemAtPosition(posicao);

                ProvaActivity activity = (ProvaActivity) getActivity();

                activity.lidaComAProvaSelecionada(prova);


            }
        });


        return view;
    }

}
