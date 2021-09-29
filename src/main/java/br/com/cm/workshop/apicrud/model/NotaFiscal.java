package br.com.cm.workshop.apicrud.model;

import br.com.cm.workshop.apicrud.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotaFiscal implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String nomeCliente;

    private String endereco;

    private String telefone;

    private Double valorTotalProdutos;

    private Double frete;

    private Double valorTotal;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Itens> itens;


}
