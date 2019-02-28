package br.com.wscontazul.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.wscontazul.model.Ca01Usuario;

public interface Ca01UsuarioRepository extends CrudRepository<Ca01Usuario, Long>{
	
	Ca01Usuario findByNomeUsuario(String nomeUsuario);
	
	Ca01Usuario findByNomeUsuarioAndSenha(String nomeUsuario, String senha);
}
