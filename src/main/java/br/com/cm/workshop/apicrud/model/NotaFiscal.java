package br.com.cm.workshop.apicrud.model;

import br.com.cm.workshop.apicrud.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O nome do cliente não pode ser vazio")
    private String nomeCliente;

    @NotEmpty(message = "O endereço do cliente não pode ser vazio")
    private String endereco;

    private String telefone;

    private Double valorTotalProdutos;

    @NotNull
    @Min(value = 0,message = "O valor do frete deve ser maior que ou igual a 0")
    private Double frete;

    private Double valorTotal;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Valid
    @NotEmpty
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Itens> itens;

    public NotaFiscal(Status status){
        this.status = status;
    }

}
