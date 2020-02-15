package br.com.alura.agenda.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import br.com.alura.agenda.model.Telefone;

@Dao
public interface TelefoneDao {

    @Query("SELECT  * FROM Telefone WHERE  alunoId = :alunoId LIMIT 1")
    Telefone buscaPrimeiroTelefoneDoAluno(int alunoId);
}
