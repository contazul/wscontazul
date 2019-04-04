package br.com.wscontazul.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.wscontazul.model.Ca06DividaMensal;

public interface Ca06DividaMensalRepository extends CrudRepository<Ca06DividaMensal, Long>{
	
	List<Ca06DividaMensal> findByNumeroContazulAndPago(long numeroContazul, int pago);
	
	Ca06DividaMensal findById(long id);
}
