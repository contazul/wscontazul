package br.com.wscontazul.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.wscontazul.model.Ca01Usuario;
import br.com.wscontazul.repository.Ca01UsuarioRepository;
import br.com.wscontazul.statics.Excecoes;

@RestController
@RequestMapping("/wscontazul")
public class WsContazul {
	
	@Autowired
	private Ca01UsuarioRepository usuarioR;
	
	@GetMapping("/ex01")
	public String verificarNomeUsuarioExistenteEX01(String nomeUsuario) {
		
		if(usuarioR.findByNomeUsuario(nomeUsuario) != null) {
			
			return Excecoes.EX01;
		}
		return Excecoes.SUCESSO;
	}
	
	@PostMapping("/inserirUsuario")
	public void inserirUsuario(@RequestParam String nomeUsuario, @RequestParam String senha) {
		
		usuarioR.save(new Ca01Usuario(nomeUsuario, senha));
	}
	
	@GetMapping("/ex08")
	public String autenticarEX08(String nomeUsuario, String senha) {
		
		if(usuarioR.findByNomeUsuarioAndSenha(nomeUsuario, senha) == null) {
			
			return Excecoes.EX08;
		}
		return Excecoes.SUCESSO;
	}
}

















