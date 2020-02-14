package br.com.alura.agenda.database.conversor;

import android.arch.persistence.room.TypeConverter;

import br.com.alura.agenda.model.TipoTelefone;

public class ConversorTelefone {

    @TypeConverter
    public String paraString(TipoTelefone valor) {
        return valor.name();
    }

    @TypeConverter
    public TipoTelefone paraTipoTelefone(String valor) {
        if(valor != null) {
            return TipoTelefone.valueOf(valor);
        }
        return TipoTelefone.FIXO;
    }
}
