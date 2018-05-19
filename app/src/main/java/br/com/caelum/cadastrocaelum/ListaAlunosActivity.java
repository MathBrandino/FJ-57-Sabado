package br.com.caelum.cadastrocaelum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListaAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        String[] alunos = {"Gabriel", "Luiz", "Dilan", "Janaina"};

        ListView lista = findViewById(R.id.lista);


        ArrayAdapter<String> adapter =
                new ArrayAdapter(this,android.R.layout.simple_list_item_1,alunos);

        lista.setAdapter(adapter);




    }
}
