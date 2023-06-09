package com.api.attornatus.controller;

import com.api.attornatus.dto.endereco.DadosAtualizacaoEnderecoDto;
import com.api.attornatus.dto.endereco.DadosCadastroEnderecoDto;
import com.api.attornatus.dto.pessoa.DadosAtualizacaoPessoaDto;
import com.api.attornatus.dto.pessoa.DadosCadastroPessoaDto;
import com.api.attornatus.dto.pessoa.DadosRetornoAtualizacaoPessoaDto;
import com.api.attornatus.dto.pessoa.DadosRetornoCadastroPessoaDto;
import com.api.attornatus.model.Endereco;
import com.api.attornatus.model.Pessoa;
import com.api.attornatus.service.pessoa.ServiceAtualizarDadosPessoa;
import com.api.attornatus.service.pessoa.ServiceCadastrarPessoa;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class PessoaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosCadastroPessoaDto> dadosCadastroPessoaDtoJson;

    @Autowired
    private JacksonTester<DadosRetornoCadastroPessoaDto> dadosRetornoCadastroPessoaDtoJson;

    @Autowired
    private JacksonTester<DadosAtualizacaoPessoaDto> dadosAtualizacaoPessoaDtoJson;

    @Autowired
    private JacksonTester<DadosRetornoAtualizacaoPessoaDto> dadosRetornoAtualizacaoPessoaDtoJson;

    @MockBean
    private ServiceCadastrarPessoa serviceCadastrarPessoa;

    @MockBean
    private ServiceAtualizarDadosPessoa serviceAtualizarDadosPessoa;

    @Test
    @DisplayName("Deveria devolver codigo 400 quando informações estao inválidas")
    void cadastrar_cenario1() throws Exception {
        var response = mvc.perform(post("/pessoa"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo 201 quando informações estão válidas")
    void cadastrar_cenario2() throws Exception {
        var modelPessoa = cadastrarPessoa();
        var modelEndereco = modelPessoa.getEndereco().get(0);

        var dadosRetorno = new DadosRetornoCadastroPessoaDto(modelPessoa, modelEndereco);

        when(serviceCadastrarPessoa.execute(any()))
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED)
                        .body(dadosRetorno));

        var response = mvc.perform(post("/pessoa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCadastroPessoaDtoJson
                                .write(new DadosCadastroPessoaDto(
                                        modelPessoa.getNome(),
                                        modelPessoa.getDataDeNascimento(),
                                        new DadosCadastroEnderecoDto(modelEndereco.getLogradouro(),
                                                modelEndereco.getCep(),
                                                modelEndereco.getNumero(),
                                                modelEndereco.getCidade())))
                                .getJson()))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonEsperado = dadosRetornoCadastroPessoaDtoJson
                .write(dadosRetorno)
                .getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("Deveria devolver codigo 400 quando informações estao inválidas")
    void atualizarDadosPessoa_cenario1() throws Exception {
        var response = mvc.perform(post("/pessoa"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo 200 alterando os dados do usuario")
    void atualizarDadosPessoa_cenario2() throws Exception {
        var modelPessoa = cadastrarPessoa();

        var dadosAtualizacao = new DadosAtualizacaoPessoaDto(1L, "Nome", null,
                new DadosAtualizacaoEnderecoDto(
                        "Logradouro",
                        "87654321",
                        null,
                        null));

        var modelAtualizada = DadosAtualizacaoPessoaDto.atualizarModel(modelPessoa, dadosAtualizacao);
        var modelEnderecoAtualizada = DadosAtualizacaoEnderecoDto.atualizarModel(dadosAtualizacao.endereco(), modelPessoa.getEndereco().get(0));

        var dadosRetorno = new DadosRetornoAtualizacaoPessoaDto(modelAtualizada, modelEnderecoAtualizada);

        when(serviceAtualizarDadosPessoa.execute(any()))
                .thenReturn(ResponseEntity.status(HttpStatus.OK)
                        .body(dadosRetorno));

        var response = mvc.perform(put("/pessoa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosAtualizacaoPessoaDtoJson
                                .write(new DadosAtualizacaoPessoaDto(
                                        dadosAtualizacao.id(),
                                        dadosAtualizacao.nome(),
                                        dadosAtualizacao.dataDeNascimento(),
                                        dadosAtualizacao.endereco()))
                                .getJson()))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosRetornoAtualizacaoPessoaDtoJson
                .write(dadosRetorno)
                .getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    private Pessoa cadastrarPessoa(){
        List<Endereco> listEndereco = new ArrayList<>();
        var modelPessoa = DadosCadastroPessoaDto.transformModel(dadosCadastroPessoa());
        var modelEndereco = DadosCadastroEnderecoDto.transformarModel(dadosCadastroEndereco(), modelPessoa);
        listEndereco.add(modelEndereco);
        modelPessoa.setEndereco(listEndereco);

        return modelPessoa;
    }

    private DadosCadastroPessoaDto dadosCadastroPessoa(){
        return new DadosCadastroPessoaDto(
                "Pessoa",
                LocalDate.now(),
                null
        );
    }

    private DadosCadastroEnderecoDto dadosCadastroEndereco(){
        return new DadosCadastroEnderecoDto(
                "Endereco",
                "12345678",
                "100",
                "Cidade"
        );
    }
}