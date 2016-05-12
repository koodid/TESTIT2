package application;
import java.util.ArrayList;

public class Vastaja {
	private ArrayList<String> vastused;
	public ArrayList<String> getVastused() {
		return vastused;
	}

	public Vastaja() {
		this.vastused = new ArrayList<>();
	}
	
	public void lisaVastus(String vastus) {
		vastused.add(vastus);
	}
}
