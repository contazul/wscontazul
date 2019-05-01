package br.com.wscontazul.model.presenter;

public class SimuladorEntrada {
	
	private long numeroContazul;
	private double totalDivida;
	private double totalDividaRemovida;
	private double totalBeneficio;
	private double totalBeneficioRemovido;
	private long idMeta;
	private boolean simulandoMeta;
	
	public SimuladorEntrada() {
		
		this.numeroContazul = 0;
		this.totalDivida = 0.0;
		this.totalDividaRemovida = 0.0;
		this.totalBeneficio = 0.0;
		this.totalBeneficioRemovido = 0.0;
		this.idMeta = 0;
		this.simulandoMeta = false;
	}

	public SimuladorEntrada(long numeroContazul, double totalDivida, double totalDividaRemovida,
			double totalBeneficio, double totalBeneficioRemovido, long idMeta, boolean simulandoMeta) {
		
		this.numeroContazul = numeroContazul;
		this.totalDivida = totalDivida;
		this.totalDividaRemovida = totalDividaRemovida;
		this.totalBeneficio = totalBeneficio;
		this.totalBeneficioRemovido = totalBeneficioRemovido;
		this.idMeta = idMeta;
		this.simulandoMeta = simulandoMeta;
	}
	
	public long getNumeroContazul() {
		return numeroContazul;
	}

	public void setNumeroContazul(long numeroContazul) {
		this.numeroContazul = numeroContazul;
	}

	public double getTotalDivida() {
		return totalDivida;
	}

	public void setTotalDivida(double totalDivida) {
		this.totalDivida = totalDivida;
	}

	public double getTotalDividaRemovida() {
		return totalDividaRemovida;
	}

	public void setTotalDividaRemovida(double totalDividaRemovida) {
		this.totalDividaRemovida = totalDividaRemovida;
	}

	public double getTotalBeneficio() {
		return totalBeneficio;
	}

	public void setTotalBeneficio(double totalBeneficio) {
		this.totalBeneficio = totalBeneficio;
	}

	public double getTotalBeneficioRemovido() {
		return totalBeneficioRemovido;
	}

	public void setTotalBeneficioRemovido(double totalBeneficioRemovido) {
		this.totalBeneficioRemovido = totalBeneficioRemovido;
	}

	public long getIdMeta() {
		return idMeta;
	}

	public void setIdMeta(long idMeta) {
		this.idMeta = idMeta;
	}

	public boolean isSimulandoMeta() {
		return simulandoMeta;
	}

	public void setSimulandoMeta(boolean simulandoMeta) {
		this.simulandoMeta = simulandoMeta;
	}
}
