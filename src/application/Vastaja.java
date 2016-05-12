package application;

import java.util.ArrayList;
import java.util.List;

public class Vastaja {
	private List<String> vastused;
	public List<String> getVastused() {
		return vastused;
	}

	public Vastaja() {
		this.vastused = new ArrayList<>();
	}
	
	public void lisaVastus(String vastus) {
		vastused.add(vastus);
	}
	
	
}
