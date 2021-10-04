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
import org.aspectj.weaver.ast.Not;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@SpringBootTest
public class StatusControllerTest {

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
    public void deveriaAtualizarStatusComSucesso() throws JsonProcessingException {
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

        NotaFiscal notaFiscalComStatusAtualizado =
                given()
                        .spec(requestSpecification)
                        .body("{ \"status\" : \""+dadoUmStatusComErro()+"\"}")
                        .when()
                        .patch("{id}/status", notaFiscal.getId())
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .as(NotaFiscal.class);
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


    private Status dadoUmStatusComErro() {
        return Status.COM_ERRO;
    }


}
