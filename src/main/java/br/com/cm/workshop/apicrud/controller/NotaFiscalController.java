package br.com.cm.workshop.apicrud.controller;

import br.com.cm.workshop.apicrud.model.NotaFiscal;
import br.com.cm.workshop.apicrud.service.NotaFiscalService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/notas-fiscais")
public class NotaFiscalController {

    @Autowired
    private NotaFiscalService notaFiscalService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public NotaFiscal criaNotaFiscal(@RequestBody NotaFiscal notaFiscal){
        return notaFiscalService.criaNotaFiscal(notaFiscal);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<NotaFiscal> listaTodasNotasFiscais(){
        return notaFiscalService.listaTodasNotasFiscais();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Optional<NotaFiscal> buscaPorId(@PathVariable Long id){
        return notaFiscalService.buscaPorId(id);
    }

}
