package br.com.cm.workshop.apicrud.api;


import br.com.cm.workshop.apicrud.enums.Status;
import br.com.cm.workshop.apicrud.model.Itens;
import br.com.cm.workshop.apicrud.model.NotaFiscal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class NotaFiscalControllerTest {

    @Value("${server.port}")
    private int porta;

    private RequestSpecification requestSpecification;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    private void preparaRequesicao() {
        requestSpecification = new RequestSpecBuilder()
                .setBasePath("/api/v1/notas-fiscais")
                .setPort(porta)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    @Test
    public void deveriaBuscarNotasFiscaisComSucesso() {
        given()
                .spec(requestSpecification)
                .expect()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .get();
    }

    @Test
    public void deveriaCriarNotasFiscaisComSucesso() throws JsonProcessingException {
        NotaFiscal notaFiscal =
                given()
                        .spec(requestSpecification)
                        .body(objectMapper.writeValueAsString(dadoUmaNotaFiscalPadrao()))
                        .when()
                        .post()
                        .then()
                        .statusCode(HttpStatus.SC_CREATED)
                        .extract()
                        .as(NotaFiscal.class);

        assertNotNull(notaFiscal);
        assertNotNull(notaFiscal.getId());
    }

    @Test
    public void deveriaCriarNotasFiscaisComValorTotalCorretoComSucesso() throws JsonProcessingException {
        NotaFiscal notaFiscal =
                given()
                        .spec(requestSpecification)
                        .body(objectMapper.writeValueAsString(dadoUmaNotaFiscalComValorTotalCorreto()))
                        .when()
                        .post()
                        .then()
                        .statusCode(HttpStatus.SC_CREATED)
                        .extract()
                        .as(NotaFiscal.class);

        assertNotNull(notaFiscal);
        assertNotNull(notaFiscal.getId());
    }

    @Test
    public void deveriaCriarNotasFiscaisComValorTotalProdutosCorretoComSucesso() throws JsonProcessingException {
        NotaFiscal notaFiscal =
                given()
                        .spec(requestSpecification)
                        .body(objectMapper.writeValueAsString(dadoUmaNotaFiscalComValorTotalProdutosCorreto()))
                        .when()
                        .post()
                        .then()
                        .statusCode(HttpStatus.SC_CREATED)
                        .extract()
                        .as(NotaFiscal.class);

        assertNotNull(notaFiscal);
        assertNotNull(notaFiscal.getId());
    }


    @Test
    public void deveriaAtualizarNotaFiscalComSucesso() throws JsonProcessingException {
        NotaFiscal notaFiscal =
                given()
                        .spec(requestSpecification)
                        .body(objectMapper.writeValueAsString(dadoUmaNotaFiscalPadrao()))
                        .when()
                        .post()
                        .then()
                        .statusCode(HttpStatus.SC_CREATED)
                        .extract()
                        .as(NotaFiscal.class);

        notaFiscal.setNomeCliente("Ronaldo");

        NotaFiscal notaFiscalAtualizada =
                given()
                        .spec(requestSpecification)
                        .body(objectMapper.writeValueAsString(notaFiscal))
                        .when()
                        .put("{id}", notaFiscal.getId())
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .as(NotaFiscal.class);
    }

    @Test
    public void deveriaDeletarNotaFiscalComSucesso() throws JsonProcessingException {
        NotaFiscal notaFiscal =
                given()
                        .spec(requestSpecification)
                        .body(objectMapper.writeValueAsString(dadoUmaNotaFiscalPadrao()))
                        .when()
                        .post()
                        .then()
                        .statusCode(HttpStatus.SC_CREATED)
                        .extract()
                        .as(NotaFiscal.class);

        given()
                .spec(requestSpecification)
                .when()
                .delete("{id}", notaFiscal.getId())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void naoDeveriaCriarNotaFiscalSemNomeDoCliente() throws JsonProcessingException {
        given()
                .spec(requestSpecification)
                .body(objectMapper.writeValueAsString(dadoUmaNotaFiscalSemNomeDoCliente()))
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void naoDeveriaCriarNotaFiscalSemEndereco() throws JsonProcessingException {
        given()
                .spec(requestSpecification)
                .body(objectMapper.writeValueAsString(dadoUmaNotaFiscalSemEndereco()))
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void naoDeveriaCriarNotaFiscalSemFrete() throws JsonProcessingException {
        given()
                .spec(requestSpecification)
                .body(objectMapper.writeValueAsString(dadoUmaNotaFiscalSemFrete()))
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void naoDeveriaCriarNotaFiscalSemItens() throws JsonProcessingException {
        given()
                .spec(requestSpecification)
                .body(objectMapper.writeValueAsString(dadoUmaNotaFiscalSemItens()))
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void naoDeveriaCriarNotaFiscalComFreteValorNegativo() throws JsonProcessingException {
        given()
                .spec(requestSpecification)
                .body(objectMapper.writeValueAsString(dadoUmaNotaFiscalComFreteValorNegativo()))
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void naoDeveriaCriarNotaFiscalComValorTotalIncorreto() throws JsonProcessingException {
        given()
                .spec(requestSpecification)
                .body(objectMapper.writeValueAsString(dadoUmaNotaFiscalComValorTotalIncorreto()))
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);
    }

    @Test
    public void naoDeveriaCriarNotaFiscalComValorTotalProdutosIncorreto() throws JsonProcessingException {
        given()
                .spec(requestSpecification)
                .body(objectMapper.writeValueAsString(dadoUmaNotaFiscalComValorTotalProdutosIncorreto()))
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);
    }

    @Test
    public void naoDeveriaCriarNotaFiscalComStatusDiferenteDePendente() throws JsonProcessingException {
        given()
                .spec(requestSpecification)
                .body(objectMapper.writeValueAsString(dadoUmaNotaFiscalComStatusDiferenteDePendente()))
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);
    }


    private NotaFiscal dadoUmaNotaFiscalPadrao() {
        NotaFiscal notaFiscal = new NotaFiscal();
        List<Itens> itens = new ArrayList<>();
        Itens item = new Itens();
        item.setDescricao("Sorvete");
        item.setQuantidade(1);
        item.setPrecoUnitario(3.5);
        itens.add(item);
        notaFiscal.setNomeCliente("JOSE FRANCISCO");
        notaFiscal.setEndereco("Rua A, 500");
        notaFiscal.setTelefone("8532795578");
        notaFiscal.setFrete(9.90);
        notaFiscal.setItens(itens);
        return notaFiscal;
    }

    private NotaFiscal dadoUmaNotaFiscalComStatusDiferenteDePendente() {
        NotaFiscal notaFiscal = new NotaFiscal();
        List<Itens> itens = new ArrayList<>();
        Itens item = new Itens();
        item.setDescricao("Sorvete");
        item.setQuantidade(1);
        item.setPrecoUnitario(3.5);
        itens.add(item);
        notaFiscal.setStatus(Status.APROVADA);
        notaFiscal.setNomeCliente("JOSE FRANCISCO");
        notaFiscal.setEndereco("Rua A, 500");
        notaFiscal.setTelefone("8532795578");
        notaFiscal.setFrete(9.90);
        notaFiscal.setItens(itens);
        return notaFiscal;
    }

    private NotaFiscal dadoUmaNotaFiscalComValorTotalIncorreto() {
        NotaFiscal notaFiscal = new NotaFiscal();
        List<Itens> itens = new ArrayList<>();
        Itens item = new Itens();
        item.setDescricao("Sorvete");
        item.setQuantidade(1);
        item.setPrecoUnitario(3.5);
        itens.add(item);
        notaFiscal.setValorTotal(3.5);
        notaFiscal.setNomeCliente("JOSE FRANCISCO");
        notaFiscal.setEndereco("Rua A, 500");
        notaFiscal.setTelefone("8532795578");
        notaFiscal.setFrete(9.90);
        notaFiscal.setItens(itens);
        return notaFiscal;
    }

    private NotaFiscal dadoUmaNotaFiscalComValorTotalCorreto() {
        NotaFiscal notaFiscal = new NotaFiscal();
        List<Itens> itens = new ArrayList<>();
        Itens item = new Itens();
        item.setDescricao("Sorvete");
        item.setQuantidade(1);
        item.setPrecoUnitario(3.5);
        itens.add(item);
        notaFiscal.setValorTotal(13.40);
        notaFiscal.setNomeCliente("JOSE FRANCISCO");
        notaFiscal.setEndereco("Rua A, 500");
        notaFiscal.setTelefone("8532795578");
        notaFiscal.setFrete(9.90);
        notaFiscal.setItens(itens);
        return notaFiscal;
    }

    private NotaFiscal dadoUmaNotaFiscalComValorTotalProdutosIncorreto() {
        NotaFiscal notaFiscal = new NotaFiscal();
        List<Itens> itens = new ArrayList<>();
        Itens item = new Itens();
        item.setDescricao("Sorvete");
        item.setQuantidade(1);
        item.setPrecoUnitario(3.5);
        itens.add(item);
        notaFiscal.setValorTotalProdutos(3.54);
        notaFiscal.setNomeCliente("JOSE FRANCISCO");
        notaFiscal.setEndereco("Rua A, 500");
        notaFiscal.setTelefone("8532795578");
        notaFiscal.setFrete(9.90);
        notaFiscal.setItens(itens);
        return notaFiscal;
    }

    private NotaFiscal dadoUmaNotaFiscalComValorTotalProdutosCorreto() {
        NotaFiscal notaFiscal = new NotaFiscal();
        List<Itens> itens = new ArrayList<>();
        Itens item = new Itens();
        item.setDescricao("Sorvete");
        item.setQuantidade(1);
        item.setPrecoUnitario(3.5);
        itens.add(item);
        notaFiscal.setValorTotalProdutos(3.5);
        notaFiscal.setNomeCliente("JOSE FRANCISCO");
        notaFiscal.setEndereco("Rua A, 500");
        notaFiscal.setTelefone("8532795578");
        notaFiscal.setFrete(9.90);
        notaFiscal.setItens(itens);
        return notaFiscal;
    }

    private NotaFiscal dadoUmaNotaFiscalSemNomeDoCliente() {
        NotaFiscal notaFiscal = new NotaFiscal();
        List<Itens> itens = new ArrayList<>();
        Itens item = new Itens();
        item.setDescricao("Sorvete");
        item.setQuantidade(1);
        item.setPrecoUnitario(3.5);
        itens.add(item);
        notaFiscal.setEndereco("Rua A, 500");
        notaFiscal.setTelefone("8532795578");
        notaFiscal.setFrete(9.90);
        notaFiscal.setItens(itens);
        return notaFiscal;
    }

    private NotaFiscal dadoUmaNotaFiscalSemEndereco() {
        NotaFiscal notaFiscal = new NotaFiscal();
        List<Itens> itens = new ArrayList<>();
        Itens item = new Itens();
        item.setDescricao("Sorvete");
        item.setQuantidade(1);
        item.setPrecoUnitario(3.5);
        itens.add(item);
        notaFiscal.setNomeCliente("JOSE FRANCISCO");
        notaFiscal.setTelefone("8532795578");
        notaFiscal.setFrete(9.90);
        notaFiscal.setItens(itens);
        return notaFiscal;
    }

    private NotaFiscal dadoUmaNotaFiscalSemFrete() {
        NotaFiscal notaFiscal = new NotaFiscal();
        List<Itens> itens = new ArrayList<>();
        Itens item = new Itens();
        item.setDescricao("Sorvete");
        item.setQuantidade(1);
        item.setPrecoUnitario(3.5);
        itens.add(item);
        notaFiscal.setNomeCliente("JOSE FRANCISCO");
        notaFiscal.setEndereco("Rua A, 500");
        notaFiscal.setTelefone("8532795578");
        notaFiscal.setItens(itens);
        return notaFiscal;
    }

    private NotaFiscal dadoUmaNotaFiscalSemItens() {
        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setEndereco("Rua A, 500");
        notaFiscal.setTelefone("8532795578");
        notaFiscal.setFrete(9.90);
        return notaFiscal;
    }

    private NotaFiscal dadoUmaNotaFiscalComFreteValorNegativo() {
        NotaFiscal notaFiscal = new NotaFiscal();
        List<Itens> itens = new ArrayList<>();
        Itens item = new Itens();
        item.setDescricao("Sorvete");
        item.setQuantidade(1);
        item.setPrecoUnitario(3.5);
        itens.add(item);
        notaFiscal.setEndereco("Rua A, 500");
        notaFiscal.setTelefone("8532795578");
        notaFiscal.setFrete(-9.90);
        notaFiscal.setItens(itens);
        return notaFiscal;
    }

}
