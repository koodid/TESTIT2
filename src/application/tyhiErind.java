package application;

public class tyhiErind extends RuntimeException {
	private int veaRida;
	private int veaVeerg;
	
	
	public tyhiErind(int veaRida, int veaVeerg) {
		this.veaRida = veaRida;
		this.veaVeerg = veaVeerg;
	}

	public int getVeaRida() {
		return veaRida;
	}
	public int getVeaVeerg() {
		return veaVeerg;
	}


	@Override
	public String getMessage() {
		return veaRida + ". reas " + veaVeerg + ". veerus on info puudu. "
					+ "Lisa puuduv info tabelisse ja lae fail uuesti.";
	}
}
