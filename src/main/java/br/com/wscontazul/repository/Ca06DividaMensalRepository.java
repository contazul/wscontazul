package br.com.wscontazul.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.wscontazul.model.Ca06DividaMensal;

public interface Ca06DividaMensalRepository extends CrudRepository<Ca06DividaMensal, Long>{
	
	List<Ca06DividaMensal> findByNumeroContazulAndPago(long numeroContazul, int pago);
	
	Ca06DividaMensal findById(long id);
	
	@Query("SELECT SUM(a.valor) FROM Ca06DividaMensal a WHERE a.numeroContazul =:numeroContazul AND a.pago = 0")
	Double selectTotalDividaMensal(@Param("numeroContazul") long numeroContazul);
	
	@Query("SELECT SUM(a.valor) FROM Ca06DividaMensal a WHERE a.numeroContazul =:numeroContazul AND a.pago = 0 AND a.prioridade = 'baixa'")
	Double selectTotalDividaMensalBaixaPrioridade(@Param("numeroContazul") long numeroContazul);
}
