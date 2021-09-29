package br.com.cm.workshop.apicrud.enums;

import lombok.Getter;

@Getter
public enum Status {
    PENDENTE("Pendente"),
    EM_PROCESSAMENTO("Em_processamento"),
    APROVADA("Aprovada"),
    COM_ERRO("Com_erro"),
    CANCELADA("Cancelada");

    private String status;

    Status(String status){
        this.status = status;
    }

}
