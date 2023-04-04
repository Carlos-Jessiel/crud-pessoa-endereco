package com.api.attornatus.dto.endereco;

import jakarta.validation.constraints.NotNull;

public record DadosDefinirEnderecoPrincipalDto(

        @NotNull
        Long idPessoa,
        @NotNull
        Long idEnderecoPrincipal
) {
}
