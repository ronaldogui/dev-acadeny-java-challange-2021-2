package br.com.cm.workshop.apicrud.service;

import br.com.cm.workshop.apicrud.model.NotaFiscal;
import br.com.cm.workshop.apicrud.repository.NotaFiscalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotaFiscalService {

    @Autowired
    private NotaFiscalRepository notaFiscalRepository;

    public NotaFiscal criaNotaFiscal(NotaFiscal notaFiscal){
        return notaFiscalRepository.saveAndFlush(notaFiscal);
    }

}
