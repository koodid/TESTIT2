package application;
import java.util.ArrayList;
import java.util.Collections;

public class ParimValik {
	private ArrayList<String> vastajaVastused;
	private ArrayList<Organisatsioon> organisatsioonideAndmed;
	private ArrayList<ArrayList<String>> sobivadValikud = new ArrayList<>();
	private ArrayList<Edetabel> edetabeliInfo = new ArrayList<>();
	
	public ArrayList<ArrayList<String>> getSobivadValikud() {
		return sobivadValikud;
	}
	public ArrayList<Edetabel> getEdetabeliInfo() {
		return edetabeliInfo;
	}
	
	public ParimValik(ArrayList<String> vastajaVastused, ArrayList<Organisatsioon> organisatsioonideAndmed) {
		this.vastajaVastused = vastajaVastused;
		this.organisatsioonideAndmed = organisatsioonideAndmed;
	}
	
	// Loob kõikide valikuvariantide lõikes vastaja tulemuste edetabeli.
	public ArrayList<Edetabel> valiParimEdetabel() {
		int i_organisatsioon;
		
		for (i_organisatsioon = 0; i_organisatsioon < organisatsioonideAndmed.size(); i_organisatsioon++) {
			int loendur = 0;
			ArrayList<String> vastusteKomplekt = organisatsioonideAndmed.get(i_organisatsioon).getValikVastused();;
			for (int i_kysimus = 0; i_kysimus < vastajaVastused.size(); i_kysimus++) {
				if(vastajaVastused.get(i_kysimus).equals(vastusteKomplekt.get(i_kysimus))) {
					loendur += 1;
				}
			}
			Edetabel vastus = new Edetabel(loendur, organisatsioonideAndmed.get(i_organisatsioon).getOrganisatsioon(), organisatsioonideAndmed.get(i_organisatsioon).getOrgLisainfo());
			edetabeliInfo.add(vastus);
		}
		Collections.sort(edetabeliInfo);
		return edetabeliInfo;	
	}
	
	// Moodustab kuvamiseks sobiva sõne edetabeli parimatest vastavalt soovitud parimate tulemuste arvule.
	public String kuvaTop(int parimateArv) {
		StringBuilder sb = new StringBuilder(2000);
		for (int i = 0; i < parimateArv && i < edetabeliInfo.size(); i++) {
			sb.append(edetabeliInfo.get(i).getNimetus());
			sb.append(" (sobivus ");
			sb.append(edetabeliInfo.get(i).getPunktideHulk() * 100 / organisatsioonideAndmed.get(0).getValikVastused().size());
			sb.append(" %)");
			sb.append("\n");
			sb.append(edetabeliInfo.get(i).getLisainfo());
			sb.append("\n");
			sb.append("\n");
			}
		String parimad = sb.toString();
		return parimad;
	}
}
