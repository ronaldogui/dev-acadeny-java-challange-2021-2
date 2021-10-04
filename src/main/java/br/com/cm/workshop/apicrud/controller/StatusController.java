package br.com.cm.workshop.apicrud.controller;

import br.com.cm.workshop.apicrud.model.NotaFiscal;
import br.com.cm.workshop.apicrud.service.StatusService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/notas-fiscais/{id}/status")
@Tag(name = "Status Da Nota Fiscal")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @PatchMapping
    @ResponseStatus(code = HttpStatus.OK)
    public NotaFiscal alteraStatusNotaFiscal(@RequestBody Map<Object,Object> campos, @PathVariable Long id){
        return statusService.alteraStatusDeNotaFiscal(id,campos);
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

}
