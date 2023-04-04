package com.api.attornatus.service.endereco.filtro;


import com.api.attornatus.dto.endereco.DadosDefinirEnderecoPrincipalDto;
import com.api.attornatus.model.Endereco;
import com.api.attornatus.model.Pessoa;

import java.util.List;
import java.util.Objects;

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

    public static Endereco filtrarEnderecoEAlterarStatus(DadosDefinirEnderecoPrincipalDto dados, Pessoa modelPessoa) {
        Endereco modelEndereco = null;
        for (Endereco model : modelPessoa.getEndereco()) {

            if (!Objects.equals(model.getId(), dados.idEnderecoPrincipal())) {
                model.setPrincipal(false);
            } else if (model.getId().longValue() == dados.idEnderecoPrincipal()) {
                model.setPrincipal(true);
                modelEndereco = model;
            }
        }
        return modelEndereco;
    }
}
