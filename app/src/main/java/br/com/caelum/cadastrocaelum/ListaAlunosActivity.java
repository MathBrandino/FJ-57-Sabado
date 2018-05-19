package br.com.caelum.cadastrocaelum;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListaAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        String[] alunos = {"Gabriel", "Luiz", "Dilan", "Janaina"};

        final ListView lista = findViewById(R.id.lista);


        ArrayAdapter<String> adapter =
                new ArrayAdapter(this,android.R.layout.simple_list_item_1,alunos);

        lista.setAdapter(adapter);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {

                String aluno = (String) lista.getItemAtPosition(posicao);

                Toast.makeText(ListaAlunosActivity.this, aluno, Toast.LENGTH_SHORT).show();

            }
        });


        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long id) {


                Toast.makeText(ListaAlunosActivity.this, "A posição é: " + posicao, Toast.LENGTH_SHORT).show();

                return true;
            }
        });





    }
}
