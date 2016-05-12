package application;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Nimekiri {
	private File fail;
	private ArrayList<String> nimekiri = new ArrayList<>();
	private int reaNr;
	private int veeruNr;
	private ArrayList<Integer> kodeeritudVastused = new ArrayList<>();
	
	public File getFailinimi() {
		return fail;
	}
	public ArrayList<String> getNimekiri() {
		return nimekiri;
	}	
	public int getReaNr() {
		return reaNr;
	}
	public int getVeeruNr() {
		return veeruNr;
	}
	
	// Nii veergudes kui ka ridades tuleb konstruktorisse esimene rida märkida numbriga 1 
	// ja arvestada alates sellest numbrist.
	public ArrayList<String> loeNimekiriVeerust(File fail, int esimeseReaNr, int veeruNr) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fail), "cp1257"));
		String rida;
		int i = 1;
		while ((rida = br.readLine()) != null) {
			if (i < esimeseReaNr) {
				i++;
				continue;
			}
			else {
				String[] osad = rida.split(";");
				if (osad.length < veeruNr || osad[veeruNr-1].isEmpty()) {
					br.close();
					throw new tyhiErind(i, veeruNr);
				} else {
					nimekiri.add(osad[veeruNr-1]);
					i++;
				}
				 
			}
		}
		br.close();
		return nimekiri;
	}
	
	public ArrayList<String> loeNimekiriReast(File fail, int reaNr, int esimeseVeeruNr) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fail), "cp1257"));
		String rida;
		int i = 1;
		while ((rida = br.readLine()) != null) {
			if (i < reaNr) {
				i++;
				continue;
			}
			else {
				String[] osad = rida.split(";");
				if (osad.length < 2) {
					br.close();
					throw new tyhiErind(i, esimeseVeeruNr + 1);
				} else {
					for (int j = esimeseVeeruNr - 1; j < osad.length; j++){
						nimekiri.add(osad[j]);
						i++;
						
					}
					break;
				}
				
			}
		}
		br.close();
		return nimekiri;
	}	
	
	public ArrayList<Integer> kodeeriVastused(ArrayList<String> valikvastusteList, ArrayList<String> vastusteList) {
		for (int i = 0; i < vastusteList.size(); i++){
			String[] valikvastused = valikvastusteList.get(i).split(",");
			for (int j = 0; j < valikvastused.length; j++){
				String vastus = valikvastused[j].trim();
				if ((vastusteList.get(i)).equalsIgnoreCase(vastus)) {
					kodeeritudVastused.add(j);
				}
			}
		}
		return kodeeritudVastused;
	}
}
	
