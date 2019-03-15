package br.com.wscontazul.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.wscontazul.model.Ca03SomaSaldo;

public interface Ca03SomaSaldoRepository extends CrudRepository<Ca03SomaSaldo, Long>{
	
	List<Ca03SomaSaldo> findByNumeroContazulAndDataMovimentoBetweenOrderByValorDesc(long numeroContazul, final Date start, final Date end);
}
