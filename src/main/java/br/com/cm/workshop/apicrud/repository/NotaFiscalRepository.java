package br.com.cm.workshop.apicrud.repository;

import br.com.cm.workshop.apicrud.model.NotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal,Long> {

}
