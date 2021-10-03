package br.com.cm.workshop.apicrud.service;

import br.com.cm.workshop.apicrud.enums.Status;
import br.com.cm.workshop.apicrud.model.Itens;
import br.com.cm.workshop.apicrud.model.NotaFiscal;
import br.com.cm.workshop.apicrud.repository.NotaFiscalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class NotaFiscalService {

    @Autowired
    private NotaFiscalRepository notaFiscalRepository;

    public NotaFiscal criaNotaFiscal(NotaFiscal notaFiscal) {
        return notaFiscalRepository.saveAndFlush(aplicaRegrasDeNegociosNotaFiscal(notaFiscal));
    }

    public List<NotaFiscal> listaTodasNotasFiscais() {
        return notaFiscalRepository.findAll();
    }

    public Optional<NotaFiscal> buscaPorId(Long id) {
        if (notaFiscalRepository.existsById(id)) {
            return notaFiscalRepository.findById(id);
        } else
            throw new EntityNotFoundException("A nota fiscal com a identificação informada não existe ! ");
    }

    public NotaFiscal alteraNotaFiscal(Long id, NotaFiscal notaFiscal) {
        if (notaFiscalRepository.existsById(id)) {
            if (id.equals(notaFiscal.getId()))
                return notaFiscalRepository.saveAndFlush(aplicaRegrasDeNegociosNotaFiscal(notaFiscal));
            else
                throw new UnsupportedOperationException("Identificação informada é diferente da identificação da nota fiscal.");
        } else
            throw new EntityNotFoundException("Nota fiscal não encontrada.");
    }

    public void deletaNotaFiscal(Long id) {
        Optional<NotaFiscal> notaFiscal = notaFiscalRepository.findById(id);
        if (!notaFiscal.isPresent())
            throw new EntityNotFoundException("A nota fiscal com a identificação informada não existe ! ");
        notaFiscalRepository.deleteById(id);
    }

    public NotaFiscal aplicaRegrasDeNegociosNotaFiscal(NotaFiscal notaFiscal) {

        Double valorTotalProdutos = 0.0;

        if (notaFiscal.getStatus() == null || notaFiscal.getStatus().equals(Status.PENDENTE))
            notaFiscal.setStatus(Status.PENDENTE);
        else
            throw new UnsupportedOperationException("Por favor o valor inicial de status da sua nota precisa ser 'PENDENTE', não é preciso inserir se preferir , já fazemos isso por você ! ");

        for (Itens item : notaFiscal.getItens()) {
            if (item.getValorTotal() == null)
                item.setValorTotal(item.getPrecoUnitario() * item.getQuantidade());
            else if (item.getValorTotal() != (item.getPrecoUnitario() * item.getQuantidade()))
                throw new UnsupportedOperationException("O valorTotal do item informado está incorreto , insira o valor correto ou se preferir podemos calcular o valorTotal do Item para você , basta remover este campo da sua requisição !");
            valorTotalProdutos += item.getValorTotal();
        }

        if (notaFiscal.getValorTotalProdutos() == null)
            notaFiscal.setValorTotalProdutos(valorTotalProdutos);
        else if (notaFiscal.getValorTotalProdutos().doubleValue() != valorTotalProdutos)
            throw new UnsupportedOperationException("O valorTotalProdutos da sua nota informado está incorreto , insira o valor correto ou se preferir podemos calcular o valorTotalProdutos para você , basta remover este campo da sua requisição !");

        if (notaFiscal.getValorTotal() == null)
            notaFiscal.setValorTotal(valorTotalProdutos + notaFiscal.getFrete());
        else if (notaFiscal.getValorTotal() != (valorTotalProdutos + notaFiscal.getFrete()))
            throw new UnsupportedOperationException("O valorTotal da sua nota informado está incorreto , insira o valor correto ou se preferir podemos calcular o valorTotal da sua nota para você , basta remover este campo da sua requisição !");

        return notaFiscal;
    }

}
