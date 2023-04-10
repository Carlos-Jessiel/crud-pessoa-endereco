package com.api.attornatus.controller;

import com.api.attornatus.dto.endereco.DadosCadastroEnderecoDto;
import com.api.attornatus.dto.pessoa.DadosCadastroPessoaDto;
import com.api.attornatus.dto.pessoa.DadosRetornoCadastroPessoaDto;
import com.api.attornatus.model.Endereco;
import com.api.attornatus.model.Pessoa;
import com.api.attornatus.service.endereco.ServiceCadastrarNovoEndereco;
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

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class EnderecoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosCadastroEnderecoDto> dadosCadastroEnderecoDtoJson;

    @Autowired
    private JacksonTester<DadosRetornoCadastroPessoaDto> dadosRetornoCadastroPessoaDtoJson;

    @MockBean
    private ServiceCadastrarNovoEndereco serviceCadastrarNovoEndereco;

    @Test
    @DisplayName("Deveria devolver codigo 405 quando ID não informado no path")
    void cadastrarNovoEndereco_cenario1() throws Exception {
        var response = mvc.perform(post("/endereco"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo 400 quando dados inválidos")
    void cadastrarNovoEndereco_cenario2() throws Exception {
        var response = mvc.perform(post("/endereco/1"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo 201 quando informações estão válidas")
    void cadastrarNovoEndereco_cenario3() throws Exception {
        var modelPessoa = cadastrarPessoa();
        var novoEndereco = new DadosCadastroEnderecoDto(
                "Logradouro",
                "87654321",
                "200",
                "Cidade");

        var modelNovoEndereco = DadosCadastroEnderecoDto.transformarModel(novoEndereco, modelPessoa);
        var dadosRetorno = new DadosRetornoCadastroPessoaDto(modelPessoa, modelNovoEndereco);

        when(serviceCadastrarNovoEndereco.execute(any(), any()))
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED)
                        .body(dadosRetorno));

        var response = mvc.perform(post("/endereco/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCadastroEnderecoDtoJson
                                .write(new DadosCadastroEnderecoDto(
                                        "Logradouro",
                                        "87654321",
                                        "200",
                                        "Cidade"
                                )).getJson()))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonEsperado = dadosRetornoCadastroPessoaDtoJson
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