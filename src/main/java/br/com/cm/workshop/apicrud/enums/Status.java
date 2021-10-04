package br.com.cm.workshop.apicrud.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public enum Status {
    PENDENTE("PENDENTE"),
    EM_PROCESSAMENTO("EM_PROCESSAMENTO"),
    APROVADA("APROVADA"),
    COM_ERRO("COM_ERRO"),
    CANCELADA("CANCELADA");

    private String status;

    Status(String status){
        this.status = status;
    }


}
