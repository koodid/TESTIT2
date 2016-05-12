package application;
import java.io.File;
import java.util.ArrayList;

public class Organisatsioon {
	private String organisatsioon;
	private String orgLisainfo;
	private ArrayList<String> valikVastused;
	private ArrayList<Organisatsioon> organisatsioonid = new ArrayList<>();
	
	public String getOrganisatsioon() {
		return organisatsioon;
	}
	public String getOrgLisainfo() {
		return orgLisainfo;
	}
	public ArrayList<String> getValikVastused() {
		return valikVastused;
	}
	public ArrayList<Organisatsioon> getOrganisatsioonid() {
		return organisatsioonid;
	}
	public Organisatsioon(String organisatsioon, String orgLisainfo, ArrayList<String> valikVastused) {
		this.organisatsioon = organisatsioon;
		this.orgLisainfo = orgLisainfo;
		this.valikVastused = valikVastused;
	}
	
	public Organisatsioon() {
		ArrayList<Organisatsioon> organisatsioonid = new ArrayList<>();
	}
	
	public ArrayList<Organisatsioon> loeOrganisatsioonideAndmed(File fail) throws Exception {
		Nimekiri vastuseVariandid = new Nimekiri();
		ArrayList<String> vastused = vastuseVariandid.loeNimekiriVeerust(fail, 4, 2);
		
		Nimekiri rida = new Nimekiri();
		int orgArv = rida.loeNimekiriReast(fail, 2, 1).size();
		for (int veerg = 3; veerg < orgArv+1; veerg++) {
			Lahter org_nimi = new Lahter(fail, 2, veerg);
			Lahter lisainfo = new Lahter(fail, 3, veerg);
			Nimekiri orgVastused = new Nimekiri();
	
			Organisatsioon uusOrg = new Organisatsioon(
					org_nimi.loeLahter(), 
					lisainfo.loeLahter(),
					orgVastused.loeNimekiriVeerust(fail, 4, veerg));
			organisatsioonid.add(uusOrg);
			
		}
		return organisatsioonid;
	}
}
