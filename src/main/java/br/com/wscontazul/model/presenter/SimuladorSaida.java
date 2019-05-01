package br.com.wscontazul.model.presenter;

public class SimuladorSaida {
	
	private String statusSimulado;
	private String statusAtual;
	private double percentualEconomizadoAtual;
	private double percentualEconomizadoSimulado;
	private double valorEconomizadoAtual;
	private double valorEconomizadoSimulado;
	private double valorAfetado;
	private boolean lucrou;
	private String statusMeta;
	private double valorRestanteMeta;
	
	public SimuladorSaida() {
		
		this.statusSimulado = "";
		this.statusAtual = "";
		this.percentualEconomizadoAtual = 0.0;
		this.percentualEconomizadoSimulado = 0.0;
		this.valorEconomizadoAtual = 0.0;
		this.valorEconomizadoSimulado = 0.0;
		this.valorAfetado = 0.0;
		this.lucrou = false;
		this.statusMeta = "";
		this.valorRestanteMeta = 0.0;
	}	

	public SimuladorSaida(String statusSimulado, String statusAtual, double percentualEconomizadoAtual,
			double percentualEconomizadoSimulado, double valorEcnomizadoAtual, double valorEcnomizadoSimulado, 
			double valorAfetado, boolean lucrou, String statusMeta, double valorRestanteMeta) {
		
		this.statusSimulado = statusSimulado;
		this.statusAtual = statusAtual;
		this.percentualEconomizadoAtual = percentualEconomizadoAtual;
		this.percentualEconomizadoSimulado = percentualEconomizadoSimulado;
		this.valorEconomizadoAtual = valorEcnomizadoAtual;
		this.valorEconomizadoSimulado = valorEcnomizadoSimulado;
		this.valorAfetado = valorAfetado;
		this.lucrou = lucrou;
		this.statusMeta = statusMeta;
		this.valorRestanteMeta = valorRestanteMeta;
	}

	public String getStatusSimulado() {
		return statusSimulado;
	}

	public void setStatusSimulado(String statusSimulado) {
		this.statusSimulado = statusSimulado;
	}

	public String getStatusAtual() {
		return statusAtual;
	}

	public void setStatusAtual(String statusAtual) {
		this.statusAtual = statusAtual;
	}

	public double getPercentualEconomizadoAtual() {
		return percentualEconomizadoAtual;
	}

	public void setPercentualEconomizadoAtual(double percentualEconomizadoAtual) {
		this.percentualEconomizadoAtual = percentualEconomizadoAtual;
	}

	public double getPercentualEconomizadoSimulado() {
		return percentualEconomizadoSimulado;
	}

	public void setPercentualEconomizadoSimulado(double percentualEconomizadoSimulado) {
		this.percentualEconomizadoSimulado = percentualEconomizadoSimulado;
	}

	public double getValorEconomizadoAtual() {
		return valorEconomizadoAtual;
	}

	public void setValorEconomizadoAtual(double valorEconomizadoAtual) {
		this.valorEconomizadoAtual = valorEconomizadoAtual;
	}

	public double getValorEconomizadoSimulado() {
		return valorEconomizadoSimulado;
	}

	public void setValorEconomizadoSimulado(double valorEconomizadoSimulado) {
		this.valorEconomizadoSimulado = valorEconomizadoSimulado;
	}

	public double getValorAfetado() {
		return valorAfetado;
	}

	public void setValorAfetado(double valorAfetado) {
		this.valorAfetado = valorAfetado;
	}

	public boolean isLucrou() {
		return lucrou;
	}

	public void setLucrou(boolean lucrou) {
		this.lucrou = lucrou;
	}

	public String getStatusMeta() {
		return statusMeta;
	}

	public void setStatusMeta(String statusMeta) {
		this.statusMeta = statusMeta;
	}

	public double getValorRestanteMeta() {
		return valorRestanteMeta;
	}

	public void setValorRestanteMeta(double valorRestanteMeta) {
		this.valorRestanteMeta = valorRestanteMeta;
	}
}
