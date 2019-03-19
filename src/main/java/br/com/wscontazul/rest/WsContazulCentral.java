package br.com.wscontazul.rest;

import br.com.wscontazul.model.Ca02Contazul;
import br.com.wscontazul.model.presenter.Central;
import br.com.wscontazul.repository.Ca02ContazulRepository;
import br.com.wscontazul.repository.Ca03SomaSaldoRepository;
import br.com.wscontazul.repository.Ca04SubtracaoSaldoRepository;
import br.com.wscontazul.repository.Ca05LucroMensalRepository;
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

    // 9032019201215
    @GetMapping("/informacoesCentral")
    public Central informacoesCentral(long numeroContazul) {

        Central central = new Central();
        Ca02Contazul contazul = contazulR.findByNumeroContazul(numeroContazul);
        central.setStatus(contazul.getStatus());
        central.setSaldo(contazul.getSaldo());
        UtilDatas utilDatas = new UtilDatas();
        Date[] intervalo = utilDatas.getDateIntervaloDeDatasMensal();
        central.setTotalSubtracaoSaldoBaixaPrioridade(this.subtracaoSaldoR.selectTotalSubtracaoSaldoBaixaPrioridade(
                numeroContazul, intervalo[0], intervalo[1], "Baixa"
        ));
        central.setTotalSubtracaoSaldoGeral(subtracaoSaldoR.selectTotalSubtracaoSaldo(numeroContazul, intervalo[0],
                intervalo[1]));
        central.setTotalBeneficioMensal(lucroMensalR.selectTotalLucroMensal(numeroContazul));
        central.setTotalSomaSaldo(somaSaldoR.selectTotalSomaSaldo(numeroContazul, intervalo[0], intervalo[1]));
        return central;
    }
}
