package br.com.wscontazul.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.wscontazul.model.Ca07Meta;

public interface Ca07MetaRepository extends CrudRepository<Ca07Meta, Long>{
	
	List<Ca07Meta> findByNumeroContazulAndIsRealizada(long numeroContazul, Integer isRealizada);
	
	Ca07Meta findById(long id);
	
	Long countByNumeroContazulAndIsRealizada(long numeroContazul, int isRealizada);
}
