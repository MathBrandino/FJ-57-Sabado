package br.com.caelum.cadastrocaelum.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import br.com.caelum.cadastrocaelum.R;
import br.com.caelum.cadastrocaelum.fragment.DetalhesProvaFragment;
import br.com.caelum.cadastrocaelum.fragment.ListaProvasFragment;
import br.com.caelum.cadastrocaelum.modelo.Prova;

public class ProvaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (taDePe()) {
            transaction.replace(R.id.frame, new ListaProvasFragment());
        } else {
            transaction.replace(R.id.frame_esquerda, new ListaProvasFragment());
            transaction.replace(R.id.frame_direita, new DetalhesProvaFragment());
        }

        transaction.commit();


    }

    private boolean taDePe() {
        return getResources().getBoolean(R.bool.taDePe);
    }

    public void lidaComAProvaSelecionada(Prova prova) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (taDePe()) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            DetalhesProvaFragment detalhes = new DetalhesProvaFragment();


            Bundle argumentos = new Bundle();

            argumentos.putSerializable("prova", prova);

            detalhes.setArguments(argumentos);


            transaction.replace(R.id.frame, detalhes);
            transaction.addToBackStack(null);

            transaction.commit();

        } else {

            DetalhesProvaFragment detalhes = (DetalhesProvaFragment) fragmentManager.findFragmentById(R.id.frame_direita);

            detalhes.populaCamposCom(prova);

        }

    }
}
