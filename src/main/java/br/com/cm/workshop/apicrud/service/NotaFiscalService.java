package br.com.cm.workshop.apicrud.service;

import br.com.cm.workshop.apicrud.model.NotaFiscal;
import br.com.cm.workshop.apicrud.repository.NotaFiscalRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaFiscalService {

    @Autowired
    private NotaFiscalRepository notaFiscalRepository;

    public NotaFiscal criaNotaFiscal(NotaFiscal notaFiscal){
        return notaFiscalRepository.saveAndFlush(notaFiscal);
    }

    public List<NotaFiscal> listaTodasNotasFiscais(){
        return notaFiscalRepository.findAll();
    }

    public Optional<NotaFiscal> buscaPorId(Long id){
        return notaFiscalRepository.findById(id);
    }

}
