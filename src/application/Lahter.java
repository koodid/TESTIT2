package application;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Lahter {
	private String lahtriSisu;
	private File fail;
	private int reaNr;
	private int veeruNr;
	
	public String getTutvustus() {
		return lahtriSisu;
	}
	public File getFailinimi() {
		return fail;
	}
	public int getReaNr() {
		return reaNr;
	}
	public int getVeeruNr() {
		return veeruNr;
	}
	
	public Lahter(File fail, int reaNr, int veeruNr) {
		this.fail = fail;
		this.reaNr = reaNr;
		this.veeruNr = veeruNr;
	}
	
	public Lahter(File fail) {
		this.fail = fail;
		this.reaNr = 1;
		this.veeruNr = 2;
	}

	public String loeLahter() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fail), "cp1257"));
		String rida;
		int i = 1;
		while ((rida = br.readLine()) != null) {
			if (i < reaNr) {
				i++;
				continue;
			}
			else {
				if (!rida.contains(";")) {
					br.close();
					throw new tyhiErind(i, veeruNr);
				} else {
					String[] osad = rida.split(";");
					if (osad.length < veeruNr || osad[veeruNr-1].isEmpty()) {
						br.close();
						throw new tyhiErind(i, veeruNr);
					} else {
						lahtriSisu = osad[veeruNr-1];
						br.close();
						break;
					}
					
				}
			}
		}
		return lahtriSisu;
	}
}
