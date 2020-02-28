package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import br.com.alura.agenda.database.dao.TelefoneDao;
import br.com.alura.agenda.model.Telefone;

public class BuscaPrimeiroTelefoneDoAluno extends AsyncTask<Void, Void, Telefone> {

    private final TelefoneDao dao;
    private final TextView campoTelefone;
    private final int alunoId;

    public BuscaPrimeiroTelefoneDoAluno(TelefoneDao dao, TextView campoTelefone, int alunoId) {
        this.dao = dao;
        this.campoTelefone = campoTelefone;
        this.alunoId = alunoId;
    }

    @Override
    protected Telefone doInBackground(Void... voids) {
        return  dao.buscaPrimeiroTelefoneDoAluno(alunoId);
    }

    @Override
    protected void onPostExecute(Telefone primeiroTelefone) {
        super.onPostExecute(primeiroTelefone);
        campoTelefone.setText(primeiroTelefone.getNumero());
    }
}
