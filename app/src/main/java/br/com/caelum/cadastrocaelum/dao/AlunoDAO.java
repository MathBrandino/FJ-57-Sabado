package br.com.caelum.cadastrocaelum.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.cadastrocaelum.modelo.Aluno;

public class AlunoDAO extends SQLiteOpenHelper {

    public static final String DATABASE = "CadastroEscola";
    public static final int VERSAO = 2;

    public AlunoDAO(Context contexto) {
        super(contexto, DATABASE, null, VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        String sql_aluno = "create table Aluno ( id integer primary key, " +
                "nome text not null, " +
                "email text, " +
                "telefone text, " +
                "endereco text, " +
                "caminhoFoto text, " +
                "nota real );";


        sqLiteDatabase.execSQL(sql_aluno);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int versaoAntiga, int versaoNova) {


        switch (versaoAntiga) {
            case 1:
                String alteracao = "alter table Aluno add column caminhoFoto text;";


                sqLiteDatabase.execSQL(alteracao);

            case 2:

                // coisas da versão dois
        }


    }

    public void salva(Aluno aluno) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues dados = criaContentValues(aluno);


        database.insert("Aluno", null, dados);

    }

    @NonNull
    private ContentValues criaContentValues(Aluno aluno) {
        ContentValues dados = new ContentValues();


        dados.put("nome", aluno.getNome());
        dados.put("email", aluno.getEmail());
        dados.put("endereco", aluno.getEndereco());
        dados.put("telefone", aluno.getTelefone());
        dados.put("nota", aluno.getNota());
        dados.put("caminhoFoto", aluno.getCaminhoFoto());


        return dados;
    }


    public List<Aluno> getAlunos() {

        SQLiteDatabase database = getReadableDatabase();

        String sql = "select * from Aluno order by nome asc;";


        Cursor cursor = database.rawQuery(sql, null);

        List<Aluno> alunos = new ArrayList<>();


        while (cursor.moveToNext()) {
            Aluno aluno = new Aluno();

            aluno.setId(cursor.getLong(cursor.getColumnIndex("id")));

            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            aluno.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            aluno.setNota(cursor.getDouble(cursor.getColumnIndex("nota")));
            aluno.setCaminhoFoto(cursor.getString(cursor.getColumnIndex("caminhoFoto")));

            alunos.add(aluno);
        }

        cursor.close();

        return alunos;


    }

    public void atualiza(Aluno aluno) {

        ContentValues dados = criaContentValues(aluno);

        getWritableDatabase().update("Aluno", dados, "id=?", new String[]{aluno.getId().toString()});
    }

    public void excluir(Aluno aluno) {


        Long alunoId = aluno.getId();

        String idASerDeletado = alunoId.toString();


        getWritableDatabase().delete("Aluno", "id=?", new String[]{idASerDeletado});

    }











}
