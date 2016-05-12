package application;

import java.util.ArrayList;
import java.util.List;

public class TestiFailiSisu {
	private List<String> kysimusteList;
	private List<String> valikvastusteList;
	private ArrayList<Organisatsioon> tulemused;
	private String tutvustusTekst;
	
	public TestiFailiSisu(List<String> kysimusteList, List<String> valikvastusteList,
			ArrayList<Organisatsioon> tulemused, String tutvustusTekst) {
		this.kysimusteList = kysimusteList;
		this.valikvastusteList = valikvastusteList;
		this.tulemused = tulemused;
		this.tutvustusTekst = tutvustusTekst;
	}

	public List<String> getKysimusteList() {
		return kysimusteList;
	}

	public List<String> getValikvastusteList() {
		return valikvastusteList;
	}

	public ArrayList<Organisatsioon> getTulemused() {
		return tulemused;
	}

	public String getTutvustusTekst() {
		return tutvustusTekst;
	}
	
	public String toString(int n) {
		StringBuilder sb = new StringBuilder(1000);
		if (n == 0) {
			sb.append(tutvustusTekst);
			String tekst = sb.toString();
			return tekst;
		} else if (n == 1) {
			for (int i = 0; i < kysimusteList.size(); i++) {
				sb.append(kysimusteList.get(i) + " (Vastusevariandid: " + valikvastusteList.get(i) + ")");
				sb.append("\n");
			}
			String tekst = sb.toString();
			return tekst;
		}
		else if (n == 2) {
			for (int i = 0; i < tulemused.size(); i++) {
				sb.append(tulemused.get(i).getOrganisatsioon() + " (Lisainfo: " + tulemused.get(i).getOrgLisainfo() + ")");
				sb.append("\n");
			}
			String tekst = sb.toString();
			return tekst;
		} else {
			sb.append("Testi tutvustus: " + "\n");
			sb.append(tutvustusTekst);
			sb.append("\n" + "\n");
			sb.append("Testis esitatavad küsimused ja valikvastused: " + "\n");
			for (int i = 0; i < kysimusteList.size(); i++) {
				sb.append(kysimusteList.get(i) + " (Vastusevariandid: " + valikvastusteList.get(i) + ")");
				sb.append("\n");
			}
			sb.append("\n");
			sb.append("Testi võimalikud tulemused: " + "\n");
			for (int i = 0; i < tulemused.size(); i++) {
				sb.append(tulemused.get(i).getOrganisatsioon() + " (Lisainfo: " + tulemused.get(i).getOrgLisainfo() + ")");
				sb.append("\n");
			}
			String tekst = sb.toString();
			return tekst;
		}
		
	}
	
	
}
