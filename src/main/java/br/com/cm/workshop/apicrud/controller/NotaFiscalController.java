package br.com.cm.workshop.apicrud.controller;

import br.com.cm.workshop.apicrud.model.NotaFiscal;
import br.com.cm.workshop.apicrud.service.NotaFiscalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/notas-fiscais")
public class NotaFiscalController {

    @Autowired
    private NotaFiscalService notaFiscalService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public NotaFiscal criaNotaFiscal(@Valid @RequestBody NotaFiscal notaFiscal){
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

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public NotaFiscal alteraNotaFiscal(@PathVariable Long id,@Valid @RequestBody NotaFiscal notaFiscal){
        return notaFiscalService.alteraNotaFiscal(id,notaFiscal);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletaNotaFiscal(@PathVariable Long id){
        notaFiscalService.deletaNotaFiscal(id);
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public String handleUnsupportedEntityExceptions(UnsupportedOperationException unsupportedOperationException) {
        return unsupportedOperationException.getMessage();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handleNotFoundExceptions(EntityNotFoundException entityNotFoundException) {
       return entityNotFoundException.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> errors = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
