package br.com.cm.workshop.apicrud.service;

import br.com.cm.workshop.apicrud.enums.Status;
import br.com.cm.workshop.apicrud.model.NotaFiscal;
import br.com.cm.workshop.apicrud.repository.NotaFiscalRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class NotaFiscalService {

    @Autowired
    private NotaFiscalRepository notaFiscalRepository;

    public NotaFiscal criaNotaFiscal(NotaFiscal notaFiscal){
        notaFiscal.setStatus(Status.PENDENTE);
        return notaFiscalRepository.saveAndFlush(notaFiscal);
    }

    public List<NotaFiscal> listaTodasNotasFiscais(){
        return notaFiscalRepository.findAll();
    }

    public Optional<NotaFiscal> buscaPorId(Long id){
        if(notaFiscalRepository.existsById(id)){
            return notaFiscalRepository.findById(id);
        }else
            throw new EntityNotFoundException("A nota fiscal com a identificação informada não existe ! ");
    }

    public NotaFiscal alteraNotaFiscal(Long id,NotaFiscal notaFiscal){
        if(notaFiscalRepository.existsById(id)){
            if(id.equals(notaFiscal.getId()))
                return notaFiscalRepository.saveAndFlush(notaFiscal);
            else
                throw new UnsupportedOperationException("Identificação informada é diferente da identificação da nota fiscal.");
        }else
            throw new EntityNotFoundException("Nota fiscal não encontrada.");
    }

    public NotaFiscal alteraStatusDeNotaFiscal(Long id, Map<Object,Object> campos){
        if(notaFiscalRepository.existsById(id)){
               Optional<NotaFiscal> optional = notaFiscalRepository.findById(id);
               if(optional.isPresent()){
                   NotaFiscal notaFiscal = optional.get();
                   campos.forEach((k,v)->{
                       Field field = ReflectionUtils.findField(NotaFiscal.class,(String) k);
                       field.setAccessible(true);
                       ReflectionUtils.setField(field,notaFiscal,verificaStatus(v.toString()));
                   });
                   return notaFiscalRepository.saveAndFlush(notaFiscal);
               }else
                   throw new EntityNotFoundException("A nota fiscal com a identificação informada não foi encontrada ! ");
        }else
            throw new EntityNotFoundException("A nota fiscal com a identificação informada não existe ! ");
    }

    public void deletaNotaFiscal(Long id){
        Optional<NotaFiscal> notaFiscal = notaFiscalRepository.findById(id);
        if(!notaFiscal.isPresent())
            throw new EntityNotFoundException("A nota fiscal com a identificação informada não existe ! ");
        notaFiscalRepository.deleteById(id);
    }

    public Status verificaStatus(String status){
        Status statusAtualizado;
        switch (status){
            case "PENDENTE":
                statusAtualizado = Status.PENDENTE;
                break;
            case "EM_PROCESSAMENTO":
                statusAtualizado =  Status.EM_PROCESSAMENTO;
                break;
            case "APROVADA":
                statusAtualizado =  Status.APROVADA;
                break;
            case "COM_ERRO":
                statusAtualizado =  Status.COM_ERRO;
                break;
            case "CANCELADA":
                statusAtualizado =  Status.CANCELADA;
                break;
            default:
                throw new UnsupportedOperationException("Esse status não existe");
        }
        return statusAtualizado;
    }
}
