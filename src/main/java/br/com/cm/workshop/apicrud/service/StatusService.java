package br.com.cm.workshop.apicrud.service;

import br.com.cm.workshop.apicrud.enums.Status;
import br.com.cm.workshop.apicrud.model.NotaFiscal;
import br.com.cm.workshop.apicrud.repository.NotaFiscalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@Service
public class StatusService {

    @Autowired
    private NotaFiscalRepository notaFiscalRepository;

    public NotaFiscal alteraStatusDeNotaFiscal(Long id, Map<Object, Object> campos) {
        if (notaFiscalRepository.existsById(id)) {
            Optional<NotaFiscal> optional = notaFiscalRepository.findById(id);
            if (optional.isPresent()) {
                NotaFiscal notaFiscal = optional.get();
                campos.forEach((k, v) -> {
                    Field field = ReflectionUtils.findField(NotaFiscal.class, (String) k);
                    field.setAccessible(true);
                    if(aplicaRegrasDeNegociosStatus(notaFiscal,v.toString()))
                        ReflectionUtils.setField(field, notaFiscal, converteStringEmEnumStatus(v.toString()));
                });
                return notaFiscalRepository.saveAndFlush(notaFiscal);
            } else
                throw new EntityNotFoundException("A nota fiscal com a identificação informada não foi encontrada ! ");
        } else
            throw new EntityNotFoundException("A nota fiscal com a identificação informada não existe ! ");
    }


    public Status converteStringEmEnumStatus(String status) {
        Status statusAtualizado;
        switch (status) {
            case "PENDENTE":
                statusAtualizado = Status.PENDENTE;
                break;
            case "EM_PROCESSAMENTO":
                statusAtualizado = Status.EM_PROCESSAMENTO;
                break;
            case "APROVADA":
                statusAtualizado = Status.APROVADA;
                break;
            case "COM_ERRO":
                statusAtualizado = Status.COM_ERRO;
                break;
            case "CANCELADA":
                statusAtualizado = Status.CANCELADA;
                break;
            default:
                throw new UnsupportedOperationException("Esse status não existe");
        }
        return statusAtualizado;
    }

    public Boolean aplicaRegrasDeNegociosStatus(NotaFiscal notaFiscal, String statusNovo) {

        Boolean retorno = false;

        if (statusNovo.equals("CANCELADA") && (notaFiscal.getStatus().equals(Status.EM_PROCESSAMENTO) || notaFiscal.getStatus().equals(Status.CANCELADA)))
            throw new UnsupportedOperationException("Você não pode alterar o status para 'CANCELADA' pois o status atual está em 'EM_PROCESSAMENTO' ou 'CANCELADA' ");
        else if (statusNovo.equals("CANCELADA") && !(notaFiscal.getStatus().equals(Status.EM_PROCESSAMENTO) || notaFiscal.getStatus().equals(Status.CANCELADA))){
            retorno = true;
        }else if (statusNovo.equals("EM_PROCESSAMENTO") && (notaFiscal.getStatus().equals(Status.PENDENTE) || notaFiscal.getStatus().equals(Status.COM_ERRO))) {
            retorno = true;
        } else if (statusNovo.equals("EM_PROCESSAMENTO") && !(notaFiscal.getStatus().equals(Status.PENDENTE) || notaFiscal.getStatus().equals(Status.COM_ERRO)))
            throw new UnsupportedOperationException("Você não pode alterar o status para 'EM_PROCESSAMENTO' pois o status atual está em 'CANCELADA' ou 'APROVADA' ");
        if (statusNovo.equals("APROVADA") && notaFiscal.getStatus().equals(Status.EM_PROCESSAMENTO)) {
            retorno = true;
        }else  if (statusNovo.equals("APROVADA") && !(notaFiscal.getStatus().equals(Status.EM_PROCESSAMENTO))){
            throw new UnsupportedOperationException("Você não pode alterar o status para 'APROVADA' pois o status atual não está em 'EM_PROCESSAMENTO' ");
        }else {
            retorno = true;
        }

        return retorno;
    }

}
