package br.com.caelum.cadastrocaelum.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import br.com.caelum.cadastrocaelum.R;
import br.com.caelum.cadastrocaelum.fragment.DetalhesProvaFragment;
import br.com.caelum.cadastrocaelum.fragment.ListaProvasFragment;

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
}
