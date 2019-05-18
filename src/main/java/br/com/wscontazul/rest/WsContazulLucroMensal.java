package br.com.wscontazul.rest;

import br.com.wscontazul.model.Ca02Contazul;
import br.com.wscontazul.model.Ca05LucroMensal;
import br.com.wscontazul.model.Ca08Centralizacao;
import br.com.wscontazul.model.presenter.ListaLucroMensal;
import br.com.wscontazul.repository.Ca02ContazulRepository;
import br.com.wscontazul.repository.Ca05LucroMensalRepository;
import br.com.wscontazul.repository.Ca08CentralizacaoRepository;
import br.com.wscontazul.statics.Contazul;
import br.com.wscontazul.util.UtilDatas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wscontazul/lucroMensal")
public class WsContazulLucroMensal {

	@Autowired
	private Ca05LucroMensalRepository lucroMensalR;

	@Autowired
	private Ca02ContazulRepository contazulRepository;
	
	@Autowired
	private Ca08CentralizacaoRepository centralizacaoR;

	@PostMapping("/inserirLucroMensal")
	public void inserirLucroMensal(long numeroContazul, String descricao, double valor) {

		lucroMensalR.save(new Ca05LucroMensal(descricao, valor, null, numeroContazul));
	}

	@PostMapping("/excluirLucroMensal")
	public void excluirLucroMensal(long id) {

		lucroMensalR.delete(lucroMensalR.findById(id));
	}

	@GetMapping("/listaDeLucroMensal")
	public List<ListaLucroMensal> listaDeLucroMensal(long numeroContazul) {

		Ca02Contazul contazul = contazulRepository.findByNumeroContazul(numeroContazul);

		if (contazul.getPerfil().equals(Contazul.TIPO2)) {
			
			List<ListaLucroMensal> lucros = new ArrayList<>();
			long[] centralizadas = this.centralizacaoR.findNumeroContazulCentralizadaByNumeroContazulCentralizadora(numeroContazul);
			for(int i = 0; i < centralizadas.length; i++) {
				
				List<Ca05LucroMensal> lucrosMensais = lucroMensalR.findByNumeroContazulOrderByValorDesc(centralizadas[i]);
				for (Ca05LucroMensal lucroMensal : lucrosMensais) {

					UtilDatas utilDatas = new UtilDatas();
					ListaLucroMensal listaLucroMensal2 = new ListaLucroMensal(lucroMensal.getId(), lucroMensal.getDescricao(),
							lucroMensal.getValor(),
							utilDatas.converterSqlDateParaString(lucroMensal.getUltimaDataRecebimento()));
					lucros.add(listaLucroMensal2);
				}
			}
			
			return lucros;
		}

		List<Ca05LucroMensal> lucrosMensais = lucroMensalR.findByNumeroContazulOrderByValorDesc(numeroContazul);
		List<ListaLucroMensal> listaLucroMensal = new ArrayList<>();
		for (Ca05LucroMensal lucroMensal : lucrosMensais) {

			UtilDatas utilDatas = new UtilDatas();
			ListaLucroMensal listaLucroMensal2 = new ListaLucroMensal(lucroMensal.getId(), lucroMensal.getDescricao(),
					lucroMensal.getValor(),
					utilDatas.converterSqlDateParaString(lucroMensal.getUltimaDataRecebimento()));
			listaLucroMensal.add(listaLucroMensal2);
		}
		return listaLucroMensal;
	}

	@PostMapping("/receber")
	public void receber(long id, long numeroContazul) {

		UtilDatas utilDatas = new UtilDatas();
		Ca05LucroMensal lucroMensal = lucroMensalR.findById(id);
		lucroMensal.setUltimaDataRecebimento(utilDatas.getDataCorrente());
		lucroMensalR.save(lucroMensal);
		Ca02Contazul contazul = contazulRepository.findByNumeroContazul(numeroContazul);
		if(contazul.getPerfil().equals(Contazul.TIPO3)) {
			
			Ca08Centralizacao centralizacao = this.centralizacaoR.findIdByNumeroContazulCentralizada(contazul.getNumeroContazul());
			Ca02Contazul centralizadora = this.contazulRepository.findByNumeroContazul(centralizacao.getNumeroContazulCentralizadora());
			centralizadora.setSaldo(centralizadora.getSaldo() + lucroMensal.getValor());
			this.contazulRepository.save(centralizadora);
		}
		contazul.setSaldo(contazul.getSaldo() + lucroMensal.getValor());
		contazulRepository.save(contazul);
	}
}
