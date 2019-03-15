package br.com.wscontazul.repository;

import br.com.wscontazul.model.Ca04SubtracaoSaldo;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface Ca04SubtracaoSaldoRepository extends CrudRepository<Ca04SubtracaoSaldo, Long> {

    List<Ca04SubtracaoSaldo> findByNumeroContazulAndDataMovimentoBetweenOrderByValorAsc(long numeroContazul, final Date start, final Date end);
}
