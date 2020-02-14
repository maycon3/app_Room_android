package br.com.alura.agenda.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

class AgendaMigrations {

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Aluno ADD COLUMN sobrenome TEXT");
        }
    };
    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // temos que criar uma tabela nova
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT, `telefone` TEXT, `email` TEXT)");

            // Copiar dados da tabela antiga para nova
            database.execSQL("INSERT INTO Aluno_novo (id, nome, telefone, email) SELECT id, nome, telefone, email FROM Aluno");

            // Remover a tabela antiga
            database.execSQL("DROP TABLE Aluno");

            // Renomar a tabela nova com o nome da tabela antiga
            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
        }
    };
    private static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Aluno ADD COLUMN momentoDeCadastro INTEGER");
        }
    };
    private static final Migration MIGRATION_4_5 = new Migration(4,5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " `nome` TEXT," +
                    " `telefoneFixo` TEXT," +
                    " `email` TEXT," +
                    " `dataDeCadastro` INTEGER," +
                    " `telefoneCelular` TEXT)");

            database.execSQL("INSERT INTO Aluno_novo (id, nome, telefoneFixo, email,momentoDeCadastro)" +
                    " SELECT id, nome, telefone, email,momentoDeCadastro FROM Aluno");

            database.execSQL("DROP TABLE Aluno");
            
            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
        }
    };

    static final Migration[] TODAS_MIGRATION = {MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4};
}