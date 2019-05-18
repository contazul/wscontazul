package br.com.wscontazul.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wscontazul.model.Ca02Contazul;
import br.com.wscontazul.model.Ca08Centralizacao;
import br.com.wscontazul.model.presenter.Centralizacao;
import br.com.wscontazul.model.presenter.EstruturaDeContas;
import br.com.wscontazul.repository.Ca01UsuarioRepository;
import br.com.wscontazul.repository.Ca02ContazulRepository;
import br.com.wscontazul.repository.Ca08CentralizacaoRepository;
import br.com.wscontazul.statics.Contazul;
import br.com.wscontazul.util.UtilContazul;

@RestController
@RequestMapping("/wscontazul/centralizacao")
public class WsContazulCentralizacao {

	@Autowired
	private Ca01UsuarioRepository usuarioR;

	@Autowired
	private Ca02ContazulRepository contazulR;

	@Autowired
	private Ca08CentralizacaoRepository centralizacaoR;

	@GetMapping("/gerarCentralizadora")
	public long gerarCentralizadora() {

		UtilContazul util = new UtilContazul();
		return util.gerardorDeContazul();
	}

	@PostMapping("/incluirEstruturaDeCentralizacao")
	public void incluirEstruturaDeCentralizacao(long centralizadora, long centralizada, String nomeUsuario) {

		// GRAVANDO CENTRALIZADORA NA TABELA 2
		this.contazulR.save(new Ca02Contazul(centralizadora, 0.0, 0.0, "" + centralizadora, Contazul.TIPO2,
				this.usuarioR.findByNomeUsuario(nomeUsuario).getId()));

		// GRAVANDO ESTRUTURA DE CENTRALIZACAO
		this.centralizar(centralizadora, centralizada);
	}

	@PostMapping("/centralizar")
	public void centralizar(long centralizadora, long centralizada) {

		double saldoCentralizada = getSaldoContazul(centralizada);
		double valorIdealCentralizada = getValorIdealContazul(centralizada);

		// ATUALIZANDO INFORMAÇÕES DA CENTRALIZADA
		Ca02Contazul contazulCentralizadora = this.contazulR.findByNumeroContazul(centralizadora);
		contazulCentralizadora.setSaldo(contazulCentralizadora.getSaldo() + saldoCentralizada);
		contazulCentralizadora.setValorIdeal(contazulCentralizadora.getValorIdeal() + valorIdealCentralizada);

		this.centralizacaoR.save(new Ca08Centralizacao(0, centralizadora, centralizada));
		this.atualizarPerfilDaConta(centralizada, Contazul.TIPO3);
	}

	private void atualizarPerfilDaConta(long centralizada, String perfil) {

		Ca02Contazul objCentralizada = this.contazulR.findByNumeroContazul(centralizada);
		objCentralizada.setPerfil(perfil);
		this.contazulR.save(objCentralizada);
	}

	private double getSaldoContazul(long contazul) {

		return contazulR.findSaldoByNumeroContazul(contazul);
	}

	private double getValorIdealContazul(long contazul) {

		return contazulR.findValorIdealByNumeroContazul(contazul);
	}

	@PostMapping("/excluirEstruturaDeCentralizacao")
	public void excluirEstruturaDeCentralizacao(long centralizadora) {

		// OBTENDO CENTRALIZADAS
		long[] centralizadas = this.centralizacaoR
				.findNumeroContazulCentralizadaByNumeroContazulCentralizadora(centralizadora);

		// ATUALIZANDO PERFIL DAS CENTRALIZADAS PARA COMUM
		for (int i = 0; i < centralizadas.length; i++) {

			this.atualizarPerfilDaConta(centralizadas[i], Contazul.TIPO1);
		}

		// EXCLUINDO ESTRUTURA DE CENTRALIZACÃO
		long[] centralizacoes = this.centralizacaoR.findIdByNumeroContazulCentralizadora(centralizadora);
		for (int i = 0; i < centralizacoes.length; i++) {

			Ca08Centralizacao centralizacao = new Ca08Centralizacao();
			centralizacao.setId(centralizacoes[i]);
			this.centralizacaoR.delete(centralizacao);
		}

		// EXCLUINDO CONTA CENTRALIZADORA
		Ca02Contazul contazulCentralizadora = new Ca02Contazul();
		contazulCentralizadora.setNumeroContazul(centralizadora);
		this.contazulR.delete(contazulCentralizadora);
	}

	@PostMapping("/descentralizar")
	public void descentralizar(long centralizada) {

		Ca08Centralizacao centralizacao = this.centralizacaoR.findIdByNumeroContazulCentralizada(centralizada);
		Ca02Contazul ca02Contazul = this.contazulR
				.findByNumeroContazul(centralizacao.getNumeroContazulCentralizadora());

		// ATUALIZAR O SALDO DA CENTRALIZADORA
		ca02Contazul.setSaldo(
				ca02Contazul.getSaldo() - this.getSaldoContazul(centralizacao.getNumeroContazulCentralizada()));

		// ATUALIZAR O VALOR IDEAL
		ca02Contazul.setValorIdeal(ca02Contazul.getValorIdeal()
				- this.getValorIdealContazul(centralizacao.getNumeroContazulCentralizada()));

		// ATUALIZANDO INFORMAÇÕES DA CENTRALIZADORA
		this.contazulR.save(ca02Contazul);

		// REMOVENDO CENTRALIZADA DA ESTRUTURA DE CENTRALIZAÇÃO
		this.centralizacaoR.delete(centralizacao);

		// ATUALIZANDO PERFIL DA CONTA
		this.atualizarPerfilDaConta(centralizada, Contazul.TIPO1);
	}

	@GetMapping("/listarEstruturaDeContas")
	public EstruturaDeContas listarEstruturaDeContas(String usuario) {

		List<Ca02Contazul> contas = this.contazulR.findByIdUsuario(this.usuarioR.findByNomeUsuario(usuario).getId());
		EstruturaDeContas estrutura = new EstruturaDeContas();
		int quantidadeComum = 0;
		for (Ca02Contazul contazul : contas) {

			if (contazul.getPerfil().equals(Contazul.TIPO1)) {
				estrutura.getComuns().add(contazul);
				quantidadeComum++;
			}
			else if (contazul.getPerfil().equals(Contazul.TIPO2)) {

				long[] centralizadas = this.centralizacaoR
						.findNumeroContazulCentralizadaByNumeroContazulCentralizadora(contazul.getNumeroContazul());
				
				Centralizacao centralizacao = new Centralizacao();
				
				centralizacao.setCentralizadora(contazul);
				
				int iAtual = 0;
				while(iAtual != centralizadas.length) {
					
					for(int j = 0; j < contas.size(); j++) {
						if(contas.get(j).getNumeroContazul() == centralizadas[iAtual]) { 
							centralizacao.getCentralizadas().add(contas.get(j));
							iAtual++;
							break;
						}
					}
				}
				estrutura.getCentralizacoes().add(centralizacao);
			}
		}
		
		if(quantidadeComum >= 2)
			estrutura.setPodeCriarNovaEstrutura(true);
		
		return estrutura;
	}
}


















