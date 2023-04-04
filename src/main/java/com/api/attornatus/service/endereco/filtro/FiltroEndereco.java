package com.api.attornatus.service.endereco.filtro;


import com.api.attornatus.model.Endereco;

import java.util.List;

public class FiltroEndereco {

    public static Endereco filtrarEnderecoPrimario(List<Endereco> modelEndereco) {
        for (Endereco model : modelEndereco) {
            if (model.getPrincipal().booleanValue()) {
                return model;
            }
        }
        return null;
    }

    public static Endereco filtrarEndereco(List<Endereco> modelEndereco) {
        for (Endereco model : modelEndereco) {
            if (model.getPrincipal().booleanValue()) {
                model.setPrincipal(false);
                return model;
            }
        }
        return null;
    }
}
