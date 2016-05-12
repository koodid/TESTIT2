package application;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FailiKontrollija {
	private List<File> failideList = new ArrayList<>();
	private String nimeAlgus;
	
	public FailiKontrollija(String nimeAlgus) {
		this.nimeAlgus = nimeAlgus;
		this.failideList = leiaFailiAadressiteList();
	}

	public String getTootavKataloog() {
		Path currentRelativePath = Paths.get("");
		String tee = currentRelativePath.toAbsolutePath().toString();
		return tee;
	}
	
	public int getFailideArv() {
		return failideList.size();
	}
	
	public List<File> leiaFailiAadressiteList() {
		File kaust = new File(getTootavKataloog());
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File kataloog, String failinimi) {
	              return failinimi.startsWith(nimeAlgus) && failinimi.endsWith(".csv");
			}
		};
		File[] failid = kaust.listFiles(filter);
		for (File f : failid) {
			failideList.add(f);
		}
		return failideList;
	}
	
	public File getFailiAadress() {
		return failideList.get(0);
	}
	
	public boolean kasOnFail() {
		if (failideList.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
}
