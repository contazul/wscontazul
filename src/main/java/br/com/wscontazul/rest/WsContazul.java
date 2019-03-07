package br.com.wscontazul.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.wscontazul.model.Ca01Usuario;
import br.com.wscontazul.model.Ca02Contazul;
import br.com.wscontazul.repository.Ca01UsuarioRepository;
import br.com.wscontazul.repository.Ca02ContazulRepository;
import br.com.wscontazul.statics.Contazul;
import br.com.wscontazul.statics.Excecoes;
import br.com.wscontazul.util.UtilContazul;

@RestController
@RequestMapping("/wscontazul")
public class WsContazul {

	@Autowired
	private Ca01UsuarioRepository usuarioR;

	@Autowired
	private Ca02ContazulRepository contazulR;

	@GetMapping("/ex01")
	public String verificarNomeUsuarioExistenteEX01(String nomeUsuario) {

		if (usuarioR.findByNomeUsuario(nomeUsuario) != null) {

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

		if (usuarioR.findByNomeUsuarioAndSenha(nomeUsuario, senha) == null) {

			return Excecoes.EX08;
		}
		return Excecoes.SUCESSO;
	}
	
	@PostMapping("/inserirContazul")
	public void inserirContazul(@RequestParam String nomeUsuario) {

		UtilContazul utilContazul = new UtilContazul();
		long numeroContazul = utilContazul.gerardorDeContazul();
		contazulR.save(new Ca02Contazul(numeroContazul, Contazul.STATUS5, 0.0, 0.0, "" + numeroContazul, Contazul.TIPO1,
				usuarioR.findByNomeUsuario(nomeUsuario).getId()));
	}
	
	@GetMapping("/listaDeContazul")
	public List<Ca02Contazul> getContas(String nomeUsuario) {
		
		return contazulR.findByIdUsuario(usuarioR.findByNomeUsuario(nomeUsuario).getId());
	}
	
	@GetMapping("/perfilContazul")
	public Ca02Contazul getPerfilContazul(long numeroContazul) {
		
		return contazulR.selectFuPerfilDaConta(numeroContazul);
	}
	
	@PostMapping("/atualizarPerfilContazul")
	public void atualizarPerfilContazul(long numeroContazul, String descricao, double valorIdeal) {
		
		Ca02Contazul contazul = contazulR.findByNumeroContazul(numeroContazul);
		contazul.setDescricao(descricao);
		contazul.setValorIdeal(valorIdeal);
		contazulR.save(contazul);
	}
}

















