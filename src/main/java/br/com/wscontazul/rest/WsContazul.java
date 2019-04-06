package br.com.wscontazul.rest;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.wscontazul.model.Ca01Usuario;
import br.com.wscontazul.model.Ca02Contazul;
import br.com.wscontazul.model.Ca03SomaSaldo;
import br.com.wscontazul.model.presenter.PerfilContazul;
import br.com.wscontazul.model.presenter.Saldo;
import br.com.wscontazul.repository.Ca01UsuarioRepository;
import br.com.wscontazul.repository.Ca02ContazulRepository;
import br.com.wscontazul.repository.Ca03SomaSaldoRepository;
import br.com.wscontazul.repository.Ca05LucroMensalRepository;
import br.com.wscontazul.repository.Ca06DividaMensalRepository;
import br.com.wscontazul.statics.Contazul;
import br.com.wscontazul.statics.Excecoes;
import br.com.wscontazul.util.UtilContazul;
import br.com.wscontazul.util.UtilDatas;

@RestController
@RequestMapping("/wscontazul")
public class WsContazul {

	@Autowired
	private Ca01UsuarioRepository usuarioR;

	@Autowired
	private Ca02ContazulRepository contazulR;
	
	@Autowired
	private Ca03SomaSaldoRepository somaSaldoR;
	
	@Autowired
	private Ca05LucroMensalRepository lucroMensalR;
	
	@Autowired
	private Ca06DividaMensalRepository dividaMensalR;

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
		contazulR.save(new Ca02Contazul(numeroContazul, 0.0, 0.0, "" + numeroContazul, Contazul.TIPO1,
				usuarioR.findByNomeUsuario(nomeUsuario).getId()));
	}
	
	@GetMapping("/listaDeContazul")
	public List<Ca02Contazul> getContas(String nomeUsuario) {
		
		return contazulR.findByIdUsuario(usuarioR.findByNomeUsuario(nomeUsuario).getId());
	}
	
	@GetMapping("/perfilContazul")
	public PerfilContazul getPerfilContazul(long numeroContazul) {
		
		Ca02Contazul ca02Contazul = contazulR.findByNumeroContazul(numeroContazul);
		UtilContazul utilContazul = new UtilContazul();
		Double totalDivida =  dividaMensalR.selectTotalDividaMensal(numeroContazul);
		if(totalDivida == null) 
			totalDivida = 0.0;
		
		Double totalBeneficio = lucroMensalR.selectTotalLucroMensal(numeroContazul);
		if(totalBeneficio == null)
			totalBeneficio = 0.0;
		
		return new PerfilContazul(ca02Contazul, utilContazul.gerarStatusContazul(ca02Contazul.getValorIdeal(), totalDivida, totalBeneficio));
	}
	
	@PostMapping("/atualizarPerfilContazul")
	public void atualizarPerfilContazul(long numeroContazul, String descricao, double valorIdeal) {
		
		Ca02Contazul contazul = contazulR.findByNumeroContazul(numeroContazul);
		if(descricao != null && !descricao.isEmpty())
			contazul.setDescricao(descricao);
		
		if(valorIdeal != 0) 
			contazul.setValorIdeal(valorIdeal);
		contazulR.save(contazul);
	}
	
	@PostMapping("/inserirSomaSaldo")
	public void inserirSomaSaldo(long numeroContazul, double valor, String descricao) {
		
		somaSaldoR.save(new Ca03SomaSaldo(valor, descricao, numeroContazul));
		Ca02Contazul ca02Contazul = contazulR.findByNumeroContazul(numeroContazul);
		ca02Contazul.setSaldo(ca02Contazul.getSaldo() + valor);
		contazulR.save(ca02Contazul);
	}
	
	@GetMapping("/saldo")
	public Saldo saldo(long numeroContazul) {
		
		return new Saldo(contazulR.findSaldoByNumeroContazul(numeroContazul));
	
	}
	
	@GetMapping("/listaSomaSaldo")
	public List<Ca03SomaSaldo> listaSomaSaldo(long numeroContazul) {
		
		UtilDatas utilDatas = new UtilDatas();
		Date[] intervalo = utilDatas.getDateIntervaloDeDatasMensal();
		return somaSaldoR.findByNumeroContazulAndDataMovimentoBetweenOrderByValorDesc(numeroContazul, 
				intervalo[0], intervalo[1]);
	}
}

















