package br.com.cm.workshop.apicrud.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Itens implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "A descrição do item não pode ser nula")
    private String descricao;

    @NotNull(message = "O preço do item não pode ser nulo")
    private Double precoUnitario;

    @NotNull(message = "A quantidade do item não pode ser nula")
    @Min(value = 1,message = "A quantidade do item deve ser maior que ou igual a 1")
    private Integer quantidade;

    private Double valorTotal;

}
