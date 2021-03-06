package br.com.wscontazul.rest;

import br.com.wscontazul.model.Ca02Contazul;
import br.com.wscontazul.model.presenter.Central;
import br.com.wscontazul.repository.Ca02ContazulRepository;
import br.com.wscontazul.repository.Ca03SomaSaldoRepository;
import br.com.wscontazul.repository.Ca04SubtracaoSaldoRepository;
import br.com.wscontazul.repository.Ca05LucroMensalRepository;
import br.com.wscontazul.repository.Ca06DividaMensalRepository;
import br.com.wscontazul.repository.Ca08CentralizacaoRepository;
import br.com.wscontazul.statics.Contazul;
import br.com.wscontazul.util.UtilContazul;
import br.com.wscontazul.util.UtilDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping("/wscontazul/central")
public class WsContazulCentral {

	@Autowired
	private Ca02ContazulRepository contazulR;

	@Autowired
	private Ca04SubtracaoSaldoRepository subtracaoSaldoR;

	@Autowired
	private Ca05LucroMensalRepository lucroMensalR;

	@Autowired
	private Ca03SomaSaldoRepository somaSaldoR;

	@Autowired
	private Ca06DividaMensalRepository dividaMensalR;

	@Autowired
	private Ca08CentralizacaoRepository centralizacaoR;

	// 9032019201215
	@GetMapping("/informacoesCentral")
	public Central informacoesCentral(long numeroContazul) {

		Central central = new Central();
		Ca02Contazul contazul = contazulR.findByNumeroContazul(numeroContazul);

		if (contazul.getPerfil().equals(Contazul.TIPO2)) {

			Double saldo = contazul.getSaldo();
			Double totalSubtracaoSaldoBaixa = 0.0;
			Double totalSubtracaoSaldoGeral = 0.0;
			Double totalBeneficioMensal = 0.0;
			Double totalSomaDeSaldo = 0.0;
			Double totalDivida = 0.0;
			Double totalDividaBaixa = 0.0;
			String status = "";
			Double percentualEconomizado = 0.0;

			long[] centralizadas = this.centralizacaoR
					.findNumeroContazulCentralizadaByNumeroContazulCentralizadora(numeroContazul);

			central.setSaldo(saldo);

			UtilDatas utilDatas = new UtilDatas();
			Date[] intervalo = utilDatas.getDateIntervaloDeDatasMensal();
			UtilContazul utilContazul = new UtilContazul();

			for (int i = 0; i < centralizadas.length; i++) {
				
				Double inf1 = this.subtracaoSaldoR.selectTotalSubtracaoSaldoBaixaPrioridade(
						centralizadas[i], intervalo[0], intervalo[1], "Baixa");
				
				if(inf1 == null)
					totalSubtracaoSaldoBaixa += 0.0;
				else
					totalSubtracaoSaldoBaixa += inf1;
				
				Double inf2 = this.subtracaoSaldoR.selectTotalSubtracaoSaldo(centralizadas[i],
						intervalo[0], intervalo[1]);
				
				if(inf2 == null)
					totalSubtracaoSaldoGeral += 0.0;
				else
					totalSubtracaoSaldoGeral += inf2;

				Double inf3 = this.lucroMensalR.selectTotalLucroMensal(centralizadas[i]);
				
				if(inf3 == null)
					totalBeneficioMensal += 0.0;
				else
					totalBeneficioMensal += inf3;
				
				Double inf4 = this.somaSaldoR.selectTotalSomaSaldo(centralizadas[i], intervalo[0], intervalo[1]);
				
				if(inf4 == null)
					totalSomaDeSaldo += 0.0;
				else
					totalSomaDeSaldo += inf4;
				
				Double inf5 = dividaMensalR.selectTotalDividaMensal(centralizadas[i]);
				
				if(inf5 == null)
					totalDivida += 0.0;
				else
					totalDivida += inf5;
				
				Double inf6 = dividaMensalR.selectTotalDividaMensalBaixaPrioridade(centralizadas[i]);
				
				if(inf6 == null)
					totalDividaBaixa += 0.0;
				else
					totalDividaBaixa += inf6;
			}

			status = utilContazul.gerarStatusContazul(contazul.getValorIdeal(), totalDivida, totalBeneficioMensal);
			percentualEconomizado = utilContazul.calcularPercentualEconomizado(totalBeneficioMensal, totalDivida);

			central.setTotalSubtracaoSaldoBaixaPrioridade(totalSubtracaoSaldoBaixa);
			central.setTotalSubtracaoSaldoGeral(totalSubtracaoSaldoGeral);
			central.setTotalBeneficioMensal(totalBeneficioMensal);
			central.setTotalSomaSaldo(totalSomaDeSaldo);
			central.setTotalDividaMensal(totalDivida);
			central.setTotalDividaMensalBaixaPrioridade(totalDividaBaixa);
			central.setStatus(status);
			central.setPercentualEconomizado(percentualEconomizado);
			return central;
		}

		central.setSaldo(contazul.getSaldo());

		UtilDatas utilDatas = new UtilDatas();

		Date[] intervalo = utilDatas.getDateIntervaloDeDatasMensal();

		central.setTotalSubtracaoSaldoBaixaPrioridade(this.subtracaoSaldoR
				.selectTotalSubtracaoSaldoBaixaPrioridade(numeroContazul, intervalo[0], intervalo[1], "Baixa"));

		central.setTotalSubtracaoSaldoGeral(
				subtracaoSaldoR.selectTotalSubtracaoSaldo(numeroContazul, intervalo[0], intervalo[1]));

		central.setTotalBeneficioMensal(lucroMensalR.selectTotalLucroMensal(numeroContazul));

		central.setTotalSomaSaldo(somaSaldoR.selectTotalSomaSaldo(numeroContazul, intervalo[0], intervalo[1]));

		UtilContazul utilContazul = new UtilContazul();

		Double totalDivida = dividaMensalR.selectTotalDividaMensal(numeroContazul);

		if (totalDivida == null)
			totalDivida = 0.0;

		Double totalBeneficio = lucroMensalR.selectTotalLucroMensal(numeroContazul);
		if (totalBeneficio == null)
			totalBeneficio = 0.0;

		central.setStatus(utilContazul.gerarStatusContazul(contazul.getValorIdeal(), totalDivida, totalBeneficio));

		central.setPercentualEconomizado(utilContazul.calcularPercentualEconomizado(totalBeneficio, totalDivida));

		central.setTotalDividaMensal(totalDivida);

		central.setTotalDividaMensalBaixaPrioridade(
				dividaMensalR.selectTotalDividaMensalBaixaPrioridade(numeroContazul));
		return central;
	}
}
