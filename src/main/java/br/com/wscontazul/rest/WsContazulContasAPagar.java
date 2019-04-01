package br.com.wscontazul.rest;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wscontazul.model.Ca06DividaMensal;
import br.com.wscontazul.repository.Ca02ContazulRepository;
import br.com.wscontazul.repository.Ca06DividaMensalRepository;
import br.com.wscontazul.util.UtilDatas;

@RestController
@RequestMapping("/wscontazul/dividamensal")
public class WsContazulContasAPagar {
	
	
	@Autowired
	private Ca06DividaMensalRepository dividaMensalR;
	
	@Autowired
	private Ca02ContazulRepository contazulR;
	
	@PostMapping("/incluirconta")
	public void incluirConta(String descricao, double valor, String prioridade,
			int quantidadeParcela, long numeroContazul) {
		
		UtilDatas utilDatas = new UtilDatas();
		
		dividaMensalR.save(new Ca06DividaMensal(descricao, valor, prioridade, 0,
				quantidadeParcela, 0, utilDatas.getDataCorrente(), numeroContazul));
	}
	
	@GetMapping("/listaDeDividaMensal")
	public List<Ca06DividaMensal> listaDeDividaMensal(long numeroContazul) {
		
        return (List<Ca06DividaMensal>) dividaMensalR.findByNumeroContazulAndPago(numeroContazul, 0);
	}
}
